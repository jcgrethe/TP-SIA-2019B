package ar.edu.itba.sia.gps.gridlock.models;

import java.awt.geom.Point2D;
import java.util.*;
import java.util.stream.IntStream;

public class GridLockBoard implements Cloneable {
    private int size;
    private int[][] board;
    private HashMap<Integer, GridLockPiece> pieces;

    public static int BLANK_MARK = 0;

    /**
     * Constructor for the Initial Board
     *
     * @param size
     */
    public GridLockBoard(int size) {
        this.size = size;
        this.board = new int[size][size];
        this.pieces = new HashMap<>();
        // TODO: Set distribution of pieces

    }

    public GridLockBoard(int[][] board, HashMap<Integer, GridLockPiece> pieces) {
        this.board = board;
        this.size = board.length;
        this.pieces = pieces;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        HashMap<Integer, GridLockPiece> clonedMap = new HashMap<>();
        this.pieces.entrySet().forEach(entry -> {
            Object clonedPiece;
            try {
                clonedPiece = entry.getValue().clone();
                if (!(clonedPiece instanceof GridLockPiece)){
                    throw new CloneNotSupportedException();
                }
            } catch (CloneNotSupportedException e){
                throw new IllegalStateException("Could not clone Pieces Map");
            }
            clonedMap.put(entry.getKey(), (GridLockPiece) clonedPiece);
        });
        return new GridLockBoard(this.board.clone(), clonedMap);
    }

    public int getSize() {
        return size;
    }

    public int getCell(int x, int y){
        return this.board[y][x];
    }

    public void setCell(int x, int y, int value){
        this.board[y][x] = value;
    }

    public GridLockPiece getPiece(int id){
        return pieces.get(id);
    }

}
