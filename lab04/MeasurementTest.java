import org.junit.Test;

import static org.junit.Assert.*;

public class MeasurementTest {

    @Test
    public void test1() {
        Measurement A = new Measurement(2,10);
        Measurement B = new Measurement(1,9);
        Measurement C = new Measurement(4,7);
        assertEquals(A.toString(), "2'10\"");
        assertEquals(B.toString(), "1'9\"");
        assertEquals(C.toString(), "4'7\"");

        // TODO: stub for first test
    }
    @Test
    public void test2(){
        Measurement A = new Measurement(2,10);
        Measurement B = new Measurement(1,9);
        Measurement C = new Measurement(4,7);
        assertEquals(A.plus(B).toString(), "4'7\"");
        assertEquals(C.minus(B).toString(), "2'10\"");
        assertEquals(C.minus(A).toString(), "1'9\"");
    }
    @Test
    public void test3(){
        Measurement A = new Measurement(2,10);
        assertEquals(A.multiple(2).toString(), "5'8\"");
        assertEquals(A.multiple(3).toString(), "8'6\"");
    }
    // TODO: Add additional JUnit tests for Measurement.java here.

}