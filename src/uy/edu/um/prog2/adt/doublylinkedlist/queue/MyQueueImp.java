package uy.edu.um.prog2.adt.doublylinkedlist.queue;

import uy.edu.um.prog2.adt.doublylinkedlist.DoublyLinkedList;
import uy.edu.um.prog2.adt.doublylinkedlist.queue.exceptions.EmptyQueueException;

public class MyQueueImp <T> extends DoublyLinkedList<T> implements MyQueue<T> {
    public MyQueueImp() {
        super();
    }

    @Override
    public void enqueue(T element) {
        this.add(element);
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if (this.isEmpty()) {throw new EmptyQueueException();}

        T temp = this.get(0);
        this.remove(0);
        return temp;
    }

    //REVISAR
    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }
}
