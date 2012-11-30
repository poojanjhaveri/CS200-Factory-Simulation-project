package factory.factory200.laneManager.ServerSide;

import java.util.ArrayList;

/**
 * @brief All Part Information
 * @author Dongyoung Jung
 */
public class LMPartData {
	
	private LMServerMain serverMain;
	
	private LMPartDataInFeeder newFeeder;
	private LMPartDataInLane newLane;
	private LMPartDataInNest newNest;
	
	private ArrayList<LMPartDataInFeeder> feeders = new ArrayList<LMPartDataInFeeder>();
	private ArrayList<LMPartDataInLane> lanes = new ArrayList<LMPartDataInLane>();
	private ArrayList<LMPartDataInNest> nests = new ArrayList<LMPartDataInNest>();
	
	private double shakePossibility;
	private int randomChosenPart;
	private int laneNestNum;
	private String signal = "";
	
	public LMPartData(LMServerMain serverMain){
		this.serverMain = serverMain;
		
		for(int i=0 ; i<4 ; i++){
			newFeeder = new LMPartDataInFeeder(i, serverMain);
			feeders.add(newFeeder);
		}
		
		for(int i=0 ; i<8 ; i++){
			newLane = new LMPartDataInLane(i, serverMain);
			lanes.add(newLane);
			newNest = new LMPartDataInNest(i, serverMain);
			nests.add(newNest);
		}
	}
	
	// Feeder : Dump Bin Box Into Feeder - DONE(By Gantry Robot)
	public void addPartToFeeder(int feederNum, int partNum, int quantity, int partStatus){
		feeders.get(feederNum).addPart(partNum, quantity, partStatus);
	}
	
	public void addPartToLaneFromFeeder(int laneNum, int feederNum){
		lanes.get(laneNum).addPartFromFeeder( feeders.get(feederNum).getPartFromFeederToLane() );
	}
	
	public void sendPartToNestFromLane(int laneNestNum){
		nests.get(laneNestNum).addPartFromLane( lanes.get(laneNestNum).getPartFromLaneToNest() );
	}
	
	// Nest : Robot Takes Parts - DONE(By Part Robot)
	public void partRobotTakeOne(int nestNum){
		nests.get(nestNum).partRobotTakeOne();
	}
	
	// Feeder : Rear Gate Open - DONE
	public void rearGateOpen(int feederNum){
		feeders.get(feederNum).rearGateOpen();
	}
	
	// Nest : Nest Down
	public void nestDown(int nestNum){
		nests.get(nestNum).nestDown();
	}
	
	// Size Getter(Feeder)
	public int getFeederPartSize(int feederNum){
		return feeders.get(feederNum).getSize();
	}
	
	// Size Getter(Lane)
	public int getLanePartSize(int laneNum){
		return lanes.get(laneNum).getSize();
	}
	
	// Size Getter(Nest)
	public int getNestPartSize(int nestNum){
		return nests.get(nestNum).getSize();
	}
	
	public int getFirstPartNum(int feederNum){
		return feeders.get(feederNum).getFirstPartNum();
	}
	
	public int getFirstPartStatus(int feederNum){
		return feeders.get(feederNum).getFirstPartStatus();
	}
	
	public boolean checkAllBadParts(int nestNum){
		
		if( nests.get(nestNum).checkAllBadParts() == true ){
			return true;
		}
		return false;
		
	}
}
