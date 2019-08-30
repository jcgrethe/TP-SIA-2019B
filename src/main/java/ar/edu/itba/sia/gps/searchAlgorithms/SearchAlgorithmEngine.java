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
    private Set<GPSNode> allNodes;
    private Heuristic heuristic;
    private static int explotions;
    private static GPSNode firstNode;
    private static boolean posibleNode = false;
    Map<State, Integer> bestCosts;

    public SearchAlgorithmEngine(List<GPSNode> list, Map<State,Integer> bestCosts){
        this.frontierNodes = list;
        this.allNodes = new HashSet<>();
        this.explotions = 0;
        this.bestCosts = bestCosts;
    }
    
    public GPSNode search(Problem p, SearchStrategy strategy, Heuristic h) {
    	
    	if (h != null) {
    		System.out.println("Por empezar busqueda: heuristica piola");
    		return _search(p, strategy, h);
    	} else {
    		System.out.println("Por empezar busqueda: sin heuristica");
    		return _search(p, strategy, (v) -> 0);
    	}
    }
    
    //Returns true if solution was found. False, otherwise.
    private GPSNode _search(Problem p, SearchStrategy strategy, Heuristic h){

        State startingState = p.getInitState();
        GPSNode currentNode = firstNode = new GPSNode(startingState, 0, null);
        SearchAlgorithmLogic searchLogic = SearchAlgorithmFactory.getAlgorithm(strategy);

        frontierNodes.add(currentNode);
        allNodes.add(currentNode);
                
        try {
        	 while (!p.isGoal(currentNode.getState())) {
                 currentNode = frontierNodes.remove(0);
                 bestCosts.put(currentNode.getState(),currentNode.getCost());
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

        System.out.printf("Sol:\n%s\n", currentNode.getState().toString());

        return currentNode;
    }

    private void explode(GPSNode node, List<Rule> rules, SearchAlgorithmLogic searchLogic, Heuristic h){
    	
    	explotions++;
    	for(Rule rule: rules){
    		Optional<State> newState = rule.apply(node.getState());
    		newState.ifPresent( ns -> {
    			GPSNode newNode = new GPSNode(ns, node.getCost() + 1, rule);
    			newNode.setParent(node);

    			if (!allNodes.contains(newNode)) {
    				searchLogic.pushNode(frontierNodes, allNodes, newNode, h);
    			}
    		});

    	}
                
    }


    private void explodeIDDFS(GPSNode node, List<Rule> rules, IDDFS searchLogic, Heuristic h){

        explotions++;
        List<GPSNode> nodesToAdd = new LinkedList<>();
        rules.forEach((rule)-> {
            Optional<State> newState = rule.apply(node.getState());

            newState.ifPresent( ns -> {

                GPSNode newNode = new GPSNode(ns, node.getCost() + 1, rule);
                newNode.setParent(node);

                if (!allNodes.contains(newNode)) {
                    nodesToAdd.add(newNode);
                }
            });
        });
        Collections.reverse(nodesToAdd);
        nodesToAdd.forEach( newNode -> {  searchLogic.pushNode(frontierNodes, allNodes, newNode, h); });

        if(frontierNodes.size() == 0 && posibleNode == true){
            searchLogic.restart(frontierNodes, allNodes);
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

	public Set<GPSNode> getAllNodes() {
		return allNodes;
	}

	public int getExplotions() {
		return explotions;
	}
	
    
}
