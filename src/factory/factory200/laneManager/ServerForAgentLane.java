import java.util.ArrayList;

/**
 * Note : This is done for agent's codes to integrate with manager's codes in a easier manner.
 * Agent just runs any function that changes the graphics or the GUI panel.
 * 
 * @author Dongyoung Jung
 */
public class ServerForAgentLane {
	
	private LaneManagerApp app;	///< Instance of class 'LaneManagerApp'
	private final int laneQuantity = 8;	///< Fixed quantity of lanes
	private ServerLaneManagerThreadLane newLane;	///< Instance of class 'ServerLaneManagerThreadLane'
	private ArrayList<ServerLaneManagerThreadLane> lanes = new ArrayList<ServerLaneManagerThreadLane>();	///< ArrayList of lanes
	
	/**
	 * @brief Lane generations
	 * @param app : Instance of 'LaneManagerApp'
	 */
	public ServerForAgentLane(LaneManagerApp app){
		this.app = app;
		
		for(int newLaneNum=0 ; newLaneNum<laneQuantity ; newLaneNum++){
			newLane = new ServerLaneManagerThreadLane( newLaneNum, app );
			lanes.add(newLane);
		}
	}
	
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Lane Switch On( Integration with agent )
	 * @param laneNum : lane number
	 */
	public void setSwitchOn( int laneNum ){
		lanes.get( laneNum ).switchOn();
	}
	
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Lane Switch Off( Integration with agent )
	 * @param laneNum : lane number
	 */
	public void setSwitchOff( int laneNum ){
		lanes.get( laneNum ).switchOff();
	}
	
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Vibration Amplitude Setting 1( Integration with agent )
	 * @param laneNum : lane number
	 */
	public void setVibrationAmplitudeStrong( int laneNum ){
		lanes.get( laneNum ).setVibrationAmplitudeStrong();
	}
	
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Vibration Amplitude Setting 2( Integration with agent )
	 * @param laneNum : lane number
	 */
	public void setVibrationAmplitudeNormal( int laneNum ){
		lanes.get( laneNum ).setVibrationAmplitudeNormal();
	}

	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Vibration Amplitude Setting 3( Integration with agent )
	 * @param laneNum : lane number
	 */
	public void setVibrationAmplitudeWeak( int laneNum ){
		lanes.get( laneNum ).setVibrationAmplitudeWeak();
	}
	
	/**
	 * This controls the signals from server. 
	 * If getLaneMoveBool() is true, the signal makes the lane to move. Otherwise, the lane does not move.
	 * 
	 * @brief 'Timer' Signal Controller
	 */
	public void moveLaneTimer(){
		for(int laneNum = 0 ; laneNum < lanes.size() ; laneNum++){
			if( lanes.get( laneNum ).getLaneMoveBool() == true ){
				lanes.get( laneNum ).moveLane();
			}
		}
	}
}
