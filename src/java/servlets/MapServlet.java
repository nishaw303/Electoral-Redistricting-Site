/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import algorithm.Metric;
import algorithm.ObjectiveFunction;
import algorithm.RegionGrowing;
import mapObjects.State;
import dataTypes.StateName;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import seeding.RandomSeeding;
import seeding.SeedStrategy;

/**
 *
 * @author spitlord
 */
//@WebServlet(name = "MapServlet", urlPatterns = {"/map"})
public class MapServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 //       response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet MapServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet MapServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
       
       
       boolean isLoaded = Boolean.valueOf(request.getParameter("isLoaded"));
       
      
       
       if(!isLoaded) {
            ObjectiveFunction objectiveFunction = new ObjectiveFunction();
            HashMap<Metric, Double> metrics = new HashMap();
            metrics.put(Metric.COMPACTNESS, Double.valueOf(request.getParameter("compactness")));
            metrics.put(Metric.PARTISANFAIRNESS, Double.valueOf(request.getParameter("partisanFairness")));
            metrics.put(Metric.POPOULATIONEQUALITY, Double.valueOf(request.getParameter("consistency")));
            metrics.put(Metric.CONSISTENCY, Double.valueOf(request.getParameter("gerrymandering")));
            metrics.put(Metric.GERRYMANDERING, Double.valueOf(request.getParameter("populationWeight")));
            metrics.put(Metric.ALIGNMENT, Double.valueOf(request.getParameter("comppopulationWeightactness")));
            objectiveFunction.setMetrics(metrics);
            
            SeedStrategy seedStrategy = null;
            
            if (request.getParameter("seedStrategy").equals("random")){
                seedStrategy = new RandomSeeding();
            }
            else if (request.getParameter("seedStrategy").equals("incumbent")) {
                seedStrategy = new RandomSeeding();
            }
            
            StateName stateName = StateName.valueOf(request.getParameter("stateName"));
            RegionGrowing regionGrowing = new RegionGrowing(
                    StateManager.
                    
            );
                    
            );
            regionGrowing.setObjectiveFunction(objectiveFunction);
            regionGrowing.setSeedStrategy(seedStrategy);
            
            regionGrowing.run();
       }
       else {
           
       }
            
            
            
            
            
            RegionGrowing regionGrowing = new RegionGrowing();
            regionGrowing.setObjectiveFunction(objectiveFunction);
            regionGrowing.setSeedStrategy(seedStrategy);
            
            
            
            
           
           
           
       }
       
       
        SimulatedAnealing simulatedAnealing = new SimulatedAnealing();
          


       SeedStrategy s;
       if ( request.getParameter("seedStrategy").equals("randomS")) {
       }
            
       Algorithm algorithm = new Algorithm();
       algorithm.setObjectiveFunction(objectiveFunction);
       algorithm.run();

        
        
       

        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
