/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapObjects;

import java.util.ArrayList;
import java.util.Set;

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
        int minPopulation = Integer.MAX_VALUE;
        District lowestPopulationDistrict = districts.get(1);
        
        for (int i = 2; i < districts.size(); i++) {
            if (districts.get(i).getPopulation() < minPopulation) {
                lowestPopulationDistrict = districts.get(i);
            }    
        }
        return lowestPopulationDistrict;
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
}
