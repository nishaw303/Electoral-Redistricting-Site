package algorithm;

import java.util.Queue;
import java.util.Set;

import mapObjects.District;
import mapObjects.Precinct;
import mapObjects.State;

public class SimulatedAnnealing extends Algorithm {

	public SimulatedAnnealing(State s) {
		super();
		this.currentState = s;
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
}
