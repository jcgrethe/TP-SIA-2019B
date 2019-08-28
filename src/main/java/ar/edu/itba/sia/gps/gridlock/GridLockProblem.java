package ar.edu.itba.sia.gps.gridlock;

import ar.edu.itba.sia.gps.api.Problem;
import ar.edu.itba.sia.gps.api.Rule;
import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.gps.gridlock.models.GridLockBoard;
import ar.edu.itba.sia.gps.gridlock.rules.GLMoveDownRule;
import ar.edu.itba.sia.gps.gridlock.rules.GLMoveLeftRule;
import ar.edu.itba.sia.gps.gridlock.rules.GLMoveRightRule;
import ar.edu.itba.sia.gps.gridlock.rules.GLMoveUpRule;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GridLockProblem implements Problem {
    private State initState;

    private static List<Rule> rules = new LinkedList<>();
    private final static int boardSize = 5;

    @Override
    public State getInitState() {
        GridLockState initialState = new GridLockState(boardSize);
        initialState.getBoard().getPieces().forEach(piece -> {
            rules.add(new GLMoveRightRule(piece));
            rules.add(new GLMoveLeftRule(piece));
            rules.add(new GLMoveUpRule(piece));
            rules.add(new GLMoveDownRule(piece));
        });
        return initState;
    }

    @Override
    public boolean isGoal(State state) {
        return !state.getRepresentation().contains(GridLockBoard.MAIN_PIECE_ID.toString());
    }

    @Override
    public List<Rule> getRules() {
        return rules;
    }
}
