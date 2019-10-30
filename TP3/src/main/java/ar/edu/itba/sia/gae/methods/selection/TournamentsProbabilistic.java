package ar.edu.itba.sia.gae.methods.selection;

import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class TournamentsProbabilistic extends SelectionMethod {

    @Override
    public List<GameCharacter> select(List<GameCharacter> population, int k,
                                      long generations, Boolean isBolztmann, int tournamentsM) {
        Integer available = population.size();
        final List<GameCharacter> selection = new LinkedList<>();
        IntStream.range(0, k).forEach(ki -> {
            GameCharacter competitor1 = population.get(ThreadLocalRandom.current().nextInt(available));
            GameCharacter competitor2;
            do{
                competitor2 = population.get(ThreadLocalRandom.current().nextInt(available));
            } while (competitor1.equals(competitor2));
            if (ThreadLocalRandom.current().nextDouble() < 0.75){
                selection.add((competitor1.getFitness() > competitor2.getFitness())?competitor1:competitor2);
            } else {
                selection.add((competitor1.getFitness() > competitor2.getFitness())?competitor2:competitor1);
            }
        });
        return selection;
    }
}
