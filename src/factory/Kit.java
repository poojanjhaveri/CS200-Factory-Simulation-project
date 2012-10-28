/**
 * Kit Object
 * @author James Dalton
 *
 */
public class Kit {

public Part parts[];
public enum Status {empty, full, inspected};
public Status status;
public int kitNeedsParts;
public static enum KittingStandNumber {none, one, two, three};
public KittingStandNumber kittingStandNumber;

public Kit(Part p[]){
this.kittingStandNumber = KittingStandNumber.none;
this.status = Status.empty;
this.kitNeedsParts = p.length;
for (int i=0; i<p.length; i++){
	parts[i] = p[i];
}
}



}