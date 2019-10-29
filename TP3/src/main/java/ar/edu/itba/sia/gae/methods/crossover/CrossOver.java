package ar.edu.itba.sia.gae.methods.crossover;

import ar.edu.itba.sia.gae.models.GameCharacter;
import ar.edu.itba.sia.gae.models.Item;
import ar.edu.itba.sia.gae.models.ItemType;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public abstract class CrossOver {

    public abstract List<GameCharacter> crossOver(GameCharacter c1, GameCharacter c2);

    public List<GameCharacter> crossSelection(List<GameCharacter> selection){
        List<GameCharacter> children = new LinkedList<>();
        Collections.shuffle(selection);
        int size = selection.size();
        for (int i = 0; i < size / 2; i++) {
            List<GameCharacter> cross = crossOver(selection.get(i).clone(), selection.get(size - i - 1).clone());
            children.addAll(cross);
        }
        return children;
    }

    void swapItem(GameCharacter c1, GameCharacter c2, ItemType type){
        Item aux = c1.getItem(type);
        c1.setItem(c2.getItem(type));
        c2.setItem(aux);
    }
}
