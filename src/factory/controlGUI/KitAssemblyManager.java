package factory.controlGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import factory.agentGUI.*;
/**
 * This class keeps track of everything that will be visible to the Kit Assembly
 * Manager. This includes the graphics panel and the GUI panel. The graphics
 * panel will show the kit stand, the camera, the kit delivery station, the
 * parts robot, the kit robot, and the completed/empty kits. The GUI panel will
 * allow the Kit Assembly Manager to break any of the components that are
 * contained in this window. <img url="addthis.jpg" /> <img url="addthis.jpg" />
 * <img src="../img/image11.png" /> <img src="../img/image16.png" />
 *
 * @brief keeps track of everything that will be visible to the Kit Assembly
 * Manager
 * @author Deepa Borkar
 */
public class KitAssemblyManager extends Manager implements ActionListener {

    public static final Integer LANE0Y = 0;///<y-coordinate of lane 0's nest
    public static final Integer LANE1Y = 0;///<y-coordinate of lane 1's nest
    public static final Integer LANE2Y = 0;///<y-coordinate of lane 2's nest
    public static final Integer LANE3Y = 0;///<y-coordinate of lane 3's nest
    public static final Integer LANE4Y = 0;///<y-coordinate of lane 4's nest
    public static final Integer LANE5Y = 0;///<y-coordinate of lane 5's nest
    public static final Integer LANE6Y = 0;///<y-coordinate of lane 6's nest
    public static final Integer LANE7Y = 0;///<y-coordinate of lane 7's nest
    public static final Integer RAILX = 0;///<fixed x-coordinate of the rail the parts robot traverses
    public static final Integer PARTSROBOTINITIALX = 0;///<x coordinate for parts robot to spawn in
    public static final Integer PARTSROBOTINITIALY = 0;///<y coordinate for parts robot to spawn in
    private GuiPartRobot kitter;///<declares an object that keeps track of the parts robot animation and graphics
    //private GuiKitRobot kitbot;///<declares an object that keeps track of the kit robot animation and graphics
    private KitStand kitstand;///<declares an object that keeps track of what is happening with the kit stand
    private KitDeliveryStation delivery;///<declares an object that keeps track of the delivery station
    //private KitAssemblyManagerDeliveryStation kamdelivery;///<keeps track of all of the objects listed above and paints the objects according to a timer
    //private KitAssemblyManagerGUIPanel gui;///<keeps track of the GUI components and allows the manager to pick which components will be broken

    /**
     * changes the panel based on what the user clicks
     */
    public void actionPerformed(ActionEvent ae) {
    }

    /**
     * declares objects within the class and sets the states of each of the
     * objects
     */
    public KitAssemblyManager() {
    }
}
