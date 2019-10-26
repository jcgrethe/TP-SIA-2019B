package ar.edu.itba.sia.gae.mutationMethods;

import ar.edu.itba.sia.gae.models.GameCharacter;
import ar.edu.itba.sia.gae.models.Item;
import ar.edu.itba.sia.gae.models.ItemType;

import java.util.List;
import java.util.Map;

public abstract class MutationMethod {
    Map<ItemType, List<Item>> items;

    public MutationMethod(final Map<ItemType, List<Item>> items){
        this.items = items;
    }

    abstract void mutate(GameCharacter gameCharacter);
}
