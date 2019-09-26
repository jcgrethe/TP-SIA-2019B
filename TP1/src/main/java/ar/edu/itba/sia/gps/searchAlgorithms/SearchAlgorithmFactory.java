package ar.edu.itba.sia.gps.searchAlgorithms;

import ar.edu.itba.sia.gps.SearchStrategy;

public class SearchAlgorithmFactory {

    public static SearchAlgorithmLogic getAlgorithm(SearchStrategy strategy){

        switch (strategy){
            case BFS:
                return new BFS();
            case DFS:
                return new DFS();
            case IDDFS:
                return new IDDFS();
            case ASTAR:
                return new AStar();
            case GREEDY:
                return new Greedy();
        }

        return null;
    }

}
