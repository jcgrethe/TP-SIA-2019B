package ar.edu.itba.sia.gae.methods.selection;

import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

public class Ranking extends SelectionMethod{
    @Override
    public List<GameCharacter> select(List<GameCharacter> population, int size, long generations, Boolean isBolztmann, int tournamentsM) {
        List<GameCharacter> copy = new LinkedList<>(population);
        copy.sort(Collections.reverseOrder());

        List<Double> randoms = SelectionHelper.generateNRandomNumbers(size);
        List<Double> cumulatives = getCumulativeByIndex(copy);
        return SelectionHelper.getByRouletteMetodology(randoms, cumulatives, copy);
    }

    private List<Double> getCumulativeByIndex(List<GameCharacter> population){
        Integer available = population.size();
        final List<Double> cummulatives = new LinkedList<>();
        IntStream.range(0, population.size()).forEach(i -> {
            cummulatives.add((double) (available - i));
        });
        final Double cummulative = cummulatives.get(cummulatives.size() - 1);
        cummulatives.forEach(c -> c /= cummulative);
        return cummulatives;
    }
}
