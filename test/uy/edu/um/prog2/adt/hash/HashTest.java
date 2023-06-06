package uy.edu.um.prog2.adt.hash;

import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.hash.exceptions.ErrorControlado;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashTest {

    @Test
    public void testPutAndGet() throws ErrorControlado {
        MyHashImpl hashTable= new MyHashImpl(5);
        hashTable.put(11,"Dato 11");
        hashTable.put(1,"Dato 1");
        hashTable.put("Llave","Dato 5");
        hashTable.put(8,"Dato 8");

        assertEquals("Dato 1",hashTable.get(1));
        assertEquals("Dato 5",hashTable.get("Llave"));

        hashTable.put(101,"Data 101");
        assertEquals("Data 101", hashTable.get(101));
        try{
            hashTable.put(1004,"Data 1004");
        }catch (ErrorControlado e) {}
    }

    @Test
    public void testContains() throws ErrorControlado {
        MyHashImpl<Integer,String> hashTable=new MyHashImpl(5);
        hashTable.put(5,"Data 5");
        hashTable.put(6,"Data 6");
        assertEquals(true,hashTable.contains(5));
        assertEquals(true,hashTable.contains(6));
        assertEquals(false,hashTable.contains(8));
    }

    @Test
    public void testDelete() throws ErrorControlado {
        MyHashImpl<Integer, String> hashTable=new MyHashImpl(5);
        hashTable.put(4,"Data 4");
        hashTable.put(67,"Data 67");
        hashTable.remove(4);
        try{
            hashTable.remove(10);
        }catch (ErrorControlado e){}
        hashTable.remove(67);
        try{
            hashTable.remove(4);
        }catch (ErrorControlado e){}
    }

}
