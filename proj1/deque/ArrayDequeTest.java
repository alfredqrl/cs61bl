package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

/* Performs some basic array deque tests. */
public class ArrayDequeTest {
    public static Deque<Integer> ad = new ArrayDeque<Integer>();

    @Test
    public void test1() {
        assertTrue("A newly initialized ArrayDeque should be empty", ad.isEmpty());
        ad.addFirst(0);
        System.out.println(1);

        assertFalse("ad should now contain 1 item", ad.isEmpty());
        System.out.println(1);

        ad.addLast(0);
        assertFalse("ad should now contain 2 item", ad.isEmpty());
        System.out.println(1);
        ad = new ArrayDeque<Integer>();
    }

    @Test
    public void test2() {
        ad.addFirst(0);
        ad.addFirst(1);
        ad.addFirst(2);
        int a = ad.get(1);
        assertEquals(1, a);
        ad = new ArrayDeque<Integer>();
    }


    /** You MUST use the variable below for all of your tests. If you test
     * using a local variable, and not this static variable below, the
     * autograder will not grade that test. If you would like to test
     * ArrayDeques with types other than Integer (and you should),
     * you can define a new local variable. However, the autograder will
     * not grade that test. */

    //public static Deque<Integer> ad = new ArrayDequeTest<Integer>();

}
