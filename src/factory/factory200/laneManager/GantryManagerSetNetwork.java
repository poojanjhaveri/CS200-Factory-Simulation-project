package factory.factory200.laneManager;
public class GantryManagerSetNetwork{

	private GantryManagerApp app;
	private GantryManagerVerifySignalsFromServer verifyMessage;
	private GantryManagerSendSignalsToServer sendSignalsToServer;
	
	//constructor that passes in GantryManagerApp
	public GantryManagerSetNetwork(GantryManagerApp app){
		this.app = app;
		
		//GantryManagerVerifySignalsFromServer instance
	
		verifyMessage = new GantryManagerVerifySignalsFromServer(app);
		
		//GantryManagerSendSignalsToServer instance
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
