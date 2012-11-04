package factory.factory200.laneManager;

import java.util.ArrayList;

/**
 * Note : This is done for agent's codes to integrate with manager's codes in a easier manner.
 * Agent just runs any function that changes the graphics or the GUI panel.
 * 
 * @author Dongyoung Jung
 */
public class ServerForAgentFeeder {
	
	private LaneManagerApp app;	///< Instance of class 'LaneManagerApp'
	private final int feederQuantity = 4;	///< Fixed quantity of feeders
	private ServerLaneManagerThreadFeeder newFeeder;	///< Instance of 'ServerLaneManagerThreadFeeder'
	private ArrayList<ServerLaneManagerThreadFeeder> feeders = new ArrayList<ServerLaneManagerThreadFeeder>();	///< ArrayList of feeders
	private ServerMain serverMain;
	
	/**
	 * @brief Feeder generations
	 * @param app : Instance of 'LaneManagerApp'
	 */
	public ServerForAgentFeeder(){}
	public ServerForAgentFeeder(LaneManagerApp app, ServerMain serverMain){
		this.app = app;
		this.serverMain = serverMain;
	
		for(int newFeederNum=0 ; newFeederNum<feederQuantity ; newFeederNum++){
			newFeeder = new ServerLaneManagerThreadFeeder( newFeederNum, app, serverMain );
			feeders.add(newFeeder);
		}
	}
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Feeder Switch On( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setSwitchOn( int feederNum ){
		feeders.get( feederNum ).setSwitchOn();
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Feeder Switch Off( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setSwitchOff( int feederNum ){
		feeders.get( feederNum ).setSwitchOff();
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Part Low Sensor On( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setPartLowSensorOn( int feederNum ){
		feeders.get( feederNum ).setPartLowSensorOn();
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Feeder Switch Off( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setPartLowSensorOff( int feederNum ){
		feeders.get( feederNum ).setPartLowSensorOff();
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Feeder Fed Parts Switch On( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setFeedPartsSwitchOn( int feederNum ){
		feeders.get( feederNum ).setFeedPartsSwitchOn();
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Feeder Fed Parts Switch Off( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setFeedPartsSwitchOff( int feederNum ){
		feeders.get( feederNum ).setFeedPartsSwitchOff();
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Part Fed Counter Increase( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setPartFedCounterIncrease( int feederNum ){
		feeders.get( feederNum ).setPartFedCounterIncrease();
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Rear Gate Switch Lower( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setRearGateSwitchLower( int feederNum ){
		feeders.get( feederNum ).setRearGateSwitchLower();
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Rear Gate Switch Raise( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setRearGateSwitchRaise( int feederNum ){
		feeders.get( feederNum ).setRearGateSwitchRaise();
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Purge Bin Switch On( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setPurgeBinSwitchOn( int feederNum ){
		feeders.get( feederNum ).setPurgeBinSwitchOn();
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Feeder Fed Parts Switch Off( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setPurgeBinSwitchOff( int feederNum ){
		feeders.get( feederNum ).setPurgeBinSwitchOff();
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Divert To Left( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setDiverterSwitchLeft( int feederNum ){
		feeders.get( feederNum ).setDiverterSwitchLeft();
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Divert To Right( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setDiverterSwitchRight( int feederNum ){
		feeders.get( feederNum ).setDiverterSwitchRight();
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * Note : Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Dump To Feeder( Integration with Agent )
	 * @param feederNum : feeder number
	 */
	public void dumpBinBoxIntoFeeder( int feederNum, int binNum ){
		feeders.get( feederNum ).dumpBinBoxIntoFeeder(binNum);
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * Note : Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Fill-in feeder( Integration with Agent )
	 * @param feederNum : feeder number
	 * @param chosenPart : desired kind of part
	 * @param partQuantity : desired quantity of part
	 */
	public void fillInFeeder( int feederNum, int chosenPart, int partQuantity ){
		feeders.get( feederNum ).fillInFeeder( chosenPart, partQuantity );
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * Agent runs this function to get the ArrayList 'parts' in feeder 
	 * 
	 * @brief Getter( Integration with agent )
	 * @param nestNum : nest number
	 */
	public ArrayList<ServerLaneManagerPart> getPartsArrayList( int feederNum ){
		return feeders.get( feederNum ).getPartsArrayList();
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * Agent runs this function to get the feeder without box after dumping all parts onto lane 
	 * 
	 * @brief Feeder Empty
	 * @param nestNum : nest number
	 */
	public void getFeederWithoutBox( int feederNum ){
		feeders.get( feederNum ).getFeederWithoutBox();
	}
	// ---------------------------------------------------------------------------------------------------------
	
	/**
	 * Note : This is for testing by controller
	 * 
	 * @brief Feed To Left
	 * @param feederNum : feeder number
	 */
	public void feedToLeftLane( int feederNum, int partNum ){
		feeders.get( feederNum ).feedToLeftLane( partNum );
	}
	
	/**
	 * Note : This is for testing by controller
	 * 
	 * @brief Feed To Right
	 * @param feederNum : feeder number
	 */
	public void feedToRightLane( int feederNum, int partNum ){
		feeders.get( feederNum ).feedToRightLane( partNum );
	}
	
	/**
	 * This controls the signals from server. 
	 * If getFeedPartsSwitchOnOff() is true, the signal makes the feeder to feed. Otherwise, the feeder does not feed.
	 * 
	 * @brief 'Timer' Signal Controller
	 */
	public void feedPartsTimer(){
		for(int feederNum = 0 ; feederNum < feeders.size() ; feederNum++){
			if( feeders.get( feederNum ).getFeedPartsSwitchOnOff() == true ){
				feeders.get( feederNum ).feedTimer();
			}
		}
	}
}