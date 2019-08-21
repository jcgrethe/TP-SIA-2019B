package ar.edu.itba.sia.gps.gridlock.models;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class GridLockBoard implements Cloneable {
    private int size;
    private int[][] board;

    /**
     * Constructor for the Initial Board
     *
     * @param size
     */
    public GridLockBoard(int size) {
        this.size = size;
        this.board = new int[size][size];
        // TODO: Set distribution of pieces

    }

    public GridLockBoard(int[][] board) {
        this.board = board;
        this.size = board.length;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new GridLockBoard(this.board.clone());
    }

    public int getSize() {
        return size;
    }

    public int getCell(int i, int j){
        return this.board[i][j];
    }
}
