package factory.factory200.laneManager.ClientSide;

public class LMPartRobotHandler{
	
	private LMApplication app;	///< Instance of class 'LMApplication'
	private int nestNum;

	public LMPartRobotHandler(LMApplication app){
		this.app = app;
	}

	public void verify(String message){
		if(message.indexOf("&PartRobot&") != -1){
			nestNum = message.charAt(0) - 48;
			app.getGraphicsPanel().getAllPart().partRobotTakeOne(nestNum);
		}
	}
}
