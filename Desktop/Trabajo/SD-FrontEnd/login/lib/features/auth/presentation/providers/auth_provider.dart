
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../../../shared/shared.dart';
import '../../domain/domain.dart';
import '../../infrastructure/infrastructure.dart';

final authProvider = StateNotifierProvider<AuthNotifier,AuthState>((ref) {

  final authRepository = AuthRepositoryImpl();
  final keyValueStorage = KeyValueStorageImpl();

  return AuthNotifier(
    authRepository: authRepository,
    keyValueStorage: keyValueStorage,
  );
});



class AuthNotifier extends StateNotifier<AuthState> {

  final AuthRepository authRepository;
  final KeyValueStorage keyValueStorage;

  AuthNotifier({ 
    required this.authRepository,
    required this.keyValueStorage,
  }): super( AuthState() ) {
    //Justo cuando se crea la primera instancia del notifier, ya comenzamos a chequear el auth
    checkAuthStatus();
  }
  

  //Estos metodos delegan el trabajo pesado a los repositorios.
  Future<void> loginUser( String email, String password ) async {
    await Future.delayed(const Duration(milliseconds: 500));

    try {

      final condctor = await authRepository.login(email, password);
      _setLoggedUser( condctor );

    } on CustomError catch (e) {
      logout( e.message );
    } catch (e){
      logout( 'Error no controlado' );
    }
  }

  void checkAuthStatus() async {
    //Se puede especificar el tipo de dato porque lo escribimos como generico el getValue.
    final token = await keyValueStorage.getValue<String>('token');

    //Marco el estado como no autentificado
    if (token == null) return logout();

    try {
      //Hacemos el trabajo de ver si el usuario esta autentificado o no.
      final conductor = await authRepository.checkAuthStatus(token);
      _setLoggedUser(conductor);
    } catch (e) {


      
    }
    
  }

  void _setLoggedUser( Conductor conductor ) {
    keyValueStorage.setKeyValue('token', conductor.token);
    print(keyValueStorage.getValue('token'));
    state = state.copyWith(
      condctor: conductor,
      authStatus: AuthStatus.authenticated,
    );
  }

  Future<void> logout([ String? errorMessage ]) async {
    //De esta manera lo eliminamos, esto ocurre cuando la persona le da al logout o ocurre algun error.
    keyValueStorage.deleteKeyValue('token');
    state = state.copyWith(
      authStatus: AuthStatus.notAuthenticated,
      condctor: null,
      errorMessage: errorMessage
    );
  }

}


enum AuthStatus { checking, authenticated, notAuthenticated }

class AuthState {

  final AuthStatus authStatus;
  final Conductor? condctor;
  final String errorMessage;

  AuthState({
    this.authStatus = AuthStatus.checking, 
    this.condctor, 
    this.errorMessage = ''
  });

  AuthState copyWith({
    AuthStatus? authStatus,
    Conductor? condctor,
    String? errorMessage,
  }) => AuthState(
    authStatus: authStatus ?? this.authStatus,
    condctor: condctor ?? this.condctor,
    errorMessage: errorMessage ?? this.errorMessage
  );
}