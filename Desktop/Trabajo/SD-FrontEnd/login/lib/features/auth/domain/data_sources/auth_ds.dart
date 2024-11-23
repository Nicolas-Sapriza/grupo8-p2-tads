


import '../domain.dart';

abstract class AuthDS {
  
   Future<Conductor> login(String email, String password);
   Future<Conductor> checkAuthStatus( String token );

}