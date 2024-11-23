

import '../../domain/domain.dart';
import '../infrastructure.dart';

class AuthRepositoryImpl implements AuthRepository{

  final AuthDS authDS;

  AuthRepositoryImpl({
    AuthDS? authDS 
  }) : authDS = authDS ?? AuthDSimpl();
  
  @override
  Future<Conductor> login(String email, String password) {
    return authDS.login(email, password);
  }
  
  @override
  Future<Conductor> checkAuthStatus(String token) {
    return authDS.checkAuthStatus(token);
  }
}