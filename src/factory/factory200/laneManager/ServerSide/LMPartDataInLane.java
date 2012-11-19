package factory.factory200.laneManager.ServerSide;

import java.util.ArrayList;

/**
 * @brief Part Information on Lane
 * @author Dongyoung Jung
 */
public class LMPartDataInLane {

	private LMServerMain serverMain;
	private LMPart newPart;
	private ArrayList<LMPart> parts = new ArrayList<LMPart>();
	private int laneNum;
	
	public LMPartDataInLane(int laneNum, LMServerMain serverMain){
		this.laneNum = laneNum;
		this.serverMain = serverMain;
	}
	
	public void addPartFromFeeder(LMPart newPartFromFeeder){
		parts.add(newPartFromFeeder);
	}
	
	public int getSize(){
		return parts.size();
	}
	
	public LMPart getPartFromLaneToNest(){
		return parts.remove(0);
	}
	
	public void removePart(){
		parts.remove(0);
	}
}