package uy.edu.um.prog2.adt.treebst;


import uy.edu.um.prog2.adt.treebst.exceptions.ErrorControlado;

public interface MyBinarySearchTree<K extends Comparable<K>, T> {
    T find(K key);
    void insert(K key, T data);
    void delete(Object key) throws ErrorControlado;
}