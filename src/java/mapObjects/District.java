/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapObjects;

import java.util.PriorityQueue;
import java.util.Set;

/**
 *
 * @author spitlord
 */
public class District {
    
    private int ID;
    private int stateID;
    private Set<Precinct> precincts;
    private int population;
    private PriorityQueue<Precinct> candidates;
    
    
    
    public Precinct getPrecinctById(int id) {
        return null;
    }
    
    // done
    public boolean addPrecinct(Precinct precinct) {
        if (precincts.add(precinct)) {
            updatePopulation(population + precinct.getPopulation());
            return true;
        }
        return false;
    }
    
    // done
    public boolean removePrecinct(Precinct precinct) {
        if (precincts.remove(precinct)) {
            updatePopulation(population - precinct.getPopulation());
            return true;
        }
        return false;
    }

    // done
    public PriorityQueue<Precinct> getCandidates() {
        return candidates;
    }

    // done
    public Set<Precinct> getPrecincts() {
        return precincts;
    }
    
    // done
    public void updatePopulation(int population) {
        this.population = population;
    }
    
    
    public double getPerimeter() {
        return 0;
    }
    
    public double getArea() {
        return 0;
    }
    
    public int getID() {
        return ID;
    }

    public int getStateID() {
        return stateID;
    }

    public int getPopulation() {
        return population;
    }
    
    
}
