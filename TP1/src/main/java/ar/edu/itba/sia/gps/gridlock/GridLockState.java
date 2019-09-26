package ar.edu.itba.sia.gps.gridlock;

import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.gps.gridlock.models.GridLockBoard;
import ar.edu.itba.sia.gps.gridlock.models.GridLockBoardFactory;
import ar.edu.itba.sia.gps.gridlock.models.GridLockPiece;

import java.util.Objects;

public class GridLockState implements State, Cloneable {

    private GridLockBoard board;
    private Boolean solution = false;

    public GridLockState(int size) {
        this.board = new GridLockBoard(size);
    }

    public GridLockState(GridLockBoard board) {
        this.board = board;
    }

    public GridLockState(String inputFile) {
        this.board = GridLockBoardFactory.generateBoardFromFile(inputFile);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Object board = this.board.clone();
        if (!(board instanceof GridLockBoard))
            throw new CloneNotSupportedException();
        return new GridLockState((GridLockBoard) board);
    }

    @Override
    public String getRepresentation() {
        StringBuilder sb = new StringBuilder();
        for (int x = 0 ; x < board.getSize() ; x++ ){
            for (int y = 0 ; y < board.getSize() ; y++){
                if (board.getCell(x, y) == 0){
                    sb.append("  . ");
                } else {
                    sb.append(String.format(" %02d ", board.getCell(x, y)).replace(" 0", "  "));
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public GridLockBoard getBoard() {
        return board;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridLockState that = (GridLockState) o;
        return board.equals(that.board);
    }

    @Override
    public int hashCode() {
        return board.hashCode();
    }

    public Boolean getSolution() {
        return solution;
    }

    public void setSolution(Boolean solution) {
        this.solution = solution;
    }
}
