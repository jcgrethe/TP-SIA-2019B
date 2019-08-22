package ar.edu.itba.sia.gps.searchAlgorithms;

import java.util.List;

import ar.edu.itba.sia.gps.GPSNode;

public class IDDFS implements SearchAlgorithmLogic {
	
	private GPSNode startingNode;
	private int finalDepth = 0;

	@Override
	public GPSNode getNext(List<GPSNode> frontierNodes) throws IndexOutOfBoundsException {
		
		//que pasa cuando saco todos? necesito check
		
		GPSNode nextNode = frontierNodes.get(frontierNodes.size() - 1);

		if (nextNode.getDepth() == 0) {
			startingNode = nextNode;
		}
				
		if (nextNode.getDepth() <= finalDepth) {
			return frontierNodes.remove(frontierNodes.size() - 1);
		} else {
			finalDepth++;
			frontierNodes.clear();
			//allNodes.clear();
			return startingNode;
 		}	
			
	}

	@Override
	public int calculateCost(int gCost, int hCost) {
		
		return gCost + 1;
	}
	
	
}
