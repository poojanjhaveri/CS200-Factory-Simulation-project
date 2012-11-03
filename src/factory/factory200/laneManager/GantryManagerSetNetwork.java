package factory.factory200.laneManager;

public class GantryManagerSetNetwork{

	private GantryManagerApp app;
	private GantryManagerVerifySignalsFromServer verifyMessage;
	private GantryManagerSendSignalsToServer sendSignalsToServer;
	
	public GantryManagerSetNetwork(GantryManagerApp app){
		this.app = app;
		
		verifyMessage = new GantryManagerVerifySignalsFromServer(app);
		sendSignalsToServer = new GantryManagerSendSignalsToServer(app);
	}
	
	public GantryManagerApp getApp(){
		return app;
	}
	
	public GantryManagerVerifySignalsFromServer getVerify(){
		return verifyMessage;
	}
	
	public GantryManagerSendSignalsToServer getSendToServer(){
		return sendSignalsToServer;
	}
}
