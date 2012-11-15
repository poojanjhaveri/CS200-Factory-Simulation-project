package factory.factory200.factoryProductionManager.LaneManager.Test;

import factory.factory200.factoryProductionManager.LaneManager.*;

public class LMPartRobotHandler{
	
	private LMApplication app;	///< Instance of class 'LMApplication'
	private int nestNum;

	public LMPartRobotHandler(LMApplication app){
		this.app = app;
	}

	public void verify(String message){
		if(message.indexOf("&PartRobot&") != -1){
			nestNum = message.charAt(0) - 48;
			app.getAllPart().partRobotTakeOne(nestNum);
		}
	}
}
