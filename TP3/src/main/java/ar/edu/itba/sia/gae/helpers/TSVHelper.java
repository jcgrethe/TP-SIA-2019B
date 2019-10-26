package ar.edu.itba.sia.gae.helpers;

import ar.edu.itba.sia.gae.models.Item;
import ar.edu.itba.sia.gae.models.ItemType;
import jdk.internal.util.xml.impl.Input;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.*;

public class TSVHelper {

    public static Map initData(Properties properties) throws IOException {
        Map<ItemType, List<Item>> items = new HashMap<ItemType, List<Item>>(){
            {
                put(ItemType.BOOTS,     loadData(properties.getProperty("bootsPath"), ItemType.BOOTS) );
                put(ItemType.GLOVES,    loadData(properties.getProperty("glovesPath"), ItemType.GLOVES));
                put(ItemType.HELMET,    loadData(properties.getProperty("helmetsPath"), ItemType.HELMET));
                put(ItemType.VEST,      loadData(properties.getProperty("vestsPath"), ItemType.VEST));
                put(ItemType.WEAPON,    loadData(properties.getProperty("weaponsPath"), ItemType.WEAPON));
            }
        };



        return items;
    }

    private static List loadData(String file, ItemType type) throws IOException {
        List<Item> list = new LinkedList<>();
        Reader in = new FileReader(file);
        CSVParser csvParser = CSVFormat.TDF.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : csvParser) {
            Item item = new Item(
                    Long.valueOf(record.get("id")),
                    Double.parseDouble(record.get("Fu")),
                    Double.parseDouble(record.get("Ag")),
                    Double.parseDouble(record.get("Ex")),
                    Double.parseDouble(record.get("Re")),
                    Double.parseDouble(record.get("Vi")),
                    type
                    );
            list.add(item);
        }
        return list;
    }

}
