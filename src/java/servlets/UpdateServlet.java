/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import algorithm.Algorithm;
import algorithm.Move;
import algorithm.MovesShort;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateServlet extends HttpServlet {
    
    public static final int MAX_MOVES_SENT = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        Algorithm algorithm = (Algorithm) req.getSession().getAttribute("algorithm");
        
        ArrayList<MovesShort> moves = new ArrayList();
        
        Stack<Move> stack = algorithm.getMoves();
        int sp = algorithm.getSp();
        int difference = stack.size() - sp;
        int numMoves = difference < MAX_MOVES_SENT ? difference : MAX_MOVES_SENT;
        
        for (int i = 0; i < difference; i++) {
            moves.add(MovesShort.toShort(stack.get(sp+ i)));
        } 
        algorithm.setSp(algorithm.getSp() + difference);
        
        Gson g = new Gson();
        
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(g.toJson(moves));
    }
    
    
    
    
  
    
    
    
    
    
}
