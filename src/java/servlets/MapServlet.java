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
import algorithm.Move;
import algorithm.MovesShort;
import algorithm.ObjectiveFunction;
import algorithm.RegionGrowing;
import com.google.gson.Gson;
import dataTypes.StateName;
import java.awt.Toolkit;
import java.util.ArrayList;
import manager.MapEntityManagerFactory;
import manager.StateManager;
import mapObjects.State;
import seeding.IncumbentSeedStrategy;
import seeding.RandomSeedStrategy;
import seeding.SeedStrategy;

public class MapServlet extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("enter this dumb servlets");
        System.out.println("enter this dumb servlets");
        System.out.println("araygo");
        Toolkit.getDefaultToolkit().beep();
        
        ObjectiveFunction objectiveFunction = new ObjectiveFunction(retrieveMetrics(request));
        SeedStrategy seedStrategy = new RandomSeedStrategy(); 
        StateName stateName = StateName.OREGON;
        State s = StateManager.getState(stateName.OREGON);
        
        s.setNumDistricts(5);
        System.out.println(s.unassigned.getPrecincts().size());
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        int a = 10;
        response.getWriter().write((new Gson()).toJson(a));
//        response.
        
 

        Toolkit.getDefaultToolkit().beep();

        
        RegionGrowing regionGrowing = new RegionGrowing(
                s,
                objectiveFunction,
                seedStrategy
        );


        // now all servlets have access to the algorithms
        
        request.getSession().setAttribute("algorithm", regionGrowing);
        regionGrowing.run();
        Toolkit.getDefaultToolkit().beep();
        System.out.println("cum plete");
        
       
      
        
  
        
    }
    
    private Map<Metric, Double> retrieveMetrics(HttpServletRequest request) {
        Map<Metric, Double> metrics = new HashMap<Metric, Double>();
//        metrics.put(Metric.COMPACTNESS, Double.valueOf(request.getParameter("compactness")));
//        metrics.put(Metric.POLTSBY_POPPER, Double.valueOf(request.getParameter("polsby")));
//        metrics.put(Metric.SCHWARTZBERG, Double.valueOf(request.getParameter("schwartzberg")));
//        metrics.put(Metric.REOCK, Double.valueOf(request.getParameter("reock")));
//        metrics.put(Metric.PARTISANFAIRNESS, Double.valueOf(request.getParameter("partisanFairness")));
//        metrics.put(Metric.POPOULATIONEQUALITY, Double.valueOf(request.getParameter("consistency")));
//        metrics.put(Metric.CONSISTENCY, Double.valueOf(request.getParameter("gerrymandering")));
//        metrics.put(Metric.GERRYMANDERING, Double.valueOf(request.getParameter("populationWeight")));
//        metrics.put(Metric.ALIGNMENT, Double.valueOf(request.getParameter("comppopulationWeightactness")));
        metrics.put(Metric.COMPACTNESS, 0.5);
        metrics.put(Metric.POLTSBY_POPPER, 0.8);
        metrics.put(Metric.SCHWARTZBERG, 0.0);
        metrics.put(Metric.REOCK, 0.0);
        metrics.put(Metric.PARTISANFAIRNESS, 0.0);
        metrics.put(Metric.POPOULATIONEQUALITY, 0.0);
        metrics.put(Metric.CONSISTENCY, 0.0);
        metrics.put(Metric.GERRYMANDERING, 0.0);
        metrics.put(Metric.ALIGNMENT, 0.1);
        metrics.put(Metric.EFFICIENCYGAP, 0.1);
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
