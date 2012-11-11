package factory.factory200.laneManager.ClientSide;

/**
 * This class verifies signals from server( in V0, it is just a platform )
 * 
 * @author Dongyoung Jung
 */
public class LMSignalFromServerVerification{
	
	private LMApplication app;	///< Instance of class 'LaneManagerApp'
	private LMFeederHandler feederHandler;	///< Instance of class 'LaneManagerFeederHandler'
	private LMLaneHandler laneHandler;	///< Instance of class 'LaneManagerLaneHandler'
	private LMNestHandler nestHandler;	///< Instance of class 'LaneManagerNestHandler'
	private LMCameraHandler cameraHandler;	///< Instance of class 'LaneManagerCameraHandler'
	private LMPartHandler partHandler;	///< Instance of class 'LMPartHandler'
	private LMPartRobotHandler partRobotHandler;
	private LMGantryRobotHandler gantryRobotHandler;
	private int feedingTiming;
	/**
	 * @brief Constructor
	 * @param laneManagerApp : Instance of class 'LaneManagerApp'
	 */
	public LMSignalFromServerVerification(LMApplication app){
		this.app = app;
		cameraHandler = new LMCameraHandler(app);
		feederHandler = new LMFeederHandler(app);
		laneHandler = new LMLaneHandler(app);
		nestHandler = new LMNestHandler(app);
		partHandler = new LMPartHandler(app);
		partRobotHandler = new LMPartRobotHandler(app);
		gantryRobotHandler = new LMGantryRobotHandler(app);
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
		
		if( message.indexOf("&Timer&") != -1 ){
			feedingTiming++;
			app.getGraphicsPanel().getAllLane().laneMove();
			app.getGraphicsPanel().getAllCamera().cameraShoot();
			app.getGraphicsPanel().getAllPart().partMove();
			app.getGraphicsPanel().repaint();
		}
		
		else if( message.indexOf("&Camera&") != -1 ){
			cameraHandler.cameraShoot(message);
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
		
		else if( message.indexOf("&Part&") != -1 ){
			partHandler.verify(message);
		}
		
		else if( message.indexOf("&PartRobot&") != -1 ){
			partRobotHandler.verify(message);
		}
		
		else if( message.indexOf("&Bin&") != -1 ){
			gantryRobotHandler.verify(message);
		}
	}
}