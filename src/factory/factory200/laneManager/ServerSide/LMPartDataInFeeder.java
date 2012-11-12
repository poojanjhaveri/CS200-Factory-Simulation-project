package factory.factory200.laneManager.ServerSide;

import java.util.ArrayList;

public class LMPartDataInFeeder {

	LMServerMain serverMain;
	
	private LMPart newPart;
	private ArrayList<LMPart> parts = new ArrayList<LMPart>();
	private int feederNum;
	
	public LMPartDataInFeeder(int feederNum, LMServerMain serverMain){
		this.feederNum = feederNum;
		this.serverMain = serverMain;
	}
	
	public void addPart(int partNum, int quantity){
		// When rear gate is open, no use putting parts into feeder
		if( serverMain.getForAgentFeeder().getFeeder(feederNum).getRearGateSwitch() == false ){
			for(int i=0 ; i<quantity ; i++){
				newPart = new LMPart(partNum);
				parts.add(newPart);
			}
		}
	}
	
	public int getFirstPartNum(){
		return parts.get(0).getPartNum();
	}
	
	public LMPart getPartFromFeederToLane(){
		return parts.remove(0);
	}
	
	public void rearGateOpen(){
		parts.clear();
	}
	
	public int getSize(){
		return parts.size();
	}
}
