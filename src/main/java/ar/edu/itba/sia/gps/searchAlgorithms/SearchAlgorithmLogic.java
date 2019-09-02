package ar.edu.itba.sia.gps.searchAlgorithms;

import java.util.List;
import java.util.Map;
import java.util.Set;

import ar.edu.itba.sia.gps.GPSNode;
import ar.edu.itba.sia.gps.SearchStrategy;
import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.State;

public interface SearchAlgorithmLogic {
	
	List pushNode(List<GPSNode> frontierNodes, Map<State,Integer> bestCosts, GPSNode node, Heuristic h);
	SearchStrategy getType();
}
