    

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    private final int trials;
    private final double[] xses;
    private double mean;
    private double stddev;

    public PercolationStats(int n, int trials){
        if(n<=0 || trials<=0)throw new IllegalArgumentException();
        this.trials = trials;
        xses = new double[trials];
        for(int k = 0; k < trials; k++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(1,n+1);
                int col = StdRandom.uniform(1,n+1);
                if(!percolation.isOpen(row,col)){
                    percolation.open(row,col);
                }

            }
            xses[k] = ((double)percolation.numberOfOpenSites()/(n*n));
        }
    }

    public double mean(){
        mean = StdStats.mean(xses);
        return mean;
    }

    public double stddev(){
        stddev = StdStats.stddev(xses);
        return stddev;
    }

    public double confidenceLo(){
        return mean - (1.96f * stddev)/(Math.sqrt((double)trials));
    }
    public double confidenceHi(){
        return mean + (1.96f * stddev)/(Math.sqrt((double)trials));
    }

    public static void main(String[] args){
        //int n = Integer.parseInt(args[0]);
        //int trials = Integer.parseInt(args[1]);
        int n = StdIn.readInt();
        int trials = StdIn.readInt();
        PercolationStats stats = new PercolationStats(n,trials);
        stats.mean();
        stats.stddev();
        StdOut.println("mean                    = " + stats.mean);
        StdOut.println("stddev                  = " + stats.stddev);
        StdOut.println("95% confidence interval = [" + stats.confidenceLo()+", "+stats.confidenceHi()+"]");


    }
}
