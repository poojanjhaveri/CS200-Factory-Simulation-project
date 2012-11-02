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
public class GUIKitRobot extends GUIRobot{
    public static final String IMAGE_PAGE = "res/kitrobot.png";

    //GUIKit kit;///<null if not carrying a kit, otherwise contains a reference the kit its carrying
    //KitWorkingArea kits;///information about all work benches and their kits
    
    Boolean busy;///whether or not the KitterRobot has orders
    
    public GUIKitRobot()
    {
        //this is actually set in the KAMGraphicsPanel
	super(KitAssemblyManager.ROBOT_INITIAL_X,KitAssemblyManager.ROBOT_INITIAL_Y,0.0,GUIKitRobot.IMAGE_PATH);
	this.kit = null;

    }

    /**
sets a new lane destination coordinate for KitterRobot, the passed Integer specifies which lane to head to
    */
    public void moveToKit(Integer l) {
	switch(l)
	    {
	    case 0:moveTo(KitAssemblyManager.KITX,KitAssemblyManager.KIT0Y);
		break;
	    case 1:moveTo(KitAssemblyManager.KITX,KitAssemblyManager.KIT1Y);
		break;
	    case 2:moveTo(KitAssemblyManager.KITX,KitAssemblyManager.KIT2Y);
		break;
	    default:
	    }
    }
    /**
moves to the conveyer
     */
    public void moveToConveyer()
    {
	this.moveTo(KitAssemblyManager.CONVEYERX,KitAssemblyManager.CONVEYERY);
    }
    /**
updates the location of the kit items carrying
    */
    public void updateKit() {
    }
}