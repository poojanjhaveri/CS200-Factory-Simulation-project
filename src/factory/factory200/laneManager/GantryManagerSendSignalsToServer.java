package factory.factory200.laneManager;

/**
* This class helps to send signals to server ( In V0, it sends to a platform )
* 
* @brief Send Signal To Server
* @author Dongyoung Jung 
*/
public class GantryManagerSendSignalsToServer {

	private GantryManagerApp app;	///< Instance of class 'GantryManagerApp'
	
	/**
	 * @brief Constructor
	 * @param app : Instance of class 'GantryManagerApp'
	 */
	public GantryManagerSendSignalsToServer(GantryManagerApp app){
		this.app = app;
	}
	
	/**
	 * @brief Send To Server
	 * @param message : Message to server
	 */
	public void sendToServer(String message){
		app.getServer().getReadFromGantryManager().verify( message );
	}
}
