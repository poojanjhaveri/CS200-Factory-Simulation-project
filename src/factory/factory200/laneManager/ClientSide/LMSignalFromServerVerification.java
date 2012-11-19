package factory.factory200.laneManager.ClientSide;

import factory.general.Manager;
import factory.general.Message;

/**
 * This class verifies signals from server( in V0, it is just a platform )
 * 
 * @author Dongyoung Jung
 */
public class LMSignalFromServerVerification extends Manager{
	
	private LMApplication app;
	private LMFeederHandler feederHandler;
	private LMLaneHandler laneHandler;
	private LMNestHandler nestHandler;
	private LMCameraHandler cameraHandler;
	private LMPartHandler partHandler;
	private LMPartRobotHandler partRobotHandler;
	private LMGantryRobotHandler gantryRobotHandler;
	private LMTimerThread timer = new LMTimerThread(this);
	
	/**
	 * @brief Constructor
	 * @param laneManagerApp : Instance of class 'LaneManagerApp'
	 */
	public LMSignalFromServerVerification(LMApplication app){
		// Send Identification to Server
		super.sendToServer(Message.IDENTIFY_LANEMANAGER);
		
		this.app = app;
		cameraHandler = new LMCameraHandler(app);
		feederHandler = new LMFeederHandler(app);
		laneHandler = new LMLaneHandler(app);
		nestHandler = new LMNestHandler(app);
		partHandler = new LMPartHandler(app);
		partRobotHandler = new LMPartRobotHandler(app);
		gantryRobotHandler = new LMGantryRobotHandler(app);
		
		new Thread(timer).start();
		timer.timerStart();
	}
	
	public void timerAction(){
		app.getGraphicsPanel().getAllLane().laneMove();
		app.getGraphicsPanel().getAllCamera().cameraShoot();
		app.getGraphicsPanel().getAllPart().partMove();
		app.getGraphicsPanel().repaint();
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
	public void processMessage(String msg){
		super.processMessage(msg);
		
		if( msg.indexOf("&Camera&") != -1 ){
			cameraHandler.cameraShoot(msg);
		}
		
		else if( msg.indexOf("&Feeder&") != -1 ){
			feederHandler.verify(msg);
		}
		
		else if( msg.indexOf("&Lane&") != -1 ){
			laneHandler.verify(msg);
		}
		
		else if( msg.indexOf("&Nest&") != -1 ){
			nestHandler.verify(msg);
		}
		
		else if( msg.indexOf("&Part&") != -1 ){
			partHandler.verify(msg);
		}
		
		else if( msg.indexOf("&PartRobot&") != -1 ){
			partRobotHandler.verify(msg);
		}
		
		else if( msg.indexOf("&Bin&") != -1 ){
			gantryRobotHandler.verify(msg);
		}
	}
}