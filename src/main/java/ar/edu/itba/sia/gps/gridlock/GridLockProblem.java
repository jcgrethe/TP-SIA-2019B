package ar.edu.itba.sia.gps.gridlock;

import ar.edu.itba.sia.gps.api.Problem;
import ar.edu.itba.sia.gps.api.Rule;
import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.gps.gridlock.models.GridLockBoard;
import ar.edu.itba.sia.gps.gridlock.models.GridLockPieceDirection;
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

    public GridLockProblem() {
        GridLockState initialState = new GridLockState(boardSize);
        initialState.getBoard().getPieces().forEach(piece -> {
            rules.add(new GLMoveRightRule(piece));
            rules.add(new GLMoveLeftRule(piece));
            rules.add(new GLMoveUpRule(piece));
            rules.add(new GLMoveDownRule(piece));
        });
        this.initState = initialState;
    }

    public GridLockProblem(String inputFile) {
        GridLockState initialState = new GridLockState(inputFile);
        initialState.getBoard().getPieces().forEach(piece -> {
            if (piece.getDirection() == GridLockPieceDirection.HORIZONTAL){
                rules.add(new GLMoveRightRule(piece));
                rules.add(new GLMoveLeftRule(piece));
            } else {
                rules.add(new GLMoveUpRule(piece));
                rules.add(new GLMoveDownRule(piece));
            }
        });
        this.initState = initialState;
    }

    @Override
    public State getInitState() {
        return this.initState;
    }

    @Override
    public boolean isGoal(State state) {
        if (!(state instanceof GridLockState))
            throw new IllegalStateException();
        return ((GridLockState) state).getSolution();
    }

    @Override
    public List<Rule> getRules() {
        return rules;
    }
}
