package ar.edu.itba.sia.gps;

import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.Problem;
import ar.edu.itba.sia.gps.gridlock.GridLockProblem;
import ar.edu.itba.sia.gps.gridlock.heuristics.GridLockMediumHeuristic;

public class Main {

    public static void main(String[] args) {
        Problem problem = new GridLockProblem();
        Heuristic heuristic = new GridLockMediumHeuristic();
        GPSEngine engine = new GPSEngine(problem, SearchStrategy.GREEDY, heuristic);
        engine.findSolution();
    }
}
