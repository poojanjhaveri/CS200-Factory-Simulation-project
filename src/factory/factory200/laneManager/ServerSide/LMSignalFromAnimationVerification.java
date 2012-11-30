package factory.factory200.laneManager.ServerSide;

/**
 * @brief Signal Interpreter from Clients
 * @author Dongyoung Jung
 */
public class LMSignalFromAnimationVerification {
	
	private LMServerMain serverMain;
	private int laneNestNum;
	private int feederNum;
	private int partNum;
	private int randomPartNum;
	
	public LMSignalFromAnimationVerification(LMServerMain serverMain){
		this.serverMain = serverMain;
	}
	
	public void verify(String message){
		if(message.contains("PART_TO_NEST_FROM_LANE") ){
			laneNestNum = message.charAt(0) - 48;
			serverMain.getPartData().sendPartToNestFromLane(laneNestNum);
		}
		else if(message.contains("PART_TAKE_BY_PARTROBOT") ){
			laneNestNum = message.charAt(0) - 48;
			serverMain.getForAgentPartRobot().takePartFromNest(laneNestNum);
		}
		// Non-normative Scenario 1
		else if(message.contains("BAD_PART_INSERTION") ){
			feederNum = message.charAt(0) - 48;
			partNum = message.charAt(1) - 48;
			serverMain.getForAgentGantryRobot().putBadBin(partNum, feederNum);
		}
		// Non-normative Scenario 2
		else if(message.contains("LANE_JAMMED") ){
			laneNestNum = message.charAt(0) - 48;
			randomPartNum = message.charAt(1) - 48;
			serverMain.sendToLM("" + laneNestNum + randomPartNum + "&Non&Jammed&");
			serverMain.sendToFPM("" + laneNestNum + randomPartNum + "&Non&Jammed&");
			
			// Signal To Agent
			serverMain.problemHappened(2);
		}
		// Non-normative Scenario 3
		else if(message.contains("PART_PILED") ) {
			laneNestNum = message.charAt(0) - 48;
			serverMain.sendToLM(laneNestNum + "&Non&Piled&");
			serverMain.sendToFPM(laneNestNum + "&Non&Piled&");
			serverMain.sendToKAM(laneNestNum + "&Non&Piled&");
			
			// Signal To Agent
			serverMain.problemHappened(3);
		}
	}
}
