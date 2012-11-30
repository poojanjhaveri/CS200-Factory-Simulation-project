package factory.factory200.laneManager.ServerSide;

import java.util.ArrayList;

/**
 * @brief Part Information on Nest
 * @author Dongyoung Jung
 */
public class LMPartDataInNest {

	private LMServerMain serverMain;
	private LMPart newPart;
	private ArrayList<LMPart> parts = new ArrayList<LMPart>();
	private int nestNum;
	private String signal = "";
	private ArrayList<Integer> partNumInfos = new ArrayList<Integer>();
	
	public LMPartDataInNest(int nestNum, LMServerMain serverMain){
		this.nestNum = nestNum;
		this.serverMain = serverMain;
		for(int i=0 ; i<8 ; i++){
			partNumInfos.add(0);
		}
	}
	/*
	public void addPart(int partNum, int quantity){
		for(int i=0 ; i<quantity ; i++){
			newPart = new LMPart(partNum);
			parts.add(newPart);
		}
	}
	*/
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
		signal = "LMA_";
		parts.add(newPartFromLane);
		if(parts.size() == 8){
		//	serverMain.getGantryAgent().msgAnimationComplete("Nest"+nestNum);
		}
		signal += nestNum + "_" + newPartFromLane.getPartNum();
		serverMain.sendToKAM(signal);
                serverMain.sendToFPM(signal);
	}
}