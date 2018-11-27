/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

import java.util.HashMap;
import java.util.Set;

import mapObjects.Precinct;

/**
 *
 * @author spitlord
 */
public class ObjectiveFunction {
    
	HashMap<Metric, Double> metrics = new HashMap<Metric, Double>();
	
	public void setWeight(Metric metric, double val) {
		metrics.put(metric, val);
	}
	
	public double calculateObjectiveFunction(Set<Precinct> precincts) {
		double OFV = 0;
		return OFV;
	}
}
