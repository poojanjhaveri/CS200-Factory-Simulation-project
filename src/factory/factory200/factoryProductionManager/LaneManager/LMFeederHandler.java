package factory.factory200.factoryProductionManager.LaneManager;

/**
 * The signal from server should be checked in class 'LaneManagerVerifySignalsFromServer'
 * Here, the signals get checked again and take an action to Graphics Panel or GUI Panel.
 * 
 * @brief Verifies signals from server and take an action
 * @author Dongyoung Jung
 */
public class LMFeederHandler {
	
	private LMApplication app;	///< Instance of class 'LaneManagerApp'
	private int feederNum;	///< Feeder number
	
	/**
	 * @brief Constructor
	 * @param app : Instance of class 'LaneManagerApp'
	 */
	public LMFeederHandler(LMApplication app){
		this.app = app;
		
		verify("0Feeder Switch On");
		verify("1Feeder Switch On");
		verify("2Feeder Switch On");
		verify("3Feeder Switch On");
		
		verify("0Feed Part Switch On");
		verify("1Feed Part Switch On");
		verify("2Feed Part Switch On");
		verify("3Feed Part Switch On");
	}
	
	/**
	 * The feeder number is verified at the first line.
	 * "Feeder Switch On" : On graphics panel, the feeder image changes. On GUI Panel, the switch turns 'on'.
	 * "Feeder Switch Off" : On graphics panel, the feeder image changes. On GUI Panel, the switch turns 'off'
	 * "Part Low Sensor On" : On GUI Panel, the switch turns 'on'
	 * "Part Low Sensor Off" : On GUI Panel, the switch turns 'off'
	 * "Feed Part Switch On" : On GUI Panel, the switch turns 'on'
	 * "Feed Part Switch Off" : On GUI Panel, the switch turns 'off'
	 * "Part Fed Counter" : On GUI Panel, the counter increases by 1
	 * "Rear Gate Lower" : On graphics panel, the feeder image changes. On GUI Panel, the switch turns 'lower'
	 * "Rear Gate Raise" : On graphics panel, the feeder image changes. On GUI Panel, the switch turns 'raise'
	 * "Purge On" : On GUI Panel, the switch turns 'on'
	 * "Purge Off" : On GUI Panel, the switch turns 'off'
	 * "Divert To Left" : On graphics panel,the feeder image changes. On GUI Panel, the switch turns 'left'
	 * "Divert To Right" : On graphics panel, the feeder image changes. On GUI Panel, the switch turns 'right'
	 * "Dumped" : On graphics panel, the feeder image changes.
	 * "Feed To Left" : On graphics panel, one part is put on left lane. ( Made For testing ) 
	 * "Feed To Right" : On graphics panel, one part is put on right lane. ( Made For testing )
	 * 
	 * @brief Verification and Action Takes
	 * @param message : message from server
	 */
	public void verify(String message){		
		feederNum =  message.charAt(0) - 48;
				
		if( message.contains("Feeder Switch On") ){
			app.getAllFeeder().getFeeder(feederNum).setSwitch(true);
		}
		
		else if( message.contains("Feeder Switch Off") ){
			app.getAllFeeder().getFeeder(feederNum).setSwitch(false);
		}
		
		else if( message.contains("Part Low Sensor On") ){
			app.getAllFeeder().getPartLowBulb(feederNum).setSwitch(true);
		}
		
		else if( message.contains("Part Low Sensor Off") ){
			app.getAllFeeder().getPartLowBulb(feederNum).setSwitch(false);
		}
		
		else if( message.contains("Feed Part Switch On") ){
			app.getAllFeeder().getFeedingBulb(feederNum).setSwitch(true);
		}
		
		else if( message.contains("Feed Part Switch Off") ){
			app.getAllFeeder().getFeedingBulb(feederNum).setSwitch(false);
		}
		
		else if( message.contains("Rear Gate Lower") ){
			app.getAllFeeder().getRearGateBulb(feederNum).setSwitch(true);
		}
	
		else if( message.contains("Rear Gate Raise") ){
			app.getAllFeeder().getRearGateBulb(feederNum).setSwitch(false);
		}
		
		else if( message.contains("Purge On") ){
			app.getAllFeeder().getPurgingBulb(feederNum).setSwitch(true);
		}
		
		else if( message.contains("Purge Off") ){
			app.getAllFeeder().getPurgingBulb(feederNum).setSwitch(false);
		}
		
		else if( message.contains("Divert To Left") ){
			app.getAllFeeder().getDiversionBulb(feederNum).setDivertToLeft();
		}
		
		else if( message.contains("Divert To Right") ){
			app.getAllFeeder().getDiversionBulb(feederNum).setDivertToRight();
		}
	}
}
