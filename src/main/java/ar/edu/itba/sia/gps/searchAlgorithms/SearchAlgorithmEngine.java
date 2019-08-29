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
    private int explotions;

    public SearchAlgorithmEngine(){
        this.frontierNodes = new LinkedList<>();
        this.allNodes = new HashSet<>();
        this.explotions = 0;
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
        GPSNode currentNode = new GPSNode(startingState, 0, null);
        SearchAlgorithmLogic searchLogic = SearchAlgorithmFactory.getAlgorithm(strategy);

        frontierNodes.add(currentNode);
        allNodes.add(currentNode);
                
        try {
        	 while (!p.isGoal(currentNode.getState())){
                 currentNode = frontierNodes.remove(0);
                 System.out.printf("State:\n%s\n", currentNode.getState().getRepresentation());
                 List<Rule> rulesToApply = p.getRules();
                 explode(currentNode, rulesToApply, searchLogic, h);
             }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("No encontro.");
            return null;
        }

        System.out.printf("Sol:\n%s\n", currentNode.getState().toString());
        System.out.println(explotions);

        return currentNode;
    }

    private void explode(GPSNode node, List<Rule> rules, SearchAlgorithmLogic searchLogic, Heuristic h){
    	
    	explotions++;
    	//System.out.println(explotions);
    	rules.forEach((rule)-> {
    		Optional<State> newState = rule.apply(node.getState());
    		
    		newState.ifPresent( ns -> {

    			GPSNode newNode = new GPSNode(ns, node.getCost() + 1, rule);
    			newNode.setParent(node);
    			
    			if (!allNodes.contains(newNode)) {

    				searchLogic.pushNode(frontierNodes, allNodes, newNode, h);
    				allNodes.add(newNode);
    			}
    		});
    		
    	});
                
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
