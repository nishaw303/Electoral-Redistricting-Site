package seeding;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import algorithm.Move;
import dataTypes.Representative;
import mapObjects.District;
import mapObjects.Precinct;
import mapObjects.State;

public class IncumbentSeedStrategy implements SeedStrategy {

	@Override
	public Stack<Move> seed(State s) {
		Stack<Move> tempMoves = new Stack<Move>();
		Set<Representative> reps = s.getRepresentatives();
		District unassigned = s.getUnassignedDistrict();
		getSeedsByRep(unassigned, reps);
		for (Precinct seed : unassigned.getSeeds()) {
			District d = new District();
			s.addDistrict(d);
			d.addPrecinct(seed);
			unassigned.removePrecinct(seed);
			tempMoves.add(new Move(seed, unassigned, d));
		}
		return tempMoves;
	}

	public void getSeedsByRep(District unassigned, Set<Representative> reps) {
		Set<Precinct> seeds = new HashSet<Precinct>();
		reps.forEach(rep -> addSeed(rep.getHomePrecinct(), seeds));
		unassigned.setSeeds(seeds);
	}

	public boolean addSeed(Precinct seed, Set<Precinct> seeds) {
		if (seeds.contains(seed)) {
			return false;
		}
		seeds.add(seed);
		return true;
	}
}
