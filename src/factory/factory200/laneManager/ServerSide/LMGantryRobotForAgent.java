package factory.factory200.laneManager.ServerSide;

public class LMGantryRobotForAgent {
	
	private LMServer server;
	private LMServerMain serverMain;
	private String signal = "";
	
	public LMGantryRobotForAgent(LMServer server, LMServerMain serverMain){
		this.server = server;
		this.serverMain = serverMain;
	}
	
	public void putBin(int binNum, int quantity, int feederNum){
		int partNum = binNum;
		
		// Signal To Managers
		signal = feederNum + "&Bin&Put&" + binNum;
		
		//----------------------------------------------------------------------------------For Test
		for(int i=0 ; i<server.clients.size() ; i++){
			server.clients.get(i).sendToClient(signal);
		}
		
		//server.getClientHandler().sendToClient(signal);
		//-----------------------------------------------------------------------------------------------
		
		// Send To FPM
		
		// Server Data Task
		serverMain.getPartData().addPartToFeeder(feederNum, partNum, quantity);
	}
	
	public void purgeBin(int feederNum){
		// Signal To Managers
		signal = feederNum + "&Bin&Purge&";
		
		//----------------------------------------------------------------------------------For Test
		for(int i=0 ; i<server.clients.size() ; i++){
			server.clients.get(i).sendToClient(signal);
		}
		
		//server.getClientHandler().sendToClient(signal);
		//-------------------------------------------------------------------------------------------------
		
		// Send To FPM
		
	}
}
