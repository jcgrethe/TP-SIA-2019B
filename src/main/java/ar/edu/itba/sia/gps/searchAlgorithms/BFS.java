package ar.edu.itba.sia.gps.searchAlgorithms;

import java.util.List;
import java.util.Set;

import ar.edu.itba.sia.gps.GPSNode;
import ar.edu.itba.sia.gps.api.Heuristic;

public class BFS implements SearchAlgorithmLogic{

	@Override
	public void pushNode(List<GPSNode> frontierNodes, Set<GPSNode> allNodes, GPSNode node, Heuristic h){
		
		frontierNodes.add(node);
	}
	
}
