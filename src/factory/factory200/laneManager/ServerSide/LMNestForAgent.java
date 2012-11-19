package factory.factory200.laneManager.ServerSide;

import java.util.ArrayList;

/**
 * @brief Nest Functions for Agent
 * @author Dongyoung Jung
 */
public class LMNestForAgent {
	
	private LMServerMain serverMain;
	private LMNestData newNest;
	private ArrayList<LMNestData> nests = new ArrayList<LMNestData>();

	public LMNestForAgent(LMServerMain serverMain){
		this.serverMain = serverMain;
		
		for(int i=0 ; i<8 ; i++){
			newNest = new LMNestData( i, serverMain );
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
