package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] flag;
    private int openNumber;
    private int width;
    private WeightedQuickUnionUF sites, sitesPer;

    /**
     * Create N-by-N grid, with all sites initially blocked.
     * @param N is width of the square.
     */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("index " + N + " is not bigger than 0");
        }

        int num = N * N + 2;
        width = N;
        openNumber = 0;
        flag = new boolean[num];
        sites = new WeightedQuickUnionUF(num);
        sitesPer = new WeightedQuickUnionUF(num);

        for (int i = 0; i < N; i++) {
            sitesPer.union(i, num - 2);
            sitesPer.union(num - 3 - i, num - 1);
            sites.union(i, num - 2);
        }
    }

    /**
     * Open the site (row, col) if it is not open already.
     * @param row is the coordinate.
     * @param col is the coordinate.
     */
    public void open(int row, int col) {
        int index = row * width + col;
        if (row < 0 || col < 0 || row > width - 1 || col > width - 1) {
            throw new IndexOutOfBoundsException("It's outside its prescribed range");
        }
        if (flag[index]) return;

        flag[index] = true;
        if (col < width - 1 && flag[index + 1]) {
            sites.union(index, index + 1);
            sitesPer.union(index, index + 1);
        }
        if (row < width - 1) {
            if (flag[index + width]) {
                sites.union(index, index + width);
                sitesPer.union(index, index + width);
            }
        }
        if (row > 0) {
            if (flag[index - width]) {
                sites.union(index, index - width);
                sitesPer.union(index, index - width);
            }
        }
        if (col > 0) {
            if (flag[index - 1]) {
                sites.union(index, index - 1);
                sitesPer.union(index, index - 1);
            }
        }
        openNumber++;
    }

    /**
     * Ts the site (row, col) open?
     * @param row is the coordinate.
     * @param col is the coordinate.
     * @return true if it is open.
     */
    public boolean isOpen(int row, int col) {
        int index = row * width + col;
        if (row < 0 || col < 0 || row > width - 1 || col > width - 1) {
            throw new IndexOutOfBoundsException("Outside its prescribed range");
        }
        return flag[index];
    }

    /**
     * Is the site (row, col) full?
     * @param row is the coordinate.
     * @param col is the coordinate.
     * @return true if it is full.
     */
    public boolean isFull(int row, int col) {
        int index = row * width + col;
        if (row < 0 || col < 0 || row > width - 1 || col > width - 1) {
            throw new IndexOutOfBoundsException("Outside its prescribed range");
        }
        return flag[index] && sites.connected(index, width * width);
    }

    /**
     * Number of open sites.
     * @return the number of open sites
     */
    public int numberOfOpenSites() {
        return openNumber;
    }

    /**
     * Does the system percolate?
     * @return true if it is percolated.
     */
    public boolean percolates() {
        if (width == 1) {
            return flag[0];
        } else {
            int num  = width * width;
            return sitesPer.connected(num, num + 1);
        }
    }
    public static void main(String[] args) {
        Percolation p = new Percolation(1);
        p.open(0, 0);
//        p.open(1, 0);
//        p.open(2,0);
//        p.open(1, 2);
//        p.open(2, 2);
        p.percolates();
        System.out.println(p.percolates());
//        System.out.println(p.isOpen(1, 0));
//        System.out.println(p.isFull(1, 0));
    }
}
