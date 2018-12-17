/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import algorithm.Algorithm;
import algorithm.MovesShort;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author spitlord
 */
public class SaveMap extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            Algorithm a = (Algorithm)req.getSession().getAttribute("algorithm");
            int sp = a.getSp();
            ArrayList<MovesShort> moves = new ArrayList<>();
            for (int i = 0; i < sp; i++) {
                
            
        }
            
            try (Writer writer = new FileWriter("Output.json")) {
            Gson gson = new GsonBuilder().create();
//            gson.toJson(users, writer);
}
            
            

    }
    
    
    
    
    
    
    
    
    
    
//    public static save() {}
    
    
    
    
    
    
    
    
}
