package ar.edu.itba.sia.gae;

import ar.edu.itba.sia.gae.helpers.Configuration;
import ar.edu.itba.sia.gae.helpers.PropertiesHelper;
import ar.edu.itba.sia.gae.helpers.TSVHelper;
import ar.edu.itba.sia.gae.models.Item;
import ar.edu.itba.sia.gae.models.ItemType;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    // Read data


    public static void main(String[] args) throws IOException {
        GeneticAlgorithmEngine gae = new GeneticAlgorithmEngine(
                Objects.requireNonNull(PropertiesHelper.initConfiguration("config.properties")));

        gae.calculate();
    }


}
