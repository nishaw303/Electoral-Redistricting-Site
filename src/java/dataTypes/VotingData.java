package dataTypes;
import java.time.Year;
import java.util.Map;

public class VotingData {  
    private Year year;
    private Map<ElectionType, Map<Party, Integer>> data;
    
    public Map<Party, Integer> getResults(ElectionType type) {
        return data.get(type);
    }

    public Year getYear() {
        return year;
    }
}
