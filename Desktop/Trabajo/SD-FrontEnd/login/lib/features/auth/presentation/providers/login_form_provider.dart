import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:formz/formz.dart';


import '../../../shared/shared.dart';
import 'auth_provider.dart';

//Con el autoDispose, el estado se pierde (se destruye) en funcion de la interaccion del usuario.
final loginFormProvider = StateNotifierProvider.autoDispose<LoginFormNotifier,LoginFormState>((ref) {

  //Conecto el auth_provider con este provider, pero solo traigo el login, no todo,
  //es mas facil de esta manera en caso de que se tenga que modifica algo en el futuro
  final loginUserCallback = ref.watch(authProvider.notifier).loginUser;

  return LoginFormNotifier(
    loginUserCallback:loginUserCallback
  );
});


class LoginFormNotifier extends StateNotifier<LoginFormState> {


  final Function(String, String) loginUserCallback;

  //Hacemos la creacion del estado inicial con el LoginFormState()
  LoginFormNotifier({
    required this.loginUserCallback,
  }): super( LoginFormState() );
  
  onEmailChange( String value ) {
    //Modifico el correo en funcion de lo que el usuario escriba, por eso se utliza en onChange.
    final newEmail = Email.dirty(value);
    state = state.copyWith(
      email: newEmail,
      //Verificamos todos los campos del formulario en simultaneo
      isValid: Formz.validate([ newEmail, state.password ])
    );
  }

  //Misma idea que en el email
  onPasswordChanged( String value ) {
    final newPassword = Password.dirty(value);
    state = state.copyWith(
      password: newPassword,
      isValid: Formz.validate([ newPassword, state.email ])
    );
  }

  //Le damos onPressed al boton.
  onFormSubmit() async {
    _touchEveryField();
    if ( !state.isValid ) return;

    await loginUserCallback( state.email.value, state.password.value );

  }

  //Cuando se ejecuta el boton de 'Ingresar' verificamos todos los estados y en funcion de eso cambiamos el estado de nuestro
  //login el cual es muy diferente al estado inicial (pure)
  _touchEveryField() {
    final email    = Email.dirty(state.email.value);
    final password = Password.dirty(state.password.value);

    //Cambiamos el estado
    state = state.copyWith(
      //Esto esta en true porque toco el boton
      isFormPosted: true,
      email: email,
      password: password,
      isValid: Formz.validate([ email, password ])
    );

  }

}

class LoginFormState {

  final bool isPosting;
  final bool isFormPosted;
  final bool isValid;
  final Email email;
  final Password password;

  LoginFormState({
    this.isPosting = false,
    this.isFormPosted = false,
    this.isValid = false,
    this.email = const Email.pure(),
    this.password = const Password.pure()
  });

  //Con el copyWith creamos nuevos estados en base al estado acutal
  LoginFormState copyWith({
    bool? isPosting,
    bool? isFormPosted,
    bool? isValid,
    Email? email,
    Password? password,
  }) => LoginFormState(
    isPosting: isPosting ?? this.isPosting,
    isFormPosted: isFormPosted ?? this.isFormPosted,
    isValid: isValid ?? this.isValid,
    email: email ?? this.email,
    password: password ?? this.password,
  );

  //Se sobreescribe el metodo toString, para imprimir en consola y ver mas facilmente el estado de mi login.
  @override
  String toString() {
    return '''
  LoginFormState:
    isPosting: $isPosting
    isFormPosted: $isFormPosted
    isValid: $isValid
    email: $email
    password: $password
''';
  }
}
