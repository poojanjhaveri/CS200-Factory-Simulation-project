package factory.factory200.laneManager.ClientSide;

/**
 * @brief Server Signal Handler(Nest)
 * @author Dongyoung Jung
 */
public class LMNonNormativeHandler {
	
	private LMApplication app;
	private int nestLaneNum;
	private int randomPartNum;
	
	public LMNonNormativeHandler(LMApplication app){
		this.app = app;
	}

	public void verify(String message){
		nestLaneNum = message.charAt(0) - 48;
		
		if( message.contains("&Jammed&") ){
			randomPartNum = message.charAt(1) - 48;
			app.getGraphicsPanel().getAllPart().getLane(nestLaneNum).switchJammedLane(true, randomPartNum);
		}
		else if( message.contains("&Piled&") ) {
			app.getGraphicsPanel().getAllPart().getLane(nestLaneNum).switchPartPiled(true);
		}
	}
}