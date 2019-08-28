package ar.edu.itba.sia.gps.walk.rules;

import ar.edu.itba.sia.gps.Pair;
import ar.edu.itba.sia.gps.api.Rule;
import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.gps.walk.WalkingState;

import java.util.Optional;

public class WalkingMoveStraightRule extends WalkingMoveRule {

    public WalkingMoveStraightRule(Pair step) {
        super(step);
    }

    @Override
    public Integer getCost() {
        return 1;
    }

    @Override
    protected boolean controlStep() {
        return Math.abs(step.getX() + step.getY()) == 1;
    }

    @Override
    public String getName() {
        return "Straight " + step;
    }
}
