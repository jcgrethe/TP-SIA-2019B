package ar.edu.itba.sia.gps.searchAlgorithms;

import java.util.*;

import ar.edu.itba.sia.gps.GPSNode;
import ar.edu.itba.sia.gps.SearchStrategy;
import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.State;

public interface SearchAlgorithmLogic {
	
	void pushNode(Queue<GPSNode> frontierNodes, Map<State,Integer> bestCosts, GPSNode node, Heuristic h);
	SearchStrategy getType();
	Object getList(Heuristic h);
}
