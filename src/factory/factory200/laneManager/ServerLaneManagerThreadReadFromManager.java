package factory.factory200.laneManager;
/**
 * Signal from server : nest number + '%Part To Nest%' + part number
 * When a part reaches to a nest when the nest is not full, this class put the new part into 'parts' ArrayList in 'ServerLaneManagerThreadNest' class. 
 * 
 * @brief Interprets signals from server
 * @author Dongyoung Jung
 */
public class ServerLaneManagerThreadReadFromManager {
	
	private ServerMain main;	///< Instance of class 'ServerMain'
	
	public ServerLaneManagerThreadReadFromManager( ServerMain main ){
		this.main = main;
	}
	
	/**
	 * Interprets the signal and put a new part into 'parts' ArrayList in 'ServerLaneManagerThreadNest' class. 
	 * 
	 * @param message : message from manager
	 */
	public void verify( String message ){		
		if( message.indexOf("%Part To Nest%") != -1 ){
			int nestNum = message.charAt(0) - 48;	///< nest number
			int partNum = message.charAt( message.length() - 1 ) - 48;	///<part number
	
			main.getNestAgent().getNest( nestNum ).addPartInNest( partNum );
		}
	}
}
