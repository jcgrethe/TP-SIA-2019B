package ar.edu.itba.sia.gae.methods.crossover;

import ar.edu.itba.sia.gae.models.CharacterType;
import ar.edu.itba.sia.gae.models.GameCharacter;
import ar.edu.itba.sia.gae.models.ItemType;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class OnePointCross extends CrossOver {
    private final static int QUANTITY_OF_LOCUS = 5;

    @Override
    public List<GameCharacter> crossOver(GameCharacter c1, GameCharacter c2) {
        // dont swap height because we get the same
        int pointCross = ThreadLocalRandom.current().nextInt(0, QUANTITY_OF_LOCUS);
        ItemType[] itemTypes = ItemType.values();
        final GameCharacter c1child = c1.clone();
        final GameCharacter c2child = c2.clone();
        IntStream.range(pointCross, QUANTITY_OF_LOCUS).parallel().forEach(locus -> swapItem(c1child, c2child, itemTypes[locus]));
        return Arrays.asList(c1child,c2child);
    }
}
