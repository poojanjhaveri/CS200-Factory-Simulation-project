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
		System.out.println("message : " + message);
		if( message.contains("&Jammed&") ){
			
		}
		else if( message.contains("&Piled&") ) {
			app.getAllPart().getLane(nestLaneNum).switchNonNormativePartPiled(false);
		}
	}
}
