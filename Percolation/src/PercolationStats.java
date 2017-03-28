import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int[] countsArray;
    private int nn;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("n and trials should be greater than 0");
        }
        countsArray = new int[trials];
        this.nn = n * n;
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            while (!p.percolates()) {
                int row = StdRandom.uniform(1, n + 1);
                int col = StdRandom.uniform(1, n + 1);
                p.open(row, col);
            }
            this.countsArray[i] = p.numberOfOpenSites();
        }
    }

    public double mean() {
        return StdStats.mean(countsArray) / this.nn;
    }

    public double stddev() {
        return StdStats.stddev(countsArray) / this.nn;
    }

    public double confidenceLo() {
        return this.mean() - ((1.96 * this.stddev()) / (Math.sqrt(this.countsArray.length)));
    }

    public double confidenceHi() {
        return this.mean() + ((1.96 * this.stddev()) / (Math.sqrt(this.countsArray.length)));
    }

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats p = new PercolationStats(n, trials);
        StdOut.print("mean = " + p.mean() + "\n");
        StdOut.print("stddev = " + p.stddev() + "\n");
        StdOut.print("95% confidence interval = [" + p.confidenceLo() + ", " + p.confidenceHi() + "]\n");
    }
}
