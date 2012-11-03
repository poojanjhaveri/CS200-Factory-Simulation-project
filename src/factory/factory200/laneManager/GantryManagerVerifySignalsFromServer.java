package factory.factory200.laneManager;

public class GantryManagerVerifySignalsFromServer{
	
	private GantryManagerApp app;
	private GantryManagerRobotHandler robotHandler;
	
	public GantryManagerVerifySignalsFromServer(GantryManagerApp app){
		this.app = app;
		robotHandler = new GantryManagerRobotHandler(app);
	}
	
	public void verify(String message){		
		if( message.indexOf("&Robot&") != -1 ){
			robotHandler.verify( message );
		}
	}
}