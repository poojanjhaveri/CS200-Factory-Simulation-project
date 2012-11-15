package factory.factory200.laneManager.ServerSide;

public class LMPartRobotForAgent {
	
	private LMServer server;
	private LMServerMain serverMain;
	private String signal = "";
	
	public LMPartRobotForAgent(LMServer server, LMServerMain serverMain){
		this.server = server;
		this.serverMain = serverMain;
	}
	
	public void takePartFromNest(int nestNum){
		// Signal To Managers
		signal = nestNum + "&PartRobot&";
		
		//----------------------------------------------------------------------------------For Test
		for(int j=0 ; j<server.clients.size() ; j++){
			server.clients.get(j).sendToClient(signal);
		}
		
		//server.getClientHandler().sendToClient(signal);
		//-----------------------------------------------------------------------------------------------
		
		// Send To FPM
		
		// Server Data Task
		serverMain.getPartData().partRobotTakeOne(nestNum);
	}
}