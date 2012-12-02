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
	private int badPartQuantity;
	private String signal = "";
	private ArrayList<Integer> partNumInfos = new ArrayList<Integer>();
	
	public LMPartDataInNest(int nestNum, LMServerMain serverMain){
		this.nestNum = nestNum;
		this.serverMain = serverMain;
		for(int i=0 ; i<8 ; i++){
			partNumInfos.add(0);
		}
	}
	
	public boolean checkAllBadParts(){
		badPartQuantity = 0;
		for( int i=0 ; i<parts.size() ; i++ ){
			if( parts.get(i).getPartStatus() == 0 ){
				badPartQuantity++;
			}
		}
		if( badPartQuantity == 8 ){
			return true;
		}
		return false;
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
		signal = "LMA_";
		parts.add(newPartFromLane);
		
		//send message to proper feeder msgAnimationComplete();
		
		if(parts.size() == 8){
			if( nestNum == 0 || nestNum == 1 ){
				serverMain.getFeederAgents()[0].msgAnimationComplete();
			}
			else if( nestNum == 2 || nestNum == 3 ){
				serverMain.getFeederAgents()[1].msgAnimationComplete();
			}
			else if( nestNum == 4 || nestNum == 5 ){
				serverMain.getFeederAgents()[2].msgAnimationComplete();
			}
			else if( nestNum == 6 || nestNum == 7 ){
				serverMain.getFeederAgents()[3].msgAnimationComplete();
			}
        }
		
		
		// For KAM( Getting part from lane
		// Good Part
		if( newPartFromLane.getPartStatus() == 1 ){
			signal += nestNum + "_" + newPartFromLane.getPartNum();
		}
		// Bad Part
		else if( newPartFromLane.getPartStatus() == 0 ){
			signal += nestNum + "_" + 8;
		}
		serverMain.sendToKAM(signal);
        serverMain.sendToFPM(signal);
	}
}