package ar.edu.itba.sia.gps.searchAlgorithms;

import java.util.List;

import ar.edu.itba.sia.gps.GPSNode;

public class DFS implements SearchAlgorithmLogic {

	@Override
	public GPSNode getNext(List<GPSNode> frontierNodes) throws IndexOutOfBoundsException {
		
		return frontierNodes.remove(frontierNodes.size() - 1);
	}

	@Override
	public int calculateCost(int gCost, int hCost) {
		return gCost + 1;
	}
	
	
}
