package ar.edu.itba.sia.gps.searchAlgorithms;

import java.util.List;

import ar.edu.itba.sia.gps.GPSNode;

public interface SearchAlgorithmLogic {
	
	GPSNode getNext(List<GPSNode> frontierNodes) throws IndexOutOfBoundsException;
	
	int calculateCost(int gCost, int hCost);
}
