
public class VerifySignalsFromServer{
	
	private LaneManagerApp laneManagerApp;
	private FeederHandler feederHandler;
	private LaneHandler laneHandler;
	private NestHandler nestHandler;
	private CameraHandler cameraHandler;
	private int cameraNum;
	
	public VerifySignalsFromServer(LaneManagerApp laneManagerApp){
		this.laneManagerApp = laneManagerApp;
		cameraHandler = new CameraHandler(laneManagerApp);
		feederHandler = new FeederHandler(laneManagerApp);
		laneHandler = new LaneHandler(laneManagerApp);
		nestHandler = new NestHandler(laneManagerApp);
	}
	
	public void verify(String message){		
		// Message : Camera
		if( message.indexOf("&Camera&") != -1 ){
			// Camera Number must be received from server
			cameraNum = message.charAt( message.length()-1 ) - 48;
			cameraHandler.cameraShoot(cameraNum);
		}
		// Message :Feeder
		else if( message.indexOf("&Feeder&") != -1 ){
			feederHandler.verify(message);
		}
		// Message : Lane
		else if( message.indexOf("&Lane&") != -1 ){
			laneHandler.verify(message);
		}
		// Message : Nest
		else if( message.indexOf("&Nest&") != -1 ){
			nestHandler.verify(message);
		}
	}
	
	public FeederHandler getFeederHandler(){
		return feederHandler;
	}
	
	public LaneHandler getLaneHandler(){
		return laneHandler;
	}
	
	public NestHandler getNestHandler(){
		return nestHandler;
	}
}