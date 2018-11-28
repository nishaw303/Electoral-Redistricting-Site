package seeding;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import mapObjects.District;
import mapObjects.Precinct;
import mapObjects.State;

public class RandomSeedStrategy implements SeedStrategy {

    @Override
    public void seed(State s) {
    	District unassigned = s.getUnassignedDistrict();
    	HashSet<Precinct> seeds = new HashSet<Precinct>();
    	for (int i = 0; i < s.getNumDistricts();) {
    		int rand = ThreadLocalRandom.current().nextInt(unassigned.getNumPrecincts());
    		if (!seeds.contains(unassigned.getPrecinctById(rand))){
    			seeds.add(unassigned.getPrecinctById(rand));
    			i++;
    		}
    	}
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
