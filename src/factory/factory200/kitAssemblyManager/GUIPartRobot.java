//DO NOT FORMAT MY CODE IN ANYTHING OTHER THAN ASTYLE. THANKS.
package factory.factory200.kitAssemblyManager;

import factory.general.GUIRobot;

/**
 * The GUIPartRobot obtains parts from the nest and places it into the working
 * kit. It takes orders from the Kit Assembly Manager. It has pickup
 * functionality to transport parts along its arm to the working kit It moves
 * along a rail and only stops at one of eight preset positions facing a lane
 * the robot arm rotates along the rail to face the lane at the optimal angle
 * <img src="../img/image09.png" alt="GUIPartRobot waiting for nest to fill"/>
 * <img src="../img/image08.png" alt="GUIPartRobot picking from the lane"/>
 *
 * @brief Robot that creates kits using parts from the lane nests
 * @author YiWei Roy Zheng
 */
public class GUIPartRobot extends GUIRobot {


    PartsRobotInventory parts;///<current parts on the GUIPartRobot, en route to the Kit
    Boolean busy;///<whether or not the GUIPartRobot has orders
    Integer moveto;///<which lane the GUIPartRobot is heading to

    public GUIPartRobot()
    {
        // variables will be set in the KAMGraphicsPanel
        super(KAMGraphicPanel.PARTSROBOTINITIALX,KAMGraphicPanel.PARTSROBOTINITIALY,"partsrobot.png");
        this.busy = false;
        this.parts = new PartsRobotInventory();
        this.moveto = 0;
        this.moveTo(KAMGraphicPanel.RAILX,KAMGraphicPanel.LANE0Y);
    }

    /**
    sets a new lane destination coordinate for GUIPartRobot, the passed Integer specifies which lane to head to
    */
    public void moveToLane(Integer l) {
        switch(l)
        {
        case 0:
            this.moveTo(KAMGraphicPanel.RAILX,KAMGraphicPanel.LANE0Y);
            this.moveto = 0;
            break;
        case 1:
            this.moveTo(KAMGraphicPanel.RAILX,KAMGraphicPanel.LANE1Y);
            this.moveto = 1;
            break;
        case 2:
            this.moveTo(KAMGraphicPanel.RAILX,KAMGraphicPanel.LANE2Y);
            this.moveto = 2;
            break;
        case 3:
            this.moveTo(KAMGraphicPanel.RAILX,KAMGraphicPanel.LANE3Y);
            this.moveto = 3;
            break;
        case 4:
            this.moveTo(KAMGraphicPanel.RAILX,KAMGraphicPanel.LANE4Y);
            this.moveto = 4;
            break;
        case 5:
            this.moveTo(KAMGraphicPanel.RAILX,KAMGraphicPanel.LANE5Y);
            this.moveto = 5;
            break;
        case 6:
            this.moveTo(KAMGraphicPanel.RAILX,KAMGraphicPanel.LANE6Y);
            this.moveto = 6;
            break;
        case 7:
            this.moveTo(KAMGraphicPanel.RAILX,KAMGraphicPanel.LANE7Y);
            this.moveto = 7;
            break;
        }
    }

    public void updateParts() {
    }///<updates the location of all parts in the inventory
}