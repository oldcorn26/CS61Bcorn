package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int remainNum;
    private boolean targetFound = false;
    private Maze maze;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = 0;
        remainNum = maze.V();
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    @Override
    public void solve() {
        cycleHelper(s);
    }

    // Helper methods go here
    private void cycleHelper(int v) {
        marked[v] = true;
        announce();

        if (remainNum == 0) {
            targetFound = true;
        }

        if (targetFound) {
            return;
        }

        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                announce();
                distTo[w] = distTo[v] + 1;
                cycleHelper(w);
                if (targetFound) {
                    return;
                }
            } else {
                if (edgeTo[w] != v && w != 0) {
                    edgeTo[w] = v;
                    announce();
                    return;
                }
            }
        }
    }
}

