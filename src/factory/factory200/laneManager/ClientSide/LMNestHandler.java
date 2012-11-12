package factory.factory200.laneManager.ClientSide;

public class LMNestHandler {
	
	private LMApplication app;	///< Instance of class 'LMApplication'
	private int nestNum;	///< nest number

	public LMNestHandler(LMApplication app){
		this.app = app;
	}

	public void verify(String message){
		nestNum =  message.charAt(0) - 48;
				
		if( message.indexOf("Switch Up") != -1 ){
			app.getGUIPanel().getGUINest().getGUINestArray(nestNum).setNestSwitch(true);
			app.getGraphicsPanel().getAllNest().getNest(nestNum).nestUp();
		}

		else if( message.indexOf("Switch Down") != -1 ){																		
			app.getGUIPanel().getGUINest().getGUINestArray(nestNum).setNestSwitch(false);
			app.getGraphicsPanel().getAllNest().getNest(nestNum).nestDown();
			app.getGraphicsPanel().getAllPart().nestDown(nestNum);
		}

		else if( message.indexOf("Robot Take Part") != -1 ){
			
		}
	}
}
