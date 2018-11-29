/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import dataTypes.StateName;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import mapObjects.State;

/**
 *
 * @author spitlord
 */
public class StateManager {
    
    // sets have no get-like methods
    private HashMap<StateName, State> loadedStates;

    public HashMap<StateName, State> getStates() {
        return loadedStates;
    }
    
    public State getState(StateName name) {
        return loadedStates.get(name);
    }
    
    
    
    public void loadStates() {
      
        
    }
    
    
     public State initializeState(StateName name) {
        State state = new State();
        return state;
    }


    
}
