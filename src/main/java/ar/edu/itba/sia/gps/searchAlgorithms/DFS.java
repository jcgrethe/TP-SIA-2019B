package ar.edu.itba.sia.gps.searchAlgorithms;

import java.util.*;

import ar.edu.itba.sia.gps.GPSNode;
import ar.edu.itba.sia.gps.SearchStrategy;
import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.State;

public class DFS implements SearchAlgorithmLogic {

	@Override
	public Queue pushNode(Queue<GPSNode> frontierNodes, Map<State,Integer> bestCosts, GPSNode node, Heuristic h){
		if(bestCosts.containsKey(node.getState()))
			return frontierNodes;
		((LinkedList)frontierNodes).add(0, node);
		bestCosts.put(node.getState(),node.getDepth());
		return frontierNodes;
	}

	@Override
	public SearchStrategy getType() {
		return SearchStrategy.DFS;
	}

	@Override
	public List getList(Comparator comparator) {
		return new LinkedList<GPSNode>();
	}

	@Override
	public Comparator getComparator(Heuristic h) {
		return null;
	}

}
