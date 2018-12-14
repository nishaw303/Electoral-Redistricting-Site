package algorithm;

import java.util.HashMap;
import java.util.Map;

import mapObjects.District;
import mapObjects.Precinct;
import mapObjects.State;

public class ObjectiveFunction {

	private Map<Metric, Double> metrics;

	public ObjectiveFunction(Map<Metric, Double> metrics) {
		this.metrics = metrics;
	}

	public void setWeight(Metric metric, double val) {
		this.metrics.put(metric, val);
	}

	public void setMetrics(HashMap<Metric, Double> metrics) {
		this.metrics = metrics;
	}

	public double calculateObjectiveFunctionValue(State state, Move move) {
		if (state.getUnassignedDistrict().getID() == move.getSourceDistrict().getID()) {
			
		}
		else {
			
		}
	}

	// District level
	private double calcCompactness(District d) {
		return 0;
	}

	// State level
	private double calcPopulationEquality(State state) {
		return 0;
	}

	// State level
	private double calcPartisanFairness(State state) {
		return 0;
	}
	
	// State level
	private double calcEfficiencyGap(District d) {
		return 0;
	}

	// 
	private double calcConsistency(District d) {
		return 0;
	}

	// 
	private double calcGerrymandering(District d) {
		return 0;
	}

	// 
	private double calcAlignment(District d) {
		return 0;
	}

}
