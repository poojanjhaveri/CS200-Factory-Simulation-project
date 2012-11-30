package factory.factory200.factoryProductionManager.LaneManager;

/**
 * @brief Part Handler
 * @author Dongyoung Jung
 */
public class LMPartHandler {
	
	private LMApplication app;
	private int laneNum, partNum, partStatus;
	
	public LMPartHandler(LMApplication app){
		this.app = app;
	}

	public void verify(String message){
		if( message.contains("&Add&") ){
			laneNum = message.charAt(0) - 48;
			partNum = message.charAt(1) - 48;
			partStatus = message.charAt(2) - 48;
			app.getAllPart().addPartFromFeederToLane(laneNum, partNum, partStatus);
		}
	}
}
