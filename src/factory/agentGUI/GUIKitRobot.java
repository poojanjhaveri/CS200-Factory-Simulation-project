/*
HEY YOU. YOU YES YOU. STOP FORMATTING MY CODE? MMK? THANKS <3.
 */
package factory.agentGUI;

/**
 * The GUIKitRobot handles the moving of kits in the kit assembly area. It takes
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
public class GUIKitRobot extends Robot {

    public static final String IMAGE_PATH = "res/kitrobot.png";///<kit robot image path
    public static final Integer VELOCITY_X = 0;
    public static final Integer VELOCITY_Y = 0;
    public static final Double ROTATION_SPEED = 5;

    GUIKit kit;///<null if not carrying a kit, otherwise contains a reference the kit its carrying
    KitWorkingArea kits;///<information about all work benches and their kits

    public GUIKitRobot() {
        super(KitAssemblyManager.ROBOT_START_X,KitAssemblyManager.ROBOT_START_Y,GUIKitRobot.IMAGE_PATH);
        this.setConstants(this.VELOCITY_x,this.VELOCITY_Y,this.ROTATION_SPEED);
    }
                     /**
                     sets a new rotation angle for the KitterRobot
                     */
    public void turnTo(Double a) {
        this.rotation = a;
    }
    /**
    sets a new lane destination coordinate for KitterRobot, the passed Integer specifies which lane to head to
    */
    public void moveToLane(Integer l) {
	switch(l)
	    {
	    case 0:this.
	    case 1:
	    case 2:
	    case 3:
	    case 4:
	    case 5:
	    case 6:
	    case 7:
	    }
    }
    /**
    updates the location of the kit it��s carrying
    */
    public void updateKit() {
    }
    
}