package lab11.graphs;

import java.util.LinkedList;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private Maze maze;
    private LinkedList queue;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        queue = new LinkedList<Integer>();
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        marked[s] = true;
        announce();
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        while (s != t) {
            for (int flag : maze.adj(s)) {
                if (!marked[flag]) {
                    edgeTo[flag] = s;
                    marked[flag] = true;
                    distTo[flag] = distTo[s] + 1;
                    announce();
                    queue.addLast(flag);
                }
            }
            s = (int) queue.removeFirst();
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

