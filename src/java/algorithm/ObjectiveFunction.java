/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

import java.util.HashMap;
import mapObjects.State;

/**
 *
 * @author spitlord
 */
public class ObjectiveFunction {
    private HashMap<Metric, Double> metrics;

    public ObjectiveFunction(HashMap<Metric, Double> metrics) {
        this.metrics = metrics;
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
