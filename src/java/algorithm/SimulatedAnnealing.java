package algorithm;

import java.util.Queue;
import java.util.Set;

import mapObjects.District;
import mapObjects.Precinct;
import mapObjects.State;
import properties.GetProperties;

public class SimulatedAnnealing extends Algorithm {
	
	protected double noImprovement;
	protected static double noImprovementTolerance;
	protected static double noImprovementThreshold;

	public SimulatedAnnealing(State s) {
		super();
		this.currentState = s;
		this.noImprovementTolerance = Double.valueOf(GetProperties.getInstance().getValue("NoImprovementTolerance"));
		this.noImprovementThreshold = Double.valueOf(GetProperties.getInstance().getValue("NoImprovementThreshold"));
	}

	@Override
	public void run() {
		while (this.checkTerimanationConditions() != true) {
			District d = this.selectDistrictToGrow();
			Precinct precinctToMove = this.findMovablePrecinct(d);
			if (precinctToMove != null) {
				District oldD = currentState.getDistrict(precinctToMove.getDistrictID());
				moves.push(new Move(precinctToMove, oldD, d));
				d.addPrecinct(precinctToMove);
				oldD.removePrecinct(precinctToMove);
			}
		}
	}

	@Override
	protected boolean checkTerimanationConditions() {
		return true;
	}

	private Precinct findMovablePrecinct(District d) {
		Queue<Precinct> candidates = d.getCandidates();
		Set<Precinct> precincts = d.getPrecincts();
		Precinct bestP = null;
		double bestOFV = 0;
		for (Precinct p : candidates) {
			if (p.getDistrictID() != d.getID()) {
				precincts.add(p);
				double currentOFV = objectiveFunction.calculateObjectiveFunction(precincts);
				if (currentOFV > bestOFV) {
					bestOFV = currentOFV;
					bestP = p;
				}
				precincts.remove(p);
			}
		}
		return bestP;
	}

	private District selectDistrictToGrow() {
		return this.currentState.getRandomDistrict();
	}
	
	private int checkImprovement() {
		return 0;
	}
}
