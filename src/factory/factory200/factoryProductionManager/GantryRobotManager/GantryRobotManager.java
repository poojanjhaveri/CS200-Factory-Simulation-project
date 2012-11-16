package factory.factory200.factoryProductionManager.GantryRobotManager;
/**
 * Gantry Robot Manager takes care of movement of gantry robot, bins, purge
 * station, feeders. Inner classes are GantryRobot,
 * GUIGantryManager,GraphicGantryPanel, GantryState, Feeder and Bins
 * GUIGantryManager extends a JPanel with JButtons, JMenu and JLabels
 * GraphicGantryPanel extends a JPanel which shows animation of every process
 * before feeders GantryState stores data needed to do animation for bins,
 * feeders,robot GantryRobot handles the movement of gantry robot <img
 * src="../img/image01.png" /> <img src="../img/image12.png" />
 *
 * @brief takes care of movement of gantry robot, bins, purge station, feeders.
 * @author Yuting Liu
 */

import factory.factory200.factoryProductionManager.*;
import java.awt.Graphics2D;

public class GantryRobotManager{
   
    GRMGraphicPanel graphics;    
   // GUIGantryRobot gbot;///<class which includes Gantry Robot Manager Methods
    int purgeStationx;//x coordinate of purgeStation
    int purgeStationy;//y coordinate of purgeStation

//    public void receiveFromServer();///<pull data from server
//   GUIGantryManager gui;///<break the nonormative situations
    public static final Integer FEED0X = 1000;///<x-coordinate of feeder 0
    public static final Integer FEED0Y = 50;///<y-coordinate of feeder 0
    public static final Integer FEED1X = 1000;///<x-coordinate of feeder 1
    public static final Integer FEED1Y = 200;///<y-coordinate of feeder 1
    public static final Integer FEED2X = 1000;///<x-coordinate of feeder 2
    public static final Integer FEED2Y = 350;///<y-coordinate of feeder 2
    public static final Integer FEED3X = 1000;///<x-coordinate of feeder 3
    public static final Integer FEED3Y = 500;///<y-coordinate of feeder 3

    public static final Integer DUMPX = 1260;///<x-coordinate of dump 
    public static final Integer DUMPY = 600;///<y-coordinate of dump 

    public static final Integer BIN_X = 1350;///<x coordinate of all bin locations
    public static final Integer BIN0Y = 30;///<y coordinate of bin0
    public static final Integer BIN1Y = 110;///<y coordinate of bin1
    public static final Integer BIN2Y = 190;///<y coordinate of bin2
    public static final Integer BIN3Y = 270;///<y coordinate of bin3
    public static final Integer BIN4Y = 350;///<y coordinate of bin4
    public static final Integer BIN5Y = 430;///<y coordinate of bin5
    public static final Integer BIN6Y = 510;///<y coordinate of bin6
    public static final Integer BIN7Y = 590;///<y coordinate of bin7

    public static final Integer ROBOT_INITIAL_X = 1100;///<spawn x coordinate of gantrybot
    public static final Integer ROBOT_INITIAL_Y = 10;///<spawn y coordinate of gantrybot
    
    public GantryRobotManager() {
        graphics = new GRMGraphicPanel();
    }
	
	public void paint(GraphicsPanel panel, Graphics2D g){
		graphics.paint(panel, g);
	}
}