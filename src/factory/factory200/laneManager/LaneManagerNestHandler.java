/**
 * The signal from server should be checked in class 'LaneManagerVerifySignalsFromServer'
 * Here, the signals get checked again and take an action to Graphics Panel or GUI Panel.
 * 
 * @brief Verifies signals from server and take an action
 * @author Dongyoung Jung
 */
public class LaneManagerNestHandler {
	
	private LaneManagerApp app;	///< Instance of class 'LaneManagerApp'
	private int nestNum;	///< nest number
	
	/**
	 * @brief Constructor
	 * @param app : Instance of class 'LaneManagerApp'
	 */
	public LaneManagerNestHandler(LaneManagerApp app){
		this.app = app;
	}
	
	/**
	 * The nest number is verified at the first line.
	 * "Switch Up" : On graphics panel, the nest goes up. On GUI Panel, the switch turns 'up'.
	 * "Switch Down" : On graphics panel, the nest goes down. On GUI Panel, the switch turns 'down'
	 * "Robot Takes One" : One part on nest disappears.
	 * 
	 * @brief Verification and Action Takes
	 * @param message : message from server
	 */
	public void verify(String message){
		nestNum =  message.charAt(0) - 48;
				
		// GUI & Graphic changes--------------------------------------------------------------------------------------------------------------
		// Message : Nest Switch Up
		if( message.indexOf("Switch Up") != -1 ){
			app.getGUIPanel().getGUINest().getGUINestArray(nestNum).setNestSwitch(true);
		}
		// Message : Nest Switch Down
		else if( message.indexOf("Switch Down") != -1 ){																		
			app.getGUIPanel().getGUINest().getGUINestArray(nestNum).setNestSwitch(false);
			// Clear Image ArrayList
			app.getGraphicsPanel().getNestArray(nestNum).clearImageArrayList();
		}
		// ----------------------------------------------------------------------------------------------------------------------------------------------------
		
		// Action ----------------------------------------------------------------------------------------------------------------------------------------
		// Message : Robot Takes One
		else if( message.indexOf("Robot Takes One") != -1 ){																		
			app.getGraphicsPanel().getNestArray(nestNum).robotTakePart();
		}
		// ----------------------------------------------------------------------------------------------------------------------------------------------------
	}
}
