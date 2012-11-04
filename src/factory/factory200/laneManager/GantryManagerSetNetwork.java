package factory.factory200.laneManager;

/**
 * This class sets up the server. In V0, there is no server requirements.
 * However instead, this creates instances of two classes like below.
 * class GantryManagerSetNetwork : Verifies signals from server
 * class GantryManagerSendSignalsToServer : Sends signals to server
 * 
 * @brief Server Setups ( In V0, it is a platform )
 *	@author Dongyoung Jung 
 */
public class GantryManagerSetNetwork{

	private GantryManagerApp app;	///< Instance of class 'GantryManagerApp'
	private GantryManagerVerifySignalsFromServer verifyMessage;	///< Instance of class 'GantryManagerVerifySignalsFromServer'
	private GantryManagerSendSignalsToServer sendSignalsToServer;	///< Instance of class 'GantryManagerSendSignalsToServer'
	
	/**
	 * @brief Constructor
	 * @param app : Instance of class 'GantryManagerApp'
	 */
	public GantryManagerSetNetwork(GantryManagerApp app){
		this.app = app;
		
		verifyMessage = new GantryManagerVerifySignalsFromServer(app);
		sendSignalsToServer = new GantryManagerSendSignalsToServer(app);
	}
	
	/**
	 * @brief Getter
	 * @return Instance of class 'GantryManagerApp'
	 */
	public GantryManagerApp getApp(){
		return app;
	}
	
	/**
	 * @brief Getter
	 * @return Instance of class 'GantryManagerVerifySignals'
	 */
	public GantryManagerVerifySignalsFromServer getVerify(){
		return verifyMessage;
	}
	
	/**
	 * @brief Getter
	 * @return Instance of clas 'GantryManagerSendSignalsToServer'
	 */
	public GantryManagerSendSignalsToServer getSendToServer(){
		return sendSignalsToServer;
	}
}
