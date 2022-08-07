package synthesizer;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Double> x = new ArrayRingBuffer(4);
        x.enqueue(33.1); // 33.1 null null  null
        x.enqueue(44.8); // 33.1 44.8 null  null
        x.enqueue(62.3); // 33.1 44.8 62.3  null
        x.enqueue(-3.4); // 33.1 44.8 62.3 -3.4
        x.dequeue();     // 44.8 62.3 -3.4  null (returns 33.1)
        assertEquals((Double) 44.8, x.peek());
        Iterator<Double> seer = x.iterator();
        while (seer.hasNext()) {
            System.out.println(seer.next());
        }
//        x.dequeue();     // 62.3 -3.4  null null (returns 44.8)
//        assertEquals((Double) 62.3, x.peek());
//        assertEquals( 2, x.fillCount());
//        x.dequeue();     // -3.4  null null null (returns 62.3)
//        x.dequeue();     // null null null null (returns -3.4)
//        x.dequeue();     // RuntimeException
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
