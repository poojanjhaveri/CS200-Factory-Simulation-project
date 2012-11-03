package factory.factory200.laneManager;

public class GantryManagerSendSignalsToServer {

	private GantryManagerApp app;
	
	//constructor: passes GantryManagerApp in
	public GantryManagerSendSignalsToServer(GantryManagerApp app){
		this.app = app;
	}
	
	//handles messages
	public void sendToServer(String message){
		app.getServer().getReadFromGantryManager().verify( message );
	}
}
