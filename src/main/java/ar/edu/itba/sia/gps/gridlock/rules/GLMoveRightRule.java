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
public class GLMoveRightRule extends GLMoveRule {

    private GridLockPiece piece;

    public GLMoveRightRule(GridLockPiece piece) {
        this.piece = piece;
    }

    @Override
    public String getName() {
        return "Move Right for piece with id " + piece.getId() ;
    }

    @Override
    public Optional<State> apply(State state) {
        if (!(state instanceof GridLockState))
            throw new IllegalStateException("Invalid state class");
        GridLockState GLState = (GridLockState) state;
        GridLockBoard board = GLState.getBoard();
        if (piece.getDirection() == GridLockPieceDirection.VERTICAL){
            return Optional.empty();
        }
        return super.apply(state, piece, GLMoveDirection.RIGHT);
    }
}
