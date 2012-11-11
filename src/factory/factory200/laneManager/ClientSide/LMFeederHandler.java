package factory.factory200.laneManager.ClientSide;

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
			// GUIPanel change
			app.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setFeederSwitchOn();
			// GraphicsPanel change
			app.getGraphicsPanel().getAllFeeder().getFeeder(feederNum).setSwitch(true);
		}
		
		// Message : Feeder Off
		else if( message.indexOf("Feeder Switch Off") != -1 ){
			// GUIPanel change
			app.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setFeederSwitchOff();
			// GraphicsPanel change
			app.getGraphicsPanel().getAllFeeder().getFeeder(feederNum).setSwitch(false);
		}
		
		// Message : Part Low Sensor On
		else if( message.indexOf("Part Low Sensor On") != -1 ){
			// GUIPanel change
			app.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setFeederPartLowSensor();
			// GraphicsPanel change
			app.getGraphicsPanel().getAllFeeder().getPartLowBulb(feederNum).setSwitch(true);
		}
		
		// Message : Part Low Sensor Off
		else if( message.indexOf("Part Low Sensor Off") != -1 ){
			// GUIPanel change
			app.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setFeederPartHighSensor();
			// GraphicsPanel change
			app.getGraphicsPanel().getAllFeeder().getPartLowBulb(feederNum).setSwitch(false);
		}
		
		// Message : Feed Parts Switch On
		else if( message.indexOf("Feed Part Switch On") != -1 ){
			// GUIPanel change
			app.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setFeederFeedPartsSwitchOn();
			// GraphicsPanel change
			app.getGraphicsPanel().getAllFeeder().getFeedingBulb(feederNum).setSwitch(true);
		}
		
		// Message : Feed Parts Switch Off
		else if( message.indexOf("Feed Part Switch Off") != -1 ){
			// GUIPanel change
			app.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setFeederFeedPartsSwitchOff();
			// GraphicsPanel change
			app.getGraphicsPanel().getAllFeeder().getFeedingBulb(feederNum).setSwitch(false);
		}
		
		// Message : Part Fed Counter Setup
		else if( message.indexOf("Part Fed Counter") != -1 ){
			// GUIPanel change
			app.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setPartFedCounterIncrease();
		}
		
		// Message : Lowers Rear Gate
		else if( message.indexOf("Rear Gate Lower") != -1 ){
			// GUIPanel change
			app.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setLowerRaiseRearGateSwitchOff();
			// GraphicsPanel change
			app.getGraphicsPanel().getAllFeeder().getRearGateBulb(feederNum).setSwitch(true);
		}
		
		// Message : Raises Rear Gate
		else if( message.indexOf("Rear Gate Raise") != -1 ){
			// GUIPanel change
			app.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setLowerRaiseRearGateSwitchOn();
			// GraphicsPanel change
			app.getGraphicsPanel().getAllFeeder().getRearGateBulb(feederNum).setSwitch(false);
		}
		
		// Message : Purge Bin Switch On
		else if( message.indexOf("Purge On") != -1 ){
			// GUIPanel change
			app.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setPurgeBinSwitchOn();	
			// GraphicsPanel change
			app.getGraphicsPanel().getAllFeeder().getPurgingBulb(feederNum).setSwitch(true);
		}
		
		// Message : Purge Bin Switch Off
		else if( message.indexOf("Purge Off") != -1 ){
			// GUIPanel change
			app.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setPurgeBinSwitchOff();
			// GraphicsPanel change
			app.getGraphicsPanel().getAllFeeder().getPurgingBulb(feederNum).setSwitch(false);
		}
		
		// Message : Divert To Left
		else if( message.indexOf("Divert To Left") != -1 ){
			// GUIPanel change
			app.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setDivertToLeft();
			// GraphicsPanel change
			app.getGraphicsPanel().getAllFeeder().getDiversionBulb(feederNum).setDivertToLeft();
		}
		
		// Message : Divert To Right
		else if( message.indexOf("Divert To Right") != -1 ){
			// GUIPanel change
			app.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setDivertToRight();
			// GraphicsPanel change
			app.getGraphicsPanel().getAllFeeder().getDiversionBulb(feederNum).setDivertToRight();
		}
		// ----------------------------------------------------------------------------------------------------------------------------------------------------
		
		// Action ----------------------------------------------------------------------------------------------------------------------------------------
		// Message : Robot Dump Part Box
		else if( message.indexOf("Dumped") != -1 ){
			colorNum = message.charAt( message.length() - 1 ) - 48;
			//app.getGraphicsPanel().getFeederArray(feederNum).setDumpedIntoFeeder(colorNum);
		}
		
		// Message : Robot Dump Part Box ( LEFT LANE, Made For Testing )
		else if( message.indexOf("Feed To Left") != -1 ){
			// Part Number Assignment
			partNum =  message.charAt( message.length() - 1 ) - 48;
			//app.getGraphicsPanel().getFeederArray(feederNum).feedPartOntoLaneLeft( partNum );
		}
		
		// Message : Robot Dump Part Box ( RIGHT LANE, Made For Testing )
		else if( message.indexOf("Feed To Right") != -1 ){
			// Part Number Assignment
			partNum =  message.charAt( message.length() - 1 ) - 48;
			//app.getGraphicsPanel().getFeederArray(feederNum).feedPartOntoLaneRight( partNum );
		}
		
		// Message : Feeder Without Box
		else if( message.indexOf("Feeder Without Box") != -1 ){
			// Part Number Assignment
			//app.getGraphicsPanel().getFeederArray(feederNum).setFeederWithoutBox();
		}
		// ----------------------------------------------------------------------------------------------------------------------------------------------------
	}
}
