import org.junit.Test;
import static org.junit.Assert.*;

public class MinHeapPQTest {

    @Test
    public void test1() {
        MinHeapPQ<String> i = new MinHeapPQ<String>();
        i.insert("a", 0.1);
        i.insert("b", 0.2);
        i.insert("c", 0.3);
        System.out.println(i.toString());
        System.out.println(i.size());

        System.out.println(i);
    }

    @Test
    public void test2() {
        MinHeapPQ<String> i = new MinHeapPQ<String>();
        i.insert("a", 0);
        i.insert("b", 0.1);
        i.insert("c", 0.2);
        i.changePriority("a", 0.5);

    }
}
