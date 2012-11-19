package factory.factory200.laneManager.ServerSide;

/**
 * @brief Signal Interpreter from Clients
 * @author Dongyoung Jung
 */
public class LMSignalFromAnimationVerification {
	
	private LMServerMain serverMain;
	private int laneNestNum;
	
	public LMSignalFromAnimationVerification(LMServerMain serverMain){
		this.serverMain = serverMain;
	}
	
	public void verify(String message){
		if(message.indexOf("PART_TO_NEST_FROM_LANE") != -1){
			laneNestNum = message.charAt(0) - 48;
			serverMain.getPartData().sendPartToNestFromLane(laneNestNum);
		}
		else if(message.indexOf("PART_TAKE_BY_PARTROBOT") != -1){
			laneNestNum = message.charAt(0) - 48;
			serverMain.getForAgentPartRobot().takePartFromNest(laneNestNum);
		}
	}
}
