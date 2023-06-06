package uy.edu.um.prog2.adt.doublylinkedlist.stack.exceptions;

public class EmptyStackException extends Exception{
    public EmptyStackException() {
    }

    public EmptyStackException(String message) {
        super(message);
    }
}