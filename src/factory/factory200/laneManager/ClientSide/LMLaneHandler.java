package factory.factory200.laneManager.ClientSide;

public class LMLaneHandler {
	
	private LMApplication app;	///< Instance of class 'LMApplication'
	private int laneNum;	///< Lane number

	public LMLaneHandler(LMApplication app){
		this.app = app;
	}

	public void verify(String message){
		laneNum =  message.charAt(0) - 48;
		
		// GUI & Graphic changes--------------------------------------------------------------------------------------------------------------
		// Message : Lane Switch On
		if( message.indexOf("Switch On") != -1 ){
			// GUI Panel change
			app.getGUIPanel().getGUILane().getGUILaneArray(laneNum).setLaneSwitch(true);
			// Graphics Panel change
			app.getGraphicsPanel().getAllLane().getLane(laneNum).setSwitch(true);
		}
		
		// Message : Lane Switch Off
		else if( message.indexOf("Switch Off") != -1 ){
			// GUI Panel change
			app.getGUIPanel().getGUILane().getGUILaneArray(laneNum).setLaneSwitch(false);
			// Graphics Panel change
			app.getGraphicsPanel().getAllLane().getLane(laneNum).setSwitch(false);
		}
		
		// Message : Lane Vibration Amplitude Weak
		else if( message.indexOf("Amplitude Weak") != -1 ){
			// GUI Panel change
			app.getGUIPanel().getGUILane().getGUILaneArray(laneNum).setLaneVibrationAmplitudeWeak();
		}
		
		// Message : Lane Vibration Amplitude Normal
		else if( message.indexOf("Amplitude Normal") != -1 ){
			// GUI Panel change
			app.getGUIPanel().getGUILane().getGUILaneArray(laneNum).setLaneVibrationAmplitudeNormal();
		}
		
		// Message : Lane Vibration Amplitude Strong
		else if( message.indexOf("Amplitude Strong") != -1 ){
			// GUI Panel change
			app.getGUIPanel().getGUILane().getGUILaneArray(laneNum).setLaneVibrationAmplitudeStrong();
		}
	}
}
