

import '../domain.dart';

abstract class AuthRepository {

  Future<Conductor> login(String email, String password);
  Future<Conductor> checkAuthStatus( String token );
   
}