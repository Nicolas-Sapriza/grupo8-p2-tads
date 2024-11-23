
import 'package:shared_preferences/shared_preferences.dart';

import 'key_value_storage.dart';

class KeyValueStorageImpl extends KeyValueStorage{
  
  @override
  Future<T?> getValue<T>(String key) async {

    final SharedPreferences prefs = await SharedPreferences.getInstance();

    switch(T){
      case String:
        return prefs.getString(key) as T?;
      
      default: 
        throw UnimplementedError('GET no esta implementado para el tipo de dato ${ T.runtimeType }');
    }

  }

  @override
  Future<void> setKeyValue<T>(String key, T value) async {
    final SharedPreferences prefs = await SharedPreferences.getInstance();

    switch(T){
      case String:
        prefs.setString(key,value as String);
        break;
      
      default: 
        throw UnimplementedError('SET no esta implementado para el tipo de dato ${ T.runtimeType }');
    }
  }

  @override
  Future<bool> deleteKeyValue(String key) async{
    final SharedPreferences prefs = await SharedPreferences.getInstance();
    return prefs.remove(key);
  
  }
}
