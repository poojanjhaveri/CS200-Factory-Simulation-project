package factory.factory200.kitAssemblyManager;

import factory.general.GUIRobot;
import java.util.LinkedList;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 * The GuiKitRobot handles the moving of kits in the kit assembly area. It takes
 * orders from the Kit Assembly Manager. The GuiKitRobot must also communicate
 * with the KitDelivery manager to obtain and deliver kits It has pickup
 * functionality to move entire kits The robot is capable of rotating along the
 * base and extending the arm, but is incapable of moving from its base. <img
 * src="../img/image04.png" alt="idle GuiKitRobot"/> <img
 * src="../img/image10.png" alt="GuiKitRobot with active kit"/>
A queue of orders
0 - pick up kit from conveyer
1 - pick up kit from kit1
2 - pick up kit from kit2
3 - pick up kit from kit3
4 - drop kit onto kit1
5 - drop kit onto kit2
6 - drop kit onto kit3
7 - drop kit onto conveyer
...
9  - move to dropoff conveyer
10 - move to pickup conveyer
11 - move to kit1
12 - move to kit2
13 - move to kit3
...
20 - move to default position
 *
 * @brief Robot that moves kits in the KitWorkingArea
 * @author YiWei Roy Zheng
 * @version 1.0
 */
public class GUIKitRobot extends GUIRobot {

    public static final String IMAGE_PAGE = "pics/kitrobot.png";
    public static final String IMAGE_ARM = "pics/kitrobotarm.png";
    public static final String IMAGE_BASE = "pics/kitrobotbase.png";
    private ImageIcon arm;
    private ImageIcon base;
    KAMKit kit;///<null if not carrying a kit, otherwise contains a reference the kit its carrying
    /**
    @brief creates the GUIKitRobot from the constants specified in KAMGraphicPanel
     */
    public GUIKitRobot() {

        //this is actually set in the KAMGraphicsPanel
        super(KAMGraphicPanel.KITROBOT_INITIAL_X,KAMGraphicPanel.KITROBOT_INITIAL_Y,GUIKitRobot.IMAGE_PAGE);
        this.setConstants(KAMGraphicPanel.KITROBOT_VELOCITYX, KAMGraphicPanel.KITROBOT_VELOCITYY, KAMGraphicPanel.KITROBOT_ROTATION_SPEED);
        this.arm = new ImageIcon(GUIKitRobot.IMAGE_ARM);
        this.base = new ImageIcon(GUIKitRobot.IMAGE_BASE);

    }

    /**
    sets a new lane destination coordinate for KitterRobot, the passed Integer specifies which lane to head to
    */
    private void moveToKit(Integer l) {
        switch(l) {
        case 0:
            moveTo(KAMGraphicPanel.KITX,KAMGraphicPanel.KIT0Y);
            break;
        case 1:
            moveTo(KAMGraphicPanel.KITX,KAMGraphicPanel.KIT1Y);
            break;
        case 2:
            moveTo(KAMGraphicPanel.KITX,KAMGraphicPanel.KIT2Y);
            break;
        default:
            return;
        }

    }
    private void moveToDefault() {
        this.moveTo(KAMGraphicPanel.KITROBOT_INITIAL_X,KAMGraphicPanel.KITROBOT_INITIAL_Y);
    }
    /**
    @brief moves to the position to drop off full kits
     */
    private void moveToFullConveyer() {
        this.moveTo(KAMGraphicPanel.FULL_CONVEYERX,KAMGraphicPanel.FULL_CONVEYERY);

    }
    /**
    @brief    moves to the position on the  conveyer for empty kits
     */
    private void moveToEmptyConveyer() {
        this.moveTo(KAMGraphicPanel.EMPTY_CONVEYERX,KAMGraphicPanel.EMPTY_CONVEYERY);

    }
    /**
    @brief moves an empty kit onto the kitstand
     */
    public void pickUpEmptyKit() {
        this.orders.add(10);//move to conveyer
        this.orders.add(0);//pick up empty kit from conveyer
        this.orders.add(11);//move to kit stand
        this.orders.add(4);//drop kit onto stand
    }
    /**
    @brief moves an empty kit to the active position
     */
    public void moveEmptyKitToActive() {
        this.orders.add(11);//move to empty kit stand position
        this.orders.add(1);//pick up the kit
        this.orders.add(12);//move to the active stand position
        this.orders.add(5);//drop the kit onto stand
    }
    /**
    @brief moves the active kit to the camera inspection stand
    */
    public void moveActiveKitToInspection() {
        this.orders.add(12);//move to active kit stand position
        this.orders.add(2);//pick up the kit
        this.orders.add(13);//move to the inspection stand position
        this.orders.add(6);//drop the kit onto stand
    }
    /**
    @brief moves a full stand off of the kit stand onto the conveyer
     */
    public void dropOffFullKit() {
        this.orders.add(13);//move to kit stand
        this.orders.add(3);//pick up kit
        this.orders.add(9);//move to dropoff conveyer
        this.orders.add(7);//drop off kit
    }
    /**
    updates the location of the kit items carrying
    */
    public void updateKit() {
        if(this.kit != null) {
            this.kit.setX(this.cords.getX());
            this.kit.setY(this.cords.getY());
        }
    }
    public void update() {
        super.update();
        this.updateKit();
        //System.out.println(this);
    }
    /**
    @brief hands the GUIKitRobot a kit to manage
     */
    public void giveKit(KAMKit in) {
        this.kit = in;
        this.popOrder();
    }
    public KAMKit dropKit() {
        this.popOrder();
        KAMKit k = this.kit;
        this.kit = null;
        return k;
    }
    public Integer popOrder() {
        if(this.orders.size() == 1) {
            this.orders.add(20);
        }
        return super.popOrder();
    }
    public Boolean hasKit() {
        return this.kit == null?false:true;
    }
    public KAMKit getKit() {
        return this.kit;
    }
    public void returnOrder(Integer i) {
        this.orders.addFirst(i);
    }
    public String toString() {
        return super.toString()+("Heading to " +this.moveToX+","+this.moveToY+")");
    }
    /**
    @brief pops the order and performs it
     */
    public Boolean performOrder() {
        Integer i = this.popOrder();
        switch(i) {
        case 9:
            this.moveToFullConveyer();
            break;
        case 10:
            this.moveToEmptyConveyer();
            break;
        case 11:
            this.moveToKit(0);
            break;
        case 12:
            this.moveToKit(1);
            break;
        case 13:
            this.moveToKit(2);
            break;
        case 20:
            this.moveToDefault();
            break;
        default:
            return false;
        }
        return true;
    }
    public void paintMe(JPanel panel, Graphics2D g) {
        Graphics2D g2d = (Graphics2D)g.create();

        this.base.paintIcon(panel,g2d,KAMGraphicPanel.KITROBOT_INITIAL_X-11,KAMGraphicPanel.KITROBOT_INITIAL_Y-11);
	/*//this code sucks, dont use it
        //	System.out.println(this.getY());
        Double a = (double)this.getY()-(double)KAMGraphicPanel.KITROBOT_INITIAL_Y;
        Double b = (double)this.getX()-(double)KAMGraphicPanel.KITROBOT_INITIAL_X;
        Double angle = Math.tan(a/b);
        System.out.println(angle + " " + a + " " + b);

        if(angle != null) {
	    Integer topaint =(int)Math.pow((Math.pow(a,2.0)+Math.pow(b,2.0)),0.5);
	    int x = KAMGraphicPanel.KITROBOT_INITIAL_X-2;
	    int y = KAMGraphicPanel.KITROBOT_INITIAL_Y-2;
	    for(int i =1; i != topaint+1; i++){
    Graphics2D g2 = (Graphics2D)g.create();
	g2.rotate(angle,KAMGraphicPanel.KITROBOT_INITIAL_X+x,KAMGraphicPanel.KITROBOT_INITIAL_Y+y);
	    	this.arm.paintIcon(panel,g2,(int)(i*(b/topaint)),(int)(i*(a/topaint)));
		x += (int)(i*(b/topaint));
		y += (int)(i*(a/topaint));
            }
        }
	*/
        g2d = (Graphics2D)g.create();
        if (this.hasKit()) {
            this.getKit().getImage().paintIcon(panel, g2d, this.getCoordinate().getX(), this.getCoordinate().getY());
        }
        this.getImage().paintIcon(panel, g2d, this.getCoordinate().getX(), this.getCoordinate().getY());
        g2d.dispose();
    }
}