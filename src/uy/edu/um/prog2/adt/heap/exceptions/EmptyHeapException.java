package uy.edu.um.prog2.adt.heap.exceptions;

public class EmptyHeapException extends RuntimeException {
    public EmptyHeapException() {
        super("Heap is empty.");
    }

    public EmptyHeapException(String message) {
        super(message);
    }
}
