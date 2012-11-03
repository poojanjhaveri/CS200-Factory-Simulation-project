package factory.factory200.laneManager;
/**
 * This class sets up the server. In V0, there is no server requirements.
 * However instead, this creates instances of two classes like below.
 * class LaneManagerSetNetwork : Verifies signals from server
 * class LaneManagerSendSignalsToServer : Sends signals to server
 * 
 * @brief Server Setups ( In V0, it is a platform )
 *	@author Dongyoung Jung 
 */
public class LaneManagerSetNetwork{

	private LaneManagerApp app;	///< Instance of class 'LaneManagerApp'
	private LaneManagerVerifySignalsFromServer verifyMessage;	///< Instance of class 'LaneManagerVerifySignalsFromServer'
	private LaneManagerSendSignalsToServer sendSignalsToServer;	///< Instance of class 'LaneManagerSendSignalsToServer'
	
	/**
	 * @brief Constructor
	 * @param app : Instance of class 'LaneManagerApp'
	 */
	public LaneManagerSetNetwork(LaneManagerApp app){
		this.app = app;
		verifyMessage = new LaneManagerVerifySignalsFromServer(app);
		sendSignalsToServer = new LaneManagerSendSignalsToServer(app);
	}
	
	/**
	 * @brief Getter
	 * @return Instance of class 'LaneManagerApp'
	 */
	public LaneManagerApp getApp(){
		return app;
	}
	
	/**
	 * @brief Getter
	 * @return Instance of class 'LaneManagerManagerVerifySignalsFromServer'
	 */
	public LaneManagerVerifySignalsFromServer getVerify(){
		return verifyMessage;
	}
	
	/**
	 * @brief Getter
	 * @return Instance of class 'LaneManagerSendSignalsToServer'
	 */
	public LaneManagerSendSignalsToServer getSendToServer(){
		return sendSignalsToServer;
	}
}
