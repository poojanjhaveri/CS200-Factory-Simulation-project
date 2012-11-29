package factory.factory200.laneManager.ServerSide;

import java.util.ArrayList;

/**
 * @brief Part Information in Feeder
 * @author Dongyoung Jung
 */
public class LMPartDataInFeeder {

	LMServerMain serverMain;
	
	private LMPart newPart;
	private ArrayList<LMPart> parts = new ArrayList<LMPart>();
	private LMPart removablePart;
	private int feederNum;
	
	public LMPartDataInFeeder(int feederNum, LMServerMain serverMain){
		this.feederNum = feederNum;
		this.serverMain = serverMain;
	}
	
	public void addPart(int partNum, int quantity, int partStatus){ // 0 - bad, 1 - good
		// When rear gate is open, no use putting parts into feeder
		if( serverMain.getForAgentFeeder().getFeeder(feederNum).getRearGateSwitch() == false ){
			for(int i=0 ; i<quantity ; i++){
				newPart = new LMPart(partNum, partStatus);
				parts.add(newPart);
				partLowSensor();
			}
		}
	}
	
	public int getFirstPartNum(){
		return parts.get(0).getPartNum();
	}
	
	public int getFirstPartStatus(){
		return parts.get(0).getPartStatus();
	}
	
	public LMPart getPartFromFeederToLane(){
		removablePart = parts.remove(0);
		partLowSensor();
		purgeSensor();
		return removablePart;
	}
	
	public void rearGateOpen(){
		parts.clear();
		partLowSensor();
		purgeSensor();
	}
	
	public int getSize(){
		return parts.size();
	}
	
	public void partLowSensor(){
		if(parts.size() <= 4){
			serverMain.getForAgentFeeder().setPartLowSensorOn(feederNum);
		}
		else if(parts.size() > 4){
			serverMain.getForAgentFeeder().setPartLowSensorOff(feederNum);
		}
	}
	
	public void purgeSensor(){
		if(parts.size() == 0 && serverMain.getForAgentFeeder().getFeeder(feederNum).getWithBin() == true){
			serverMain.getForAgentFeeder().setPurgeBinSwitchOn(feederNum);
			serverMain.getForAgentGantryRobot().emptyBin(feederNum);
		}
		else if(parts.size() != 0){
			serverMain.getForAgentFeeder().setPurgeBinSwitchOff(feederNum);
		}
	}
}