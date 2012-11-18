package factory.factory200.laneManager.ServerSide;

public class LMGantryRobotForAgent {
	
	private LMServerMain serverMain;
	private String signal = "";
	
	public LMGantryRobotForAgent(LMServerMain serverMain){
		this.serverMain = serverMain;
	}
	
	public void putBin(int binNum, int quantity, int feederNum){
		int partNum = binNum;
		
		// Signal To LM & FPM
		signal = feederNum + "&Bin&Put&" + binNum;
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
		
		// Server Data Task
		serverMain.getPartData().addPartToFeeder(feederNum, partNum, quantity);
	}
	
	public void purgeBin(int feederNum){
		// Signal To LM & FPM
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
	}
}
