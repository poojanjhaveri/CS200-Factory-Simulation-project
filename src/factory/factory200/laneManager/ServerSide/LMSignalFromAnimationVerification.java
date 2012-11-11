package factory.factory200.laneManager.ServerSide;

public class LMSignalFromAnimationVerification {
	
	private LMServerMain serverMain;
	private int laneNestNum;
	
	public LMSignalFromAnimationVerification(LMServerMain serverMain){
		this.serverMain = serverMain;
	}
	
	public void verify(String message){
		if(message.indexOf("&Nest&AddPart&") != -1){
			System.out.println("(Server) From Client : " + message);
			laneNestNum = message.charAt(0) - 48;
			System.out.println("LaneNestNum : " + laneNestNum);
			serverMain.getPartData().sendPartToNestFromLane(laneNestNum);
		}
	}
}
