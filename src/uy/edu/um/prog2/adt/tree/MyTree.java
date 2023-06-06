package uy.edu.um.prog2.adt.tree;

import uy.edu.um.prog2.adt.linkedlist.MyLinkedListImp;
import uy.edu.um.prog2.adt.tree.exceptions.EmptyListException;
import uy.edu.um.prog2.adt.tree.exceptions.ErrorControlado;

public interface MyTree<K, T> {

    T find(K key) throws ErrorControlado;

    void insert(K key, T data, K parentKey) throws ErrorControlado;

    void delete(K key) throws EmptyListException, ErrorControlado;

    Integer size() throws ErrorControlado;
    Integer countLeaf() throws ErrorControlado;
    Integer countCompleteElements() throws ErrorControlado;

    MyLinkedListImp inOrder();
    MyLinkedListImp preOrder();
    MyLinkedListImp postOrder();
}