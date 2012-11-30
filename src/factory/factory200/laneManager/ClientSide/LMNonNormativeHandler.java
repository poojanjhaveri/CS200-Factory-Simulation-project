package factory.factory200.laneManager.ClientSide;

/**
 * @brief Server Signal Handler(Nest)
 * @author Dongyoung Jung
 */
public class LMNonNormativeHandler {
	
	private LMApplication app;
	private int nestLaneNum;

	public LMNonNormativeHandler(LMApplication app){
		this.app = app;
	}

	public void verify(String message){
		nestLaneNum = message.charAt(0) - 48;
		
		if( message.contains("&Jammed&") ){
			
		}
		else if( message.contains("&Piled&") ) {
			app.getGraphicsPanel().getAllPart().getLane(nestLaneNum).switchNonNormativePartPiled(true);
		}
	}
}
