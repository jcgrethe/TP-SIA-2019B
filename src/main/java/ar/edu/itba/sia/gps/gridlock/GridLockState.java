package ar.edu.itba.sia.gps.gridlock;

import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.gps.gridlock.models.GridLockBoard;
import ar.edu.itba.sia.gps.gridlock.models.GridLockPiece;

public class GridLockState implements State, Cloneable {

    private GridLockBoard board;
    private GridLockPiece pieceToMove;

    public GridLockState(int size) {
        this.board = new GridLockBoard(size);
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
        StringBuilder sb = new StringBuilder();
        for (int x = 0 ; x < board.getSize() ; x++ ){
            for (int y = 0 ; y < board.getSize() ; y++){
                sb.append(board.getCell(x, y));
            }
        }
        return sb.toString();
    }

    public GridLockBoard getBoard() {
        return board;
    }
}
