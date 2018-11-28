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
import seeding.SeedStrategy;

/**
 *
 * @author spitlord
 */
public class RegionGrowing extends Algorithm {
    
   private SeedStrategy seedStrategy;

    public RegionGrowing(State state, ObjectiveFunction of, SeedStrategy strategy) {
        super();
        this.currentState = state;
        this.objectiveFunction = of;
        this.seedStrategy = strategy;
    }
    
   
   

    public RegionGrowing(SeedStrategy seedStrategy) {
       super();
       this.seedStrategy = seedStrategy;
    }

    @Override
    public void run() {
    	seedStrategy.seed(currentState);
    	District unassigned = currentState.getUnassignedDistrict();
    	while (unassigned.getPrecincts().size() > currentState.getNumPrecincts() / 2) {
    		District d = this.selectDistrictToGrow();
    		Precinct precinctToMove = d.getRandomCandidate();
    		moves.push(new Move(precinctToMove, unassigned, d));
    		d.addPrecinct(precinctToMove);
    		unassigned.removePrecinct(precinctToMove);
    	}
    	while (this.checkTerimanationConditions() != true) {
    		District d = this.selectDistrictToGrow();
    		Precinct precinctToMove = this.findBestMovablePrecinct(d);
    		moves.push(new Move(precinctToMove, unassigned, d));
    		d.addPrecinct(precinctToMove);
    		unassigned.removePrecinct(precinctToMove);
    	}
    }

    @Override
    public boolean checkTerimanationConditions() {
    	District unassigned = this.currentState.getUnassignedDistrict();
    	return unassigned.getPrecincts().isEmpty();
    }
    
    private Precinct findBestMovablePrecinct(District d) {
    	PriorityQueue<Precinct> candidates = d.getCandidates();
    	Set<Precinct> precincts = d.getPrecincts();
    	Precinct bestP = null;
    	double bestOFV = 0;
    	for (Precinct p: candidates) {
    		precincts.add(p);
    		double currentOFV = objectiveFunction.calculateObjectiveFunction(precincts);
    		if (currentOFV > bestOFV) {
    			bestOFV = currentOFV;
    			bestP = p;
    		}
    		precincts.remove(p);
    	}
    	return bestP;
    }
    
    public District selectDistrictToGrow() {
    	return this.currentState.getLowestPolulationDistrict();
    }
    
    public void setSeedStrategy(SeedStrategy seedStrategy) {
        this.seedStrategy = seedStrategy;
    } 

    public void setObjectiveFunction(ObjectiveFunction objectiveFunction) {
        this.objectiveFunction = objectiveFunction;
    }
    
    
}
