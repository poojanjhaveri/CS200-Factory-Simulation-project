package factory.factory200.laneManager;
import java.util.ArrayList;

/**
 * This class contains all data for feeders. Lane agent and lane manager use these data to process.
 * 
 * @brief Feeder data
 *	@author Dongyoung Jung
 */
public class ServerLaneManagerThreadFeeder {
	private LaneManagerApp app;	///< Instance of class 'LaneManagerApp'
	private ServerMain serverMain;
	private ArrayList<ServerLaneManagerPart> parts = new ArrayList<ServerLaneManagerPart>();	///< Parts inside feeder
	private String signalToLM = "";	///< Signal to Lane Manager
	private Boolean feedPartsSwitchOnOff = false;	///< Feeder Switch
	private Boolean divertToLeftRight = false;	///< Diversion from feeder to lane. True : Divert to right, False : Diver to left
	private int insertPartFrequency;	///< Frequency of part Insertion to lane
	private int feederNum;	///< Feeder number
	
	/**
	 * Since there are 4 feeders, there are 8 instances of this class in class 'ServerForAgentFeeder'
	 * 
	 * @brief Assigns feeder number
	 * @param feederNum : feeder number
	 * @param app : Instance of class 'LaneManagerApp'
	 */
	public ServerLaneManagerThreadFeeder( int feederNum, LaneManagerApp app, ServerMain serverMain ){		
		this.feederNum = feederNum;
		this.app = app;
		this.serverMain = serverMain;
	}

	/**
	 * This function turns on the feeder. 
	 * This function sends a signal to Lane Manager
	 * Signal : feeder number + "&Feeder&" + "Feeder Switch On"
	 * 
	 * @brief Feeder Switch On
	 */
	public void setSwitchOn(){ 
		signalToLM = feederNum + "&Feeder&" + "Feeder Switch On";
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * This function turns off the feeder. 
	 * This function sends a signal to Lane Manager
	 * Signal : feeder number + "&Feeder&" + "Feeder Switch Off"
	 * 
	 * @brief Feeder Switch Off
	 */
	public void setSwitchOff(){
		signalToLM = feederNum + "&Feeder&" + "Feeder Switch Off";
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * This function turns on the part low sensor.
	 * Part Low Sensor : If the quantity of parts inside feeder goes lower than some level, it turns into red.  
	 * This function sends a signal to Lane Manager
	 * Signal : feeder number + "&Feeder&" + "Part Low Sensor On";
	 * 
	 * @brief Part Low Sensor On
	 */
	public void setPartLowSensorOn(){
		signalToLM = feederNum + "&Feeder&" + "Part Low Sensor On";
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * This function turns off the part low sensor.
	 * Part Low Sensor : If the quantity of parts inside feeder goes higher than some level, it turns into green.  
	 * This function sends a signal to Lane Manager
	 * Signal : feeder number + "&Feeder&" + "Part Low Sensor Off";
	 * 
	 * @brief Part Low Sensor Off
	 */
	public void setPartLowSensorOff(){
		signalToLM = feederNum + "&Feeder&" + "Part Low Sensor Off";
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * This function turns on the feed part switch.
	 * Feed part switch : If on, it keeps feeding onto lanes.
	 * It makes the feeder start accepting 'Timer' signal by setting 'feedPartsSwitchOnOff' as true.  
	 * This function sends a signal to Lane Manager.
	 * Signal : feeder number + "&Feeder&" + "Feed Part Switch On"
	 * 
	 * @brief Part Low Sensor On
	 */
	public void setFeedPartsSwitchOn(){
		feedPartsSwitchOnOff = true;
		signalToLM = feederNum + "&Feeder&" + "Feed Part Switch On";
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * This function turns off the feed part switch.
	 * Feed part switch : If off, it stops feeding onto lanes.
	 * It keeps the feeder from accepting 'Timer' signal by setting 'feedPartsSwitchOnOff' as false.  
	 * This function sends a signal to Lane Manager.
	 * Signal : feeder number + "&Feeder&" + "Feed Part Switch Off"
	 * 
	 * @brief Part Low Sensor Off
	 */
	public void setFeedPartsSwitchOff(){
		feedPartsSwitchOnOff = false;
		signalToLM = feederNum + "&Feeder&" + "Feed Part Switch Off";
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * This function increases the number of parts fed.  
	 * This function sends a signal to Lane Manager.
	 * Signal : feeder number + "&Feeder&" + "Part Fed Counter"
	 * 
	 * @brief Number incremental of parts fed
	 */
	public void setPartFedCounterIncrease(){
		signalToLM = feederNum + "&Feeder&" + "Part Fed Counter";
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * This function makes the rear gate lowered.
	 * Rear gate : If it is lowered, all parts inside the feeder fall down.  
	 * This function sends a signal to Lane Manager.
	 * Signal : feeder number + "&Feeder&" + "Rear Gate Lower"
	 * 
	 * @brief Lower Rear Gate
	 */
	public void setRearGateSwitchLower(){
		parts.clear();
		signalToLM = feederNum + "&Feeder&" + "Rear Gate Lower";
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * This function makes the rear gate raised.
	 * Rear gate : If it is raised, all parts keep staying inside the feeder.  
	 * This function sends a signal to Lane Manager.
	 * Signal : feeder number + "&Feeder&" + "Rear Gate Raise"
	 * 
	 * @brief Raise Rear Gate
	 */
	public void setRearGateSwitchRaise(){
		signalToLM = feederNum + "&Feeder&" + "Rear Gate Raise";
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * This function enables purging bins.
	 * Purge Bin : If it is switched on, after putting parts inside feeder, the empty bin is taken aside.  
	 * This function sends a signal to Lane Manager.
	 * Signal : feeder number + "&Feeder&" + "Purge On"
	 * 
	 * @brief Purge Bin On
	 */
	public void setPurgeBinSwitchOn(){
		signalToLM = feederNum + "&Feeder&" + "Purge On";
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * This function disables purging bins.
	 * Purge Bin : If it is switched off, even after putting parts inside feeder, the empty bin stays inside the feeder.  
	 * This function sends a signal to Lane Manager.
	 * Signal : feeder number + "&Feeder&" + "Purge Off"
	 * 
	 * @brief Purge Bin Off
	 */
	public void setPurgeBinSwitchOff(){
		signalToLM = feederNum + "&Feeder&" + "Purge Off";
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * This function makes the feeder feed onto the left lane.  
	 * This function sends a signal to Lane Manager.
	 * Signal : feeder number + "&Feeder&" + "Divert To Left"
	 * 
	 * @brief Divert to left
	 */
	public void setDiverterSwitchLeft(){
		divertToLeftRight = false;
		signalToLM = feederNum + "&Feeder&" + "Divert To Left";
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * This function makes the feeder feed onto the right lane.  
	 * This function sends a signal to Lane Manager.
	 * Signal : feeder number + "&Feeder&" + "Divert To Right"
	 * 
	 * @brief Divert to right
	 */
	public void setDiverterSwitchRight(){
		divertToLeftRight = true;
		signalToLM = feederNum + "&Feeder&" + "Divert To Right";
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * Note : This is made for testing by controller.
	 * This function makes the feeder feed onto the left lane just one time. 
	 * This function adds the new part to ArrayList 'parts' on lane. 
	 * This function sends a signal to Lane Manager.
	 * Signal : feeder number + "&Feeder&" + "Feed To Left" + part number
	 * 
	 * @brief Feed To Left
	 */
	public void feedToLeftLane( int partNum ){
		serverMain.getForAgentLane().getServerLaneArrayList().get( 2*feederNum+1 ).addPartToArrayList(  partNum );
		
		signalToLM = feederNum + "&Feeder&" + "Feed To Left" + partNum;
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * Note : This is made for testing by controller.
	 * This function makes the feeder feed onto the right lane just one time. 
	 * This function adds the new part to ArrayList 'parts' on lane.
	 * This function sends a signal to Lane Manager.
	 * Signal : feeder number + "&Feeder&" + "Feed To Right" + part number
	 * 
	 * @brief Feed To Right
	 */
	public void feedToRightLane( int partNum ){
		serverMain.getForAgentLane().getServerLaneArrayList().get( 2*feederNum ).addPartToArrayList(  partNum );
		
		signalToLM = feederNum + "&Feeder&" + "Feed To Right" + partNum;
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * This function changes the normal feeder image into feeder-with-bin image.  
	 * This function sends a signal to Lane Manager.
	 * Signal : feeder number + "&Feeder&" + "Dumped"
	 * 
	 * @brief Dumped
	 */
	public void dumpBinBoxIntoFeeder(int colorNum){
		signalToLM = feederNum + "&Feeder&" + "Dumped" + colorNum;
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * Note : This is made for testing by controller.
	 * This function fills the feeder with a quantity and a kind of part the user wants.  
	 * 
	 * @brief Fill-in Feeder
	 */
	public void fillInFeeder( int chosenPart, int partQuantity ){
		for(int i=0 ; i<partQuantity ; i++){
			parts.add( new ServerLaneManagerPart( chosenPart ) );
		}
	}

	/**
	 * This boolean variable 'feedPartsSwitchOnOff' plays a role to decide whether to accept the 'Timer' signal from server.
	 * If 'feedPartsSwitchOnOff' is true, it starts accepting the 'Timer' signal.
	 * 
	 * @return Boolean feedPartsSwitchOnOff
	 */	
	public Boolean getFeedPartsSwitchOnOff(){
		return feedPartsSwitchOnOff;
	}
	
	/**
	 * @brief Getter
	 * @return ArrayList 'parts'
	 */
	public ArrayList<ServerLaneManagerPart> getPartsArrayList(){
		return parts;
	}
	
	/**
	 * @brief Empty Feeder Making
	 * @param feederNum : Feeder number
	 */
	public void getFeederWithoutBox(){
		signalToLM = feederNum + "&Feeder&" + "Feeder Without Box";
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * If the Boolean variable 'feedPartsSwitchOnOff' is on, the variable 'insertPartFrequency' keeps increasing.
	 * If it is 30, it orders the feeder to feed onto lane.
	 * Also, this helps the feeder divert to a specific lane.
	 * 
	 * @brief Feeding timing controller
	 */
	public void feedTimer(){
		if( parts.size() > 0 ){
			if( insertPartFrequency == 30 ){
				if( divertToLeftRight == false ){
					feedToLeftLane( parts.remove(0).getPartNum() );
					insertPartFrequency = 0;
				}
				else if( divertToLeftRight == true ){
					feedToRightLane( parts.remove(0).getPartNum() );
					insertPartFrequency = 0;
				}
			}
			insertPartFrequency++;
		}
	}
}