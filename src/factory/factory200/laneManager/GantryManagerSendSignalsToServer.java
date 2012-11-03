package factory.factory200.laneManager;

public class GantryManagerSendSignalsToServer {

	private GantryManagerApp app;
	
	public GantryManagerSendSignalsToServer(GantryManagerApp app){
		this.app = app;
	}
	
	public void sendToServer(String message){
		app.getServer().getReadFromGantryManager().verify( message );
	}
}
