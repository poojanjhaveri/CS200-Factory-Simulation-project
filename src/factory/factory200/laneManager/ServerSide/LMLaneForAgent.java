package factory.factory200.laneManager.ServerSide;

import java.util.ArrayList;

/**
 * @brief Lane Functions for Agent
 * @author Dongyoung Jung
 */
public class LMLaneForAgent {
	
	private LMServerMain serverMain;
	private LMLaneData newLane;
	private ArrayList<LMLaneData> lanes = new ArrayList<LMLaneData>();

	public LMLaneForAgent(LMServerMain serverMain){
		this.serverMain = serverMain;
		
		for(int i=0 ; i<8 ; i++){
			newLane = new LMLaneData( i, serverMain );
			lanes.add(newLane);
		}
	}
	
	public LMLaneData getLane(int laneNum){
		return lanes.get(laneNum);
	}
	
	//---------------------------------------------------------------------------
	public void setSwitchOn( int laneNum ){
		lanes.get( laneNum ).switchOn();
	}

	public void setSwitchOff( int laneNum ){
		lanes.get( laneNum ).switchOff();
	}
	
	public void setVibrationAmplitudeStrong( int laneNum ){
		lanes.get( laneNum ).setVibrationAmplitudeStrong();
	}

	public void setVibrationAmplitudeNormal( int laneNum ){
		lanes.get( laneNum ).setVibrationAmplitudeNormal();
	}
}
