/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

import java.util.PriorityQueue;
import java.util.Set;

import mapObjects.District;
import mapObjects.Precinct;
import mapObjects.State;

/**
 *
 * @author spitlord
 */
public class SimulatedAnnealing extends Algorithm {

    public SimulatedAnnealing(State s) {
        super();
        this.currentState = s;
    }

    @Override
    public void run() {
    	while (this.checkTerimanationConditions() != true) {
    		District d = this.selectDistrictToGrow();
    		Precinct precinctToMove = this.findMovablePrecinct(d);
    		if (precinctToMove != null) {
    			District oldD = currentState.getDistrict(precinctToMove.getDistrictID());
        		moves.push(new Move(precinctToMove, oldD, d));
        		d.addPrecinct(precinctToMove);
        		oldD.removePrecinct(precinctToMove);
    		}
    	}
    }

    @Override
    protected boolean checkTerimanationConditions() {
        return true;
    }
    
    private Precinct findMovablePrecinct(District d) {
    	PriorityQueue<Precinct> candidates = d.getCandidates();
    	Set<Precinct> precincts = d.getPrecincts();
    	Precinct bestP = null;
    	double bestOFV = 0;
    	for(Precinct p: candidates) {
    		if (p.getDistrictID() != d.getID()){
        		precincts.add(p);
        		double currentOFV = objectiveFunction.calculateObjectiveFunction(precincts);
        		if (currentOFV > bestOFV) {
        			bestOFV = currentOFV;
        			bestP = p;
        		}
        		precincts.remove(p);
    		}
    	}
    	return bestP;
    }

    private District selectDistrictToGrow() {
    	return this.currentState.getRandomDistrict();
    }
}
