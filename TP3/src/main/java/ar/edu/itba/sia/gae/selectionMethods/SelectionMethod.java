package ar.edu.itba.sia.gae.selectionMethods;


import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.List;

public abstract class SelectionMethod {
    public abstract List<GameCharacter> select(List<GameCharacter> population, int size, long generations);
}
