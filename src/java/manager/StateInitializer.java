package manager;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import dataTypes.StateName;
import mapObjects.Precinct;
import mapObjects.State;

public class StateInitializer {

	public static State initializeState(StateName name, File jsonFile) {
		Map<Integer, Precinct> precincts = new HashMap<Integer, Precinct>();
		State state = new State(name, precincts);
		return state;
	}

}
