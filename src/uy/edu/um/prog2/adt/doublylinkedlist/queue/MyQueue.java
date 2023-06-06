package uy.edu.um.prog2.adt.doublylinkedlist.queue;


import uy.edu.um.prog2.adt.doublylinkedlist.queue.exceptions.EmptyQueueException;

public interface MyQueue <T> {
    public void enqueue (T element);
    public T dequeue () throws EmptyQueueException;
    public boolean isEmpty();
}
