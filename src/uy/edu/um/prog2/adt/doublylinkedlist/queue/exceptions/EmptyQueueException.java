package uy.edu.um.prog2.adt.doublylinkedlist.queue.exceptions;

public class EmptyQueueException extends Exception{
    public EmptyQueueException() {
    }

    public EmptyQueueException(String message) {
        super(message);
    }
}
