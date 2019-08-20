package ar.edu.itba.sia.gps.gridlock;

import ar.edu.itba.sia.gps.api.Rule;
import ar.edu.itba.sia.gps.api.State;

import java.util.Optional;

public class GridLockRule implements Rule {
    @Override
    public Integer getCost() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Optional<State> apply(State state) {
        return Optional.empty();
    }
}
