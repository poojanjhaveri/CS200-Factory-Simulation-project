package factory.factory200.factoryProductionManager.LaneManager;

/**
 * @brief Lane Handler
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
		
		// Message : Lane Switch On
		if( message.contains("Switch On") ){
			// Graphics Panel change
			app.getAllLane().getLane(laneNum).setSwitch(true);
		}
		
		// Message : Lane Switch Off
		else if( message.contains("Switch Off") ){
			// Graphics Panel change
			app.getAllLane().getLane(laneNum).setSwitch(false);
		}
		
		// Message : Lane Vibration Amplitude Normal
		else if( message.contains("Amplitude Normal") ){
			// Track Setting
			app.getAllLane().getLane(laneNum).setLaneVibrationAmplitudeNormal();
			System.out.println("afdasf");
		}
		
		// Message : Lane Vibration Amplitude Strong
		else if( message.contains("Amplitude Strong") ){			
			// Track Setting
			app.getAllLane().getLane(laneNum).setLaneVibrationAmplitudeStrong();
			
			// Jammed Lane Cancel
			app.getAllPart().getLane(laneNum).switchJammedLane(false, -1);
		}
	}
}
