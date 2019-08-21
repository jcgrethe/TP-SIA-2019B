package ar.edu.itba.sia.gps.gridlock.rules;

import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.gps.gridlock.GridLockState;
import ar.edu.itba.sia.gps.gridlock.models.GridLockBoard;
import ar.edu.itba.sia.gps.gridlock.models.GridLockPiece;
import ar.edu.itba.sia.gps.gridlock.models.GridLockPieceDirection;
import javafx.util.Pair;

import java.util.Optional;

public class GLMoveLeftRule extends GLMoveRule {

    private GridLockPiece piece;

    public GLMoveLeftRule(GridLockPiece piece) {
        this.piece = piece;
    }

    @Override
    public String getName() {
        return "Move Right";
    }

    @Override
    public Optional<State> apply(State state) {
        if (!(state instanceof GridLockState))
            throw new IllegalStateException("Invalid state class");
        GridLockState GLState = (GridLockState) state;
        GridLockBoard board = GLState.getBoard();
        if (piece.getDirection() == GridLockPieceDirection.VERTICAL
                || piece.getX() == 0
                || board.getCell(piece.getX() - 1, piece.getY()) != 0){
            return Optional.empty();
        }
        return super.apply(state, piece, new Pair(piece.getX() - 1, piece.getY()));
    }
}
