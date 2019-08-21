package ar.edu.itba.sia.gps;

import java.util.Map;
import java.util.Queue;

import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.Problem;
import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.gps.searchAlgorithms.*;

public class GPSEngine {

	Queue<GPSNode> open;
	Map<State, Integer> bestCosts;
	Problem problem;
	long explosionCounter;
	boolean finished;
	boolean failed;
	GPSNode solutionNode;
	Heuristic heuristic;

	protected SearchStrategy strategy;
	private SearchAlgorithm algorithm;

	public GPSEngine(Problem problem, SearchStrategy strategy, Heuristic heuristic) {
		this.problem = problem;
		this.heuristic = heuristic;
		this.strategy = strategy;
		switch (strategy){
			case BFS: algorithm = new BFS();
				break;
			case DFS: algorithm = new DFS();
				break;
			case IDDFS: algorithm = new IDDFS();
				break;
			case ASTAR: algorithm = new AStar();
				break;
			case GREEDY: algorithm = new Greedy();
				break;
		}
	}

	public void findSolution() {

	}

	// GETTERS FOR THE PEOPLE!

	public Queue<GPSNode> getOpen() {
		return open;
	}

	public Map<State, Integer> getBestCosts() {
		return bestCosts;
	}

	public Problem getProblem() {
		return problem;
	}

	public long getExplosionCounter() {
		return explosionCounter;
	}

	public boolean isFinished() {
		return finished;
	}

	public boolean isFailed() {
		return failed;
	}

	public GPSNode getSolutionNode() {
		return solutionNode;
	}

	public SearchStrategy getStrategy() {
		return strategy;
	}

}
