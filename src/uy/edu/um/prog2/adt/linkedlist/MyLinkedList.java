package uy.edu.um.prog2.adt.linkedlist;

import uy.edu.um.prog2.adt.linkedlist.exceptions.EmptyListException;

public interface MyLinkedList {
    void add(Object value);
    void remove(Integer position) throws EmptyListException;
    Object get(Integer position);
}
