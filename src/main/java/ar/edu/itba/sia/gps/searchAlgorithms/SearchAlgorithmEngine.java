package ar.edu.itba.sia.gps.searchAlgorithms;

import ar.edu.itba.sia.gps.GPSNode;
import ar.edu.itba.sia.gps.SearchStrategy;
import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.Problem;
import ar.edu.itba.sia.gps.api.Rule;
import ar.edu.itba.sia.gps.api.State;

import java.util.LinkedList;
import java.util.List;

public class SearchAlgorithmEngine {

    private List<GPSNode> frontierNodes;
    private List<GPSNode> allNodes;

    public SearchAlgorithmEngine(){
        this.frontierNodes = new LinkedList<>();
        this.allNodes = new LinkedList<>();

    }

    //Returns true if solution was found. False otherwise.
    public boolean search(Problem p, SearchStrategy strategy, Heuristic h){

        State startingState = p.getInitState();
        GPSNode startingNode = new GPSNode(startingState, 0, null);

        frontierNodes.add(startingNode);
        allNodes.add(startingNode);

        while (!frontierNodes.isEmpty()){

            GPSNode currentNode = frontierNodes.remove(0);
            List<Rule> rulesToApply = p.getRules();

            List<GPSNode> posibilities = explode(currentNode, rulesToApply, h);




        }

        SearchAlgorithm s = SearchAlgorithmFactory.getAlgorithm(strategy);



    }

    private explode(GPSNode node, List<Rule> rules, Heuristic h){

        


    }


}
