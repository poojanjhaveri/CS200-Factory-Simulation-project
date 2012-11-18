package factory.factory200.laneManager.ServerSide;

/**
 * @brief Put Bin & Purge Bin Action Functions for Agent
 * @author Dongyoung Jung
 */
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
		serverMain.getForAgentFeeder().getFeeder(feederNum).setWithBin(true);
		serverMain.getPartData().addPartToFeeder(feederNum, partNum, quantity);
	}
	
	public void purgeBin(int feederNum){
		// Signal To LM & FPM
		signal = feederNum + "&Bin&Purge&";
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
		
		// Server Data Task
		serverMain.getForAgentFeeder().getFeeder(feederNum).setWithBin(false);
	}
	
	public void emptyBin(int feederNum){
		// Signal To LM & FPM
		signal = feederNum + "&Bin&Empty&";
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
		serverMain.sendToGRM(signal);
	}
}
