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
        super(KAMGraphicPanel.KITROBOT_INITIAL_X, KAMGraphicPanel.KITROBOT_INITIAL_Y, GUIKitRobot.IMAGE_PAGE);
        this.setConstants(KAMGraphicPanel.KITROBOT_VELOCITYX, KAMGraphicPanel.KITROBOT_VELOCITYY, KAMGraphicPanel.KITROBOT_ROTATION_SPEED);
        this.arm = new ImageIcon(GUIKitRobot.IMAGE_ARM);
        this.base = new ImageIcon(GUIKitRobot.IMAGE_BASE);
    }

    /**
     * @author David Zhang
     * @param kitRobotInitX - init kit position x coordinate
     * @param kitRobotInitY - init kit position y coordinate
     */
    public GUIKitRobot(int kitRobotInitX, int kitRobotInitY) {
        super(kitRobotInitX, kitRobotInitY, GUIKitRobot.IMAGE_PAGE);
        this.setConstants(KAMGraphicPanel.KITROBOT_VELOCITYX, KAMGraphicPanel.KITROBOT_VELOCITYY, KAMGraphicPanel.KITROBOT_ROTATION_SPEED);
        this.arm = new ImageIcon(GUIKitRobot.IMAGE_ARM);
        this.base = new ImageIcon(GUIKitRobot.IMAGE_BASE);
    }

    /**
    sets a new lane destination coordinate for KitterRobot, the passed Integer specifies which lane to head to
     */
    private void moveToKit(Integer l) {
        switch (l) {
            case 0:
                moveTo(KAMGraphicPanel.KITX, KAMGraphicPanel.KIT0Y+15);
                break;
            case 1:
                moveTo(KAMGraphicPanel.KITX, KAMGraphicPanel.KIT1Y+15);
                break;
            case 2:
                moveTo(KAMGraphicPanel.KITX, KAMGraphicPanel.KIT2Y+15);
                break;
            default:
                return;
        }

    }

    private void moveToDefault() {
        this.moveTo(KAMGraphicPanel.KITROBOT_INITIAL_X, KAMGraphicPanel.KITROBOT_INITIAL_Y);
    }

    /**
    @brief moves to the position to drop off full kits
     */
    private void moveToFullConveyer() {
        this.moveTo(KAMGraphicPanel.FULL_CONVEYERX+30, KAMGraphicPanel.FULL_CONVEYERY+50);

    }

    /**
    @brief    moves to the position on the  conveyer for empty kits
     */
    private void moveToEmptyConveyer() {
        this.moveTo(KAMGraphicPanel.EMPTY_CONVEYERX+30, KAMGraphicPanel.EMPTY_CONVEYERY+50);

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
     * @brief moves the empty kit to the active position immediately
     * 
     */
public void pickUpEmptyKitToActive()
{
    this.orders.add(10);//move to conveyer
    this.orders.add(0);//pick up empty kit
    this.orders.add(12);//move to ACTIVE kit position
    this.orders.add(5);//drop off the kit
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
    public void moveFrom0To2()
    {
        this.orders.add(11);
        this.orders.add(1);
        this.orders.add(13);
        this.orders.add(6);
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
        if (this.kit != null) {
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
        if (this.orders.size() == 1) {
            this.orders.add(20);
        }
        return super.popOrder();
    }

    public Boolean hasKit() {
        return this.kit == null ? false : true;
    }

    public KAMKit getKit() {
        return this.kit;
    }

    public void returnOrder(Integer i) {
        this.orders.addFirst(i);
    }

    public String toString() {
        return super.toString() + ("Heading to " + this.moveToX + "," + this.moveToY + ")");
    }

    /**
    @brief pops the order and performs it
     */
    public Boolean performOrder() {
        Integer i = this.popOrder();
        switch (i) {
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
        Double dy = Math.abs((double) KAMGraphicPanel.KITROBOT_INITIAL_Y - (double) this.getY());
        Double dx = Math.abs((double) KAMGraphicPanel.KITROBOT_INITIAL_X - (double) this.getX());
        Double angle = Math.abs(Math.atan(dx / dy));

        //System.out.println(Math.toDegrees(angle) + " " + dy + " " + dx);
        if (dx == 0) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.rotate(Math.toRadians(180), KAMGraphicPanel.KITROBOT_INITIAL_X + 15, KAMGraphicPanel.KITROBOT_INITIAL_Y + 20);
            this.arm.paintIcon(panel, g2, KAMGraphicPanel.KITROBOT_INITIAL_X, KAMGraphicPanel.KITROBOT_INITIAL_Y);
        } else {

            //if (dx != 0) {
            Integer x = 0, y = 0;
            if (this.getY() < KAMGraphicPanel.KITROBOT_INITIAL_Y) {
                y = KAMGraphicPanel.KITROBOT_INITIAL_Y;//-15;
            } else {
                y = KAMGraphicPanel.KITROBOT_INITIAL_Y;//+15;
            }
            if (this.getX() < KAMGraphicPanel.KITROBOT_INITIAL_X) {
                x = KAMGraphicPanel.KITROBOT_INITIAL_X;// -15;
            } else {
                x = KAMGraphicPanel.KITROBOT_INITIAL_X;// + 15;
            }
            if (this.getX() <= KAMGraphicPanel.KITROBOT_INITIAL_X && this.getY() <= KAMGraphicPanel.KITROBOT_INITIAL_Y) {
                angle *= -1;
            }
            if(this.getY() > KAMGraphicPanel.KITROBOT_INITIAL_Y && this.getX() < KAMGraphicPanel.KITROBOT_INITIAL_X){
                angle = angle + Math.toRadians(180);
            }
            
            // if(this.getX() == KAMGraphicPanel.KITROBOT_INITIAL_X && this.getY() == KAMGraphicPanel.KITROBOT_INITIAL_Y){

            if (this.getX() > KAMGraphicPanel.KITROBOT_INITIAL_X && this.getY() > KAMGraphicPanel.KITROBOT_INITIAL_Y) {
                angle = 135 - angle;
            }

            Graphics2D g2 = (Graphics2D) g.create();
            g2.rotate(Math.toRadians(180) + angle, x + 15, y + 20);

            //while(Math.abs(x) > 15 && Math.abs(y) > 15){
            // while(Math.pow((this.getY()-y),2)+Math.pow((this.getX()-x),2) > 900) {

            //while(Math.pow(Math.abs(y-this.getY()),2) + Math.pow(Math.abs(x-this.getX()),2) > 225)
            double num = Math.pow(Math.pow(Math.abs(y - this.getY()), 2) + Math.pow(Math.abs(x - this.getX()), 2), .5);
            num -= 15;
            num = num / 40;
            //for (int i = 0; i != (int) num; i++) {
            this.arm.paintIcon(panel, g2, x, y);

            //if(this.getY() <= KAMGraphicPanel.KITROBOT_INITIAL_Y)
            if (this.getY() < y) // if(y > 0)
            {
                y -= (int) (40.0 * Math.abs(Math.cos(angle / 2)));
            } else {
                y += (int) (40.0 * Math.abs(Math.cos(angle / 2)));
            }
            //if(this.getX() <= KAMGraphicPanel.KITROBOT_INITIAL_X)
            // if(x > 0)
            if (this.getX() < x) {
                x -= (int) (40.0 * Math.abs(Math.sin(angle)));
            } else {
                x += (int) (40.0 * Math.abs(Math.sin(angle)));
            }

            //                System.out.println("ANGLE: " + (Math.toDegrees(angle) % 90));
            // System.out.println(Math.sin(angle) + " " + Math.cos(angle));
            //System.out.println(x + "," + y);
            //System.out.println(x - this.getX() + " " + (y - this.getY()));
            //                System.out.println("Printed " + num + " times");
            //}

        }




        /*//this code sucks, dont use it
        //	System.out.println(this.getY());
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
        Graphics2D g2d = (Graphics2D) g.create();

        this.base.paintIcon(panel,g2d,KAMGraphicPanel.KITROBOT_INITIAL_X-11,KAMGraphicPanel.KITROBOT_INITIAL_Y-11);


        if (this.hasKit()) {
            //this.getKit().updateParts();
            this.getKit().paintMe(panel, g2d, this.getCoordinate().getX(), this.getCoordinate().getY()-30);
        }
        this.getImage().paintIcon(panel, g2d, this.getCoordinate().getX()-2, this.getCoordinate().getY());
        g2d.dispose();
    }
}