/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

import java.util.HashMap;
import java.util.Set;

import mapObjects.Precinct;
import mapObjects.State;

/**
 *
 * @author spitlord
 */
public class ObjectiveFunction {
    private HashMap<Metric, Double> metrics;
    
    
    public ObjectiveFunction() {
            metrics = new HashMap<Metric, Double>();
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
    
    
    
    
    public void setWeights(Metric metric, double weight) {
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
