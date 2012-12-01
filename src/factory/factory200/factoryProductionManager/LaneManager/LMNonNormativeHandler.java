package factory.factory200.factoryProductionManager.LaneManager;

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
			app.getAllPart().getLane(nestLaneNum).switchJammedLane(true);
		}
		else if( message.contains("&Piled&") ) {
			app.getAllPart().getNest(nestLaneNum).switchPartPiled(true);
		}
		else if( message.contains("&Toggling&") ) {
			
		}
		else if( message.contains("&UnToggling&") ) {
			
		}
	}
}
