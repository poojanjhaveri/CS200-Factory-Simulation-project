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
	private int partNum;	///< Part number
	private int colorNum; ///< Color number for feeder
	
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
				
		// GUI & Graphic changes--------------------------------------------------------------------------------------------------------------
		// Message : Feeder On
		if( message.contains("Feeder Switch On") ){
			// GraphicsPanel change
			app.getAllFeeder().getFeeder(feederNum).setSwitch(true);
		}
		
		// Message : Feeder Off
		else if( message.contains("Feeder Switch Off") ){
			// GraphicsPanel change
			app.getAllFeeder().getFeeder(feederNum).setSwitch(false);
		}
		
		// Message : Part Low Sensor On
		else if( message.contains("Part Low Sensor On") ){
			// GraphicsPanel change
			app.getAllFeeder().getPartLowBulb(feederNum).setSwitch(true);
		}
		
		// Message : Part Low Sensor Off
		else if( message.contains("Part Low Sensor Off") ){
			// GraphicsPanel change
			app.getAllFeeder().getPartLowBulb(feederNum).setSwitch(false);
		}
		
		// Message : Feed Parts Switch On
		else if( message.contains("Feed Part Switch On") ){
			// GraphicsPanel change
			app.getAllFeeder().getFeedingBulb(feederNum).setSwitch(true);
		}
		
		// Message : Feed Parts Switch Off
		else if( message.contains("Feed Part Switch Off") ){
			// GraphicsPanel change
			app.getAllFeeder().getFeedingBulb(feederNum).setSwitch(false);
		}
		
		// Message : Lowers Rear Gate
		else if( message.contains("Rear Gate Lower") ){
			// GraphicsPanel change
			app.getAllFeeder().getRearGateBulb(feederNum).setSwitch(true);
		}
		
		// Message : Raises Rear Gate
		else if( message.contains("Rear Gate Raise") ){
			// GraphicsPanel change
			app.getAllFeeder().getRearGateBulb(feederNum).setSwitch(false);
		}
		
		// Message : Purge Bin Switch On
		else if( message.contains("Purge On") ){
			// GraphicsPanel change
			app.getAllFeeder().getPurgingBulb(feederNum).setSwitch(true);
		}
		
		// Message : Purge Bin Switch Off
		else if( message.contains("Purge Off") ){
			// GraphicsPanel change
			app.getAllFeeder().getPurgingBulb(feederNum).setSwitch(false);
		}
		
		// Message : Divert To Left
		else if( message.contains("Divert To Left") ){
			// GraphicsPanel change
			app.getAllFeeder().getDiversionBulb(feederNum).setDivertToLeft();
		}
		
		// Message : Divert To Right
		else if( message.contains("Divert To Right") ){
			// GraphicsPanel change
			app.getAllFeeder().getDiversionBulb(feederNum).setDivertToRight();
		}
	}
}
