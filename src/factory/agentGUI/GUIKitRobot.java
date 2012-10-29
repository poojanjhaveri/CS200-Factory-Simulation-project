/*
HEY YOU. YOU YES YOU. STOP FORMATTING MY CODE? MMK? THANKS <3.
 */
package factory.agentGUI;

/**
 * The GuiKitRobot handles the moving of kits in the kit assembly area. It takes
 * orders from the Kit Assembly Manager. The GuiKitRobot must also communicate
 * with the KitDelivery manager to obtain and deliver kits It has pickup
 * functionality to move entire kits The robot is capable of rotating along the
 * base and extending the arm, but is incapable of moving from its base. 
 * <img src="../img/image04.png" alt="idle GuiKitRobot"/> 
 * <img src="../img/image10.png" alt="GuiKitRobot with active kit"/>
 *
 * @brief Robot that moves kits in the KitWorkingArea
 * @author YiWei Roy Zheng
 */
public class GUIKitRobot {

    GuiKit kit;///<null if not carrying a kit, otherwise contains a reference the kit its carrying
    KitWorkingArea kits;///<information about all work benches and their kits
    Double rotation;///<information on degrees of rotation. 0 degrees is due right
    Boolean busy;///<whether or not the KitterRobot has orders
    Double turnto;///<angle the GuiKitRobot is turning to
    public GUIKitRobot{

    }
    /**
sets a new rotation angle for the KitterRobot
    */
    public void turnTo(Double a) {
    }
    /**
sets a new lane destination coordinate for KitterRobot, the passed Integer specifies which lane to head to
    */
    public void moveToLane(Integer l) {
    }
    /**
updates the location of the kit it��s carrying
    */
    public void updateKit() {
    }
}