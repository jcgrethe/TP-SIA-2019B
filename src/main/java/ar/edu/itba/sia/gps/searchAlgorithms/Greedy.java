package ar.edu.itba.sia.gps.searchAlgorithms;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ar.edu.itba.sia.gps.GPSNode;
import ar.edu.itba.sia.gps.SearchStrategy;
import ar.edu.itba.sia.gps.api.Heuristic;

public class Greedy implements SearchAlgorithmLogic {

	@Override
	public List pushNode(List<GPSNode> frontierNodes, Set<GPSNode> allNodes, GPSNode node, Heuristic h){
		
		frontierNodes.add(node);
		allNodes.add(node);
		return frontierNodes.parallelStream().sorted((n1,n2)-> (h.getValue(n1.getState()))  - (h.getValue(n2.getState()))).collect(Collectors.toList());
	}

	@Override
	public SearchStrategy getType() {
		return SearchStrategy.GREEDY;
	}
}
