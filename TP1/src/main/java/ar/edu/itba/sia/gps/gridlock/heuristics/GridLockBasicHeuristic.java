package ar.edu.itba.sia.gps.gridlock.heuristics;

import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.gps.gridlock.GridLockState;
import ar.edu.itba.sia.gps.gridlock.models.GridLockBoard;
import ar.edu.itba.sia.gps.gridlock.models.GridLockPiece;

/**
 * The {@link GridLockBasicHeuristic} is calculated based on the quantity of cells away from the goal.
 */
public class GridLockBasicHeuristic implements Heuristic {
    @Override
    public Integer getValue(State state) {
        if (!(state instanceof GridLockState)){
            throw new IllegalStateException("Not a GridLockState");
        }
        GridLockState GLState = (GridLockState) state;
        GridLockBoard board = GLState.getBoard();
        GridLockPiece mainPiece = board.getPiece(GridLockBoard.MAIN_PIECE_ID);
        return board.getSize() - (mainPiece.getX() + mainPiece.getSize());
    }
}
