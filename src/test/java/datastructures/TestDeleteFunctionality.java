package datastructures;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import datastructures.concrete.DoubleLinkedList;
import datastructures.interfaces.IList;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class should contain all the tests you implement to verify that
 * your 'delete' method behaves as specified.
 *
 * This test _extends_ your TestDoubleLinkedList class. This means that when
 * you run this test, not only will your tests run, all of the ones in
 * TestDoubleLinkedList will also run.
 *
 * This also means that you can use any helper methods defined within
 * TestDoubleLinkedList here. In particular, you may find using the
 * 'assertListMatches' and 'makeBasicList' helper methods to be useful.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDeleteFunctionality extends TestDoubleLinkedList {	
	protected IList<String> makeLongList() {
        IList<String> list = new DoubleLinkedList<>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        
        return list;
    }
	
    protected IList<String> makeLongerList() {
        IList<String> list = new DoubleLinkedList<>();

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        list.add("f");
        list.add("g");
        list.add("h");
        list.add("i");
        list.add("j");
        list.add("k");
        
        return list;
    }
    
	@Test(timeout=SECOND)
    public void testExample() {
        // Feel free to modify or delete this dummy test.
        assertTrue(true);
        assertEquals(3, 3);
    }
    
    @Test(timeout=SECOND)
    public void basicTestDelete() {
    	 IList<String> list = makeLongList();
         
         assertEquals("c", list.delete(2));
         assertEquals("d", list.delete(2));
    }
    
    @Test(timeout=SECOND)
    public void basicTestDeleteDecrementsSize() {
        IList<String> list = makeLongList();
        int initSize = list.size();
        list.delete(1);
        assertEquals(initSize - 1, list.size());

        list.delete(1);
        assertEquals(initSize - 2, list.size());
    }
    
    @Test(timeout=SECOND)
    public void testDeleteFromFront() {
    	IList<String> list = makeLongList();
        
        assertEquals("a", list.delete(0));
        assertEquals("b", list.delete(0));
    }
    
    @Test(timeout=SECOND)
    public void testDeleteFromMiddle() {
        IList<String> list = makeLongerList();
        
 
        
        assertEquals("f", list.delete(5));
        assertEquals("g", list.delete(5));
    }
    
    @Test(timeout=SECOND)
    public void testDeleteFromEnd() {
    	IList<String> list = makeLongList();
        
        assertEquals("e", list.delete(4));
        assertEquals("d", list.delete(3));
    }
    
    @Test(timeout=SECOND)
    public void testDeleteOutOfBounds() {
    	IList<String> list = makeLongList();
    	
    	try {
            // Now we're out of bounds
            list.delete(-1);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException ex) {
            // Do nothing: this is ok
        }
    	
    	try {
            // Now we're out of bounds
            list.delete(8);
            fail("Expected IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException ex) {
            // Do nothing: this is ok
        }
    }
    
    @Test(timeout=SECOND)
    public void testAddAndDeleteFromEnd() {
        IList<Integer> list = new DoubleLinkedList<>();
        int cap = 1000;

        for (int i = 0; i < cap; i++) {
            list.add(i);
        }

        assertEquals(cap, list.size());

        for (int i = cap - 1; i >= 0; i--) {
            int value = list.delete(i);
            assertEquals(i, value);
        }

        assertEquals(0, list.size());
    }
    
    @Test(timeout=SECOND)
    public void testAddAndDeleteMultiple() {
        IList<String> list = this.makeBasicList();
        assertEquals("c", list.delete(2));
        this.assertListMatches(new String[] {"a", "b"}, list);

        assertEquals("b", list.delete(1));
        this.assertListMatches(new String[] {"a"}, list);

        assertEquals("a", list.delete(0));
        this.assertListMatches(new String[] {}, list);
    }
    
    @Test(timeout=SECOND)
    public void testAlternatingAddAndDelete() {
        int iterators = 1000;

        IList<String> list = new DoubleLinkedList<>();

        for (int i = 0; i < iterators; i++) {
            String entry = "" + i;
            list.add(entry);
            assertEquals(1, list.size());
            
            String out = list.delete(0);
            assertEquals(entry, out);
            assertEquals(0, list.size());
        }
    }
    
}
