package ar.edu.itba.sia.gae.methods.selection;

import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.*;
import java.util.stream.IntStream;

public class Universal extends SelectionMethod {
    @Override
    public List<GameCharacter> select(List<GameCharacter> population, int k, long generations) {
        List<Double> cumulative = SelectionHelper.getCumulativeFitnesses(population);
        List<Double> randoms = getUniversalRandom(k);
        return SelectionHelper.getByRouletteMetodology(randoms, cumulative, population);
    }

    private List<Double> getUniversalRandom(int k){
        double r = new Random().nextDouble();
        final List<Double> rs = new LinkedList<>();
        IntStream.range(1, k + 1).forEach(j -> rs.add((r + j - 1)/k));
        return rs;
    }
}
