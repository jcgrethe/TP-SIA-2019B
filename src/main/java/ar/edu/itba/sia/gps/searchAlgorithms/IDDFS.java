package ar.edu.itba.sia.gps.searchAlgorithms;

import java.util.List;
import java.util.Set;

import ar.edu.itba.sia.gps.GPSNode;
import ar.edu.itba.sia.gps.api.Heuristic;

public class IDDFS implements SearchAlgorithmLogic {
	
	private GPSNode startingNode;
	private int finalDepth = 0;

	@Override
	public void pushNode(List<GPSNode> frontierNodes, Set<GPSNode> allNodes, GPSNode node, Heuristic h){
				
		if (node.getDepth() == 1) {
			startingNode = node.getParent();
		}
				
		if (node.getDepth() >= finalDepth) {
			restart(frontierNodes, allNodes);
 		} else {
 			frontierNodes.add(0, node);
 		}
	
	}
	
	private void restart(List<GPSNode> frontierNodes, Set<GPSNode> allNodes) {
		finalDepth++;
		frontierNodes.clear();
		allNodes.clear();
		frontierNodes.add(startingNode);
		allNodes.add(startingNode);
	}

	
	
}
