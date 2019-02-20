


import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation
{
    private final int n;

    private boolean[][] sites;
    private final WeightedQuickUnionUF virtual;
    private int openSitesNum;
    public Percolation(int n)
    {
        if (n <= 0){
            throw new IllegalArgumentException("N IS FUCKING ZERO");
        }
        this.n = n;
        openSitesNum = 0;

        sites = new boolean[n][n];
        virtual = new WeightedQuickUnionUF(n*n + 2);
        openSitesNum = 0;
        for (int i = 0; i < n; i++)
        {
           for (int j = 0; j < n; j++)
           {
               sites[i][j] = false;
           }
        }
    }



    public void open(int row, int col)
    {

        if (!check(row,col)){
            throw new IllegalArgumentException(row + " " + col);
        }
        if(isOpen(row,col))return;
        if(n == 1){
            virtual.union(0,1);
            virtual.union(0,2);
            sites[row-1][col-1] = true;
            openSitesNum++;
            return;
        }
        //int elem = grid[row-1][col-1];
        int elem = (row -1)*n + col - 1;
        if ( (elem < n) )
        {
            virtual.union(elem,n * n);
        }else if ( elem >= (n * (n - 1)))
        {
            virtual.union(elem, n*n+1);
        }

        if (elem - 1 >= 0 )
        {
            if (col - 1 >= 1)
            {
                if (isOpen(row,col-1))virtual.union(elem, elem - 1);
            }
        }
        if (elem - n >= 0 )
        {
            if (row - 1 >= 1)
            {
                if (isOpen(row - 1 ,col))virtual.union(elem, elem - n);
            }
        }
        if (elem + 1 < n*n )
        {
            if (col + 1 <= n)
            {
                if (isOpen(row,col+1))virtual.union(elem, elem + 1);
            }
        }
        if (elem + n < n*n )
        {
            if (row + 1 <= n)
            {
                if (isOpen(row + 1 ,col))virtual.union(elem, elem + n);
            }
        }
        sites[row-1][col-1] = true;
        openSitesNum++;
    }
    public boolean isOpen(int row, int col)
    {
        if (!check(row,col))
        {
            throw new IllegalArgumentException(row + " " + col);
        }
        return sites[row-1][col-1];
    }

    private boolean check(int row, int col)
    {
        if (row < 1)
        {
            return false;
        }
        if (row > n)
        {
            return false;
        }
        if (col < 1)
        {
            return false;
        }
        if (col > n)
        {
            return false;
        }
        return true;
    }

    public  int numberOfOpenSites()
    {
        return openSitesNum;
    }

    public boolean isFull(int row, int col){
        if (!check(row,col))
        {
            throw new IllegalArgumentException(row + " " + col);
        }
        int elem = (row -1)*n + col - 1;
        return isOpen(row,col) && virtual.connected(elem,n*n);
    }

    public boolean percolates(){
        return virtual.connected(n*n, n*n + 1);
    }
}
