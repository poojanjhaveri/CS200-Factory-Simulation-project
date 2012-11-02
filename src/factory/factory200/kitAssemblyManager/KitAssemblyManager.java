package factory.factory200.kitAssemblyManager;

import factory.factory200.kitAssemblyManager.GUIPartRobot;
import factory.factory200.kitAssemblyManager.KitDeliveryStation;
import factory.factory200.kitAssemblyManager.KitStand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import factory.general.Manager;
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
