import org.junit.Test;
import static org.junit.Assert.*;

public class MinHeapTest {

    @Test
    public void test1() {
        MinHeap<String> i = new MinHeap<String>();
        i.insert("a");
        i.insert("b");
        i.insert("c");
        i.insert("c");
        System.out.println(i);
        System.out.println(i.size());
        i.removeMin();
        System.out.println(i);
    }


}
