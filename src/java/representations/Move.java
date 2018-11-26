/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representations;

/**
 *
 * @author spitlord
 */
public class Move {
    
  private Precinct precinct;
  private District sourceDistrict;
  private District destinationDistrict;
  private boolean isFinalized;  // we keep this?

    public Move(Precinct precinct, District sourceDistrict, District destinationDistrict) {
        this.precinct = precinct;
        this.sourceDistrict = sourceDistrict;
        this.destinationDistrict = destinationDistrict;
    }

    public Precinct getPrecinct() {
        return precinct;
    }

    public District getSourceDistrict() {
        return sourceDistrict;
    }

    public District getDestinationDistrict() {
        return destinationDistrict;
    }

    public void setPrecinct(Precinct precinct) {
        this.precinct = precinct;
    }

    public void setSourceDistrict(District sourceDistrict) {
        this.sourceDistrict = sourceDistrict;
    }

    public void setDestinationDistrict(District destinationDistrict) {
        this.destinationDistrict = destinationDistrict;
    }
    
    public void setIsFinalized(boolean isFinalized) {
        this.isFinalized = isFinalized;
    }
    
    
  
  


    
}
