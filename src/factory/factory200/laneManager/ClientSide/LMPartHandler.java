package factory.factory200.laneManager.ClientSide;

/**
 * @brief Server Signal Handler(Part)
 * @author Dongyoung Jung
 */
public class LMPartHandler {
	
	private LMApplication app;
	private int laneNum, partNum, partStatus;
	
	public LMPartHandler(LMApplication app){
		this.app = app;
	}

	public void verify(String message){
		if( message.indexOf("&Add&") != -1 ){
			System.out.println("message : " + message);
			laneNum = message.charAt(0) - 48;
			partNum = message.charAt(1) - 48;
			partStatus = message.charAt(2) - 48;
			app.getGraphicsPanel().getAllPart().addPartFromFeederToLane(laneNum, partNum, partStatus);
		}
	}
}
