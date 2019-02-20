package com.company;

/******************************************************************************
 *  Compilation:  javac PercolationVisualizer.java
 *  Execution:    java PercolationVisualizer input.txt
 *  Dependencies: Percolation.java
 *
 *  This program takes the name of a file as a command-line argument.
 *  From that file, it
 *
 *    - Reads the grid size n of the percolation system.
 *    - Creates an n-by-n grid of sites (intially all blocked)
 *    - Reads in a sequence of sites (row i, column j) to open.
 *
 *  After each site is opened, it draws full sites in light blue,
 *  open sites (that aren't full) in white, and blocked sites in black,
 *  with with site (1, 1) in the upper left-hand corner.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

import java.awt.Font;

public class PercolationVisualizer {

    // delay in miliseconds (controls animation speed)
    private static final int DELAY = 100;

    // draw n-by-n percolation system
    public static void draw(Percolation perc, int n) {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(-0.05 * n, 1.05 * n);
        StdDraw.setYscale(-0.05 * n, 1.05 * n);   // leave a border to write text
        StdDraw.filledSquare(n / 2.0, n / 2.0, n / 2.0);

        // draw n-by-n grid
        int opened = 0;
        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= n; col++) {
                if (perc.isFull(row, col)) {
                    StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
                    opened++;
                }
                else if (perc.isOpen(row, col)) {
                    StdDraw.setPenColor(StdDraw.WHITE);
                    opened++;
                }
                else
                    StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.filledSquare(col - 0.5, n - row + 0.5, 0.45);
            }
        }

        // write status text
        StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(0.25 * n, -0.025 * n, opened + " open sites");
        if (perc.percolates()) StdDraw.text(0.75 * n, -0.025 * n, "percolates");
        else StdDraw.text(0.75 * n, -0.025 * n, "does not percolate");

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

    public static void main(String[] args) {

        int n = StdIn.readInt();         // n-by-n percolation system

        // turn on animation mode
        StdDraw.enableDoubleBuffering();
        int[] blockedSitesIndexes = new int[n*n];
        for (int i = 0; i < n*n; i++) {
            blockedSitesIndexes[i] += i;
        }
        // repeatedly read in sites to open and draw resulting system
        Percolation perc = new Percolation(n);
        draw(perc, n);
        StdDraw.show();
        StdDraw.pause(DELAY);
        PercolationVisualizer pv = new PercolationVisualizer();
        while (!perc.percolates()) {
            int len = blockedSitesIndexes.length - 1;
            int value;
            if (len == 0) {
                value = blockedSitesIndexes[0];
                blockedSitesIndexes = pv.delete(blockedSitesIndexes,value);
            }
            else if (len == -1) break;
            else {
                int index = StdRandom.uniform(len);
                value = blockedSitesIndexes[index];
                int i = value / n + 1;
                int j = value % n + 1;
                perc.open(i, j);
                blockedSitesIndexes = pv.delete(blockedSitesIndexes, value);
            }
            System.out.println(len+"    "+blockedSitesIndexes.length + "VALUE:= " + value);
            draw(perc, n);
            StdDraw.show();
            StdDraw.pause(DELAY);
        }

        System.out.println("WHAT A FUCK?"+perc.percolates());
    }
}
