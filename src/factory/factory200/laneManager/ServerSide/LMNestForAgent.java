package factory.factory200.laneManager.ServerSide;

import java.util.ArrayList;
import factory.general.*;

public class LMNestForAgent {
	
	private Server server;
	private LMServerMain serverMain;
	private LMNestData newNest;	///< Instance of class 'ServerLaneManagerThreadNest
	private ArrayList<LMNestData> nests = new ArrayList<LMNestData>();	///< ArrayList of nests

	public LMNestForAgent(Server server, LMServerMain serverMain){
		this.server = server;
		this.serverMain = serverMain;
		
		for(int i=0 ; i<8 ; i++){
			newNest = new LMNestData( i, server, serverMain );
			nests.add(newNest);
		}
	}

	public void setSwitchUp( int nestNum ){
		nests.get( nestNum ).setSwitchUp();
	}

	public void setSwitchDown( int nestNum ){
		nests.get( nestNum ).setSwitchDown();
	}
}
