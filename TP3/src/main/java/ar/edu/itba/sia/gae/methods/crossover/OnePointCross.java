package ar.edu.itba.sia.gae.methods.crossover;

import ar.edu.itba.sia.gae.models.GameCharacter;
import ar.edu.itba.sia.gae.models.ItemType;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class OnePointCross extends CrossOver {

    @Override
    public List<GameCharacter> crossOver(GameCharacter c1, GameCharacter c2) {
        // dont swap height because we get the same
        int pointCross = ThreadLocalRandom.current().nextInt(0,4);
        ItemType[] items = ItemType.values();
        for(int i = 0; i <= pointCross; i++ ){
            swapItem(c1,c2,items[i]);
        }
        return Arrays.asList(c1,c2);
    }
}
