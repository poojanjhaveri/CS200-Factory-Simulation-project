package factory.agentGUI;

/**
 * The GuiPartRobot obtains parts from the nest and places it into the working
 * kit. It takes orders from the Kit Assembly Manager. It has pickup
 * functionality to transport parts along its arm to the working kit It moves
 * along a rail and only stops at one of eight preset positions facing a lane
 * the robot arm rotates along the rail to face the lane at the optimal angle
 * <img src="../img/image09.png" alt="GuiPartRobot waiting for nest to fill"/>
 * <img src="../img/image08.png" alt="GuiPartRobot picking from the lane"/>
 *
 * @brief Robot that creates kits using parts from the lane nests
 * @author YiWei Roy Zheng
 */
public class GuiPartRobot {

    GuiInventory parts;///<current parts on the GuiPartRobot, en route to the Kit
    Boolean busy;///<whether or not the GuiPartRobot has orders
    Integer moveto;///<which lane the GuiPartRobot is heading to

    public void moveTo(Integer x, Integer y) {
    }///<sets a new destination coordinate for the GuiPartRobot

    public void moveToLane(Integer l) {
    }///<sets a new lane destination coordinate for GuiPartRobot, the passed Integer specifies which lane to head to

    public void updateParts() {
    }///<updates the location of all parts in the inventory
}