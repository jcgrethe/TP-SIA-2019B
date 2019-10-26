package ar.edu.itba.sia.gae.crossOverMethods;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class crossOver {

    public abstract List<Character> crossOver(Character c1, Character c2);

    public List<Character> crossSelection(List<Character> selection){
        List<Character> children = new LinkedList<>();
        for (int i = 0; i < selection.size() / 2; i++) {
            List<Character> cross = crossOver(selection.get(2 * i).cl, selection.get((2 * i) + 1));
            children.addAll(cross);
        }
        return children;
    }
}
