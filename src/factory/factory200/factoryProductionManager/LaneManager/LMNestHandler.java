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
				
		if( message.contains("Switch Up") ){
			app.getAllNest().getNest(nestNum).nestUp();
		}

		else if( message.contains("Switch Down") ){																		
			app.getAllNest().getNest(nestNum).nestDown();
			app.getAllPart().nestDown(nestNum);
		}
	}
}
