<<<<<<< HEAD
=======
package factory.agentGUI;
>>>>>>> e55bea809a4f01ab018a7b8b99f6378440c1052c

/**
 * @brief changes hardware status on Nest GUI and Graphics Panel
 * @author Dongyoung Jung
 */
public class NestHandler {

    private LaneManagerApp laneManagerApp;
    private int nestNum;

    public NestHandler(LaneManagerApp laneManagerApp) {
        this.laneManagerApp = laneManagerApp;
    }

    public void verify(String message) {
        // Nest Number Assignment
        nestNum = message.charAt(message.length() - 1) - 48;

        // GUI & Graphic changes--------------------------------------------------------------------------------------------------------------
        // Message : Nest Switch Up
        if (message.indexOf("Switch Up") != -1) {
            laneManagerApp.getGUIPanel().getGUINest().getGUINestArray(nestNum).setNestSwitch(true);
        } // Message : Nest Switch Down
        else if (message.indexOf("Switch Down") != -1) {
            laneManagerApp.getGUIPanel().getGUINest().getGUINestArray(nestNum).setNestSwitch(false);
        } // ----------------------------------------------------------------------------------------------------------------------------------------------------
        // Action ----------------------------------------------------------------------------------------------------------------------------------------
        // Message : Robot Takes One
        else if (message.indexOf("Robot Takes One") != -1) {
            laneManagerApp.getGraphicsPanel().getNestArray(nestNum).robotTakePart();
        }
        // ----------------------------------------------------------------------------------------------------------------------------------------------------
    }
}
