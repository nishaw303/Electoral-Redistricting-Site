package algorithm;

import mapObjects.District;
import mapObjects.Precinct;
import mapObjects.State;
import properties.PropertiesManager;
import seeding.SeedStrategy;

public class RegionGrowing extends Algorithm {
	
	private SeedStrategy seedStrategy;
	private static double RegionGrowingThreshold;

	public RegionGrowing(State s, ObjectiveFunction of, SeedStrategy seedStrategy) {
		super();
		this.currentState = s;
		this.objectiveFunction = of;
		this.seedStrategy = seedStrategy;
		RegionGrowingThreshold = Double
				.parseDouble(PropertiesManager.getInstance().getValue("RegionGrowingThreshold"));
	}

	@Override
	public void run() {
		seedStrategy.seed(currentState);
		District unassigned = currentState.getUnassignedDistrict();
		while (unassigned.getPrecincts().size() > currentState.getNumPrecincts() * RegionGrowingThreshold) {
			District d = this.selectDistrictToGrow();
			Precinct precinctToMove = d.getRandomCandidate();
			Move tempMove = new Move(precinctToMove, unassigned, d);
			tempMove.setIsFinalized(true);
			moves.push(tempMove);
			d.addPrecinct(precinctToMove);
			unassigned.removePrecinct(precinctToMove);
		}
		while (this.checkTerimanationConditions() != true) {
			District d = this.selectDistrictToGrow();
			Precinct precinctToMove = d.findMovablePrecinct(currentState, objectiveFunction);
			Move tempMove = new Move(precinctToMove, unassigned, d);
			tempMove.setIsFinalized(true);
			moves.push(tempMove);
			d.addPrecinct(precinctToMove);
			unassigned.removePrecinct(precinctToMove);
		}
	}

	@Override
	protected boolean checkTerimanationConditions() {
		District unassigned = this.currentState.getUnassignedDistrict();
		return unassigned.getPrecincts().isEmpty();
	}

	private District selectDistrictToGrow() {
		return this.currentState.getLowestPolulationDistrict();
	}

	public void setSeedStrategy(SeedStrategy seedStrategy) {
		this.seedStrategy = seedStrategy;
	}
}
