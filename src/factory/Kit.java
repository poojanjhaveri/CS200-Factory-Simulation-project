package factory;

/**
 * Kit Object
 *
 * @brief Kit Object
 * @author James Dalton
 *
 */
public class Kit {

    public Part parts[];

    public enum Status {

        empty, full, verified, error
    };
    public Status status;
    public int kitNeedsParts;

    public static enum KittingStandNumber {

        none, one, two, three
    };
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
}