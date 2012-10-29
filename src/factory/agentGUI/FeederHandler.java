
/**
 * @brief changes hardware status on Feeder GUI and Graphics Panel
 * @author Dongyoung Jung
 */
public class FeederHandler {

    private LaneManagerApp laneManagerApp;
    private int feederNum;

    public FeederHandler(LaneManagerApp laneManagerApp) {
        this.laneManagerApp = laneManagerApp;
    }

    public void verify(String message) {
        // Feeder Number Assignment
        feederNum = message.charAt(message.length() - 1) - 48;

        // GUI & Graphic changes--------------------------------------------------------------------------------------------------------------
        // Message : Feeder On
        if (message.indexOf("Feeder On") != -1) {
            // GUIPanel change
            laneManagerApp.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setFeederSwitchOn();
            // GraphicsPanel change
            laneManagerApp.getGraphicsPanel().getFeederArray(feederNum).setSwitchOn();
        } // Message : Feeder Off
        else if (message.indexOf("Feeder Off") != -1) {
            // GUIPanel change
            laneManagerApp.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setFeederSwitchOff();
            // GraphicsPanel change
            laneManagerApp.getGraphicsPanel().getFeederArray(feederNum).setSwitchOff();
        } // Message : Part Low Sensor On
        else if (message.indexOf("Part Low Sensor On") != -1) {
            laneManagerApp.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setFeederPartLowSensor();
        } // Message : Part Low Sensor Off
        else if (message.indexOf("Part Low Sensor Off") != -1) {
            laneManagerApp.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setFeederPartHighSensor();
        } // Message : Feed Parts Switch On
        else if (message.indexOf("Feed Parts Switch On") != -1) {
            laneManagerApp.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setFeederFeedPartsSwitchOn();
        } // Message : Feed Parts Switch Off
        else if (message.indexOf("Feed Parts Switch Off") != -1) {
            laneManagerApp.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setFeederFeedPartsSwitchOff();
        } // Message : Part Fed Counter Increment
        else if (message.indexOf("Part Fed Counter Increment") != -1) {
            laneManagerApp.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setPartFedCounterIncrement();
        } // Message : Lowered Rear Gate
        else if (message.indexOf("Lowered Rear Gate") != -1) {
            // GUIPanel change
            laneManagerApp.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setLowerRaiseRearGateSwitchOff();
        } // Message : Raised Rear Gate
        else if (message.indexOf("Raised Rear Gate") != -1) {
            // GUIPanel change
            laneManagerApp.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setLowerRaiseRearGateSwitchOn();
        } // Message : Purge Bin Switch On
        else if (message.indexOf("Purge Bin Switch On") != -1) {
            laneManagerApp.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setPurgeBinSwitchOn();
        } // Message : Purge Bin Switch Off
        else if (message.indexOf("Purge Bin Switch Off") != -1) {
            laneManagerApp.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setPurgeBinSwitchOff();
        } // Message : Divert To Left
        else if (message.indexOf("Divert To Left") != -1) {
            // GUIPanel change
            laneManagerApp.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setDivertToLeft();
        } // Message : Divert To Right
        else if (message.indexOf("Divert To Right") != -1) {
            // GUIPanel change
            laneManagerApp.getGUIPanel().getGUIFeeder().getGUIFeederArray(feederNum).setDivertToRight();
        } // ----------------------------------------------------------------------------------------------------------------------------------------------------
        // Action ----------------------------------------------------------------------------------------------------------------------------------------
        // Message : Robot Dump Part Box
        else if (message.indexOf("Robot Dump Part Box") != -1) {
            laneManagerApp.getGraphicsPanel().getFeederArray(feederNum).setPartBoxInFeeder();
        } // Message : Robot Dump Part Box ( LEFT LANE )
        else if (message.indexOf("Feed Part Onto Lane Left") != -1) {
            laneManagerApp.getGraphicsPanel().getFeederArray(feederNum).feedPartOntoLaneLeft();
        } // Message : Robot Dump Part Box ( RIGHT LANE )
        else if (message.indexOf("Feed Part Onto Lane Right") != -1) {
            laneManagerApp.getGraphicsPanel().getFeederArray(feederNum).feedPartOntoLaneRight();
        }
        // ----------------------------------------------------------------------------------------------------------------------------------------------------
    }
}
