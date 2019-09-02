package ar.edu.itba.sia.gps.searchAlgorithms;

import java.util.*;
import java.util.stream.Collectors;

import ar.edu.itba.sia.gps.GPSNode;
import ar.edu.itba.sia.gps.SearchStrategy;
import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.State;
import javafx.collections.transformation.SortedList;

public class AStar implements SearchAlgorithmLogic {

	@Override
	public Queue pushNode(Queue<GPSNode> frontierNodes, Map<State,Integer> bestCosts, GPSNode node, Heuristic h){

		if(bestCosts.containsKey(node.getState())) {
			if (bestCosts.get(node.getState()) <= node.getCost()) {
				return frontierNodes;
			} else {
				SearchAlgorithmEngine.restExplotions();
			}
		}
		frontierNodes.add(node);
		bestCosts.put(node.getState(), node.getCost());
		return frontierNodes;
	}

	@Override
	public SearchStrategy getType() {
		return SearchStrategy.ASTAR;
	}

	@Override
	public PriorityQueue getList(Comparator comparator) {
		return new PriorityQueue(100000,comparator);
	}

	@Override
	public Comparator<GPSNode> getComparator(Heuristic h) {
		return new Comparator<GPSNode>() {
			@Override
			public int compare(GPSNode n1, GPSNode n2) {
				return (n1.getCost() + h.getValue(n1.getState())) - (n2.getCost() + h.getValue(n2.getState()));
			}
		};
	}

}
