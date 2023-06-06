package uy.edu.um.prog2.adt.doublylinkedlist.queue;

import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.doublylinkedlist.queue.exceptions.EmptyQueueException;


import static org.junit.jupiter.api.Assertions.*;

class MyQueueImpTest {
    @Test
    void enqueueEmpty() throws EmptyQueueException {
        MyQueue<String> queue = new MyQueueImp<>();
        queue.enqueue("element");
        assertEquals("element",queue.dequeue());
    }
    @Test
    void enqueueFull() throws EmptyQueueException {
        MyQueue<String> queue = new MyQueueImp<>();
        queue.enqueue("element");
        queue.enqueue("element");
        queue.enqueue("element");
        assertEquals("element",queue.dequeue());
        assertEquals("element",queue.dequeue());
        assertEquals("element",queue.dequeue());
    }

    @Test
    void dequeueEmpty() throws EmptyQueueException{
        MyQueue<String> queue = new MyQueueImp<>();
        queue.enqueue("element");
        assertEquals("element",queue.dequeue());
        assertThrows(EmptyQueueException.class,() -> {queue.dequeue();});
    }

    @Test
    void dequeueFull() throws EmptyQueueException{
        MyQueue<String> queue = new MyQueueImp<>();
        queue.enqueue("element 1");
        queue.enqueue("element 2");
        assertEquals("element 1",queue.dequeue());
        assertEquals("element 2",queue.dequeue());
        assertThrows(EmptyQueueException.class,() -> {queue.dequeue();});
    }
}