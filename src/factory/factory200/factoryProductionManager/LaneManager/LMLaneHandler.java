package factory.factory200.factoryProductionManager.LaneManager;

public class LMLaneHandler {
	
	private LMApplication app;	///< Instance of class 'LMApplication'
	private int laneNum;	///< Lane number

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
			// Graphics Panel change
			app.getAllLane().getLane(laneNum).setSwitch(true);
		}
		
		// Message : Lane Switch Off
		else if( message.indexOf("Switch Off") != -1 ){
			// Graphics Panel change
			app.getAllLane().getLane(laneNum).setSwitch(false);
		}
		
		// Message : Lane Vibration Amplitude Weak
		else if( message.indexOf("Amplitude Weak") != -1 ){

		}
		
		// Message : Lane Vibration Amplitude Normal
		else if( message.indexOf("Amplitude Normal") != -1 ){

		}
		
		// Message : Lane Vibration Amplitude Strong
		else if( message.indexOf("Amplitude Strong") != -1 ){

		}
	}
}
