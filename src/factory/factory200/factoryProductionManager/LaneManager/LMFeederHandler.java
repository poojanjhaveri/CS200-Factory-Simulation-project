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
		if( message.indexOf("Feeder Switch On") != -1 ){
			// GraphicsPanel change
			app.getAllFeeder().getFeeder(feederNum).setSwitch(true);
		}
		
		// Message : Feeder Off
		else if( message.indexOf("Feeder Switch Off") != -1 ){
			// GraphicsPanel change
			app.getAllFeeder().getFeeder(feederNum).setSwitch(false);
		}
		
		// Message : Part Low Sensor On
		else if( message.indexOf("Part Low Sensor On") != -1 ){
			// GraphicsPanel change
			app.getAllFeeder().getPartLowBulb(feederNum).setSwitch(true);
		}
		
		// Message : Part Low Sensor Off
		else if( message.indexOf("Part Low Sensor Off") != -1 ){
			// GraphicsPanel change
			app.getAllFeeder().getPartLowBulb(feederNum).setSwitch(false);
		}
		
		// Message : Feed Parts Switch On
		else if( message.indexOf("Feed Part Switch On") != -1 ){
			// GraphicsPanel change
			app.getAllFeeder().getFeedingBulb(feederNum).setSwitch(true);
		}
		
		// Message : Feed Parts Switch Off
		else if( message.indexOf("Feed Part Switch Off") != -1 ){
			// GraphicsPanel change
			app.getAllFeeder().getFeedingBulb(feederNum).setSwitch(false);
		}
		
		// Message : Part Fed Counter Setup
		else if( message.indexOf("Part Fed Counter") != -1 ){
		}
		
		// Message : Lowers Rear Gate
		else if( message.indexOf("Rear Gate Lower") != -1 ){
			// GraphicsPanel change
			app.getAllFeeder().getRearGateBulb(feederNum).setSwitch(true);
		}
		
		// Message : Raises Rear Gate
		else if( message.indexOf("Rear Gate Raise") != -1 ){
			// GraphicsPanel change
			app.getAllFeeder().getRearGateBulb(feederNum).setSwitch(false);
		}
		
		// Message : Purge Bin Switch On
		else if( message.indexOf("Purge On") != -1 ){
			// GraphicsPanel change
			app.getAllFeeder().getPurgingBulb(feederNum).setSwitch(true);
		}
		
		// Message : Purge Bin Switch Off
		else if( message.indexOf("Purge Off") != -1 ){
			// GraphicsPanel change
			app.getAllFeeder().getPurgingBulb(feederNum).setSwitch(false);
		}
		
		// Message : Divert To Left
		else if( message.indexOf("Divert To Left") != -1 ){
			// GraphicsPanel change
			app.getAllFeeder().getDiversionBulb(feederNum).setDivertToLeft();
		}
		
		// Message : Divert To Right
		else if( message.indexOf("Divert To Right") != -1 ){
			// GraphicsPanel change
			app.getAllFeeder().getDiversionBulb(feederNum).setDivertToRight();
		}
	}
}
