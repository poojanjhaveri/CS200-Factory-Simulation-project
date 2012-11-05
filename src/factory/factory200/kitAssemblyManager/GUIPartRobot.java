//DO NOT FORMAT MY CODE IN ANYTHING OTHER THAN ASTYLE. THANKS.
package factory.factory200.kitAssemblyManager;

import factory.general.GUIRobot;
import factory.general.Part;

import java.util.Collection;
import java.util.LinkedList;
/**
 * The GUIPartRobot obtains parts from the nest and places it into the working
 * kit. It takes orders from the Kit Assembly Manager. It has pickup
 * functionality to transport parts along its arm to the working kit It moves
 * along a rail and only stops at one of eight preset positions facing a lane
 * the robot arm rotates along the rail to face the lane at the optimal angle
 * <img src="../img/image09.png" alt="GUIPartRobot waiting for nest to fill"/>
 * <img src="../img/image08.png" alt="GUIPartRobot picking from the lane"/>
 *
Orders
0-7 - pick up part nest 0-7
8 - drop parts onto kit
...
10-17 - move to nest 0-7
18 - move to kit
 * @brief Robot that creates kits using parts from the lane nests
 * @author YiWei Roy Zheng
 * @version 0.5
 */
public class GUIPartRobot extends GUIRobot {


    PartsRobotInventory parts;///<current parts on the GUIPartRobot, en route to the Kit
    Boolean busy;///<whether or not the GUIPartRobot has orders
    Integer moveto;///<which lane the GUIPartRobot is heading to

    public GUIPartRobot()
    {
        // variables will be set in the KAMGraphicsPanel
        super(KAMGraphicPanel.PARTSROBOTINITIALX,KAMGraphicPanel.PARTSROBOTINITIALY,"pics/partsrobot.png");
        this.busy = false;
        this.parts = new PartsRobotInventory();
        this.moveto = 0;
        this.setConstants(KAMGraphicPanel.PARTSROBOT_VELOCITYX, KAMGraphicPanel.PARTSROBOT_VELOCITYY, KAMGraphicPanel.KITROBOT_ROTATION_SPEED);
    
    }

    /**
    sets a new lane destination coordinate for GUIPartRobot, the passed Integer specifies which lane to head to
    */
    public void moveToNest(Integer l) {
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
    /**
@brief moves the parts robot to the dropoff site for parts
     */
    public void moveToKit()
    {
	this.moveTo(KAMGraphicPanel.PARTS_ROBOT_KITX,KAMGraphicPanel.PARTS_ROBOT_KITY);
    }
    public void update()
    {
	super.update();
	this.parts.update(this.cords);
    }
    public void addPart(Part in)
    {
	this.parts.addPart(in);
    }
    public LinkedList<Part> removePart()
    {
	return this.parts.giveAll();
    }
    public LinkedList<Part> getPart()
    {
        return this.parts.getAll();
    }
        public void pickPartCommand(Integer i)
    {
     this.orders.add(i);   
    }
        public void dropPartCommand()
        {
            this.orders.add(8);
        }
        public void cheat()
        {
	    moveToNestCommand(0);    
        pickPartCommand(0);
            pickPartCommand(0);
            pickPartCommand(0);
            pickPartCommand(0);
            dropOffParts();
    
        }
    public void moveToNestCommand(Integer i)
    {
	this.orders.add(i+10);
    }
    public void dropOffParts()
    {
       this.orders.add(18);
            dropPartCommand();     
    }
    /**
@brief pops the order and performs it
     */
    public Boolean performOrder()
    {
        System.out.println("PERFORMING ORDER");
	Integer i = this.popOrder();
	switch(i)
	    {
	    case 10:
	    case 11:
	    case 12:
	    case 13:
	    case 14:
	    case 15:
	    case 16:
	    case 17:this.moveToNest(i-10);
		    break;
	    case 18:this.moveToKit();
		break;
	    default:return false;
	    }
	return true;
    }
}