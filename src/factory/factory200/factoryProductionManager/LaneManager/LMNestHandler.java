package factory.factory200.factoryProductionManager.LaneManager;

/**
 * @brief Nest Handler
 * @author Dongyoung Jung
 */
public class LMNestHandler {
	
	private LMApplication app;
	private int nestNum;

	public LMNestHandler(LMApplication app){
		this.app = app;
	}

	public void verify(String message){
		nestNum =  message.charAt(0) - 48;
				
		if( message.indexOf("Switch Up") != -1 ){
			app.getAllNest().getNest(nestNum).nestUp();
		}

		else if( message.indexOf("Switch Down") != -1 ){																		
			app.getAllNest().getNest(nestNum).nestDown();
			app.getAllPart().nestDown(nestNum);
		}
	}
}
