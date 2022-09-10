package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/** An implementation of a motile pacifist photosynthesizer.
 *  @author Josh Hug
 */
public class Plip extends Creature {

    /** red color. */
    private int r;
    /** green color. */
    private int g;
    /** blue color. */
    private int b;

    /** creates plip with energy equal to E. */
    public Plip(double e) {
        super("plip");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /** creates a plip with energy equal to 1. */
    public Plip() {
        this(1);
    }

    /** Should return a color with red = 99, blue = 76, and green that varies
     *  linearly based on the energy of the Plip. If the plip has zero energy,
     *  it should have a green value of 63. If it has max energy, it should
     *  have a green value of 255. The green value should vary with energy
     *  linearly in between these two extremes. It's not absolutely vital
     *  that you get this exactly correct.
     */
    public Color color() {
        r = 99;
        b = 76;
        g = (int) (energy / 2 * 192 + 63);
        return color(r, g, b);
    }

    /** Do nothing with C, Plips are pacifists. */
    public void attack(Creature c) {
    }

    /** Plips should lose 0.15 units of energy when moving. If you want to
     *  to avoid the magic number warning, you'll need to make a
     *  private static final variable. This is not required for this lab.
     */
    public void move() {
        energy -= 0.15;
    }


    /** Plips gain 0.2 energy when staying due to photosynthesis. */
    public void stay() {
        if (energy <= 1.8) {
            energy += 0.2;
        } else {
            energy = 2;
        }
    }

    /** Plips and their offspring each get 50% of the energy, with none
     *  lost to the process. Now that's efficiency! Returns a baby
     *  Plip.
     */
    public Plip replicate() {
        Plip offspring = new Plip(this.energy / 2);
        this.energy /= 2;
        return offspring;
    }

    /** Plips take exactly the following actions based on NEIGHBORS:
     *  1. If no empty adjacent spaces, STAY.
     *  2. Otherwise, if energy >= 1, REPLICATE.
     *  3. Otherwise, if any Cloruses, MOVE with 50% probability.
     *  4. Otherwise, if nothing else, STAY
     *
     *  Returns an object of type Action. See Action.java for the
     *  scoop on how Actions work. See SampleCreature.chooseAction()
     *  for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        int[] block = getEmptyNumber(neighbors); //Empty is 0, clorus is 1.

        if (block[0] == 0) {
            return new Action(Action.ActionType.STAY);
        } else if (energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, getDirection(block));
        } else if (checkIfClorus(block)) {
            if (ifMove()) {
                return new Action(Action.ActionType.MOVE, getDirection(block));
            } else {
                return new Action(Action.ActionType.STAY);
            }
        } else {
            return new Action(Action.ActionType.STAY);
        }
    }

    private int[] getEmptyNumber(Map<Direction, Occupant> neighbors) {
        int[] block = new int[5]; //Empty is 0, clorus is 1.

        if (Objects.equals(neighbors.get(Direction.TOP).name(), "clorus")) {
            block[1] = 1;
        } else if (Objects.equals(neighbors.get(Direction.TOP).name(), "empty")) {
            block[0]++;
        } else {
            block[1] = 2;
        }

        if (Objects.equals(neighbors.get(Direction.RIGHT).name(), "clorus")) {
            block[2] = 1;
        } else if (Objects.equals(neighbors.get(Direction.RIGHT).name(), "empty")) {
            block[0]++;
        } else {
            block[2] = 2;
        }

        if (Objects.equals(neighbors.get(Direction.BOTTOM).name(), "clorus")) {
            block[3] = 1;
        } else if (Objects.equals(neighbors.get(Direction.BOTTOM).name(), "empty")) {
            block[0]++;
        } else {
            block[3] = 2;
        }

        if (Objects.equals(neighbors.get(Direction.LEFT).name(), "clorus")) {
            block[4] = 1;
        } else if (Objects.equals(neighbors.get(Direction.LEFT).name(), "empty")) {
            block[0]++;
        } else {
            block[4] = 2;
        }

        return block;
    }

    private Direction getDirection(int[] blocks) {
        Random r = new Random();
        int flag = r.nextInt(0, blocks[0]);

        for (int i = 1; i < 5; i++) {
            if (blocks[i] == 0) {
                if (flag > 0) {
                    flag--;
                } else {
                    switch (i) {
                        case 1:
                            return Direction.TOP;
                        case 2:
                            return Direction.RIGHT;
                        case 3:
                            return Direction.BOTTOM;
                        case 4:
                            return Direction.LEFT;
                    }
                }
            }
        }
        return null;
    }

    private boolean checkIfClorus(int[] blocks) {
        for (int i = 1; i < 5; i++) {
            if (blocks[i] == 1) {
                return true;
            }
        }
        return false;
    }

    private boolean ifMove() {
        Random r = new Random();
        if (r.nextInt(0, 2) == 0) {
            return true;
        } else {
            return false;
        }
    }
}
