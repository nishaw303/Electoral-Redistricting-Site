/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

import java.util.Stack;
import representations.Move;
import representations.State;

/**
 *
 * @author spitlord
 */
public abstract class Algorithm {
    
 private double currentObjectiveValue;
 private ObjectiveFunction objectiveFunction;
 private State currentState;
 private int noImprovement;
 private static int noImprovementTolerance;
 private Stack<Move> moves;
    
 
 abstract public void run();
 
 abstract public boolean checkTerimanationConditions();

 
 
}
