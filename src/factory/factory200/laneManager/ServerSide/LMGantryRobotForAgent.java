package factory.factory200.laneManager.ServerSide;

import factory.general.*;

public class LMGantryRobotForAgent {
	
	private Server server;
	private LMServerMain serverMain;
	private String signal = "";
	
	public LMGantryRobotForAgent(Server server, LMServerMain serverMain){
		this.server = server;
		this.serverMain = serverMain;
	}
	
	public void putBin(int binNum, int quantity, int feederNum){
		int partNum = binNum;
		
		// Signal To Managers
		signal = feederNum + "&Bin&Put&" + binNum;
		
		//----------------------------------------------------------------------------------For Test
		server.sendMessage(signal);
		//-----------------------------------------------------------------------------------------------
		
		// Send To FPM
		
		// Server Data Task
		serverMain.getPartData().addPartToFeeder(feederNum, partNum, quantity);
	}
	
	public void purgeBin(int feederNum){
		// Signal To Managers
		signal = feederNum + "&Bin&Purge&";
		
		//----------------------------------------------------------------------------------For Test
		server.sendMessage(signal);
		//-------------------------------------------------------------------------------------------------
		
		// Send To FPM
		
	}
}
