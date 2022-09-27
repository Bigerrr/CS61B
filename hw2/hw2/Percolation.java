package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// 虚拟节点降低操作的时间复杂度
public class Percolation {
    private int[][] grid;
    private int openCount;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufExcludeBottom; // avoid backwash
    private int[] dx = {-1, 0, 1, 0};
    private int[] dy = {0, 1, 0, -1};
    private int top;
    private int bottom;
    public int size;
    private int xyTo1D(int x, int y) {
        return x * size + y;
    }
    private boolean outOfRange(int x, int y) {
        return !(x >= 0 && x < size && y >= 0 && y < size);
    }
    public Percolation(int N) {            // create N-by-N grid, with all sites initially blocked
        if (N < 0) {
            throw new IllegalArgumentException();
        }
        grid = new int[N][N];
        openCount = 0;
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufExcludeBottom = new WeightedQuickUnionUF(N * N + 1);
        this.size = N;
        top = N * N;
        bottom = N * N + 1;
        for(int i = 0; i < N; i++) {
            uf.union(top, xyTo1D(0, i));
            ufExcludeBottom.union(top, xyTo1D(0, i));
            uf.union(bottom, xyTo1D(N-1, i));
        }
    }

    public static void main(String[] args) {   // use for unit testing (not required, but keep this here for the autograder)
    }

    public void open(int row, int col) {       // open the site (row, col) if it is not open already
        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = 1;
        openCount++;
        for (int i = 0; i < 4; i++) {
            int newX = row + dx[i], newY = col + dy[i];
            if(outOfRange(newX, newY)) {
                continue;
            }
            if(isOpen(newX, newY)) {
                uf.union(xyTo1D(row, col), xyTo1D(newX, newY));
                ufExcludeBottom.union(xyTo1D(row,col), xyTo1D(newX, newY));
            }
        }
    }

    public boolean isOpen(int row, int col) { // is the site (row, col) open?
        if(outOfRange(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row][col] == 1;
    }

    public boolean isFull(int row, int col) {  // is the site (row, col) full?
        if(outOfRange(row, col)) {
            throw new IndexOutOfBoundsException();
        }
        if(!isOpen(row, col)) {
            return false;
        }
        return ufExcludeBottom.connected(top, xyTo1D(row, col));
    }

    public int numberOfOpenSites() {           // number of open sites
        return openCount;
    }

    public boolean percolates() {              // does the system percolate?
        return uf.connected(top, bottom);
    }
}
