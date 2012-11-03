package factory.factory200.laneManager;
import java.util.ArrayList;
/**
 * This class contains all data for nests. Lane agent and lane manager use these data to process.
 * 
 * @brief Nest data
 *	@author Dongyoung Jung
 */
public class ServerLaneManagerThreadNest {
	
	private LaneManagerApp app;	///< Instance of class 'LaneManagerLaneManagerApp'
	private String signalToLM = "";	///< Signal to Lane Manager
	private int nestNum;	///< Nest number
	private ArrayList<ServerLaneManagerPart> parts = new ArrayList<ServerLaneManagerPart>();	///< Parts on nest
	
	/**
	 * Since there are 8 nests, there are 8 instances of this class in class 'ServerForAgentNest'
	 * 
	 * @brief Assigns nest number
	 * @param nestNum : Nest number
	 * @param app : Instance of class 'LaneManagerApp'
	 */
	public ServerLaneManagerThreadNest( int nestNum, LaneManagerApp app ){
		this.nestNum = nestNum;
		this.app = app;
	}
	
	/**
	 * This sends a signal to Lane Manager.
	 * Signal : nest number + "&Nest&" + "Switch Up"
	 * 
	 * @brief sets the nest switch up
	 */
	public void setSwitchUp(){
		// Signal to Lane Manager
		signalToLM = nestNum + "&Nest&" + "Switch Up";
		
		// Send signal to Lane Manager
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * One the nest is down, all parts from the nest just fall down. 
	 * This clears the ArrayList 'parts' and send signal to Lane Manager
	 * Signal to lane manager : nest number + "&Nest&" + "Switch Down"
	 * 
	 * @brief sets the nest switch down
	 */
	public void setSwitchDown(){
		// Thread change
		parts.clear();
		
		// Signal to Lane Manager
		signalToLM = nestNum + "&Nest&" + "Switch Down";
		
		// Send signal to Lane Manager
		app.getNetwork().getVerify().verify(signalToLM);
	}
	
	/**
	 * @brief Getter 
	 * @return the size of ArrayList 'parts' 
	 */
	public int getPartQuantityInNest(){
		return parts.size();
	}
	
	/**
	 * @brief A new part addition into ArrayList 'parts'
	 * @param partNum : number of new part
	 */
	public void addPartInNest( int partNum ){
		parts.add( new ServerLaneManagerPart( partNum ) );
	}
}
