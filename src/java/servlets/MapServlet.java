package servlets;
import algorithm.Metric;
import algorithm.ObjectiveFunction;
import algorithm.RegionGrowing;
import algorithm.SimulatedAnnealing;
import mapObjects.State;
import dataTypes.StateName;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import seeding.IncumbentSeedStrategy;
import seeding.RandomSeedStrategy;
import seeding.SeedStrategy;

//@WebServlet(name = "MapServlet", urlPatterns = {"/map"})
public class MapServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean isLoaded = Boolean.valueOf(request.getParameter("isLoaded"));
        if (!isLoaded) {
            ObjectiveFunction objectiveFunction = new ObjectiveFunction(retrieveMetrics(request));
            SeedStrategy seedStrategy = retrieveSeedingStrategy(request);
            StateName stateName = StateName.valueOf(request.getParameter("stateName"));
            RegionGrowing regionGrowing = new RegionGrowing(
                    new State(StateName.OHIO),// TODO: state manager? how to use? static!!!!!
                    objectiveFunction,
                    seedStrategy
            );
            regionGrowing.run();
            SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing(new State()); //  State manager
        } else {
        }
    }

    private Map retrieveMetrics(HttpServletRequest request) {
        Map<Metric, Double> metrics = new HashMap();
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
        if (request.getParameter("seedStrategy").equals("random")) {
            seedStrategy = new RandomSeedStrategy();
        } 
        else if (request.getParameter("seedStrategy").equals("incumbent")) {
            seedStrategy = new IncumbentSeedStrategy();
        }
        return seedStrategy;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
