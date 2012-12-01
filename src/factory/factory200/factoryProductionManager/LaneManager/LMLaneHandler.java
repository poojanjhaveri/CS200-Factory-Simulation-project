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
		
		if( message.contains("Switch On") ){
			app.getAllLane().getLane(laneNum).setSwitch(true);
		}
		
		else if( message.contains("Switch Off") ){
			app.getAllLane().getLane(laneNum).setSwitch(false);
		}
		
		else if( message.contains("Amplitude Normal") ){
			app.getAllLane().getLane(laneNum).setLaneVibrationAmplitudeNormal();
		}
		
		else if( message.contains("Amplitude Strong") ){			
			app.getAllLane().getLane(laneNum).setLaneVibrationAmplitudeStrong();
			// Jammed Lane Cancel
			app.getAllPart().getLane(laneNum).switchJammedLane(false);
		}
	}
}
