import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestArrayDequeGold {
    @Test
    public void testStudentArray() {
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StringBuilder command = new StringBuilder();
        for(int i = 0; i < 100; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            Integer expected = 1, actual = 1;
            if(numberBetweenZeroAndOne < 0.25) {
                sad.addFirst(i);
                ads.addFirst(i);
                command.append("addFirst(").append(i).append(")\n");
            } else if(numberBetweenZeroAndOne < 0.5) {
                sad.addLast(i);
                ads.addLast(i);
                command.append("addLast(").append(i).append(")\n");
            } else if(numberBetweenZeroAndOne < 0.75) {
                if(sad.size() > 0) {
                    actual = sad.removeFirst();
                    expected = ads.removeFirst();
                    command.append("removeFirst()\n");
                }
            } else {
                if(sad.size() > 0) {
                    actual = sad.removeLast();
                    expected = ads.removeLast();
                    command.append("removeLast()\n");
                }
            }
            assertEquals(String.valueOf(command), expected, actual);
        }
    }
}
