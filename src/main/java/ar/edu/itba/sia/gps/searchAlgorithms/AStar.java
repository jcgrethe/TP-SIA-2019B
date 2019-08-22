package ar.edu.itba.sia.gps.searchAlgorithms;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.itba.sia.gps.GPSNode;

public class AStar implements SearchAlgorithmLogic {

	@Override
	public GPSNode getNext(List<GPSNode> frontierNodes) throws IndexOutOfBoundsException {
		
		return frontierNodes
				.stream()
				.sorted((n1,n2)-> n1.getCost() - n2.getCost())
				.collect(Collectors.toList())
				.remove(0);
	}

	@Override
	public int calculateCost(int gCost, int hCost) {
		
		return (gCost + 1) + hCost;
	}
	
	
}
