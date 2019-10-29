package ar.edu.itba.sia.gae;

import ar.edu.itba.sia.gae.helpers.PropertiesHelper;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        GeneticAlgorithmEngine gae = new GeneticAlgorithmEngine(
                Objects.requireNonNull(PropertiesHelper.initConfiguration("config.properties")));

        gae.calculate();
    }
}
