package ar.edu.itba.sia.gae.methods.selection;

import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Elite extends SelectionMethod {


    /*
        Selects the firsts with better fitness.
     */
    @Override
    public List<GameCharacter> select(List<GameCharacter> population, int k,
                                      long generations, Boolean isBolztmann, int tournamentsM) {
        List<GameCharacter> copy = new LinkedList<>(population);
        Collections.sort(copy, Collections.reverseOrder());
        return copy.subList(0, k);
    }
}
