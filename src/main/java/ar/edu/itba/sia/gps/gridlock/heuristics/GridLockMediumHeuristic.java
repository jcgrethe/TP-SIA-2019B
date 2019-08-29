package ar.edu.itba.sia.gps.gridlock.heuristics;

import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.gps.gridlock.GridLockState;
import ar.edu.itba.sia.gps.gridlock.models.GridLockBoard;
import ar.edu.itba.sia.gps.gridlock.models.GridLockPiece;

import java.util.stream.IntStream;

/**
 * The {@link GridLockMediumHeuristic} is calculated based on the quantity of empty cells in the path to the goal.
 */
public class GridLockMediumHeuristic implements Heuristic {

    @Override
    public Integer getValue(State state) {
        if (!(state instanceof GridLockState)){
            throw new IllegalStateException("Not a GridLockState");
        }
        GridLockState GLState = (GridLockState) state;
        GridLockBoard board = GLState.getBoard();
        GridLockPiece mainPiece = board.getPiece(GridLockBoard.MAIN_PIECE_ID);
        return IntStream.range(mainPiece.getX() + mainPiece.getSize(), board.getSize()).
                filter(x -> board.getCell(x, board.getSize() / 2) != GridLockBoard.BLANK_MARK).
                reduce(Integer::sum)
                .orElse(0);
    }
}
