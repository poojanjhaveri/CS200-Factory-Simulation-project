package factory.factory200.laneManager;

public class GantryManagerRobotHandler {
	
	private GantryManagerApp app;
	private int laneNum;
	private int moveX;
	private int moveY;
	private int star;
	private int temp2;
	
	//constructor, passes in GantryManagerApp 
	public GantryManagerRobotHandler(GantryManagerApp app){
		this.app = app;
	}
	
	public void verify(String message){
		// Action ----------------------------------------------------------------------------------------------------------------------------------------
		// Message : Robot Move
		if( message.indexOf("Move") != -1){
			star = message.indexOf("**");
			moveX = Integer.parseInt( message.substring(11, star) );
			moveY = Integer.parseInt( message.substring(star+2) );
			
			app.getGraphicsPanel().getRobot().moveTo(moveX, moveY);
		}
		
		else if( message.indexOf("PickBin") != -1){
			app.getGraphicsPanel().getRobot().pickUpBin();
		}
		
		else if( message.indexOf("PutOffBin") != -1){
			app.getGraphicsPanel().getRobot().putOffBin();
		}
		// ----------------------------------------------------------------------------------------------------------------------------------------------------
	}
}
