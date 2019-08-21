package ar.edu.itba.sia.gps.gridlock.rules;


import ar.edu.itba.sia.gps.api.Rule;
import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.gps.gridlock.GridLockState;
import ar.edu.itba.sia.gps.gridlock.models.GridLockPiece;
import javafx.util.Pair;

import java.util.Optional;

/**
 * Created by eric on 15/03/17.
 */
public abstract class GLMoveRule implements Rule {

    @Override
    public Integer getCost() {
        return 1;
    }

    public Optional<State> apply(State state, GridLockPiece piece, Pair newPosition){
        if (!(state instanceof GridLockState))
            throw new IllegalStateException("Invalid state class");
        GridLockState GLState = (GridLockState) state;
        try{
            Object potentialState = GLState.clone();
            if (!(potentialState instanceof GridLockState)){
                throw new CloneNotSupportedException();
            }
            GridLockState newState = (GridLockState) potentialState;

            // TODO: MOVE PIECE (NEED TO SAVE PIECES POSTITIONS ETC)

            return Optional.of(newState);
        } catch (CloneNotSupportedException e){
            throw new IllegalStateException("There was some problem cloning the state.");
        }
    }
}
