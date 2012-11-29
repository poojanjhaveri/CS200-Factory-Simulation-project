package factory.factory200.factoryProductionManager.LaneManager;

/**
 * This class verifies signals from server( in V0, it is just a platform )
 * 
 * @author Dongyoung Jung
 */
public class LMSignalFromServerVerification{
	
	private LMApplication app;
	private LMFeederHandler feederHandler;
	private LMLaneHandler laneHandler;
	private LMNestHandler nestHandler;	
	private LMCameraHandler cameraHandler;
	private LMPartHandler partHandler;
	private LMPartRobotHandler partRobotHandler;
	private LMGantryRobotHandler gantryRobotHandler;

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
	
	public void timerAction(){
		app.getAllLane().laneMove();
		app.getAllCamera().cameraShoot();
		app.getAllPart().partMove();
	}
	
	/**
	 * This function clarifies where the signal should go.
	 * The way it does it to check a specific letter inside the 'message' String.
	 * "&Camera&" : Message with cameras ( Signal : camera number + "&Camera&" )
	 * "&Feeder&" : Message with feeders
	 * "&Lane&" : Message with lanes
	 * "&Nest&" : Message with nests
	 * "&Part&" : Message with parts
	 * "&PartRobot" : Message with Take Bin from Nest
	 * "&Bin&" : Message with Bin(Gantry Robot)
	 * 
	 * @brief Message Checker ( From Server )
	 * @param message : Message from server
	 */
	public void verify(String message){		
		if( message.contains("&Camera&") ){
			cameraHandler.cameraShoot(message);
		}
		
		else if( message.contains("&Feeder&") ){
			feederHandler.verify(message);
		}
		
		else if( message.contains("&Lane&") ){
			laneHandler.verify(message);
		}
		
		else if( message.contains("&Nest&") ){
			nestHandler.verify(message);
		}
		
		else if( message.contains("&Part&") ){
			partHandler.verify(message);
		}
		
		else if( message.contains("&PartRobot&") ){
			partRobotHandler.verify(message);
		}
		
		else if( message.contains("&Bin&") ){
			gantryRobotHandler.verify(message);
		}
	}
}