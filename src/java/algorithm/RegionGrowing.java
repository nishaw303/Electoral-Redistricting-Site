package algorithm;

import java.util.PriorityQueue;
import java.util.Set;

import mapObjects.District;
import mapObjects.Precinct;
import properties.GetProperties;
import seeding.SeedStrategy;

public class RegionGrowing extends Algorithm {

	private SeedStrategy seedStrategy;
	private double RegionGrowingThreshold;


	public RegionGrowing(SeedStrategy seedStrategy) {
		super();
		this.seedStrategy = seedStrategy;
		RegionGrowingThreshold = Double.parseDouble(GetProperties.getInstance().getValue("RegionGrowingThreshold"));
	}
    @Override
    public void run() {
    	seedStrategy.seed(currentState);
    	District unassigned = currentState.getUnassignedDistrict();
    	while (unassigned.getPrecincts().size() > currentState.getNumPrecincts() / 2) { // magic
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
}
