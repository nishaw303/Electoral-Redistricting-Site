/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

import java.util.Stack;

import mapObjects.State;

/**
 *
 * @author spitlord
 */
public abstract class Algorithm {
    
 protected double currentObjectiveValue;
 protected ObjectiveFunction objectiveFunction;
 protected State currentState;
 protected int noImprovement;
 protected static int noImprovementTolerance;
 protected Stack<Move> moves;
    
 
 abstract public void run();
 
 abstract public boolean checkTerimanationConditions();
 
}
