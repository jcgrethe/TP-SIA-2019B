package ar.edu.itba.sia.gae.replacementMethods;

import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.List;

public abstract class ReplacementMethod {
    abstract List<GameCharacter> replace(List<GameCharacter> population);
}
