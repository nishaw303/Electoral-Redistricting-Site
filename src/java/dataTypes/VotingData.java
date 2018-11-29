package dataTypes;
import java.time.Year;
import java.util.Map;

public class VotingData {  
    private Year year;
    private Map<ElectionType, Map<Party, Integer>> data;
   ///+ getResults(): Map<Party, Integer>
   ///+ getElectionType(et: ElectionType): Map<Party, Integer>
   //getPartyResults(party: Party): Integer
   ///+ getYear(): Year
}
