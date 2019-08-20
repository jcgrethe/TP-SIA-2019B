package ar.edu.itba.sia.gps.gridlock.models;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class GridLockBoard {
    private int size;
    private int[][] board;

    public GridLockBoard(int size) {
        this.size = size;
        this.board = new int[size][size];
        // TODO: Set distribution of pieces


    }

    public GridLockBoard(GridLockBoard initialBoard, GridLockChangesSet<GridLockChange> changes){
        this.board = new int[initialBoard.size][initialBoard.size];
        this.size = initialBoard.size;
        for (int i = 0 ; i < size ; i++)
            for (int j = 0 ; j < size ; j++)
                this.board[i][j] = initialBoard.getCell(i, j);
        changes.forEach(this::applyChange);
    }

    private void applyChange(GridLockChange change){
        this.board[change.getI()][change.getJ()] = change.getValue();
    }

    public int getCell(int i, int j){
        return this.board[i][j];
    }
}
