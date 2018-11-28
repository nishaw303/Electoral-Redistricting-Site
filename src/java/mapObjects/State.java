/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import algorithm.Move;
import dataTypes.Representative;
import dataTypes.ShortStateName;
import dataTypes.StateName;

/**
 *
 * @author spitlord
 */
public class State {
    private StateName name;
    private ShortStateName shortName;
    private int ID;
    private ArrayList<District> districts;
    private District unassigned;
    //adjacentPrecincts: List<Precinct[]>
    private Set<Representative> representatives;
    private int numPrecincts;
    private int numDistricts;
    
    public boolean makeMove(Move move) {
        return true;
    }
    
    public double compareToState(State otherState) {
        return 0;
    }
    
    public int getNumPrecincts() {
    	return this.numPrecincts;
    }
    
    // done
    public boolean isAdjacenct(Precinct p1, Precinct p2) {
        if (p1.getNeighbors().contains(p2)) return true;
        return false;
    }
    
    
    // done
    public District getUnassignedDistrict() {
        return this.unassigned;
    }
    
    
    // done
    public District getLowestPolulationDistrict() {
    	return Collections.min(districts, (District d1, District d2) -> {
    		return ((Integer) d1.getPopulation()).compareTo(d2.getPopulation());
    	});
    }
    
    // done
    public Set<Representative> getRepresentatives() {
        return representatives;
    }
    
    public int getNumDistricts() {
    	return numDistricts;
    }
    
    public int getID(){
    	return this.ID;
    }
    
    public void setDistricts(ArrayList<District> districts) {
    	this.districts = districts;
    }
    
    public District getRandomDistrict() {
    	return this.districts.get(ThreadLocalRandom.current().nextInt(this.getNumDistricts()));
    }
    
    public District getDistrict(int distID) {
    	for (District d: districts) {
    		if (distID == d.getID()) {
    			return d;
    		}
    	}
    	return null;
    }
}
