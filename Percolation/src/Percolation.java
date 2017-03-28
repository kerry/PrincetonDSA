import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import java.io.BufferedReader;
import java.io.FileReader;

public class Percolation {

    private int[][] grid;
    private int n;
    private int nn;
    private int count = 0;
    private WeightedQuickUnionUF wu;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n cannot be less than 1");
        }
        this.n = n;
        this.nn = n*n;
        this.wu = new WeightedQuickUnionUF(nn+2);
        grid = new int[n][n];
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 0;
            }
        }
    }
    public void open(int row, int col) {
        this.throwErrorIfInputInvalid(row, col);
        if (isOpen(row,col)) {
            return;
        }
        count++;
        int index = this.normalize(row, col);
        grid[row-1][col-1] = 1;
        if (row == 1) {
            wu.union(index,0);
            if (row != n) {
                this.unionIfOpen(row+1,col,index);
            } else {
                wu.union(index, nn+1);
            }
        } else if (row == n) {
//            wu.union(index, nn+1);
            this.unionIfOpen(row-1,col,index);
            if (isFull(row, col)) wu.union(index, nn+1);
        } else {
            this.unionIfOpen(row+1,col,index);
            this.unionIfOpen(row-1,col,index);
            if (isOpen(row+1, col)) wu.union(index, nn+1);
        }
        if (col == 1) {
            if (col != n) this.unionIfOpen(row,col+1,index);
        } else if (col == n) {
            this.unionIfOpen(row,col-1,index);
        } else {
            this.unionIfOpen(row,col+1,index);
            this.unionIfOpen(row,col-1,index);
        }
    }
    public boolean isOpen(int row, int col) {
        this.throwErrorIfInputInvalid(row, col);
        int value = grid[row-1][col-1];
        return (value == 1);
    }
    public boolean isFull(int row, int col) {
        this.throwErrorIfInputInvalid(row, col);
        int index = this.normalize(row,col);
        return wu.connected(0,index);
    }
    public int numberOfOpenSites() {
        return count;
    }
    public boolean percolates() {
        return wu.connected(0,nn+1);
    }
    private void throwErrorIfInputInvalid(int row, int col) {
        if (row > n || row < 1 || col > n || col < 1) {
            throw new IndexOutOfBoundsException("Invalid argument");
        }
    }
    private int normalize(int row, int col) {
        return n*(row-1) + col;
    }
    private void unionIfOpen(int row, int col, int parent) {
        if (isOpen(row,col)) {
            int neighbour = this.normalize(row,col);
            wu.union(parent, neighbour);
        }
    }
    public static void main(String[] args) {
        int seed;
        Percolation p;
        String filename = "/Users/prateekgrover/Downloads/percolation/input3.txt";
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            line = reader.readLine();
            seed = Integer.parseInt(line.replaceAll("\n",""));
            p = new Percolation(seed);
            while ((line = reader.readLine()) != null)
            {
                String[] indices = line.trim().split("\\s+");
                if (indices.length < 2) {
                    break;
                }
                p.open(Integer.parseInt(indices[0]), Integer.parseInt(indices[1]));
                p.isOpen(Integer.parseInt(indices[0]), Integer.parseInt(indices[1]));
                p.percolates();
                p.numberOfOpenSites();
                p.isFull(Integer.parseInt(indices[0]), Integer.parseInt(indices[1]));
            }
            reader.close();
            StdOut.print(p.percolates());
            StdOut.print(p.numberOfOpenSites());
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
        }

    }
}