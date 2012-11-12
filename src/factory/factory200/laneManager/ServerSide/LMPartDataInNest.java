package factory.factory200.laneManager.ServerSide;

import java.util.ArrayList;

public class LMPartDataInNest {

	private LMServerMain serverMain;
	private LMPart newPart;
	private ArrayList<LMPart> parts = new ArrayList<LMPart>();
	private int laneNum;
	
	public LMPartDataInNest(int laneNum, LMServerMain serverMain){
		this.laneNum = laneNum;
		this.serverMain = serverMain;
	}
	
	public void addPart(int partNum, int quantity){
		for(int i=0 ; i<quantity ; i++){
			newPart = new LMPart(partNum);
			parts.add(newPart);
		}
	}
	
	public void partRobotTakeOne(){
		if(parts.size() != 0){
			parts.remove(0);
		}
	}
	
	public void nestDown(){
		parts.clear();
	}
	
	public int getSize(){
		return parts.size();
	}
		
	public void addPartFromLane(LMPart newPartFromLane){
		parts.add(newPartFromLane);
	}
	
}