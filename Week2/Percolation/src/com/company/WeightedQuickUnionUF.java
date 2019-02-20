package com.company;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class WeightedQuickUnionUF {
    public int[] parent;
    private int[] size;
    private int count;

    public WeightedQuickUnionUF(int n) {
        this.count = n;
        this.parent = new int[n];
        this.size = new int[n];

        for(int i = 0; i < n; ++i) {
            this.parent[i] = i;
            this.size[i] = 1;
        }

    }

    public int count() {
        return this.count;
    }

    public int find(int p) {
        this.validate(p);

        while(p != this.parent[p]) {
            p = this.parent[p];
        }

        return p;
    }

    private void validate(int p) {
        int n = this.parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n - 1));
        }
    }

    public boolean connected(int p, int q) {
        return this.find(p) == this.find(q);
    }

    public void union(int p, int q) {
        int rootP = this.find(p);
        int rootQ = this.find(q);
        if (rootP != rootQ) {
            if (this.size[rootP] < this.size[rootQ]) {
                this.parent[rootP] = rootQ;
                this.size[rootQ] += this.size[rootP];
            } else {
                this.parent[rootQ] = rootP;
                this.size[rootP] += this.size[rootQ];
            }

            --this.count;
        }
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        edu.princeton.cs.algs4.WeightedQuickUnionUF uf = new edu.princeton.cs.algs4.WeightedQuickUnionUF(n);

        while(!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (!uf.connected(p, q)) {
                uf.union(p, q);
                StdOut.println(p + " " + q);
            }
        }

        StdOut.println(uf.count() + " components");
    }
}