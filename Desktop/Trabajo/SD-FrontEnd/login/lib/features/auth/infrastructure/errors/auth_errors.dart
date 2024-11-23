
class ConnectionTimeout implements Exception {}
class InvalidToken implements Exception {}
class WrongCredentials implements Exception {}

class CustomError implements Exception {
  final String message;

  CustomError(this.message);
}


//Errores personalizados, asi como los de java.
