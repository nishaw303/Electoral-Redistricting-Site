/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson g = new Gson();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        
        Suka s = new Suka();
        s.id = (int)(Math.random() * 1000);
        resp.getWriter().write(g.toJson(s));
    }
    
    class Suka {
        int id;
    }
    
    
    
    
    
}
