


import '../../domain/domain.dart';

class ConductorConverter {

    static Conductor conductorJsonToEntity( Map<String,dynamic> json ) => Conductor(
    //user: json['user'],
    //telefono: json['telefono'],
    token: json['token']
  );

}
