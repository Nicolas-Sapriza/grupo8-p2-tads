import 'package:flutter/material.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';

import '../providers/providers.dart';


class LoginScreen extends StatelessWidget {
  const LoginScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return const _LoginForm();
  }
}

class _LoginForm extends ConsumerWidget {
  const _LoginForm({super.key});

  @override
  Widget build(BuildContext context, WidgetRef ref) {

    //Tengo acceso al estado del provider
    final loginForm = ref.watch(loginFormProvider);
  
    return Padding(
      padding: const EdgeInsets.all(8.0),
      child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
    
                  //Tarjeta con el formulario
                  Card(
                    elevation: 10,
                    child: Container(
                      width: double.infinity,
                      margin: const EdgeInsets.all(10),
                      padding: const EdgeInsets.all(10),
    
                      child: Column(
    
                            children: [
    
                              //Aca muestro el input para el email
                              TextFormField(
                                keyboardType: TextInputType.emailAddress,
                                //Funcion con los mismos argumentos que la posicion del callback, esto es lo mismo
                                //onChanged: (value) =>  ref.read(loginFormProvider.notifier).onEmailChange(value),
                                onChanged: ref.read(loginFormProvider.notifier).onEmailChange,
                                decoration:  InputDecoration(
                                  errorText: loginForm.isFormPosted 
                                    ? loginForm.email.errorMessage
                                    : null,
                                  hintText: 'Nombre usuario',
                                  icon: const Icon(
                                    Icons.email,
                                    color: Colors.black87,
                                  ),
                                ),
                              ),
    
                              const SizedBox(height: 20),
    
                              //Aca muestro el input para la contrasena
                              TextFormField(
                                keyboardType: TextInputType.visiblePassword,
                                onChanged: ref.read(loginFormProvider.notifier).onPasswordChanged,
                                obscureText: true,
                                decoration: InputDecoration(
                                  errorText: loginForm.isFormPosted 
                                    ? loginForm.password.errorMessage 
                                    : null,
    
                                  hintText: 'Contraseña',
                                  icon: const Icon(
                                    Icons.password,
                                    color: Colors.black87,
                                  ),
                                ),
                              ),
    
                              const SizedBox(
                                height: 20,
                              ),
                            ],
                          )
                      ),
                  ),
    
    
                  const SizedBox(height: 10),
    
    
                  //Aca tengo los botones y las funcionalidades
                  TextButton(
    
                    onPressed: () {
                      ref.read(loginFormProvider.notifier).onFormSubmit();
                    },
    
    
                    style: TextButton.styleFrom(backgroundColor: Colors.orange,
                    minimumSize: const Size(double.infinity, 45)),
                    child: const Text(
                      'Ingresar',
                      style:  TextStyle(
                        color: Colors.white,
                        fontSize: 20,
                      ),
                    ),
                    
                  ),
    
    
    
                ]),
    );
  }
}