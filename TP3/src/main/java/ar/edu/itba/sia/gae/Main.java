package ar.edu.itba.sia.gae;

import ar.edu.itba.sia.gae.helpers.PropertiesHelper;
import ar.edu.itba.sia.gae.models.GameCharacter;

import java.util.*;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        GeneticAlgorithmEngine gae = new GeneticAlgorithmEngine(
                Objects.requireNonNull(PropertiesHelper.initConfiguration("config.properties")));

        GameCharacter best = gae.calculate();
        System.out.println("Best Character is: ");
        System.out.println(best);
//        exit(0);
    }
}
