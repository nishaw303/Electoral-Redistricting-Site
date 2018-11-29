package mapObjects;

import java.time.Year;
import java.util.ArrayList;
import java.util.Map;

import dataTypes.VotingData;

public class Precinct {

    private int ID;
    private int districtID;
    private int population;
    private double area;
    private ArrayList<Precinct> neighbors;
    private Map<Year, VotingData> votingData;
    private boolean isBorder;
    
    public Precinct(int ID, int area, int population) {
        this.ID = ID;
        this.area = area;
        this.population = population;
        this.neighbors = new ArrayList<Precinct>();
    }

    public ArrayList<Precinct> getNeighbors() {
        return neighbors;
    }

    public int getDistrictID() {
        return districtID;
    }

    public boolean isBorder() {
        return isBorder;
    }

    public Map<Year, VotingData> getVotingData() {
        return votingData;
    }

    public double getArea() {
        return area;
    }

    public int getPopulation() {
        return population;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNeighbors(ArrayList<Precinct> neighbors) {
        this.neighbors = neighbors;
    }

    @Override
    public int hashCode() {
        return ID * (int) area;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Precinct) {
            if (this.ID == ((Precinct) o).ID && this.area == ((Precinct) o).area) {
                return true;
            }
        }
        return false;
    }
}
