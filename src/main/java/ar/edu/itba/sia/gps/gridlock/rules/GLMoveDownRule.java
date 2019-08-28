package ar.edu.itba.sia.gps.gridlock.rules;

import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.gps.gridlock.GridLockState;
import ar.edu.itba.sia.gps.gridlock.models.GLMoveDirection;
import ar.edu.itba.sia.gps.gridlock.models.GridLockBoard;
import ar.edu.itba.sia.gps.gridlock.models.GridLockPiece;
import ar.edu.itba.sia.gps.gridlock.models.GridLockPieceDirection;

import java.util.Optional;

/**
 * Created by eric on 15/03/17.
 */
public class GLMoveDownRule extends GLMoveRule {

    private GridLockPiece piece;

    public GLMoveDownRule() {
        this.piece = null;
    }

    @Override
    public String getName() {
        return "Move Up";
    }

    @Override
    public Optional<State> apply(State state) {
        if (!(state instanceof GridLockState))
            throw new IllegalStateException("Invalid state class");
        GridLockState GLState = (GridLockState) state;
        GridLockBoard board = GLState.getBoard();
        if (piece.getDirection() == GridLockPieceDirection.HORIZONTAL
                || piece.getY() == board.getSize() - 1
                || board.getCell(piece.getX(), piece.getY() + 1) != 0){
            return Optional.empty();
        }
        return super.apply(state, piece, GLMoveDirection.DOWN);
    }
}
