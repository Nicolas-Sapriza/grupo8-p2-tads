package uy.edu.um.prog2.adt.heap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import uy.edu.um.prog2.adt.heap.exceptions.EmptyHeapException;

class MyHeapImplTest {
    @Test
    public void testAddDeleteMaxHeap() throws EmptyHeapException {
        MyHeap<String> heap = new MyHeapImpl(false);
        heap.insert("54321");
        heap.insert("654321");
        heap.insert( "321");
        assertEquals("654321", heap.delete());
        assertEquals("54321", heap.delete());
        assertEquals("321", heap.delete());
        try {
            heap.delete();
        }catch (EmptyHeapException e){}
    }
    @Test
    public void testAddDeleteMinHeap() throws EmptyHeapException {
        MyHeap<String> heap = new MyHeapImpl(true);
        heap.insert("54321");
        heap.insert("654321");
        heap.insert("321");
        assertEquals("321", heap.delete());
        assertEquals("54321", heap.delete());
        assertEquals("654321", heap.delete());
        try {
            heap.delete();
        }catch (EmptyHeapException e){}
    }

    @Test
    public void testSize() throws EmptyHeapException {
        MyHeap<String> heap = new MyHeapImpl(true);
        assertEquals(0,heap.size());
        heap.insert("Uno");
        heap.insert("Dos");
        heap.insert("Tres");
        assertEquals(3,heap.size());
        heap.delete();
        heap.delete();
        heap.delete();
        assertEquals(0, heap.size());

    }
}