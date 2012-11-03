package factory.factory200.laneManager;

import java.util.ArrayList;

/**
 * This class contains all data for lanes. Lane agent and lane manager use these data to process.
 * 
 * @brief Lane data
 *	@author Dongyoung Jung
 */
public class ServerLaneManagerThreadLane {

	private LaneManagerApp app;	///< Instance of class 'LaneManagerApp'
	private String signalToLM = "";	///< Signal to Lane Manager
	private int laneNum;	///< Lane number
	private Boolean laneMove = false;	///< Feeder switched on : true, Feeder switched off : false
	private ArrayList<ServerLaneManagerPart> parts = new ArrayList<ServerLaneManagerPart>();
	
	/**
	 * Since there are 8 lanes, there are 8 instances of this class in class 'ServerForAgentLane'
	 * 
	 * @brief Assigns lane number
	 * @param laneNum : Lane number
	 * @param app : Instance of class 'LaneManagerApp'
	 */
	public ServerLaneManagerThreadLane( int laneNum, LaneManagerApp app ){
		this.laneNum = laneNum;
		this.app = app;
	}	
	
	/**
	 * This function turns on the lane. 
	 * This function sends a signal to Lane Manager
	 * Signal : lane number + "&Lane&" + "Switch On"
	 * 
	 * @brief Lane Switch On
	 */
	public void switchOn(){
		laneMove = true;
		signalToLM = laneNum + "&Lane&" + "Switch On";
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * This function turns off the lane. 
	 * This function sends a signal to Lane Manager
	 * Signal : lane number + "&Lane&" + "Switch Off"
	 * 
	 * @brief Lane Switch Off
	 */
	public void switchOff(){
		laneMove = false;
		signalToLM = laneNum + "&Lane&" + "Switch Off";
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * This function sets up the vibration amplitude on lane in 'String' mode 
	 * This function sends a signal to Lane Manager
	 * Signal : lane number + "&Lane&" + "Amplitude Strong"
	 * 
	 * @brief Vibration Amplitude Setting into Strong
	 */
	public void setVibrationAmplitudeStrong(){
		signalToLM = laneNum + "&Lane&" + "Amplitude Strong";
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * This function sets up the vibration amplitude on lane in 'Normal' mode 
	 * This function sends a signal to Lane Manager
	 * Signal : lane number + "&Lane&" + "Amplitude Normal"
	 * 
	 * @brief Vibration Amplitude Setting into Normal
	 */
	public void setVibrationAmplitudeNormal(){
		signalToLM = laneNum + "&Lane&" + "Amplitude Normal";
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * This function sets up the vibration amplitude on lane in 'Weak' mode 
	 * This function sends a signal to Lane Manager
	 * Signal : lane number + "&Lane&" + "Amplitude Weak"
	 * 
	 * @brief Vibration Amplitude Setting into Weak
	 */
	public void setVibrationAmplitudeWeak(){
		signalToLM = laneNum + "&Lane&" + "Amplitude Weak";
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * This function runs the lane 
	 * This function sends a signal to Lane Manager
	 * Signal : lane number + "&Lane&" + "MOVE"
	 * 
	 * @brief Lane move signal
	 */
	public void moveLane(){
		signalToLM = laneNum + "&Lane&" + "MOVE";
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * As feeder feeds a lane, the part is added to ArrayList 'parts' on lane.
	 * 
	 * @brief Addition To Lane
	 * @param newPartNum : new part number
	 */
	public void addPartToArrayList( int newPartNum ){
		parts.add( new ServerLaneManagerPart( newPartNum ) );
	}
	
	/**
	 * As part moves to nest, the part is removed from ArrayList 'parts' on lane.
	 * 
	 * @brief Transfer To Nest
	 * @param newPartNum : new part number
	 */
	public void removePartFromArrayList(){
		parts.remove(0);
	}
	
	/**
	 * This boolean variable 'laneMove' plays a role to decide whether to accept the 'Timer' signal from server.
	 * If the lane is switched on, 'laneMove' enables it. Otherwise, it keeps blocking it.
	 * 
	 * @return Boolean laneMove
	 */
	public Boolean getLaneMoveBool(){
		return laneMove;
	}
}