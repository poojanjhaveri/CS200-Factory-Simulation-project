package factory.factory200.laneManager;

public class ServerGantryManagerThreadReadFromManager {

	private ServerMain main;
	
	public ServerGantryManagerThreadReadFromManager( ServerMain main ){
		this.main = main;
	}
	
	public void verify( String message ){		
		if( message.indexOf("%%") != -1 ){
			// 'Arrived' Boolean change
			
		}
	}
}
