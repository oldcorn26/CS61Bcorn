/**
 * Description: XXX.
 * Author: Corn Liu
 * Email: cornliu@zju.edu.cn
 * Date: 2022/8/4 22:49
 */

import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test(timeout = 10000)
    public void testStudentArray() {
        StudentArrayDeque<Integer> stu = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> exp = new ArrayDequeSolution();
        int size = 0;

        while(size < 10000) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.25) {
                int num = (int)(numberBetweenZeroAndOne * 666);
                stu.addFirst(num);
                exp.addFirst(num);
                assertEquals("addFirst(" + num + ")", exp.get(size), stu.get(size));
                System.out.println("addFirst(" + num + ")");
                size++;
            } else if (numberBetweenZeroAndOne < 0.5 && size > 0) {
                size--;
                int removed1 = exp.removeFirst();
                int removed2 = stu.removeFirst();
                assertEquals("removeFirst(): " + removed2, removed1, removed2);
                System.out.println("removeFirst(): " + removed1);
            } else if (numberBetweenZeroAndOne < 0.75 && size > 0) {
                size--;
                int removed1 = exp.removeLast();
                int removed2 = stu.removeLast();
                assertEquals("removeLast(): " + removed2, removed1, removed2);
                System.out.println("removeLast(): " + removed1);
            } else {
                int num = (int) (numberBetweenZeroAndOne * 666);
                stu.addLast(num);
                exp.addLast(num);
                assertEquals("addLast(" + num + ")", exp.get(size), stu.get(size));
                System.out.println("addLast(" + num + ")");
                size++;
            }
        }
    }
}
