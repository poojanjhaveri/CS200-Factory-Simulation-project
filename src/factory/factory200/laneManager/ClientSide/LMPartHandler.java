package factory.factory200.laneManager.ClientSide;

public class LMPartHandler {
	
	private LMApplication app;	///< Instance of class 'LMApplication'
	private int laneNum, partNum;
	
	public LMPartHandler(LMApplication app){
		this.app = app;
	}

	public void verify(String message){
		if( message.indexOf("&Add&") != -1 ){
			laneNum = message.charAt(0) - 48;
			partNum = message.charAt(1) - 48;
			app.getGraphicsPanel().getAllPart().addPartFromFeederToLane(laneNum, partNum);
		}
	}
}
