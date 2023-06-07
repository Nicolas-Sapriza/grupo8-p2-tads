package uy.edu.um.prog2.adt.treebst;

import org.junit.jupiter.api.Test;
import uy.edu.um.prog2.adt.tree.MyTree;
import uy.edu.um.prog2.adt.tree.Tree;
import uy.edu.um.prog2.adt.treebst.exceptions.ErrorControlado;

import static org.junit.jupiter.api.Assertions.*;

class TreeBSTTest {

    @Test
    void findAndInsert() {
        TreeBST<Integer,String> tree = new TreeBST();
        tree.insert(0,"I'm a root (0)");
        tree.insert(1,"I'm a leaf (0)");
        tree.insert(2,"I'm a root (1)");
        tree.insert(3,"I'm a leaf (1)");
        tree.insert(4,"I'm a leaf (2)");

        assertEquals("I'm a root (0)",tree.find(0));
        assertEquals("I'm a leaf (0)",tree.find(1));
        assertEquals("I'm a root (1)",tree.find(2));
        assertEquals("I'm a leaf (1)",tree.find(3));
        assertEquals("I'm a leaf (2)",tree.find(4));
    }

    @Test
    void delete() throws ErrorControlado {
        TreeBST<Integer,String> tree = new TreeBST();
        tree.insert(0,"I'm a root (0)");
        tree.insert(1,"I'm a leaf (0)");
        tree.insert(2,"I'm a root (1)");
        tree.insert(3,"I'm a leaf (1)");
        tree.insert(4,"I'm a leaf (2)");

        tree.delete(2);

        assertEquals("I'm a root (0)",tree.find(0));
        assertEquals("I'm a leaf (0)",tree.find(1));
        assertNull(tree.find(2));
    }

    @Test
    void inOrder() throws uy.edu.um.prog2.adt.tree.exceptions.ErrorControlado {
        TreeBST<Integer,String> tree = new TreeBST();
        tree.insert(0,"I'm a root (0)");
        tree.insert(1,"I'm a leaf (0)");
        tree.insert(2,"I'm a root (1)");
        tree.insert(3,"I'm a leaf (1)");
        tree.insert(4,"I'm a leaf (2)");

        assertEquals(0,tree.inOrder().get(0));
        assertEquals(1,tree.inOrder().get(1));
        assertEquals(2,tree.inOrder().get(2));
        assertEquals(3,tree.inOrder().get(3));
        assertEquals(4,tree.inOrder().get(4));


    }

    @Test
    void preOrder() throws uy.edu.um.prog2.adt.tree.exceptions.ErrorControlado {
        TreeBST<Integer,String> tree = new TreeBST();
        tree.insert(0,"I'm a root (0)");
        tree.insert(1,"I'm a leaf (0)");
        tree.insert(2,"I'm a root (1)");
        tree.insert(3,"I'm a leaf (1)");
        tree.insert(4,"I'm a leaf (2)");

        assertEquals(0,tree.preOrder().get(0));
        assertEquals(1,tree.preOrder().get(1));
        assertEquals(2,tree.preOrder().get(2));
        assertEquals(3,tree.preOrder().get(3));
        assertEquals(4,tree.preOrder().get(4));

    }

    @Test
    void postOrder() throws uy.edu.um.prog2.adt.tree.exceptions.ErrorControlado {
        TreeBST<Integer,String> tree = new TreeBST();
        tree.insert(0,"I'm a root (0)");
        tree.insert(1,"I'm a leaf (0)");
        tree.insert(2,"I'm a root (1)");
        tree.insert(3,"I'm a leaf (1)");
        tree.insert(4,"I'm a leaf (2)");

        assertEquals(4,tree.postOrder().get(0));
        assertEquals(3,tree.postOrder().get(1));
        assertEquals(2,tree.postOrder().get(2));
        assertEquals(1,tree.postOrder().get(3));
        assertEquals(0,tree.postOrder().get(4));
    }
}
