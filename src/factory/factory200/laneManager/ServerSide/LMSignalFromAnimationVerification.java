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
			
			// Tell the agent that clicked the button
                        serverMain.getCameraAgent().msgAllPartsBad(2*feederNum+1);
			serverMain.getFeederAgents()[feederNum].msgAddBadParts();
		}
		
		// Non-normative Scenario 2
		else if(message.contains("LANE_JAMMED") ){
			laneNestNum = message.charAt(0) - 48;
			
			// Tell the agent that clicked the button
			serverMain.getFeederAgents()[laneNestNum / 2].msgLaneJammed(laneNestNum);
		}
	}
}
