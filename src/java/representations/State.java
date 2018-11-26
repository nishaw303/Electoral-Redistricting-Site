/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representations;

import java.util.ArrayList;
import java.util.Set;

/**
 *
 * @author spitlord
 */
public class State {
    private StateName name;
    private ShortStateName shortName;
    private ArrayList<District> districts;
    //adjacentPrecincts: List<Precinct[]>
    private Set<Representative> representatives;
    private int numPrecincts;
    
    public boolean makeMove(Move move) {
        return true;
    }
    
    
    
    public double compareToState(State otherState) {
        return 0;
    }
    
    // done
    public boolean isAdjacenct(Precinct p1, Precinct p2) {
        if (p1.getNeighbors().contains(p2)) return true;
        return false;
    }
    
    
    // done
    public District getUnassignedDistrict() {
        return districts.get(0);
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
    
    
}
