import 'dart:convert';

import 'package:dio/dio.dart';

import '../../domain/domain.dart';
import '../infrastructure.dart';


class AuthDSimpl implements AuthDS{

  final dio = Dio();

  //TODO: Cambiar las conecciones a variables de entorno
  @override
  Future<Conductor> login(String email, String password) async {
     
      try {
          //Post body
          final loginJson = {
            "email" : email,
            "password": password
          };

          //Hago la peticion, me deberia devolver un conductor (por ahora solo token) JSON
          final resp = await dio.post(
            'http://192.168.1.166:8080/api/auth/login',
            data: jsonEncode(loginJson),
          );

          final Conductor conductor = ConductorConverter.conductorJsonToEntity(resp.data);
          return conductor;

      }on DioException catch (e) {
      //Hablar sobre el tipo de statusCode que me van a mandar
      if( e.response?.statusCode == 400 ){
         throw CustomError(e.response?.data['msg'] ?? 'Credenciales incorrectas' );
      }
      if ( e.type == DioExceptionType.connectionTimeout ){
        throw CustomError('Revisar conexión a internet');
      }
      throw Exception();

    } catch (e) {
      
      throw Exception();
    }


  }
  
  @override
  Future<Conductor> checkAuthStatus(String token) {
    // Ver en el backend como vamos a hacer esto, consutlar con MAXI.
    throw UnimplementedError();
  }

}