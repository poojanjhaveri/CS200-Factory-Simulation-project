package factory.factory200.laneManager.ServerSide;

import java.util.ArrayList;
import factory.general.*;

public class LMPartData {
	
	private Server server;
	private LMServerMain serverMain;
	
	private LMPartDataInFeeder newFeeder;
	private LMPartDataInLane newLane;
	private LMPartDataInNest newNest;
	
	private ArrayList<LMPartDataInFeeder> feeders = new ArrayList<LMPartDataInFeeder>();
	private ArrayList<LMPartDataInLane> lanes = new ArrayList<LMPartDataInLane>();
	private ArrayList<LMPartDataInNest> nests = new ArrayList<LMPartDataInNest>();
	
	private double shakePossibility;
	private int randomChosenPart;
	private String message = "";
	
	public LMPartData(Server server, LMServerMain serverMain){
		this.server = server;
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
	public void addPartToFeeder(int feederNum, int partNum, int quantity){
		feeders.get(feederNum).addPart(partNum, quantity);
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
	
	public void shakePartsFree(){
		for(int i=0 ; i<8 ; i++){
			shakePossibility = Math.random();
			if( serverMain.getPartData().getLanePartSize(i) != 0 ){
				if( serverMain.getForAgentLane().getLane(i).getVibrationAmplitude() == 0 ){
					if( Math.random() < 0.001 ){
						// Part Removal In Server Side
						lanes.get(i).removePart();
						
						// Part Removal In Client Side
						message = i + "&Part&Shake&";
						
						//----------------------------------------------------------------------------------For Test
						server.sendMessage(message);
						//---------------------------------------------------------------------------------------------
					}
				}
				else if( serverMain.getForAgentLane().getLane(i).getVibrationAmplitude() == 1 ){
					if( Math.random() < 0.005 ){
						// Part Removal In Server Side
						lanes.get(i).removePart();
						
						// Part Removal In Client Side
						message = i + "&Part&Shake&";
						
						//----------------------------------------------------------------------------------For Test
						server.sendMessage(message);
						//------------------------------------------------------------------------------------------------
						
					}
				}
				else if( serverMain.getForAgentLane().getLane(i).getVibrationAmplitude() == 2 ){
					if( Math.random() < 0.01 ){
						// Part Removal In Server Side
						lanes.get(i).removePart();
						
						// Part Removal In Client Side
						message = i + "&Part&Shake&";
						
						//----------------------------------------------------------------------------------For Test
						server.sendMessage(message);
						//-----------------------------------------------------------------------------------------------
						
					}
				}
			}
		}
	}
	
	public void laneVibrationController(){
		for( int i=0 ; i<8 ; i++){
			if( lanes.get(i).getSize() < 7 ){
				serverMain.getForAgentLane().getLane(i).setVibrationAmplitudeWeak();
			}
			else if( lanes.get(i).getSize() < 14 ){
				serverMain.getForAgentLane().getLane(i).setVibrationAmplitudeNormal();
			}
			else if( lanes.get(i).getSize() >= 14 ){
				serverMain.getForAgentLane().getLane(i).setVibrationAmplitudeStrong();
			}
		}
	}
}
