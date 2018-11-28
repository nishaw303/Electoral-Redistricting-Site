package seeding;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import dataTypes.Representative;
import mapObjects.District;
import mapObjects.Precinct;
import mapObjects.State;

public class IncumbentSeedStrategy implements SeedStrategy {

    @Override
    public void seed(State s) {
    	Set<Representative> reps = s.getRepresentatives();
    	District unassigned = s.getUnassignedDistrict();
    	HashSet<Precinct> seeds = new HashSet<Precinct>();
    	reps.forEach((rep) -> {
    		seeds.add(rep.getHomePrecinct());
    	});
    	ArrayList<District> districts = new ArrayList<District>();
    	AtomicInteger i = new AtomicInteger(0);
    	seeds.forEach((seed) -> {
    		District d = new District(s, i.getAndIncrement());
    		districts.add(d);
    		d.addPrecinct(seed);
    		unassigned.removePrecinct(seed);
    	});
    	s.setDistricts(districts);
    }
}
