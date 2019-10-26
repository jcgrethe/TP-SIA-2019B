package ar.edu.itba.sia.gae.selectionMethods;

import java.util.List;

public abstract class SelectionMethod {
    public abstract List<Character> select(List<Character> population, int size, long generations);
}
