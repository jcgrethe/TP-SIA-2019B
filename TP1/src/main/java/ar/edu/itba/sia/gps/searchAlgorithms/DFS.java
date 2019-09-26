package ar.edu.itba.sia.gps.searchAlgorithms;

import java.util.*;

import ar.edu.itba.sia.gps.GPSNode;
import ar.edu.itba.sia.gps.SearchStrategy;
import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.State;

public class DFS implements SearchAlgorithmLogic {

	@Override
	public void pushNode(Queue<GPSNode> frontierNodes, Map<State,Integer> bestCosts, GPSNode node, Heuristic h){
		
		if(bestCosts.containsKey(node.getState())) return;
		
		((LinkedList)frontierNodes).add(0, node);
		bestCosts.put(node.getState(),node.getDepth());
	}

	@Override
	public SearchStrategy getType() {
		return SearchStrategy.DFS;
	}

	@Override
	public List getList(Heuristic h) {
		return new LinkedList<GPSNode>();
	}

}
