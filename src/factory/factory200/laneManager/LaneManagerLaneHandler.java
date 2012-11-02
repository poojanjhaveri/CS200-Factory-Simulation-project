/**
 * The signal from server should be checked in class 'LaneManagerVerifySignalsFromServer'
 * Here, the signals get checked again and take an action to Graphics Panel or GUI Panel.
 * 
 * @brief Verifies signals from server and take an action
 * @author Dongyoung Jung
 */
public class LaneManagerLaneHandler {
	
	private LaneManagerApp app;	///< Instance of class 'LaneManagerApp'
	private int laneNum;	///< Lane number
	
	/**
	 * @brief Constructor
	 * @param app : Instance of class 'LaneManagerApp'
	 */
	public LaneManagerLaneHandler(LaneManagerApp app){
		this.app = app;
	}
	
	/**
	 * The lane number is verified at the first line.
	 * "Switch On" : On graphics panel, the lane runs. On GUI Panel, the switch turns 'on'.
	 * "Switch Off" : On graphics panel, the lane does not run. On GUI Panel, the switch turns 'off'
	 * "Amplitude Weak" : On graphics panel, the lane vibrates weakly. On GUI Panel, the amplitude slider turns 'weak'
	 * "Amplitude Normal" : On graphics panel, the lane vibrates normally. On GUI Panel, the amplitude slider turns 'normal'
	 * "Amplitude Strong" : On graphics panel, the lane vibrates strongly. On GUI Panel, the amplitude slider turns 'strong'
	 * "MOVE" : On graphics panel, the lane moves. This signal comes from timer in server.
	 * 
	 * @brief Verification and Action Takes
	 * @param message : message from server
	 */
	public void verify(String message){
		laneNum =  message.charAt(0) - 48;
		
		// GUI & Graphic changes--------------------------------------------------------------------------------------------------------------
		// Message : Lane Switch On
		if( message.indexOf("Switch On") != -1 ){
			app.getGUIPanel().getGUILane().getGUILaneArray(laneNum).setLaneSwitch(true);
		}
		
		// Message : Lane Switch Off
		else if( message.indexOf("Switch Off") != -1 ){
			// GUI Panel change;
			app.getGUIPanel().getGUILane().getGUILaneArray(laneNum).setLaneSwitch(false);
			// Graphics Panel change
			app.getGraphicsPanel().getLaneArray(laneNum).setLocationToOriginWhenOff();
		}
		
		// Message : Lane Vibration Amplitude Weak
		else if( message.indexOf("Amplitude Weak") != -1 ){
			// GUI Panel change
			app.getGUIPanel().getGUILane().getGUILaneArray(laneNum).setLaneVibrationAmplitudeWeak();
			// Graphics Panel change
			app.getGraphicsPanel().getLaneArray(laneNum).setLaneVibrateAmplitude(0);
		}
		
		// Message : Lane Vibration Amplitude Normal
		else if( message.indexOf("Amplitude Normal") != -1 ){
			// GUI Panel change
			app.getGUIPanel().getGUILane().getGUILaneArray(laneNum).setLaneVibrationAmplitudeNormal();
			// Graphics Panel change
			app.getGraphicsPanel().getLaneArray(laneNum).setLaneVibrateAmplitude(1);
		}
		
		// Message : Lane Vibration Amplitude Strong
		else if( message.indexOf("Amplitude Strong") != -1 ){
			// GUI Panel change
			app.getGUIPanel().getGUILane().getGUILaneArray(laneNum).setLaneVibrationAmplitudeStrong();
			// Graphics Panel change
			app.getGraphicsPanel().getLaneArray(laneNum).setLaneVibrateAmplitude(2);
		}
		// ----------------------------------------------------------------------------------------------------------------------------------------------------
		// Action ----------------------------------------------------------------------------------------------------------------------------------------
		// Message : Timer ( Lane Move )
		else if( message.indexOf("MOVE") != -1){
			app.getGraphicsPanel().getLaneArray(laneNum).moveLane();
		}
		// ----------------------------------------------------------------------------------------------------------------------------------------------------
	}
}
