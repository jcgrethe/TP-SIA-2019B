package ar.edu.itba.sia.gae.mutationMethods;

import ar.edu.itba.sia.gae.models.CharacterHelpers;
import ar.edu.itba.sia.gae.models.GameCharacter;
import ar.edu.itba.sia.gae.models.Item;
import ar.edu.itba.sia.gae.models.ItemType;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

public class MutationHelper {
    private static Map<ItemType, List<Item>> items;
    private static Double mutationProbability;
    private static Long totalGenerations;

    public static void init(Map<ItemType, List<Item>> items, Double mutationProbability, long totalGenerations) {
        MutationHelper.items = items;
        MutationHelper.mutationProbability = mutationProbability;
        MutationHelper.totalGenerations = totalGenerations;
    }

    public static Boolean checkUniformProbability(){
        return new Random().nextDouble() > MutationHelper.mutationProbability;
    }

    /*
        The probability of mutation is proportional to the percentage of generations already processed.
        Linear incrementing from 1 to zero. So, it's more probable to mutate at the begining than in the end.
        TODO: Check other functions!
     */
    public static Boolean checkNonUniformProbability(long generations){
        return new Random().nextDouble() > ((double)generations / (double)totalGenerations);
    }

    public static Item getRandomItem(ItemType type){
        List<Item> availableItems = items.get(type);
        Integer random = new Random().ints(0, availableItems.size()).findFirst()
                .orElseThrow(IllegalStateException::new);
        return availableItems.get(random);
    }

    public static void mutateOneGen(GameCharacter character, MutationProbabilityMethod probabilityMethod, long generations, Integer genToMutate){
        if ((probabilityMethod == MutationProbabilityMethod.UNIFORM && checkUniformProbability())
            || (probabilityMethod == MutationProbabilityMethod.NON_UNIFORM && checkNonUniformProbability(generations))){
            genToMutate = Optional.ofNullable(genToMutate).orElse(new Random().ints(0, 5 + 1)
                    .findFirst().orElseThrow(IllegalStateException::new));
            switch (genToMutate){
                case 0:
                    character.setHeight(CharacterHelpers.getRandomHeight());
                    break;
                case 1:
                    character.setBOOTS(getRandomItem(ItemType.BOOTS));
                    break;
                case 2:
                    character.setGLOVES(getRandomItem(ItemType.GLOVES));
                    break;
                case 3:
                    character.setHELMET(getRandomItem(ItemType.HELMET));
                    break;
                case 4:
                    character.setVEST(getRandomItem(ItemType.VEST));
                    break;
                case 5:
                    character.setWEAPON(getRandomItem(ItemType.WEAPON));
                    break;
                default: throw new IllegalStateException("Invalid gen number.");
            }
        }
    }

    static void mutateMultiGens(GameCharacter character, MutationProbabilityMethod probabilityMethod, long generations){
        IntStream.range(0, 6).forEach(gen -> mutateOneGen(character, probabilityMethod, generations, gen));
    }

    public enum MutationProbabilityMethod{
        UNIFORM, NON_UNIFORM
    }
}
