package factory.factory200.laneManager;

/**
 * This class verifies signals from server and take a proper action.
 * 
 * @brief Message Verification
 * @author Dongyoung Jung
 */
public class GantryManagerVerifySignalsFromServer{
	
	private GantryManagerApp app;	///< Instance of class 'GantryManagerApp'
	private GantryManagerRobotHandler robotHandler;	///< Instance of class 'GantryManagerRobotHandler'
	
	/**
	 * @brief Constructor
	 * @param app : Instance of class 'GantryManagerApp'
	 */
	public GantryManagerVerifySignalsFromServer(GantryManagerApp app){
		this.app = app;
		robotHandler = new GantryManagerRobotHandler(app);
	}
	
	/**
	 * @brief Message Verification
	 * @param message : Message from server
	 */
	public void verify(String message){		
		if( message.indexOf("&Robot&") != -1 ){
			robotHandler.verify( message );
		}
	}
}