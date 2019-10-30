package ar.edu.itba.sia.gae.methods.selection;

import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Roulette extends SelectionMethod {
    @Override
    public List<GameCharacter> select(List<GameCharacter> population, int size, long generations,
                                      Boolean isBolztmann, int tournamentsM) {
        List<Double> randoms = SelectionHelper.generateNRandomNumbers(size);
        List<Double> cumulatives = SelectionHelper.getCumulativeFitnesses(population, isBolztmann, generations);
        return SelectionHelper.getByRouletteMetodology(randoms, cumulatives, population);
    }
}
