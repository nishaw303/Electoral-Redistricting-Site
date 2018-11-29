package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author spitlord
 */

//@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(Integer.MAX_VALUE);
        
        // getting the info from the submitted form
        String username = request.getParameter("username");
        session.setAttribute("username", username);
        
        
        // redirecting to the map page
        response.sendRedirect("/election/map.jsp");
        
        // suka suka
      
       
    }

}
