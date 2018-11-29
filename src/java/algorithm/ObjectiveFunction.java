package algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import mapObjects.Precinct;
import mapObjects.State;

public class ObjectiveFunction {

	private Map<Metric, Double> metrics;

	public ObjectiveFunction(Map<Metric, Double> metrics) {
		this.metrics = metrics;
	}

	public void setWeight(Metric metric, double val) {
		metrics.put(metric, val);
	}

	public void setMetrics(HashMap<Metric, Double> metrics) {
		this.metrics = metrics;
	}

	public double calculateObjectiveFunction(Set<Precinct> precincts) {
		double OFV = 0;
		return OFV;
	}

	public double calculateObjectiveValue(State state) {
		return 0;
	}

	public double calcCompactness(State state) {
		return 0;
	}

	public double calcPopulationEquality(State state) {
		return 0;
	}

	public double calcPartisanFairness(State state) {
		return 0;
	}

	public double calcConsistency(State state) {
		return 0;
	}

	public double calcGerrymandering(State state) {
		return 0;
	}

	public double calcAlignment(State state) {
		return 0;
	}

}
