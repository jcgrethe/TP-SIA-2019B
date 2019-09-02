package ar.edu.itba.sia.gps.searchAlgorithms;

import java.util.*;
import java.util.stream.Collectors;

import ar.edu.itba.sia.gps.GPSNode;
import ar.edu.itba.sia.gps.SearchStrategy;
import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.State;

public class Greedy implements SearchAlgorithmLogic {

	@Override
	public Queue pushNode(Queue<GPSNode> frontierNodes, Map<State,Integer> bestCosts, GPSNode node, Heuristic h){

		if(bestCosts.containsKey(node.getState()))
			if(bestCosts.get(node.getState()) <= h.getValue(node.getState()))
				return frontierNodes;

		frontierNodes.add(node);
		bestCosts.put(node.getState(), h.getValue(node.getState()));
		return frontierNodes;
	}

	@Override
	public SearchStrategy getType() {
		return SearchStrategy.GREEDY;
	}

	@Override
	public PriorityQueue<GPSNode> getList(Comparator comparator) {
		return new PriorityQueue<GPSNode>(100000, comparator);
	}

	@Override
	public Comparator<GPSNode> getComparator(Heuristic h) {
		return new Comparator<GPSNode>() {
			@Override
			public int compare(GPSNode o1, GPSNode o2) {
				return h.getValue(o1.getState()) - h.getValue(o2.getState());
			}
		};
	}
}
