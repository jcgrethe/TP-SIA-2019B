package ar.edu.itba.sia.gae.methods.selection;

import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class TournamentsDeterministic extends SelectionMethod {

    @Override
    public List<GameCharacter> select(List<GameCharacter> population, int k,
                                      long generations, Boolean isBolztmann, int tournamentsM) {
        Integer available = population.size();
        final List<GameCharacter> selection = new LinkedList<>();
        IntStream.range(0, k).forEach(ki -> {
            List<GameCharacter> currentTournament = new LinkedList<>();
            IntStream.range(0, tournamentsM).forEach(mi -> currentTournament.add(
                    population.get(ThreadLocalRandom.current().nextInt(available))));
            currentTournament.sort(Collections.reverseOrder());
            selection.add(currentTournament.get(0));
        });
        return new LinkedList<>(selection);
    }
}
