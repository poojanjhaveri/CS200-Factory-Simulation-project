package factory.factory200.laneManager.ServerSide;

import java.util.ArrayList;

public class LMLaneForAgent {
	
	private LMServer server;
	private LMServerMain serverMain;
	private LMLaneData newLane;	///< Instance of class 'ServerLaneManagerThreadLane'
	private ArrayList<LMLaneData> lanes = new ArrayList<LMLaneData>();	///< ArrayList of lanes

	public LMLaneForAgent(LMServer server, LMServerMain serverMain){
		this.server = server;
		this.serverMain = serverMain;
		
		for(int i=0 ; i<8 ; i++){
			newLane = new LMLaneData( i, server, serverMain );
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

	public void setVibrationAmplitudeWeak( int laneNum ){
		lanes.get( laneNum ).setVibrationAmplitudeWeak();
	}
}
