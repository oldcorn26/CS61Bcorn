package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;


public class PercolationStats {
    private double[] fractions;
    private int times;

    /**
     * Perform T independent experiments on an N-by-N grid
     * @param N is the width.
     * @param T is the experiment times.
     * @param pf is the PercolationFactory
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("The input is not allowed");
        }

        times = T;
        fractions = new double[T];
        int sitesNumber = N * N;
        for (int i = 0; i < T; i++) {
            Percolation p = pf.make(N);
            while (!p.percolates()) {
                p.open(StdRandom.uniform(N), StdRandom.uniform(N));
            }
            fractions[i] = p.numberOfOpenSites() / sitesNumber;
        }
    }

    /**
     * Sample mean of percolation threshold
     * @return the mean of experiment.
     */
    public double mean() {
        return StdStats.mean(fractions);
    }

    /**
     * Sample standard deviation of percolation threshold
     * @return the sample standard deviation of percolation threshold
     */
    public double stddev() {
        return StdStats.stddev(fractions);
    }

    /**
     * Calculate low endpoint of 95% confidence interval
     * @return the low endpoint of 95% confidence interval
     */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(times);
    }

    /**
     * Calculate high endpoint of 95% confidence interval
     * @return the high endpoint of 95% confidence interval
     */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(times);
    }
}
