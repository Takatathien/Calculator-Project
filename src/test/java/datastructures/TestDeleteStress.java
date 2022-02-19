package datastructures;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import datastructures.concrete.DoubleLinkedList;
import datastructures.interfaces.IList;

import static org.junit.Assert.assertTrue;

/**
 * This file should contain any tests that check and make sure your
 * delete method is efficient.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDeleteStress extends TestDoubleLinkedList {
    @Test(timeout=SECOND)
    public void testExample() {
        // Feel free to modify or delete this dummy test.
        assertTrue(true);
        assertEquals(3, 3);
    }
    
    
    @Test(timeout=5 * SECOND)
    public void testAddAndDeleteFromEndIsEfficient() {
        IList<Integer> list = new DoubleLinkedList<>();
        int cap = 5000000;
        for (int i = 0; i < cap; i++) {
            list.add(i * 2);
        }

        for (int i = cap - 1; i >= 0; i--) {
            assertEquals(i * 2, list.delete(i));
        }
    }
    
    @Test(timeout=5 * SECOND)
    public void testAddAndDeleteFromFrontIsEfficient() {
        IList<Integer> list = new DoubleLinkedList<>();
        int cap = 5000000;
        for (int i = 0; i < cap; i++) {
            list.add(i * 2);
        }

        for (int i = 0; i < cap; i++) {
            assertEquals(i * 2, list.delete(0));
        }
    }
    
    @Test(timeout=5 * SECOND)
    public void testAddAndDeleteNearEndIsEfficient() {
        IList<Integer> list = new DoubleLinkedList<>();
        list.add(-4);
        list.add(-2);
        int cap = 5000000;
        for (int i = 0; i < cap; i++) {
            list.add(i * 2);
        }
        
        for (int i = cap - 1; i >= 0; i--) {
            assertEquals(i * 2 - 4, list.delete(i));
        }
    }  
}
