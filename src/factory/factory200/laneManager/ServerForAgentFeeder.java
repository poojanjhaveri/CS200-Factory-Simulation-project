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
	
	/**
	 * @brief Feeder generations
	 * @param app : Instance of 'LaneManagerApp'
	 */
	public ServerForAgentFeeder(LaneManagerApp app){
		this.app = app;
	
		for(int newFeederNum=0 ; newFeederNum<feederQuantity ; newFeederNum++){
			newFeeder = new ServerLaneManagerThreadFeeder( newFeederNum, app );
			feeders.add(newFeeder);
		}
	}
	
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Feeder Switch On( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setSwitchOn( int feederNum ){
		feeders.get( feederNum ).setSwitchOn();
	}
	
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Feeder Switch Off( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setSwitchOff( int feederNum ){
		feeders.get( feederNum ).setSwitchOff();
	}
	
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Part Low Sensor On( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setPartLowSensorOn( int feederNum ){
		feeders.get( feederNum ).setPartLowSensorOn();
	}
	
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Feeder Switch Off( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setPartLowSensorOff( int feederNum ){
		feeders.get( feederNum ).setPartLowSensorOff();
	}
	
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Feeder Fed Parts Switch On( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setFeedPartsSwitchOn( int feederNum ){
		feeders.get( feederNum ).setFeedPartsSwitchOn();
	}
	
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Feeder Fed Parts Switch Off( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setFeedPartsSwitchOff( int feederNum ){
		feeders.get( feederNum ).setFeedPartsSwitchOff();
	}
	
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Part Fed Counter Increase( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setPartFedCounterIncrease( int feederNum ){
		feeders.get( feederNum ).setPartFedCounterIncrease();
	}
	
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Rear Gate Switch Lower( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setRearGateSwitchLower( int feederNum ){
		feeders.get( feederNum ).setRearGateSwitchLower();
	}
	
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Rear Gate Switch Raise( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setRearGateSwitchRaise( int feederNum ){
		feeders.get( feederNum ).setRearGateSwitchRaise();
	}
	
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Purge Bin Switch On( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setPurgeBinSwitchOn( int feederNum ){
		feeders.get( feederNum ).setPurgeBinSwitchOn();
	}
	
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Feeder Fed Parts Switch Off( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setPurgeBinSwitchOff( int feederNum ){
		feeders.get( feederNum ).setPurgeBinSwitchOff();
	}
	
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Divert To Left( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setDiverterSwitchLeft( int feederNum ){
		feeders.get( feederNum ).setDiverterSwitchLeft();
	}
	
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Divert To Right( Integration with agent )
	 * @param feederNum : feeder number
	 */
	public void setDiverterSwitchRight( int feederNum ){
		feeders.get( feederNum ).setDiverterSwitchRight();
	}
	
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
	 * Note : Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Dump To Feeder( Integration with Agent )
	 * @param feederNum : feeder number
	 */
	public void dumpBinBoxIntoFeeder( int feederNum ){
		feeders.get( feederNum ).dumpBinBoxIntoFeeder();
	}
	
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