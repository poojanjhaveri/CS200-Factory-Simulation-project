package factory.factory200.factoryProductionManager.LaneManager;

/**
 * @brief Take Bin from Nest Controller
 * @author Dongyoung Jung 
 */
public class LMPartRobotHandler{
	
	private LMApplication app;
	private int nestNum;

	public LMPartRobotHandler(LMApplication app){
		this.app = app;
	}

	public void verify(String message){
		if( message.contains("&PartRobot&") ){
			nestNum = message.charAt(0) - 48;
			app.getAllPart().partRobotTakeOne(nestNum);
		}
	}
}
