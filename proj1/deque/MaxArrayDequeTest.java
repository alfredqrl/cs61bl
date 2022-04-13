package deque;

import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.Optional;

import static org.junit.Assert.*;

/* Performs some basic array deque tests. */
public class MaxArrayDequeTest {

    public static Comparator<Integer> comparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    };
    public static MaxArrayDeque<Integer> ad = new MaxArrayDeque<Integer>(comparator);

    public static void initParams() {
        ad.addFirst(0);
        ad.addFirst(1);
        ad.addFirst(2);
        ad.addFirst(8);
        ad.addFirst(2);
    }

    @Test
    public void test1(){
        assertEquals(null, ad.max());
    }

    @Test
    public void test2(){
        initParams();
        int res = ad.max();
        assertEquals(8, res);
    }

    @Test
    public void test3(){
        initParams();
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };
        int res = ad.max(comparator);
        assertEquals(8, res);
    }

}
