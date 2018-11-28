/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seeding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import mapObjects.District;
import mapObjects.Precinct;
import mapObjects.State;

/**
 *
 * @author spitlord
 */
public class RandomSeeding implements SeedStrategy {

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
