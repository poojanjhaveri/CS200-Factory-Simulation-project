package factory.factory200.laneManager;

/**
 * Since there is a timing problem with this project, 'Timer' from animation sends back saying it got to the destination. 
 * 
 * @brief Interprets signals from server
 * @author Dongyoung Jung
 */
public class ServerGantryManagerThreadReadFromManager {

	private ServerMain main;	///< Instance of class 'ServerMain'
	
	/**
	 * @brief Constructor
	 * @param main : Instance of class 'ServerMain'
	 */
	public ServerGantryManagerThreadReadFromManager( ServerMain main ){
		this.main = main;
	}
	
	/**
	 * @brief Message Verification
	 * @param message : Message From Client
	 */
	public void verify( String message ){		

	}
}
