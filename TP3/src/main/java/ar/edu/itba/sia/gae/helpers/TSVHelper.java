package ar.edu.itba.sia.gae.helpers;

import ar.edu.itba.sia.gae.models.Item;
import ar.edu.itba.sia.gae.models.ItemType;

import java.util.*;

public class TSVHelper {

    public static Map initData(){
        Map<ItemType, List<Item>> items = new HashMap<ItemType, List<Item>>(){
            {
                put(ItemType.BOOTS,new LinkedList<>());
                put(ItemType.GLOVES,new LinkedList<>());
                put(ItemType.HELMET,new LinkedList<>());
                put(ItemType.VEST,new LinkedList<>());
                put(ItemType.WEAPON,new LinkedList<>());
            }
        };
        List<String> paths = Arrays.asList("data/armas.tsv","data/botas.tsv","data/cascos.tsv","data/guantes.tsv","data/pecheras.tsv");
        paths.forEach();



        return items;
    }
}
