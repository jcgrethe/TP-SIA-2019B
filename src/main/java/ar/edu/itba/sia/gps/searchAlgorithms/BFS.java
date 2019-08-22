package ar.edu.itba.sia.gps.searchAlgorithms;

import java.util.List;

import ar.edu.itba.sia.gps.GPSNode;

public class BFS implements SearchAlgorithmLogic{

	@Override
	public GPSNode getNext(List<GPSNode> frontierNodes) throws IndexOutOfBoundsException{
		
		return frontierNodes.remove(0);
	}

	@Override
	public int calculateCost(int gCost, int hCost) {
		return gCost + 1;
	}
	
}
