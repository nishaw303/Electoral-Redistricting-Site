/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithm;

import mapObjects.District;
import mapObjects.Precinct;
import seeding.SeedStrategy;

/**
 *
 * @author spitlord
 */
public class RegionGrowing extends Algorithm {
    
   private SeedStrategy seedStrategy;

    public RegionGrowing(SeedStrategy seedStrategy) {
       super();
       this.seedStrategy = seedStrategy;
    }

    @Override
    public void run() {
    }

    @Override
    public boolean checkTerimanationConditions() {
        return true;
    }
    
    private Precinct findBestMovablePrecinct() {
        return null;
    }
    
    public District selectDistrictToGrow() {
        return null;
    }
    
    public void setSeedStrategy(SeedStrategy seedStrategy) {
        this.seedStrategy = seedStrategy;
    }
    
    
    
    
    

    
   
    
    
    
}
