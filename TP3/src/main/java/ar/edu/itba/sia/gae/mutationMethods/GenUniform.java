package ar.edu.itba.sia.gae.mutationMethods;

import ar.edu.itba.sia.gae.models.Item;
import ar.edu.itba.sia.gae.models.ItemType;

import java.util.List;
import java.util.Map;

public class GenUniform extends MutationMethod {
    public GenUniform(Map<ItemType, List<Item>> items) {
        super(items);
    }

    /*
        One of the gens mute. Always same probability.
     */
    @Override
    void mutate(Character character) {

    }
}
