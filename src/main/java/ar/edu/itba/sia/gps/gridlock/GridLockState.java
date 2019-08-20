package ar.edu.itba.sia.gps.gridlock;

import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.gps.gridlock.models.GridLockBoard;
import ar.edu.itba.sia.gps.gridlock.models.GridLockChange;
import ar.edu.itba.sia.gps.gridlock.models.GridLockChangesSet;

import java.util.LinkedHashSet;
import java.util.List;

public class GridLockState implements State {

    private GridLockChangesSet<GridLockChange> changes;
    private GridLockBoard initialBoard;

    @Override
    public String getRepresentation() {
        return null;
    }

    /**
     * Applies changes in order to return the board of this state.
     *
     * @return  a new {@link GridLockBoard} instance with the changes applied.
     */
    public GridLockBoard getBoard(){
        return new GridLockBoard(initialBoard, changes);
    }
}
