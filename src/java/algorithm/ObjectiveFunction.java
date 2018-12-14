package algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import mapObjects.District;
import mapObjects.Point;
import mapObjects.Precinct;
import mapObjects.State;

public class ObjectiveFunction {

	private Map<Metric, Double> metrics;

	public ObjectiveFunction(Map<Metric, Double> metrics) {
		this.metrics = metrics;
		normalizeMetrics();
	}
	
	public void normalizeMetrics() {
		Map<Metric, Double> metrics = new HashMap<Metric, Double>(this.metrics);
		Map<Metric, Double> normalizedMetrics = new HashMap<Metric, Double>();
		Map<Metric, Double> compactnessMetrics = new HashMap<Metric, Double> ();
		
		compactnessMetrics.put(Metric.POLTSBY_POPPER, metrics.remove(Metric.POLTSBY_POPPER));
		compactnessMetrics.put(Metric.SCHWARTZBERG, metrics.remove(Metric.SCHWARTZBERG));
		compactnessMetrics.put(Metric.REOCK, metrics.remove(Metric.REOCK));
		
		Iterator<Entry<Metric, Double>> it = metrics.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Metric, Double> pair = it.next();
			double normalized = (pair.getValue() - Collections.max(metrics.values())) / (
					Collections.max(metrics.values()) - Collections.min(metrics.values()));
			normalizedMetrics.put(pair.getKey(), normalized);
		}
		
		it = compactnessMetrics.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Metric, Double> pair = it.next();
			double normalized = (pair.getValue() - Collections.max(compactnessMetrics.values())) / (
					Collections.max(compactnessMetrics.values()) - Collections.min(compactnessMetrics.values()));
			normalizedMetrics.put(pair.getKey(), normalized);
		}
		
		this.metrics = normalizedMetrics;
	}

	public void setWeight(Metric metric, double val) {
		this.metrics.put(metric, val);
	}

	public void setMetrics(HashMap<Metric, Double> metrics) {
		this.metrics = metrics;
		normalizeMetrics();
	}

	public double calculateObjectiveFunctionValue(State state, Move move) {
		if (state.getUnassignedDistrict().getID() == move.getSourceDistrict().getID()) {
			// Move is from Region Growing algorithm
		}
		else {
			// Move is from Simulated Annealing algorithm
		}
		return 0;
	}

	// District level
	private double calcCompactness(State state) {
		int counter = 0;
		double[] totals = {0, 0, 0};
		for (District d : state.getDistricts()) {
			totals[0] += this.calcCompPP(d);
			totals[1] += this.calcCompSchwartz(d);
			totals[2] += this.calcCompReock(d);
			counter++;
		}
		return this.metrics.get(Metric.COMPACTNESS) * (
				this.metrics.get(Metric.POLTSBY_POPPER) * totals[0] / counter +
				this.metrics.get(Metric.SCHWARTZBERG) * totals[1] / counter+
				this.metrics.get(Metric.REOCK) * totals[2] / counter);
	}
	
	private class PointsAndArea {
		ArrayList<Point> points;
		double area;
		
		public PointsAndArea(ArrayList<Point> points, int area) {
			this.points = points;
			this.area = area;
		}
	}
	
	private PointsAndArea calcPointsAndArea(District d) {
		int totalArea = 0;
		ArrayList<Point> allPoints = new ArrayList<Point>();
		Iterator<Entry<Integer, Precinct>> it = d.getPrecincts().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Precinct> pair = it.next();
			totalArea += pair.getValue().getArea();
			allPoints.addAll(pair.getValue().getPoints());
			it.remove();
		}
		return new PointsAndArea(allPoints, totalArea);
	}
	
	private double calcPerimeter(ArrayList<Point> points) {
		ConcaveHull concaveHull = new ConcaveHull();
		ArrayList<Point> hull = concaveHull.calculateConcaveHull(points, 3);
		double perimeter = concaveHull.euclideanDistance(hull.get(0), hull.get(hull.size()));
		for (int i = 0; i < hull.size() - 1; i++) {
			perimeter += concaveHull.euclideanDistance(hull.get(i), hull.get(i + 1));
		}
		return perimeter;
	}
	
	private double calcCompPP(District d) {
		PointsAndArea pa = calcPointsAndArea(d);
		return 4 * Math.PI * (pa.area / Math.pow(calcPerimeter(pa.points), 2));
	}
	
	private double calcCompSchwartz(District d) {
		PointsAndArea pa = calcPointsAndArea(d);
		double C = 2 * Math.PI * Math.sqrt(pa.area / Math.PI);
		return 1 / (calcPerimeter(pa.points) / C);
	}
	
	private double calcCompReock(District d) {
		PointsAndArea pa = calcPointsAndArea(d);
		return pa.area / SmallestEnclosingCircle.makeCircle(pa.points).getArea();
	}

	// State level
	private double calcPopulationEquality(State state) {
		return 0;
	}

	// State level
	private double calcPartisanFairness(State state) {
		return 0;
	}
	
	// State level
	private double calcEfficiencyGap(District d) {
		return 0;
	}

}
