package ar.edu.itba.sia.gae;

import ar.edu.itba.sia.gae.helpers.TSVHelper;
import ar.edu.itba.sia.gae.models.Item;
import ar.edu.itba.sia.gae.models.ItemType;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Main {

    // Read data


    public static void main(String[] args) throws IOException {
        InputStream fileProperties = new FileInputStream("config.properties");
        Properties prop = new Properties();
        prop.load(fileProperties);
        Map items = TSVHelper.initData(prop);


    }


}
