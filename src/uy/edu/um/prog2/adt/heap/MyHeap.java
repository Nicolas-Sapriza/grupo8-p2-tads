package uy.edu.um.prog2.adt.heap;

public interface MyHeap<T extends Comparable<T>> {
    T delete();
    T get();
    void insert(T element);
    int size();
}