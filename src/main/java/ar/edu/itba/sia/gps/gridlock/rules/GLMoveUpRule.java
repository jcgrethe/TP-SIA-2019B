package ar.edu.itba.sia.gps.gridlock.rules;

import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.gps.gridlock.GridLockState;
import ar.edu.itba.sia.gps.gridlock.models.GridLockBoard;
import ar.edu.itba.sia.gps.gridlock.models.GridLockPiece;
import ar.edu.itba.sia.gps.gridlock.models.GridLockPieceDirection;
import javafx.util.Pair;

import javax.swing.text.html.Option;
import java.util.Optional;

/**
 * Created by eric on 15/03/17.
 */
public class GLMoveUpRule extends GLMoveRule {

    private GridLockPiece piece;

    public GLMoveUpRule(GridLockPiece piece) {
        this.piece = piece;
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
                || piece.getY() == 0
                || board.getCell(piece.getX(), piece.getY() - 1) != 0){
            return Optional.empty();
        }
        return super.apply(state, piece, new Pair(piece.getX(), piece.getY() - 1));
    }
}
