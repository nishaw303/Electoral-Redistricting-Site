package algorithm;

import java.util.Stack;

import mapObjects.State;

public abstract class Algorithm {

	protected double currentObjectiveValue;
	protected ObjectiveFunction objectiveFunction;
	protected State currentState;
	protected Stack<Move> moves;
        protected int sp;

	abstract public void run();

	abstract protected boolean checkTerimanationConditions();
        
        public Algorithm() {
            sp = 0;
            moves = new Stack<>();
        }

    public Stack<Move> getMoves() {
        return moves;
    }

    public int getSp() {
        return sp;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }
    
    
    
   
    
    

        
}
