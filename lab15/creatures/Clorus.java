package creatures;

import huglife.*;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * Description: XXX.
 * Author: Corn Liu
 * Email: cornliu@zju.edu.cn
 * Date: 2022/9/8 16:54
 */
public class Clorus extends Creature {

    /**
     * Creates a creature with the name N. The intention is that this
     * name should be shared between all creatures of the same type.
     *
     * @param e is the energy
     */
    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    @Override
    public void move() {
        energy -= 0.03;
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public Creature replicate() {
        Clorus offspring = new Clorus(this.energy / 2);
        this.energy /= 2;
        return offspring;
    }

    @Override
    public void stay() {
        energy -= 0.01;
    }

    @Override
    public Color color() {
        return color(34, 0, 231);
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> plips = getNeighborsOfType(neighbors, "plip");
        if (empties.size() == 0) {
            return new Action(Action.ActionType.STAY);
        } else if (plips.size() > 0) {
            Direction moveDir = HugLifeUtils.randomEntry(plips);
            return new Action(Action.ActionType.ATTACK, moveDir);
        } else if (energy >= 1) {
            Direction moveDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, moveDir);
        } else {
            Direction moveDir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.MOVE, moveDir);
        }
    }
}
