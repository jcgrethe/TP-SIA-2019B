package ar.edu.itba.sia.gae.selectionMethods;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Elite extends SelectionMethod {


    /*
        Selects the firsts with better fitness.
     */
    @Override
    public List<Character> select(List<Character> population, int size, long generations) {
        List<Character> copy = new LinkedList<>(population);
        copy.sort(Collections.reverseOrder());
        return copy.subList(0, size);
    }
}
