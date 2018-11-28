/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataTypes;

import java.time.Year;
import java.util.Map;

/**
 *
 * @author spitlord
 */
public class VotingData {
    
    private Year year;
    private Map<ElectionType, Map<Party, Integer>> data;
   ///+ getResults(): Map<Party, Integer>
   ///+ getElectionType(et: ElectionType): Map<Party, Integer>
   //getPartyResults(party: Party): Integer
    ///+ getYear(): Year
}
