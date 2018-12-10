package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import algorithm.Metric;
import algorithm.ObjectiveFunction;
import algorithm.RegionGrowing;
import algorithm.SimulatedAnnealing;
import dataTypes.StateName;
import manager.MapEntityManagerFactory;
import manager.StateManager;
import seeding.IncumbentSeedStrategy;
import seeding.RandomSeedStrategy;
import seeding.SeedStrategy;

public class MapServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		boolean isLoaded = Boolean.valueOf(request.getParameter("isLoaded"));
		if (!isLoaded) {
			MapEntityManagerFactory.getInstance()
					.setEntityManagerFactory((EntityManagerFactory) getServletContext().getAttribute("emf"));
			ObjectiveFunction objectiveFunction = new ObjectiveFunction(retrieveMetrics(request));
			SeedStrategy seedStrategy = retrieveSeedingStrategy(request);
			StateName stateName = StateName.valueOf(request.getParameter("stateName"));
			RegionGrowing regionGrowing = new RegionGrowing(StateManager.getInstance().getState(stateName),
					objectiveFunction, seedStrategy);
			regionGrowing.run();
			SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(
					StateManager.getInstance().getState(stateName), objectiveFunction);
			simulatedAnnealing.run();
		} else {
		}
	}

	private Map<Metric, Double> retrieveMetrics(HttpServletRequest request) {
		Map<Metric, Double> metrics = new HashMap<Metric, Double>();
		metrics.put(Metric.COMPACTNESS, Double.valueOf(request.getParameter("compactness")));
		metrics.put(Metric.PARTISANFAIRNESS, Double.valueOf(request.getParameter("partisanFairness")));
		metrics.put(Metric.POPOULATIONEQUALITY, Double.valueOf(request.getParameter("consistency")));
		metrics.put(Metric.CONSISTENCY, Double.valueOf(request.getParameter("gerrymandering")));
		metrics.put(Metric.GERRYMANDERING, Double.valueOf(request.getParameter("populationWeight")));
		metrics.put(Metric.ALIGNMENT, Double.valueOf(request.getParameter("comppopulationWeightactness")));
		return metrics;
	}

	private SeedStrategy retrieveSeedingStrategy(HttpServletRequest request) {
		SeedStrategy seedStrategy = null;
		if (request.getParameter("seedStrategy").equalsIgnoreCase("random")) {
			seedStrategy = new RandomSeedStrategy();
		} else if (request.getParameter("seedStrategy").equalsIgnoreCase("incumbent")) {
			seedStrategy = new IncumbentSeedStrategy();
		}
		return seedStrategy;
	}

	@Override
	public String getServletInfo() {
		return "Short description";
	}

}
