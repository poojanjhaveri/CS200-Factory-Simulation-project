package factory.factory200.laneManager.ServerSide;

import factory.general.*;

public class LMPartRobotForAgent {
	
	private Server server;
	private LMServerMain serverMain;
	private String signal = "";
	
	public LMPartRobotForAgent(Server server, LMServerMain serverMain){
		this.server = server;
		this.serverMain = serverMain;
	}
	
	public void takePartFromNest(int nestNum){
		// Signal To Managers
		signal = nestNum + "&PartRobot&";
		
		//----------------------------------------------------------------------------------For Test
		server.sendMessage(signal);
		//-----------------------------------------------------------------------------------------------
		
		// Send To FPM
		
		// Server Data Task
		serverMain.getPartData().partRobotTakeOne(nestNum);
	}
}