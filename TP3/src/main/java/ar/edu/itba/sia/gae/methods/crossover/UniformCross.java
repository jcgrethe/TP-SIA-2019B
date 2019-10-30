package ar.edu.itba.sia.gae.methods.crossover;

import ar.edu.itba.sia.gae.models.GameCharacter;
import ar.edu.itba.sia.gae.models.ItemType;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class UniformCross extends CrossOver {


    @Override
    public List<GameCharacter> crossOver(GameCharacter c1, GameCharacter c2) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        ItemType[] itemTypes = ItemType.values();
        final GameCharacter c1child = c1.clone();
        final GameCharacter c2child = c2.clone();
        for (int i = 0; i<= 4; i++){
            if(random.nextDouble(0,1) >= 0.5)
                swapItem(c1child, c2child, itemTypes[i]);
        }
        if(random.nextDouble(0,1) >= 0.5)
            swapHeight(c1child,c2child);
        return Arrays.asList(c1child,c2child);
    }
}
