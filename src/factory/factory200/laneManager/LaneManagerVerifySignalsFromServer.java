package factory.factory200.laneManager;
/**
 * This class verifies signals from server( in V0, it is just a platform )
 * 
 * @author Dongyoung Jung
 */
public class LaneManagerVerifySignalsFromServer{
	
	private LaneManagerApp laneManagerApp;	///< Instance of class 'LaneManagerApp'
	private LaneManagerFeederHandler feederHandler;	///< Instance of class 'LaneManagerFeederHandler'
	private LaneManagerLaneHandler laneHandler;	///< Instance of class 'LaneManagerLaneHandler'
	private LaneManagerNestHandler nestHandler;	///< Instance of class 'LaneManagerNestHandler'
	private LaneManagerCameraHandler cameraHandler;	///< Instance of class 'LaneManagerCameraHandler'
	private int cameraNum;	///< Camera number
	
	/**
	 * @brief Constructor
	 * @param laneManagerApp : Instance of class 'LaneManagerApp'
	 */
	public LaneManagerVerifySignalsFromServer(LaneManagerApp laneManagerApp){
		this.laneManagerApp = laneManagerApp;
		cameraHandler = new LaneManagerCameraHandler(laneManagerApp);
		feederHandler = new LaneManagerFeederHandler(laneManagerApp);
		laneHandler = new LaneManagerLaneHandler(laneManagerApp);
		nestHandler = new LaneManagerNestHandler(laneManagerApp);
	}
	
	/**
	 * This function clarifies where the signal should go.
	 * The way it does it to check a specific letter inside the 'message' String.
	 * "&Camera&" : Message with cameras ( Signal : camera number + "&Camera&" )
	 * "&Feeder&" : Message with feeders
	 * "&Lane&" : Message with lanes
	 * "&Nest&" : Message with nests
	 * 
	 * @brief Message Checker ( From Server )
	 * @param message : Message from server
	 */
	public void verify(String message){		
		if( message.indexOf("&Camera&") != -1 ){
			cameraNum = message.charAt(0) - 48;
			cameraHandler.cameraShoot(cameraNum);
		}
		
		else if( message.indexOf("&Feeder&") != -1 ){
			feederHandler.verify(message);
		}
		
		else if( message.indexOf("&Lane&") != -1 ){
			laneHandler.verify(message);
		}
		
		else if( message.indexOf("&Nest&") != -1 ){
			nestHandler.verify(message);
		}
	}
}