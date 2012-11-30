package factory.factory200.laneManager.ClientSide;

/**
 * @brief Server Signal Handler(Nest)
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
			app.getGUIPanel().getGUINest().getGUINestArray(nestNum).setNestSwitch(true);
			app.getGraphicsPanel().getAllNest().getNest(nestNum).nestUp();
		}

		else if( message.contains("Switch Down") ){																		
			app.getGUIPanel().getGUINest().getGUINestArray(nestNum).setNestSwitch(false);
			app.getGraphicsPanel().getAllNest().getNest(nestNum).nestDown();
			app.getGraphicsPanel().getAllPart().nestDown(nestNum);
		}
	}
}
