package ar.edu.itba.sia.gae.methods.finish;

import ar.edu.itba.sia.gae.helpers.Configuration;
import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.List;

public interface Finished {
    boolean isFinished(long generation, List<GameCharacter> population, Configuration configuration);
    String toString(Configuration configuration);
}
