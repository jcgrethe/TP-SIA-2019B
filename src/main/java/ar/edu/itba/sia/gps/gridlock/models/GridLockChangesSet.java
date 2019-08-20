package ar.edu.itba.sia.gps.gridlock.models;

import java.util.LinkedHashSet;

/**
 * A {@link LinkedHashSet} that keeps the newest inserted duplicated removing the old one.
 * Basically, an ordered set without duplicates.
 */
public class GridLockChangesSet<T> extends LinkedHashSet<T> {
    @Override
    public boolean add(T e) {
        // Get rid of old one.
        boolean wasThere = remove(e);
        // Add it.
        super.add(e);
        // Contract is "true if this set did not already contain the specified element"
        return !wasThere;
    }
}
