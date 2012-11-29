package factory.factory200.laneManager.ClientSide;

/**
 * @brief Server Signal Handler(Take Bin from Nest)
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
			app.getGraphicsPanel().getAllPart().partRobotTakeOne(nestNum);
		}
	}
}
