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
import java.io.File;
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
public class SaveMapServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Algorithm a = (Algorithm) req.getSession().getAttribute("algorithm");
        String fileName = req.getParameter("name");
        System.out.println("");
        System.out.println("sukabanw");
        System.out.println("sukabanw");

        System.out.println("sukabanw");

        int sp = a.getSp();
        ArrayList<MovesShort> moves = new ArrayList<>();
        for (int i = 0; i < sp; i++) {
            moves.add(MovesShort.toShort(a.getMoves().get(i)));
        }

//        FileWriter f = new FileWriter("suka.txt");
//      
//        f.close();
        resp.setContentType("application/text");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("suka eat this file");
        int asuka = 0;

        try {
            File f = new File("/Users/spitlord/NetBeansProjects/election/saving/SICSUKA.json");
            FileWriter fw = new FileWriter(f);
            Gson gson = new GsonBuilder().create();
            gson.toJson(moves, fw);
            fw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("saving is fucked");
        }
    }

//    public static save() {}
}
