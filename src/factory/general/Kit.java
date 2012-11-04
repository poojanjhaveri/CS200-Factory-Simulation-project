package factory.general;

import java.util.ArrayList;
import java.util.List;

/**
 * Kit Object
 *
 * @brief Kit Object
 * @author James Dalton
 *
 */
public class Kit {

    /** CS 201 stuff */
    public enum Status {
        empty, full, verified, error
    };
    public static enum StandNum {
        none, zero, one, two
    };
    
    public String name;
    public Status status;
    public StandNum standNum;
    private List<Part> parts = new ArrayList<Part>();
    public int kitNeedsParts;

    public Kit(String name) {
        this.name = name;
        this.status = Status.empty;
        this.standNum = StandNum.none;
//        this.kitNeedsParts = p.length;
    }
    
    public void addPart(Part p) {
        parts.add(p);
    }
    
    /** End CS 201 stuff*/
}