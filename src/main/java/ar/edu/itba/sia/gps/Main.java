package ar.edu.itba.sia.gps;

import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.Problem;
import ar.edu.itba.sia.gps.gridlock.GridLockProblem;
import ar.edu.itba.sia.gps.gridlock.heuristics.GridLockMediumHeuristic;
import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args) {

        CommandLine cmd = getOptions(args);
        SearchStrategy searchStrategy = null;

        try {
            if(cmd.getOptionValue("a") != null){
                searchStrategy = SearchStrategy.valueOf(cmd.getOptionValue("a"));
            }
        }catch (Exception e) {
            System.out.println("Bad algorithm name. Possibles: DFS, BFS, ASTAR, GREEDY ");
            return;
        }
        Problem problem = new GridLockProblem();
        Heuristic heuristic = new GridLockMediumHeuristic();
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
