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
    private double mean;
    private double stddev;
    private double confidenceLow;
    private double confidenceRight;
    private int[] x;
    private int size;
    public PercolationStats(int N, int T, PercolationFactory pf) {  // perform T independent experiments on an N-by-N grid
        x = new int[T];
        size = N;
        for (int i = 0; i < T; i++) {
            Percolation pc = pf.make(N);
            x[i] = perform(pc);
        }
        mean = StdStats.mean(x);
        stddev = StdStats.stddev(x);
        confidenceLow = mean - 1.96 * stddev / Math.sqrt(T);
        confidenceRight = mean + 1.96 * stddev / Math.sqrt(T);
    }
    public double mean() {                                           // sample mean of percolation threshold
        return mean;
    }
    public double stddev() { return stddev; }                                         // sample standard deviation of percolation threshold
    public double confidenceLow() {                                 // low endpoint of 95% confidence interval
        return confidenceLow;
    }
    public double confidenceHigh() { return confidenceRight; }                                // high endpoint of 95% confidence interval

    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(20, 150, pf);
        System.out.println(ps.mean);
    }
}