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
		server.getClientHandler().sendToClient(signal);
		
		// Send To FPM
		
		// Server Data Task
		serverMain.getPartData().partRobotTakeOne(nestNum);
	}
}