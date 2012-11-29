package factory.factory200.laneManager.ClientSide;

/**
 * @brief Server Signal Handler(Part)
 * @author Dongyoung Jung
 */
public class LMPartHandler {
	
	private LMApplication app;
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
		
		else if( message.indexOf("&Shake&") != -1 ){
			laneNum = message.charAt(0) - 48;
			app.getGraphicsPanel().getAllPart().shakePart(laneNum);
		}
	}
}
