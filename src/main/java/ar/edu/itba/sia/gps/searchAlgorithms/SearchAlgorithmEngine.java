package ar.edu.itba.sia.gps.searchAlgorithms;

import ar.edu.itba.sia.gps.GPSNode;
import ar.edu.itba.sia.gps.SearchStrategy;
import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.Problem;
import ar.edu.itba.sia.gps.api.Rule;
import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.gps.searchAlgorithms.helpers.Benchmark;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class SearchAlgorithmEngine {

    private List<GPSNode> frontierNodes;
    private List<GPSNode> allNodes;
    private Benchmark benchmark;

    public SearchAlgorithmEngine(){
        this.frontierNodes = new LinkedList<>();
        this.allNodes = new LinkedList<>();
        this.benchmark = new Benchmark();
    }
    
    //Used for informed search algorithms
    public boolean search(Problem p, SearchStrategy strategy, Heuristic h) {
    	return _search(p, strategy, h);
    	
    }
    
    //Used for uninformed search algorithms
    public boolean search(Problem p, SearchStrategy strategy) {
    	return _search(p, strategy, (v) -> 0);
    	
    }

    //Returns true if solution was found. False, otherwise.
    private boolean _search(Problem p, SearchStrategy strategy, Heuristic h){

        State startingState = p.getInitState();
        GPSNode currentNode = new GPSNode(startingState, 0, null);
        SearchAlgorithmLogic searchLogic = SearchAlgorithmFactory.getAlgorithm(strategy);
        
        frontierNodes.add(currentNode);
        allNodes.add(currentNode);
        
        benchmark.start();
        
        try {
        	 while (!p.isGoal(currentNode.getState())){

                 currentNode = searchLogic.getNext(frontierNodes);
                 List<Rule> rulesToApply = p.getRules();
                 explode(currentNode, rulesToApply, searchLogic, h);
        
             }
        } catch (IndexOutOfBoundsException e) {
        	System.out.println("No solution found.");
        	benchmark.reset();
        	return false;
        }

        benchmark.end();
        
        return true;
    }

    private void explode(GPSNode node, List<Rule> rules, SearchAlgorithmLogic searchLogic, Heuristic h){
    	    	
    	rules.forEach((rule)-> {
    		Optional<State> newState = rule.apply(node.getState());
    		
    		newState.ifPresent( ns -> {
    			int cost = searchLogic.calculateCost(node.getCost(), h.getValue(ns));
    			GPSNode newNode = new GPSNode(ns, cost, rule);
    			//newNode.setParent(node)?
    			if (!allNodes.contains(newNode)) {
    				frontierNodes.add(newNode);
    				allNodes.add(newNode);
    			}
    		});
    		
    	});
                
    }
    
}
