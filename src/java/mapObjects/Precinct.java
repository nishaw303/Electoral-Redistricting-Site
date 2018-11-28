/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapObjects;

import java.time.Year;
import java.util.ArrayList;
import java.util.Map;

import dataTypes.VotingData;

/**
 *
 * @author spitlord
 */
public class Precinct {
    
    private int ID;
    private int districtID;
    private String name;
    private int population;
    private double area;
    private ArrayList<Precinct> neighbors;
    private Map<Year, VotingData> votingData;
    // isMovable
    private boolean isBorder;
    // points: List<Point>

    public ArrayList<Precinct> getNeighbors() {
        return neighbors;
    }

    public int getDistrictID() {
        return districtID;
    }
    
    public boolean isBorder() {
    	return isBorder;
    }

    public Map<Year, VotingData> getVotingData() {
        return votingData;
    }

    public double getArea() {
        return area;
    }

    public int getPopulation() {
        return population;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }
    
    @Override
    public int hashCode() {
    	return (int) ID * name.hashCode();
    }
    
    @Override
    public boolean equals(Object o) {
    	if (o instanceof Precinct) {
    		if (this.ID == ((Precinct) o).ID && this.name == ((Precinct) o).name) {
    			return true;
    		}
    	}
    	return false;
    }
}
