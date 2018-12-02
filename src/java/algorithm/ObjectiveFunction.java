package algorithm;

import java.util.HashMap;
import java.util.Map;
import mapObjects.Precinct;

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

    public double calculateObjectiveFunctionValue(Map<Integer, Precinct> precincts) {
        return metrics.get(Metric.COMPACTNESS) * this.calcCompactness(precincts)
                + metrics.get(Metric.ALIGNMENT) * this.calcAlignment(precincts)
                + metrics.get(Metric.CONSISTENCY) * this.calcConsistency(precincts)
                + metrics.get(Metric.GERRYMANDERING) * this.calcGerrymandering(precincts)
                + metrics.get(Metric.PARTISANFAIRNESS) * this.calcPartisanFairness(precincts)
                + metrics.get(Metric.POPOULATIONEQUALITY) * this.calcPopulationEquality(precincts);
    }

    private double calcCompactness(Map<Integer, Precinct> precincts) {
        return 0;
    }

    private double calcPopulationEquality(Map<Integer, Precinct> precincts) {
        return 0;
    }

    private double calcPartisanFairness(Map<Integer, Precinct> precincts) {
        return 0;
    }

    private double calcConsistency(Map<Integer, Precinct> precincts) {
        return 0;
    }

    private double calcGerrymandering(Map<Integer, Precinct> precincts) {
        return 0;
    }

    private double calcAlignment(Map<Integer, Precinct> precincts) {
        return 0;
    }

}
