package ar.edu.itba.sia.gps.searchAlgorithms.helpers;

public class Benchmark {

	private long startTime;
	
	public Benchmark() {
	}
	
	public void start() {
		this.startTime = System.currentTimeMillis();
	}
	
	public void end() {
		System.out.println("Execution time: " + (System.currentTimeMillis() - startTime) + "ms");
		reset();
	}
	
	public void reset() {
		this.startTime = 0;
	}
	
}
