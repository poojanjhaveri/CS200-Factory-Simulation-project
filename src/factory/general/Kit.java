package factory.general;

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
    public static enum KittingStandNumber {

        none, one, two, three
    };
    
    public Status status;
    public int kitNeedsParts;
    public Part parts[];
    public KittingStandNumber kittingStandNumber;
    public int conveyorLocation;

    public Kit(Part p[]) {
        this.kittingStandNumber = KittingStandNumber.none;
        this.conveyorLocation = -1;
        this.status = Status.empty;
        this.kitNeedsParts = p.length;
        System.arraycopy(p, 0, parts, 0, p.length);
    }

    public Kit() {
    }
    public Kit(int loc) {
        
    }
    
    /** End CS 201 stuff*/
}