<<<<<<< HEAD
=======
package factory.agentGUI;
>>>>>>> e55bea809a4f01ab018a7b8b99f6378440c1052c

/**
 * @brief changes hardware status on Lane GUI and Graphics Panel
 * @author Dongyoung Jung
 */
public class LaneHandler {

    private LaneManagerApp laneManagerApp;
    private int laneNum;

    public LaneHandler(LaneManagerApp laneManagerApp) {
        this.laneManagerApp = laneManagerApp;
    }

    public void verify(String message) {
        // Lane Number Assignment
        laneNum = message.charAt(message.length() - 1) - 48;

        // GUI & Graphic changes--------------------------------------------------------------------------------------------------------------
        // Message : Lane Switch On
        if (message.indexOf("Switch On") != -1) {
            laneManagerApp.getGUIPanel().getGUILane().getGUILaneArray(laneNum).setLaneSwitch(true);
        } // Message : Lane Switch Off
        else if (message.indexOf("Switch Off") != -1) {
            // GUI Panel change;
            laneManagerApp.getGUIPanel().getGUILane().getGUILaneArray(laneNum).setLaneSwitch(false);
            // Graphics Panel change
            laneManagerApp.getGraphicsPanel().getLaneArray(laneNum).setLocationToOriginWhenOff();
        } // Message : Lane Vibration Amplitude Weak
        else if (message.indexOf("Amplitude Weak") != -1) {
            // GUI Panel change
            laneManagerApp.getGUIPanel().getGUILane().getGUILaneArray(laneNum).setLaneVibrationAmplitudeWeak();
            // Graphics Panel change
            laneManagerApp.getGraphicsPanel().getLaneArray(laneNum).setLaneVibrateAmplitude(0);
        } // Message : Lane Vibration Amplitude Normal
        else if (message.indexOf("Amplitude Normal") != -1) {
            // GUI Panel change
            laneManagerApp.getGUIPanel().getGUILane().getGUILaneArray(laneNum).setLaneVibrationAmplitudeNormal();
            // Graphics Panel change
            laneManagerApp.getGraphicsPanel().getLaneArray(laneNum).setLaneVibrateAmplitude(1);
        } // Message : Lane Vibration Amplitude Strong
        else if (message.indexOf("Amplitude Strong") != -1) {
            // GUI Panel change
            laneManagerApp.getGUIPanel().getGUILane().getGUILaneArray(laneNum).setLaneVibrationAmplitudeStrong();
            // Graphics Panel change
            laneManagerApp.getGraphicsPanel().getLaneArray(laneNum).setLaneVibrateAmplitude(2);
        } // ----------------------------------------------------------------------------------------------------------------------------------------------------
        // Action ----------------------------------------------------------------------------------------------------------------------------------------
        // Message : Lane Move
        else if (message.indexOf("Lane Move") != -1) {
            laneManagerApp.getGraphicsPanel().getLaneArray(laneNum).moveLane();
        }
        // ----------------------------------------------------------------------------------------------------------------------------------------------------
    }
}
