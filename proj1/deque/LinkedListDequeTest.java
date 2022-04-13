package deque;

import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some basic linked list deque tests. */
public class LinkedListDequeTest {

    /** You MUST use the variable below for all of your tests. If you test
     * using a local variable, and not this static variable below, the
     * autograder will not grade that test. If you would like to test
     * LinkedListDeques with types other than Integer (and you should),
     * you can define a new local variable. However, the autograder will
     * not grade that test. */

    public static Deque<Integer> lld = new LinkedListDeque<Integer>();

    @Test
    /** Adds a few things to the list, checks that isEmpty() is correct.
     * This is one simple test to remind you how junit tests work. You
     * should write more tests of your own.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        //System.out.println("Make sure to uncomment the lines below (and delete this print statement)." +
        //        " The code you submit to the AG shouldn't have any print statements!");


		assertTrue("A newly initialized LLDeque should be empty", lld.isEmpty());
		lld.addFirst(0);

        assertFalse("lld should now contain 1 item", lld.isEmpty());

        lld.addLast(0);
        assertFalse("lld should now contain 2 item", lld.isEmpty());
        // Reset the linked list deque at the END of the test.
        lld = new LinkedListDeque<Integer>();

    }

    @Test
    public void removeLast(){

        lld.addFirst(0);
        lld.addFirst(1);
        lld.addFirst(2);
        lld.addFirst(3);
        lld.addFirst(4);
        int value;
        value = lld.removeLast();
        assertEquals(0, value);
    }

    @Test
    public void getTest(){
        lld.addFirst(0);
        lld.addFirst(1);
        lld.addFirst(2);
        int a = lld.get(1);
        assertEquals( 1,a);
        lld = new LinkedListDeque<Integer>();
    }

    @Test
    public void getReTest(){
        lld.addFirst(0);
        lld.addFirst(1);
        lld.addFirst(2);
        int a = lld.get(1);
        assertEquals( 1,a);
        lld = new LinkedListDeque<Integer>();
    }

    @Test
    public void getReRemoveTest(){
        lld.addFirst(0);
        lld.addFirst(1);
        lld.addFirst(2);
        lld.removeFirst();
        int a = lld.get(1);
        int sizeTest = lld.size();
        assertEquals( 0,a);
        assertEquals( 2,sizeTest);
        lld = new LinkedListDeque<Integer>();
    }

    @Test
    public void sizeTest(){
        lld.addFirst(0);
        lld.addFirst(1);
        int size = lld.size();
        assertEquals(2, size);
        lld = new LinkedListDeque<Integer>();
    }

    @Test
    public void emptyTest(){
        assertTrue(lld.isEmpty());
        lld = new LinkedListDeque<Integer>();
    }

    @Test
    public void printTest(){
        lld.addFirst(0);
        lld.addFirst(1);
        String re = "0 1";
        lld = new LinkedListDeque<Integer>();
    }

    @Test
    public void removeFirst(){

        lld.addFirst(0);
        lld.addFirst(1);
        lld.addFirst(2);
        lld.addFirst(3);
        lld.addFirst(4);
        int value;
        value = lld.removeFirst();
        assertEquals(4, value);
        lld = new LinkedListDeque<Integer>();
    }

    @Test
    public void addFirstTest(){
        lld.addFirst(1);
        assertNotNull(lld);
        lld = new LinkedListDeque<Integer>();
    }

    @Test
    public void addLastTest(){
        lld.addLast(1);
        assertNotNull(lld);
        lld = new LinkedListDeque<Integer>();
    }

    @Test
    public void equalTest(){
        int a = 5;
        assertTrue(!lld.equals(a));
        lld = new LinkedListDeque<Integer>();
    }

    @Test
    public void equalTest2(){
        lld.addLast(0);
        lld.addLast(1);
        int a = lld.removeLast();
        assertEquals(1,a);
    }
}
