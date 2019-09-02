package ar.edu.itba.sia.gps.searchAlgorithms;

import java.util.*;

import ar.edu.itba.sia.gps.GPSNode;
import ar.edu.itba.sia.gps.SearchStrategy;
import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.State;

public class IDDFS implements SearchAlgorithmLogic {

	private int finalDepth = 1;


	@Override
	public Queue<GPSNode> pushNode(Queue<GPSNode> frontierNodes, Map<State,Integer> bestCosts, GPSNode node, Heuristic h){

		if(bestCosts.containsKey(node.getState()))
			if(bestCosts.get(node.getState()) <= node.getDepth())
				return frontierNodes;

		if (node.getDepth() <= finalDepth) {
 			frontierNodes.add(node);
			bestCosts.put(node.getState(),node.getDepth());
		}else
			SearchAlgorithmEngine.setPosibleNode(true);
		return frontierNodes;
	}

	@Override
	public SearchStrategy getType() {
		return SearchStrategy.IDDFS;
	}

	@Override
	public List getList(Comparator comparator) {
		return new LinkedList<GPSNode>();
	}

	@Override
	public Comparator getComparator(Heuristic h) {
		return null;
	}

	public void restart(Queue<GPSNode> frontierNodes, Map<State,Integer> allNodes) {
		finalDepth++;
		frontierNodes.clear();
		allNodes.clear();
		frontierNodes.add(SearchAlgorithmEngine.getFirstNode());
		allNodes.put(SearchAlgorithmEngine.getFirstNode().getState(),0);
		SearchAlgorithmEngine.setPosibleNode(false);
	}
}
