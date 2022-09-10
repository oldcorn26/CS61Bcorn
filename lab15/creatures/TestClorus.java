package creatures;

import huglife.*;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Description: XXX.
 * Author: Corn Liu
 * Email: cornliu@zju.edu.cn
 * Date: 2022/9/10 11:27
 */
public class TestClorus {
    @Test
    public void testBasics() {
        Clorus p = new Clorus(2);
        assertEquals(2, p.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), p.color());
        p.move();
        assertEquals(1.97, p.energy(), 0.01);
        p.move();
        assertEquals(1.94, p.energy(), 0.01);
        p.stay();
        assertEquals(1.93, p.energy(), 0.01);
        p.stay();
        assertEquals(1.92, p.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus p =  new Clorus(1.2);
        Clorus op = (Clorus) p.replicate();
        assertEquals(0.6, p.energy(), 0.01);
        assertEquals(0.6, op.energy(), 0.01);
        assertNotSame(p, op);
    }

    @Test
    public void testChoose() {
        Clorus p = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Plip(1));
        surrounded.put(Direction.RIGHT, new Impassible());

        //You can create new empties with new Empty();
        //Despite what the spec says, you cannot test for Cloruses nearby yet.
        //Sorry!

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        surrounded.remove(Direction.RIGHT);
        surrounded.put(Direction.RIGHT, new Empty());

        actual = p.chooseAction(surrounded);
        expected = new Action(Action.ActionType.ATTACK, Direction.LEFT);

        assertEquals(expected, actual);
    }

    public static void main(String[] args) {
        System.exit(jh61b.junit.textui.runClasses(TestPlip.class));
    }
}
