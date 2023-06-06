package uy.edu.um.prog2.adt.heap;

import uy.edu.um.prog2.adt.heap.exceptions.EmptyHeapException;

public class MyHeapImpl<T extends Comparable<T>> implements MyHeap<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private T[] listaElementos;
    private int size;
    private boolean esMinimo;

    public MyHeapImpl(boolean minimo) {
        this.listaElementos = (T[]) new Comparable[DEFAULT_CAPACITY];
        this.size = 0;
        this.esMinimo = minimo;
    }

    @Override
    public void insert(T element) {
        if (size == listaElementos.length) {
            resizeArray();
        }

        listaElementos[size] = element;
        size++;
        subir(size - 1);
    }

    @Override
    public T delete() {
        if (isEmpty()) {
            throw new EmptyHeapException("Esta vacio, no se puede borrar nada");
        }

        T borrado = (T) listaElementos[0];
        swap(0, size-1);
        listaElementos[size-1] = null;
        size--;

        bajar();

        return borrado;
    }

    @Override
    public T get() {
        if (isEmpty()) {
            throw new EmptyHeapException("Heap is empty.");
        }
        return (T) listaElementos[0];
    }

    @Override
    public int size() {
        return size;
    }


    private void subir(int index) {
        int parentIndex = (index - 1) / 2;

        while (index > 0 && compararNumeros(listaElementos[index], listaElementos[parentIndex]) > 0) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / 2;
        }
    }


    private void bajar() {
        int k = 0;
        int l = 2 * k + 1;

        while (l < size) {
            int max = l;
            int r = l + 1;

            if (r < size && (Integer) listaElementos[r] > (Integer) listaElementos[l]) {
                max = r;
            }

            if ((Integer) listaElementos[max] > (Integer) listaElementos[k]) {
                swap(max, k);
                k = max;
                l = 2 * k + 1;
            } else {
                break;
            }
        }
    }

    private int compararNumeros(T i, T j) {
        int resultado = i.compareTo(j);
        return esMinimo ? resultado * -1 : resultado; // Multiplicar por -1 si es un heap mínimo
    }

    public void imprimir() {
        for (int i = 0; i < size; i++) {
            System.out.println(listaElementos[i]);
        }
    }

    private boolean isEmpty() {
        return size == 0;
    }

    private void swap(int i, int j) {
        T temp = listaElementos[i];
        listaElementos[i] = listaElementos[j];
        listaElementos[j] = temp;
    }

    //Sugerencia GPT
    private void resizeArray() {
        T[] newArray = (T[]) new Comparable[listaElementos.length * 2];
        System.arraycopy(listaElementos, 0, newArray, 0, size);
        listaElementos = newArray;
    }
}

