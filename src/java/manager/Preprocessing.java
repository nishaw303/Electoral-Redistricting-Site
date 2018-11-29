/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manager;

import com.google.gson.Gson;
import dataTypes.StateName;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapObjects.State;

/**
 *
 * @author spitlord
 */
public class Preprocessing {

    public static State initializeStateFromFile(StateName name, String filePath) {
        Gson j = new Gson();
        String string;
        try {
            string = new String(Files.readAllBytes(Paths.get(filePath)));
            State s = j.fromJson(string, State.class);
            System.out.println(s.getUnassignedDistrict().getPrecincts().size());
        } catch (IOException ex) {
            System.out.println("Could not load the file");
        }
        return null;
    }


    
}
