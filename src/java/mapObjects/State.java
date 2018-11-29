package mapObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import algorithm.Move;
import dataTypes.Representative;
import dataTypes.ShortStateName;
import dataTypes.StateName;

public class State {

	private StateName name;
	private ShortStateName shortName;
	private int ID;
	private ArrayList<District> districts;
	private District unassigned;
	private Set<Precinct> neighborPrecincts;
	private Set<Representative> representatives;
	private int numPrecincts;
	private int numDistricts;

	public State(StateName name, ShortStateName shortName, int ID, Set<Precinct> precincts) {
		this.name = name;
		this.ID = ID;
		this.numDistricts = 0;
		this.numPrecincts = precincts.size();
		this.unassigned = new District(this);
	}

	public boolean makeMove(Move move) {
		return true;
	}

	public double compareToState(State otherState) {
		return 0;
	}

	public int getNumPrecincts() {
		return this.numPrecincts;
	}

	public boolean isAdjacenct(Precinct p1, Precinct p2) {
		if (p1.getNeighbors().contains(p2))
			return true;
		return false;
	}

	public District getUnassignedDistrict() {
		return this.unassigned;
	}

	public District getLowestPolulationDistrict() {
		return Collections.min(districts, (District d1, District d2) -> {
			return ((Integer) d1.getPopulation()).compareTo(d2.getPopulation());
		});
	}

	public Set<Representative> getRepresentatives() {
		return representatives;
	}

	public int getNumDistricts() {
		return numDistricts;
	}

	public int getID() {
		return this.ID;
	}

	public void addDistrict(District district) {
		if (this.districts == null) {
			this.districts = new ArrayList<District>();
		}
		this.districts.add(district);
		district.setID(this.districts.size());
	}

	public District getRandomDistrict() {
		return this.districts.get(ThreadLocalRandom.current().nextInt(this.getNumDistricts()));
	}

	public District getDistrict(int distID) {
		for (District d : districts) {
			if (distID == d.getID()) {
				return d;
			}
		}
		return null;
	}
}
