package factory.factory200.kitAssemblyManager;

import factory.general.GUIRobot;

/**
 * The GuiKitRobot handles the moving of kits in the kit assembly area. It takes
 * orders from the Kit Assembly Manager. The GuiKitRobot must also communicate
 * with the KitDelivery manager to obtain and deliver kits It has pickup
 * functionality to move entire kits The robot is capable of rotating along the
 * base and extending the arm, but is incapable of moving from its base. <img
 * src="../img/image04.png" alt="idle GuiKitRobot"/> <img
 * src="../img/image10.png" alt="GuiKitRobot with active kit"/>
 *
 * @brief Robot that moves kits in the KitWorkingArea
 * @author YiWei Roy Zheng
 */
public class GUIKitRobot extends GUIRobot {
    public static final String IMAGE_PAGE = "pics/kitrobot.png";

    KAMKit kit;///<null if not carrying a kit, otherwise contains a reference the kit its carrying
    //KitWorkingArea kits;///information about all work benches and their kits

    Boolean busy;///whether or not the KitterRobot has orders

    public GUIKitRobot()
    {
        //this is actually set in the KAMGraphicsPanel
        super(KAMGraphicPanel.KITROBOT_INITIAL_X,KAMGraphicPanel.KITROBOT_INITIAL_Y,GUIKitRobot.IMAGE_PAGE);
this.setConstants(KAMGraphicPanel.KITROBOT_VELOCITYX, KAMGraphicPanel.KITROBOT_VELOCITYY, KAMGraphicPanel.KITROBOT_ROTATION_SPEED);
    }

    /**
    sets a new lane destination coordinate for KitterRobot, the passed Integer specifies which lane to head to
    */
    public void moveToKit(Integer l) {
        switch(l)
        {
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
        }
    }
    /**
    moves to the conveyer
     */
    public void moveToConveyer()
    {
        this.moveTo(KAMGraphicPanel.CONVEYERX,KAMGraphicPanel.CONVEYERY);
    }
    /**
    updates the location of the kit items carrying
    */
    public void updateKit() {
        if(this.kit != null)
        {
            this.kit.setX(this.cords.getX());
            this.kit.setY(this.cords.getY());
        }
    }
    public void update()
    {
        super.update();
        this.updateKit();
        //System.out.println(this);
    }
    /**
    @brief hands the GUIKitRobot a kit to manage
     */
    public void giveKit(KAMKit in)
    {
        this.kit = in;
    }
    public void dropKit()
    {
        this.kit = null;
    }
}