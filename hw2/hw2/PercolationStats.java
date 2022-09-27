package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int perform(Percolation pc) {
        while(!pc.percolates()) {
            int pos = StdRandom.uniform(size * size);
            int r = pos / size, l = pos % size;
            pc.open(r, l);
        }
        return pc.numberOfOpenSites();
    }
    private double[] fractions;
    private int size;
    private int testTimes;
    public PercolationStats(int N, int T, PercolationFactory pf) {  // perform T independent experiments on an N-by-N grid
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        fractions = new double[T];
        size = N;
        testTimes = T;
        for (int i = 0; i < T; i++) {
            Percolation pc = pf.make(N);
            fractions[i] = perform(pc);
        }
    }
    public double mean() {                                           // sample mean of percolation threshold
        return StdStats.mean(fractions);
    }
    public double stddev() { return StdStats.stddev(fractions); }                                         // sample standard deviation of percolation threshold
    public double confidenceLow() {                                 // low endpoint of 95% confidence interval
        double mean = mean(), stddev = stddev();
        return mean - 1.96 * stddev / Math.sqrt(testTimes);
    }
    public double confidenceHigh() {
        double mean = mean(), stddev = stddev();
        return mean + 1.96 * stddev / Math.sqrt(testTimes);
    }                                // high endpoint of 95% confidence interval

    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(2, 10000, pf);
    }
}