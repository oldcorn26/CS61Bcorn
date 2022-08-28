package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.List;

/**
 * Description: XXX.
 * Author: Corn Liu
 * Email: cornliu@zju.edu.cn
 * Date: 2022/8/27 16:33
 */
public class Solver {
    private WorldState[] bms;

    /**
     * To save state and data about the state.
     */
    private class SearchNode implements Comparable<SearchNode>{
        private WorldState state;
        private int step;
        private int pro;
        private SearchNode pre;

        /**
         * To create a node.
         * @param sta is the state.
         * @param ste is the step we take from the original state.
         * @param pros is the prediction to the end.
         * @param p is the previous node.
         */
        public SearchNode(WorldState sta, int ste, int pros, SearchNode p) {
            state = sta;
            step = ste;
            pro = pros;
            pre = p;
        }

        public void previous(SearchNode before) {
            pre = before;
        }

        public WorldState getState() {
            return state;
        }

        public int getStep() {
            return step;
        }

        public int getPro() {
            return pro;
        }

        public SearchNode getPre() {
            return pre;
        }

        @Override
        public int compareTo(SearchNode o) {
            if ((step + pro) > (o.step + o.pro)) {
                return 1;
            } else if ((step + pro) < (o.step + o.pro)) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    public Solver(WorldState initial) {
        SearchNode helper;
        SearchNode flag = new SearchNode(initial, 0, initial.estimatedDistanceToGoal(),null);
        MinPQ<SearchNode> pq = new MinPQ<>();

        while (flag.getPro() != 0) {
            for (WorldState temp : flag.getState().neighbors()) {
                if (flag.getPre() != null) {
                    if (flag.getPre().getState().equals(temp)) {continue;}
                }
                helper = new SearchNode(temp, 1 + flag.getStep(), temp.estimatedDistanceToGoal(), flag);
                pq.insert(helper);
            }
            flag = pq.delMin();
        }

        bms = new WorldState[flag.getStep() + 1];
        for (int i = flag.getStep(); i >= 0; i--) {
            bms[i] = flag.getState();
            flag = flag.getPre();
        }
    }

    public int moves() {
        return bms.length - 1;
    }

    public Iterable<WorldState> solution() {
        return List.of(bms);
    }
}
