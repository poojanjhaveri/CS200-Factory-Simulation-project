package factory.factory200.laneManager;

public class GantryManagerVerifySignalsFromServer{
	
	private GantryManagerApp app;
	private GantryManagerRobotHandler robotHandler;
	
	//constructor: passes GantryManagerApp in
	public GantryManagerVerifySignalsFromServer(GantryManagerApp app){
		this.app = app;
		robotHandler = new GantryManagerRobotHandler(app);
	}
	
	//verify message with message in robotHandler class
	public void verify(String message){		
		if( message.indexOf("&Robot&") != -1 ){
			robotHandler.verify( message );
		}
	}
}
