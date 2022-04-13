import org.junit.Test;
import static org.junit.Assert.*;

public class SLListTest {

    @Test
    public void testSLListAdd() {
        SLList test1 = SLList.of(1, 3, 5);
        SLList test2 = new SLList();
        SLList test3 = SLList.of(1,2,3);


        test1.add(1, 2);
        test1.add(3, 4);
        assertEquals(5, test1.size());
        assertEquals(3, test1.get(2));
        assertEquals(4, test1.get(3));

        test2.add(1, 1);
        assertEquals(1, test2.get(0));
        assertEquals(1, test2.size());

        test2.add(10, 10);
        assertEquals(10, test2.get(1));
        test1.add(0, 0);

        test3.add(0, 0);
        assertEquals(0, test3.get(0));
        assertEquals(1, test3.get(1));
        assertEquals(2, test3.get(2));
        assertEquals(3, test3.get(3));

        assertEquals(SLList.of(0, 1, 2, 3, 4, 5), test1);
    }

    @Test
    public void testReverse() {
        SLList test1 = SLList.of(1, 3, 5);
        //SLList test2 = new SLList();
        SLList test2 = SLList.of();
        SLList test3 = SLList.of(1);
        SLList test4 = SLList.of(1,2);
        SLList test5 = SLList.of(1, 3, 5, 7);

        test1.reverse();
        assertEquals(SLList.of(5, 3, 1), test1);
        test2.reverse();
        assertEquals(SLList.of(), test2);
        test3.reverse();
        assertEquals(SLList.of(1), test3);
        test4.reverse();
        assertEquals(SLList.of(2,1), test4);
        test5.reverse();
        assertEquals(SLList.of(7, 5, 3, 1), test5);

    }
}
