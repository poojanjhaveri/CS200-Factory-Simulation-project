package factory.factory200.laneManager;

import java.util.ArrayList;

/**
 * Note : This is done for agent's codes to integrate with manager's codes in a easier manner.
 * Agent just runs any function that changes the graphics or the GUI panel.
 * 
 * @author Dongyoung Jung
 */
public class ServerForAgentNest {
	
	private LaneManagerApp app;	///< Instance of class 'LaneManagerApp'
	private final int nestQuantity = 8;	///< Fixed quantity of nests
	private ServerLaneManagerThreadNest newNest;	///< Instance of class 'ServerLaneManagerThreadNest
	private ArrayList<ServerLaneManagerThreadNest> nests = new ArrayList<ServerLaneManagerThreadNest>();	///< ArrayList of nests
	private ServerMain serverMain;
	
	/**
	 * @brief Nest generations
	 * @param app : Instance of 'LaneManagerApp'
	 */
	public ServerForAgentNest(LaneManagerApp app, ServerMain serverMain){
		this.app = app;
		this.serverMain = serverMain;
		
		for(int newNestNum=0 ; newNestNum<nestQuantity ; newNestNum++){
			newNest = new ServerLaneManagerThreadNest( newNestNum, app, serverMain );
			nests.add(newNest);
		}
	}
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Nest Up( Integration with agent )
	 * @param nestNum : nest number
	 */
	public void setSwitchUp( int nestNum ){
		nests.get( nestNum ).setSwitchUp();
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Nest Down( Integration with agent )
	 * @param nestNum : nest number
	 */
	public void setSwitchDown( int nestNum ){
		nests.get( nestNum ).setSwitchDown();
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * Agent runs this function to get the ArrayList 'parts' in nest 
	 * 
	 * @brief Getter( Integration with agent )
	 * @param nestNum : nest number
	 */
	public ArrayList<ServerLaneManagerPart> getPartsArrayList( int nestNum ){
		return nests.get( nestNum ).getPartsArrayList();
	}
	// ---------------------------------------------------------------------------------------------------------
	
	/**
	 * @brief Getter
	 * @param nestNum : nest number
	 * @return Quantity of parts inside nest
	 */
	public ServerLaneManagerThreadNest getNest( int nestNum ){
		return nests.get( nestNum );
	}
}