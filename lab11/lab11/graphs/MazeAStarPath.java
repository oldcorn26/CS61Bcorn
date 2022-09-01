package lab11.graphs;

import java.util.PriorityQueue;


/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private PriorityQueue pq;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        pq = new PriorityQueue<Node>();
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        marked[s] = true;
        announce();
    }

    private class Node implements Comparable {
        private int name;
        private int step;
        private int estimate;
        public Node(int name, int step, int estimate) {
            this.name = name;
            this.step = step;
            this.estimate = estimate;
        }

        public int getName() {
            return name;
        }

        @Override
        public int compareTo(Object o) {
            Node n1 = this;
            Node n2 = (Node) o;
            return Integer.compare(n1.step + n1.estimate, n2.step + n2.estimate);
        }
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return Math.abs(maze.toX(v) - maze.toX(t)) + Math.abs(maze.toY(v) - maze.toY(t));
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        Node temp;
        while (s != t) {
            for (int flag : maze.adj(s)) {
                if (!marked[flag]) {
                    edgeTo[flag] = s;
                    marked[flag] = true;
                    distTo[flag] = distTo[s] + 1;
                    announce();
                    temp = new Node(flag, distTo[flag], h(flag));
                    pq.add(temp);
                }
            }
            temp = (Node) pq.remove();
            s = temp.getName();
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

