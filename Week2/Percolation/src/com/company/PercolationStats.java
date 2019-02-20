

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
public class PercolationStats {
    private final int n;
    private final int trials;
    private final double[] xses;
    private double mean;
    private double stddev;

    public PercolationStats(int n, int trials){
        if(n<=0 || trials<=0)throw new IllegalArgumentException();
        this.n = n;
        this.trials = trials;
        xses = new double[trials];
        int[] blockedSitesIndexes;
        for(int k = 0; k < trials; k++) {
            Percolation percolation = new Percolation(n);
            blockedSitesIndexes = new int[n * n];
            for (int i = 0; i < n*n; i++) {
                    blockedSitesIndexes[i] += i;
            }
            while (!percolation.percolates()) {
                int len = blockedSitesIndexes.length - 1;
                int value;
                if (len == 0) value = blockedSitesIndexes[0];
                else if (len == -1) break;
                else {
                    int index = StdRandom.uniform(len);
                    value = blockedSitesIndexes[index];
                    int row = value / n + 1;
                    int col = value % n + 1;
                    percolation.open(row, col);
                    blockedSitesIndexes = delete(blockedSitesIndexes, value);
                }
            }
            xses[k] = ((double)percolation.numberOfOpenSites()/(n*n));
        }
    }

    private int[] delete(int[] arr,int index){
        int[] arrcopy = new int[arr.length - 1];
        int realIndex = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == index){
                realIndex = i;
                break;
            }
        }
        int j = 0;
        for(int i = 0; i < arr.length; i++){
            if (i != realIndex){
                arrcopy[j++] = arr[i];
            }
        }
        return arrcopy;
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
