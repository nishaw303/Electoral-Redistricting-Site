/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

import mapObjects.Precinct;
import mapObjects.State;

/**
 *
 * @author spitlord
 */
public class SimulatedAnnealing extends Algorithm {

    

    public SimulatedAnnealing(State state) {
        super();
        this.currentState = state;
    }
    
    

    @Override
    public void run() {
    }

    @Override
    protected boolean checkTerimanationConditions() {
        return true;
    }
    
    private Precinct findMovablePrecinct() {
        return null;
    }
    
    
}
