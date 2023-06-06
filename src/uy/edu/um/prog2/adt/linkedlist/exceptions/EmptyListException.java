package uy.edu.um.prog2.adt.linkedlist.exceptions;

public class EmptyListException extends Exception{
    public EmptyListException(String mensaje){
        super(mensaje);
    }
}