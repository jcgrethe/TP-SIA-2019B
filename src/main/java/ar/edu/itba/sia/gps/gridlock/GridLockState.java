package ar.edu.itba.sia.gps.gridlock;

import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.gps.gridlock.models.GridLockBoard;
import ar.edu.itba.sia.gps.gridlock.models.GridLockPiece;

public class GridLockState implements State, Cloneable {

    private GridLockBoard board;

    public GridLockState(GridLockState state) {

    }

    public GridLockState(GridLockBoard board) {
        this.board = board;
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
        return null;
    }

    public GridLockBoard getBoard() {
        return board;
    }
}
