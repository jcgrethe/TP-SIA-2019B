package ar.edu.itba.sia.gps.searchAlgorithms;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import ar.edu.itba.sia.gps.GPSNode;
import ar.edu.itba.sia.gps.SearchStrategy;
import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.State;

public class Greedy implements SearchAlgorithmLogic {

	@Override
	public List pushNode(List<GPSNode> frontierNodes, Map<State,Integer> bestCosts, GPSNode node, Heuristic h){

		if(bestCosts.containsKey(node.getState()))
			if(bestCosts.get(node.getState()) <= h.getValue(node.getState()))
				return frontierNodes;

		frontierNodes.add(node);
		bestCosts.put(node.getState(), h.getValue(node.getState()));
		return frontierNodes.parallelStream().sorted((n1,n2)-> (h.getValue(n1.getState()))  - (h.getValue(n2.getState()))).collect(Collectors.toList());
	}

	@Override
	public SearchStrategy getType() {
		return SearchStrategy.GREEDY;
	}
}
