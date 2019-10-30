package ar.edu.itba.sia.gae.methods.crossover;

import ar.edu.itba.sia.gae.models.GameCharacter;
import ar.edu.itba.sia.gae.models.ItemType;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class TwoPointCross extends CrossOver {
    private final static int QUANTITY_OF_LOCUS = 4;

    @Override
    public List<GameCharacter> crossOver(GameCharacter c1, GameCharacter c2) {
        int aPointCross = ThreadLocalRandom.current().nextInt(0, QUANTITY_OF_LOCUS);
        int bPointCross = ThreadLocalRandom.current().nextInt(aPointCross, QUANTITY_OF_LOCUS+1);
        ItemType[] itemTypes = ItemType.values();
        final GameCharacter c1child = c1.clone();
        final GameCharacter c2child = c2.clone();
        for (int i = aPointCross; i<= bPointCross; i++){
            if(i!=5)
                swapItem(c1child, c2child, itemTypes[i]);
            else
                swapHeight(c1child,c2child);
        }
        return Arrays.asList(c1child,c2child);
    }
}
