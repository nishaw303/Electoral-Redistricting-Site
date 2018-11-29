package algorithm;

import java.util.Map;
import java.util.PriorityQueue;

import mapObjects.District;
import mapObjects.Precinct;
import mapObjects.State;
import properties.GetProperties;
import seeding.SeedStrategy;

public class RegionGrowing extends Algorithm {

	private SeedStrategy seedStrategy;
	private static double RegionGrowingThreshold;

	public RegionGrowing(State s, ObjectiveFunction of, SeedStrategy seedStrategy) {
		super();
		this.currentState = s;
		this.objectiveFunction = of;
		this.seedStrategy = seedStrategy;
		RegionGrowing.RegionGrowingThreshold = Double
				.parseDouble(GetProperties.getInstance().getValue("RegionGrowingThreshold"));
	}

	@Override
	public void run() {
		seedStrategy.seed(currentState);
		District unassigned = currentState.getUnassignedDistrict();
		while (unassigned.getPrecincts().size() > currentState.getNumPrecincts() * RegionGrowingThreshold) {
			District d = this.selectDistrictToGrow();
			Precinct precinctToMove = d.getRandomCandidate();
			moves.push(new Move(precinctToMove, unassigned, d));
			d.addPrecinct(precinctToMove);
			unassigned.removePrecinct(precinctToMove);
		}
		while (this.checkTerimanationConditions() != true) {
			District d = this.selectDistrictToGrow();
			Precinct precinctToMove = this.findBestMovablePrecinct(d);
			moves.push(new Move(precinctToMove, unassigned, d));
			d.addPrecinct(precinctToMove);
			unassigned.removePrecinct(precinctToMove);
		}
	}

	@Override
	protected boolean checkTerimanationConditions() {
		District unassigned = this.currentState.getUnassignedDistrict();
		return unassigned.getPrecincts().isEmpty();
	}

	private Precinct findBestMovablePrecinct(District d) {
		PriorityQueue<Precinct> candidates = d.getCandidates();
		Map<Integer, Precinct> precincts = d.getPrecincts();
		Precinct bestP = null;
		double bestOFV = 0;
		for (Precinct p : candidates) {
			precincts.put(p.getID(), p);
			double currentOFV = objectiveFunction.calculateObjectiveFunction(precincts);
			if (currentOFV > bestOFV) {
				bestOFV = currentOFV;
				bestP = p;
			}
			precincts.remove(p.getID());
		}
		return bestP;
	}

	private District selectDistrictToGrow() {
		return this.currentState.getLowestPolulationDistrict();
	}

	public void setSeedStrategy(SeedStrategy seedStrategy) {
		this.seedStrategy = seedStrategy;
	}
}
