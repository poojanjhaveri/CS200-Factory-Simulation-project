import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Nest object
 * has a threshold
 * @author James Dalton
 *
 */


public class NestAgent {
	//private List<Part, Integer> inventory = new HashMap<Part, Integer>();
	private List<Part> parts =
    		Collections.synchronizedList(new ArrayList<Part>());
	PartsAgent partsagent;
	LaneAgent lane;
	CameraAgent camera;
	private int threshold;
	private Part part;
	private int howMany = 0;
	private int nestNumber;
	
	//public enum NestNumber { one, two, three, four, five, six, seven, eight};

	NestAgent(int nestNum, LaneAgent lane){
		this.nestNumber = nestNum;
		this.lane = lane;
	}
//messages
	/**
	 * This message checks if the part
	 * @param p This is a part
	 */
	public void msgNeedPart(Part p){
		if (part == p)
			partsagent.msgHereAreParts(part, howMany);
		else
			lane.msgNeedPart(p);
	}
	
	public void msgHereAreParts(Part p, int quantity){
		part = p;
		threshold = 10/p.size;
		howMany += quantity;
		if (howMany>threshold){
			this.lane.msgRejectParts(howMany - threshold);
			howMany = threshold;
			camera.msgNestIsFull(nestNumber);
		}
		
	}
	
	public void msgNestVerified(boolean result){
		partsagent.msgHereAreParts(part, howMany);
	}
	//scheduler
	protected boolean pickAndExecuteAnAction() {
		
		return false;
	}
	
	//actions
	
	public void setPartsAgent(PartsAgent parts){
		this.partsagent = parts;
	}
	
	getPart
}
