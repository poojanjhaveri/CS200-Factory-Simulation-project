package factory.factory200.laneManager;
/**
 * This class helps to send signals to server ( In V0, it sends to a platform )
 * 
 * @brief Send Signal To Server
 * @author Dongyoung Jung 
 */
public class LaneManagerSendSignalsToServer {

	private LaneManagerApp app;	///< Instance of class 'LaneManagerApp'
	
	/**
	 * @brief Constructor
	 * @param app : Instance of class 'LaneManagerApp'
	 */
	public LaneManagerSendSignalsToServer(LaneManagerApp app){
		this.app = app;
	}
	
	/**
	 * @brief Send To Server
	 * @param message : Message to server
	 */
	public void sendToServer(String message){
		app.getServer().getReadFromLaneManager().verify( message );
	}
}
