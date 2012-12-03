//DO NOT FORMAT MY CODE IN ANYTHING OTHER THAN ASTYLE. THANKS.
//                REVISION: JUST DON'T FORMAT MY CODE. TY.
package factory.factory200.kitAssemblyManager;

import factory.general.GUIRobot;
import factory.general.Part;

import java.util.Collection;
import java.util.LinkedList;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
8 - drop parts onto kit 1
9 - drop parts onto kit 0
...
10-17 - move to nest 0-7
18 - move to kit0
19 - move to kit1
20 - move to default
21-28 - flash camera nest 0-7
49 - wait for one turn

 * @brief Robot that creates kits using parts from the lane nests
 * @author YiWei Roy Zheng
 * @version 0.5
 */
public class GUIPartRobot extends GUIRobot {

    PartsRobotInventory parts;///<current parts on the GUIPartRobot, en route to the Kit
    Boolean busy;///<whether or not the GUIPartRobot has orders
    Integer moveto;///<which lane the GUIPartRobot is heading to

    public GUIPartRobot() {
        // variables will be set in the KAMGraphicsPanel
        super(KAMGraphicPanel.PARTSROBOTINITIALX, KAMGraphicPanel.PARTSROBOTINITIALY, "pics/partsrobot.png");
        this.busy = false;
        this.parts = new PartsRobotInventory();
        this.moveto = 0;
        this.setConstants(KAMGraphicPanel.PARTSROBOT_VELOCITYX, KAMGraphicPanel.PARTSROBOT_VELOCITYY, KAMGraphicPanel.KITROBOT_ROTATION_SPEED);
    }

    /**
    sets a new lane destination coordinate for GUIPartRobot, the passed Integer specifies which lane to head to
     */
    public void moveToNest(Integer l) {
        switch (l) {
            case 0:
                this.moveTo(KAMGraphicPanel.RAILX, KAMGraphicPanel.LANE0Y);
                this.moveto = 0;
                break;
            case 1:
                this.moveTo(KAMGraphicPanel.RAILX, KAMGraphicPanel.LANE1Y);
                this.moveto = 1;
                break;
            case 2:
                this.moveTo(KAMGraphicPanel.RAILX, KAMGraphicPanel.LANE2Y);
                this.moveto = 2;
                break;
            case 3:
                this.moveTo(KAMGraphicPanel.RAILX, KAMGraphicPanel.LANE3Y);
                this.moveto = 3;
                break;
            case 4:
                this.moveTo(KAMGraphicPanel.RAILX, KAMGraphicPanel.LANE4Y);
                this.moveto = 4;
                break;
            case 5:
                this.moveTo(KAMGraphicPanel.RAILX, KAMGraphicPanel.LANE5Y);
                this.moveto = 5;
                break;
            case 6:
                this.moveTo(KAMGraphicPanel.RAILX, KAMGraphicPanel.LANE6Y);
                this.moveto = 6;
                break;
            case 7:
                this.moveTo(KAMGraphicPanel.RAILX, KAMGraphicPanel.LANE7Y);
                this.moveto = 7;
                break;
        }
    }

    /**
    @brief moves the parts robot to the dropoff site for parts
     */
    public void moveToKit0() {
        this.moveTo(KAMGraphicPanel.PARTS_ROBOT_KIT0X, KAMGraphicPanel.PARTS_ROBOT_KIT0Y);
    }
    public void moveToKit1()
    {
        this.moveTo(KAMGraphicPanel.PARTS_ROBOT_KIT1X,KAMGraphicPanel.PARTS_ROBOT_KIT1Y);
    }

    public void update() {
        super.update();
        this.parts.update(this.cords);
    }

    public void addPart(Part in) {
        this.parts.addPart(in);
    }

    public LinkedList<Part> removePart() {
        if(this.parts.size()==0){
            System.out.println("ATTEMPTING TO RETRIEVE PART FROM EMPTY KITTER");
        }
        return this.parts.giveAll();
    }

    public LinkedList<Part> getPart() {
        return this.parts.getAll();
    }

    public void pickPartCommand(Integer i) {
        this.orders.add(i);
    }

    public void dropPartCommand(Integer kit) {
        if(kit == 0)
        this.orders.add(9);
        else if(kit == 1)
            this.orders.add(8);
        else System.out.println("Invalid attempt to drop part off in kit " +kit);
    }

    public void cheat() {
        moveToNestCommand(0);
        pickPartCommand(0);
        pickPartCommand(0);
        pickPartCommand(0);
        pickPartCommand(0);
        dropOffParts(1);
    }

    public void moveToNestCommand(Integer i) {
        this.orders.add(i + 10);
        this.orders.add(50);
    }

    public void dropOffParts(Integer i) {
        if(i==0){
        this.orders.add(18);
        }
        else if(i==1){
            this.orders.add(19);
        }
        dropPartCommand(i);
        this.orders.add(50);
    }
    public Part dropPartOnGround()
    {
	Part p = null;
	if(this.parts.size() != 0){
	    p = this.parts.getAll().get(this.parts.size()-1);
	    this.parts.getAll().remove(this.parts.size()-1);
	    if(p.getName() == null)
		p.setName("unnamed");
	    if(p.getFilename() == null)
		p.setFilename("nofile");
	    if(p.getDescription() == null)
		p.setDescription("no description");
	}
	return p;
    }

    /**
    @brief pops the order and performs it
     */
    public Boolean performOrder() {
        //System.out.println("PERFORMING ORDER");
        Integer i = this.popOrder();
        switch (i) {
            case 10:
               
            case 11:
               
            case 12:
               
            case 13:
               
            case 14:
              
            case 15:
              
            case 16:
              
            case 17:
                this.moveToNest(i - 10);
                break;
            case 18:
                this.moveToKit0();
                break;
            case 19:
                this.moveToKit1();
                break;
            case 20:
                this.moveToDefault();
                break;
            default:
                return false;
        }
        return true;
    }
    public void checkDefault()
    {
        if(this.orders.size() == 0){
            
          this.orders.add(20);
        }
    }
    public Integer popOrder() {
        return super.popOrder();
    }

    private void moveToDefault() {
        this.moveTo(KAMGraphicPanel.PARTSROBOTINITIALX, KAMGraphicPanel.PARTSROBOTINITIALY);
    }

    public void paintMe(JPanel panel, Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g.create();
        this.getImage().paintIcon(panel, g2d, this.getCoordinate().getX(), this.getCoordinate().getY());
        LinkedList<Part> kitterparts = this.getPart();
        for (int i = 0; i != kitterparts.size(); i++) {
            kitterparts.get(i).getGUIPart().getImage().paintIcon(panel, g2d, kitterparts.get(i).getGUIPart().getX(), kitterparts.get(i).getGUIPart().getY());
        }
        g2d.dispose();
    }
    public void blockNestNonNorm(int nest)
    {
	this.orders.add(nest+10);//move to nest
	this.orders.add(21+nest);//flash camera
	for(int i = 0; i != 30; i++)
	    this.orders.add(49);//pause
	this.orders.add(20);//move to default
	for(int i = 0; i != 70; i++)
	    this.orders.add(49);//pause
	this.orders.add(21+nest);//flash again
    }

}