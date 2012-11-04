package factory.factory200.laneManager;

/**
 * The signal from server should be checked in class 'GantryManagerVerifySignalsFromServer'
 * Here, the signals get checked again and take an action to Graphics Panel.
 * 
 * @brief Verifies signals from server and take an action
 * @author Dongyoung Jung
 */
public class GantryManagerRobotHandler {
	
	private GantryManagerApp app;	///< Instance of class 'GantryManagerApp'
	private int laneNum;	///< Lane number
	private int moveX;	///< Movements through X axis
	private int moveY;	///< Movements through Y axis
	private int star;	///< For message verification
	private int colorNum;	///< Color number
	
	/**
	 * @brief Constructor
	 * @param app : Instance of class 'GantryManagerApp'
	 */
	public GantryManagerRobotHandler(GantryManagerApp app){
		this.app = app;
	}
	
	/**
	 * @brief Message Verification
	 * @param message : Message from server
	 */
	public void verify(String message){
		// Action ----------------------------------------------------------------------------------------------------------------------------------------
		// Message : Robot Move
		if( message.indexOf("Move") != -1){
			star = message.indexOf("**");
			moveX = Integer.parseInt( message.substring(11, star) );
			moveY = Integer.parseInt( message.substring(star+2) );
			
			app.getGraphicsPanel().getRobot().moveTo(moveX, moveY);
		}
		/*
		else if( message.indexOf("PickBin") != -1){
			app.getGraphicsPanel().getRobot().pickUpBin();
		}
		
		else if( message.indexOf("PutOffBin") != -1){
			app.getGraphicsPanel().getRobot().putOffBin();
		}
		*/
		else if( message.indexOf("Color Change") != -1){
			colorNum = message.charAt(message.length()-1) - 48;
			app.getGraphicsPanel().getRobot().colorChange( colorNum );
		}
		// ----------------------------------------------------------------------------------------------------------------------------------------------------
	}
}