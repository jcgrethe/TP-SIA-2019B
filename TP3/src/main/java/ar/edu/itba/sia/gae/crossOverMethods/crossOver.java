package ar.edu.itba.sia.gae.crossOverMethods;

import ar.edu.itba.sia.gae.models.GameCharacter;
import java.util.LinkedList;
import java.util.List;

public abstract class crossOver {

    public abstract List<GameCharacter> crossOver(GameCharacter c1, GameCharacter c2);

    public List<GameCharacter> crossSelection(List<GameCharacter> selection){
        List<GameCharacter> children = new LinkedList<>();
        for (int i = 0; i < selection.size() / 2; i++) {
            List<GameCharacter> cross = crossOver(selection.get(2 * i), selection.get((2 * i) + 1));
            children.addAll(cross);
        }
        return children;
    }
}
