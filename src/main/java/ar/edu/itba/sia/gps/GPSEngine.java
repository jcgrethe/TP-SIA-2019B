package ar.edu.itba.sia.gps;

import java.util.Map;
import java.util.Queue;

import ar.edu.itba.sia.gps.api.Heuristic;
import ar.edu.itba.sia.gps.api.Problem;
import ar.edu.itba.sia.gps.api.State;
import ar.edu.itba.sia.gps.searchAlgorithms.SearchAlgorithmEngine;

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

	public GPSEngine(Problem problem, SearchStrategy strategy, Heuristic heuristic) {
		this.problem = problem;
		this.heuristic = heuristic;
		this.strategy = strategy;
	}

	public void findSolution() {
		
		SearchAlgorithmEngine searchEngine = new SearchAlgorithmEngine();
		
		this.finished = false;
		System.out.println("Voy a iniciar la busqueda");
		this.solutionNode = searchEngine.search(this.problem, this.strategy, this.heuristic);
		System.out.println("Ya corrrio la busqueda");
		this.finished = true;
		
		this.failed = (solutionNode == null);
		this.explosionCounter = searchEngine.getExplotions();
		
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
