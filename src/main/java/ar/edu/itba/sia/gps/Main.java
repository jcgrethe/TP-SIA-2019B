package ar.edu.itba.sia.gps;

import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.Problem;
import ar.edu.itba.sia.gps.gridlock.GridLockProblem;
import ar.edu.itba.sia.gps.gridlock.heuristics.GridLockBasicHeuristic;
import ar.edu.itba.sia.gps.gridlock.heuristics.GridLockMediumHeuristic;
import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args) {

        CommandLine cmd = getOptions(args);
        SearchStrategy searchStrategy = null;
        String inputFile = null;
        Heuristic heuristic = null;

        try {
            if(cmd.getOptionValue("a") != null){
                searchStrategy = SearchStrategy.valueOf(cmd.getOptionValue("a").toUpperCase());
            }
            if(cmd.getOptionValue("p") != null){
                inputFile = cmd.getOptionValue("p");
            }
        }catch (Exception e) {
            System.out.println("Bad algorithm name. Possibles: DFS, BFS, ASTAR, GREEDY ");
            return;
        }
        if(cmd.getOptionValue("h") != null){
            if(cmd.getOptionValue("h").equalsIgnoreCase("basic")){
                System.out.println("Using basic heuristic");
                heuristic = new GridLockBasicHeuristic();
            }else if(cmd.getOptionValue("h").equalsIgnoreCase("medium")){
                System.out.println("Using medium heuristic");
                heuristic = new GridLockMediumHeuristic();
            }else {
                System.out.println("Bad heuristics. Possibles: basic, medium");
                return;
            }
        }else {
            System.out.println("Using medium heuristic");
            heuristic = new GridLockMediumHeuristic();
        }
        Problem problem;
        if (inputFile != null){
            problem = new GridLockProblem(inputFile);
        } else {
            problem = new GridLockProblem();
        }
        GPSEngine engine = new GPSEngine(problem, searchStrategy, heuristic);
        engine.findSolution();
    }


    private static CommandLine getOptions(String[] args){


        Options options = new Options();

        Option v = new Option("a", "algorithm", true, "Algorithm: DFS, BFS, ASTAR, GREEDY");
        v.setRequired(true);
        options.addOption(v);


        Option p = new Option("p", "path", true, "Path to initial table");
        p.setRequired(false);
        options.addOption(p);

        Option h = new Option("h", "heuristic", true, "Heuristics: basic, medium");
        p.setRequired(false);
        options.addOption(h);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd=null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }
        return cmd;
    }
}
