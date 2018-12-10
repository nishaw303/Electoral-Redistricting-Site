package manager;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;

import dataTypes.StateName;
import mapObjects.State;

public class StateManager {

	private static StateManager instance = null;
	private Map<StateName, State> loadedStates;

	protected StateManager() {
		loadedStates = new HashMap<StateName, State>();
	}

	public static StateManager getInstance() {
		if (instance == null) {
			instance = new StateManager();
		}
		return instance;
	}

	public Map<StateName, State> getStates() {
		return loadedStates;
	}

	public State getState(StateName name) {
		if (loadedStates.containsKey(name)) {
			return loadedStates.get(name);
		}
		return this.initializeState(name);
	}

	private State initializeState(StateName name) {
		EntityManager em = MapEntityManagerFactory.getInstance().getEntityManagerFactory().createEntityManager();
		State s = em.find(State.class, name);
		em.close();
		return s;
	}

}
