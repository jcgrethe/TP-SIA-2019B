package ar.edu.itba.sia.gae.mutationMethods;

import ar.edu.itba.sia.gae.models.Item;
import ar.edu.itba.sia.gae.models.GameCharacter;
import ar.edu.itba.sia.gae.models.ItemType;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class GenUniform extends MutationMethod {
    public GenUniform(Map<ItemType, List<Item>> items) {
        super(items);
    }

    /*
        One of the gens mutates. Always same probability.
     */
    @Override
    void mutate(GameCharacter gameCharacter) {
        Integer genToMutate = new Random().ints(0, 5 + 1)
                .findFirst().orElseThrow(IllegalStateException::new);
        switch (genToMutate){
            case 0: // Change Height
//                gameCharacter
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            default: throw new IllegalStateException("Invalid gen number.");
        }

    }
}
