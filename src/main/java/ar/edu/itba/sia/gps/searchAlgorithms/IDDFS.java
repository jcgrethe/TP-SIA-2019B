package ar.edu.itba.sia.gps.searchAlgorithms;

import java.util.List;
import java.util.Set;

import ar.edu.itba.sia.gps.GPSNode;
import ar.edu.itba.sia.gps.SearchStrategy;
import ar.edu.itba.sia.gps.api.Heuristic;

public class IDDFS implements SearchAlgorithmLogic {

	private int finalDepth = 1;


	@Override
	public List pushNode(List<GPSNode> frontierNodes, Set<GPSNode> allNodes, GPSNode node, Heuristic h){

		if (node.getDepth() <= finalDepth) {
 			frontierNodes.add(0, node);
			allNodes.add(node);
		}else
			SearchAlgorithmEngine.setPosibleNode(true);
		return frontierNodes;
	}

	@Override
	public SearchStrategy getType() {
		return SearchStrategy.IDDFS;
	}

	public void restart(List<GPSNode> frontierNodes, Set<GPSNode> allNodes) {
		finalDepth++;
		frontierNodes.clear();
		allNodes.clear();
		frontierNodes.add(SearchAlgorithmEngine.getFirstNode());
		allNodes.add(SearchAlgorithmEngine.getFirstNode());
		SearchAlgorithmEngine.setPosibleNode(false);
	}
}
