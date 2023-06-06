import uy.edu.um.prog2.adt.hash.MyHashImpl;
import uy.edu.um.prog2.adt.hash.exceptions.ErrorControlado;
import uy.edu.um.prog2.adt.heap.MyHeap;
import uy.edu.um.prog2.adt.heap.MyHeapImpl;

public class Main {
    public static void main(String[] args) throws ErrorControlado {

        MyHeapImpl myHeap = new MyHeapImpl(true);

        myHeap.insert("Hola");
        myHeap.insert("Mundo");
        myHeap.insert("Ramonn");
        myHeap.insert("Com");

    }
}