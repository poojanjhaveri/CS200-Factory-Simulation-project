package factory.factory200.laneManager.ClientSide;

/**
 * @brief Server Signal Handler(Lane)
 * @author Dongyoung Jung
 */
public class LMLaneHandler {
	
	private LMApplication app;
	private int laneNum;

	public LMLaneHandler(LMApplication app){
		this.app = app;
		
		verify("0Switch On");
		verify("1Switch On");
		verify("2Switch On");
		verify("3Switch On");
		verify("4Switch On");
		verify("5Switch On");
		verify("6Switch On");
		verify("7Switch On");
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
