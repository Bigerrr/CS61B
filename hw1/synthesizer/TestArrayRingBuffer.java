package synthesizer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the ArrayRingBuffer class.
 *
 * @author Josh Hug
 */

public class TestArrayRingBuffer {
    /**
     * Calls tests for ArrayRingBuffer.
     */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }

    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer<Integer>(10);
        for (int i = 1; i <= 10; i++) {
            arb.enqueue(i);
        }
        for (Object n : arb) {
            System.out.println(n);
        }
        assertEquals(1, arb.peek());
        assertEquals(1, arb.dequeue());
        assertEquals(2, arb.dequeue());
        assertEquals(3, arb.dequeue());
        for (int i = 1; i <= 3; i++) {
            arb.enqueue(i);
        }
        for (Object n : arb) {
            System.out.println(n);
        }
    }
} 
