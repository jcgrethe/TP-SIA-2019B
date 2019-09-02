package ar.edu.itba.sia.gps.searchAlgorithms;

import ar.edu.itba.sia.gps.GPSNode;
import ar.edu.itba.sia.gps.SearchStrategy;
import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.Problem;
import ar.edu.itba.sia.gps.api.Rule;
import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.gps.searchAlgorithms.helpers.Benchmark;

import java.util.*;

public class SearchAlgorithmEngine {

    private List<GPSNode> frontierNodes;
    private Heuristic heuristic;
    private static int explotions;
    private static GPSNode firstNode;
    private static boolean posibleNode = false;
    Map<State, Integer> bestCosts;

    public SearchAlgorithmEngine(List<GPSNode> list, Map<State,Integer> bestCosts){
        this.frontierNodes = list;
        this.explotions = 0;
        this.bestCosts = bestCosts;
    }
    
    public GPSNode search(Problem p, SearchStrategy strategy, Heuristic h) {
    	
    	if (h != null) {
    		System.out.println("Beginning with heuristic.");
    		return _search(p, strategy, h);
    	} else {
    		System.out.println("Beginning without heuristic");
    		return _search(p, strategy, (v) -> 0);
    	}
    }
    
    //Returns true if solution was found. False, otherwise.
    private GPSNode _search(Problem p, SearchStrategy strategy, Heuristic h){

        State startingState = p.getInitState();
        System.out.printf("Starting Sstate:\n%s\n", startingState.getRepresentation());
        GPSNode currentNode = firstNode = new GPSNode(startingState, 0, null);
        SearchAlgorithmLogic searchLogic = SearchAlgorithmFactory.getAlgorithm(strategy);

        frontierNodes.add(currentNode);
        bestCosts.put(currentNode.getState(),0);
                
        try {
        	 while (!p.isGoal(currentNode.getState())) {
                 currentNode = frontierNodes.remove(0);
                 List<Rule> rulesToApply = p.getRules();
                 if (searchLogic.getType() != SearchStrategy.IDDFS){
                     explode(currentNode, rulesToApply, searchLogic, h);
                 }else {
                     explodeIDDFS(currentNode, rulesToApply, (IDDFS) searchLogic, h);
                 }
             }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No encontro.");
            return null;
        }

        System.out.printf("Solution State:\n%s\n", currentNode.getState().getRepresentation());
        System.out.println("Explotions: " + explotions);
        System.out.println("Depth: " + currentNode.getDepth());
        System.out.println("Cost: " + currentNode.getCost());

        return currentNode;
    }

    private void explode(GPSNode node, List<Rule> rules, SearchAlgorithmLogic searchLogic, Heuristic h){
    	
    	explotions++;
    	if(explotions % 1000 == 0)
    	    System.out.println(explotions);
    	for(Rule rule: rules){
    		Optional<State> newState = rule.apply(node.getState());
    		newState.ifPresent( ns -> {
    			GPSNode newNode = new GPSNode(ns, node.getCost() + rule.getCost(), rule);
    			newNode.setParent(node);
    			searchLogic.pushNode(frontierNodes, bestCosts, newNode, h);
    			});
    	}
                
    }


    private void explodeIDDFS(GPSNode node, List<Rule> rules, IDDFS searchLogic, Heuristic h){

        explotions++;
        List<GPSNode> nodesToAdd = new LinkedList<>();
        rules.forEach((rule)-> {
            Optional<State> newState = rule.apply(node.getState());

            newState.ifPresent( ns -> {

                GPSNode newNode = new GPSNode(ns, node.getCost() + rule.getCost(), rule);
                newNode.setParent(node);
                nodesToAdd.add(newNode);
            });
        });
        nodesToAdd.forEach( newNode -> {  searchLogic.pushNode(frontierNodes, bestCosts, newNode, h); });

        if(frontierNodes.size() == 0 && posibleNode == true){
            searchLogic.restart(frontierNodes, bestCosts);
        }

    }

    public static void setPosibleNode(boolean bool){
        posibleNode = bool;
    }

    public static GPSNode getFirstNode(){
        return firstNode;
    }

    public static void resetExplosions(){
        explotions = 0;
    }


	public List<GPSNode> getFrontierNodes() {
		return frontierNodes;
	}

	public int getExplotions() {
		return explotions;
	}
	
    
}
