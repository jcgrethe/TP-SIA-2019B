package ar.edu.itba.sia.gps.gridlock;

import ar.edu.itba.sia.gps.api.Problem;
import ar.edu.itba.sia.gps.api.Rule;
import ar.edu.itba.sia.gps.api.State;

import java.util.List;

public class GridLockProblem implements Problem {
    @Override
    public State getInitState() {
        return null;
    }

    @Override
    public boolean isGoal(State state) {
        return false;
    }

    @Override
    public List<Rule> getRules() {
        return null;
    }
}
