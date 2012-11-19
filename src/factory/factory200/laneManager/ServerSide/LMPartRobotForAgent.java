package factory.factory200.laneManager.ServerSide;

public class LMPartRobotForAgent {
	
	private LMServerMain serverMain;
	private String signal = "";
	
	public LMPartRobotForAgent(LMServerMain serverMain){
		this.serverMain = serverMain;
	}
	
	public void takePartFromNest(int nestNum){
		// Signal To LM & FPM
		signal = nestNum + "&PartRobot&";
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
		
		// Server Data Task
		serverMain.getPartData().partRobotTakeOne(nestNum);
	}
	
	// For Part Agent
	public int getPartQuantityOnNest(int nestNum){
		return serverMain.getPartData().getNestPartSize(nestNum);
	}
}