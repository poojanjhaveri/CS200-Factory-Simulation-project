package factory.factory200.laneManager;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;

/**
 * On this JPanel, Robot and Bins are drawn.
 * 
 * @brief Graphics Panel
 * @author Dongyoung Jung
 */
public class GantryManagerGraphicsPanel extends JLayeredPane {
	
	private GantryManagerApp app;	///< Instance of class 'GantryManagerApp'
	
	// Bins
	private GantryManagerBinWithParts newBinPic;	///< Instance of class 'GantryManagerBinWithParts'
	private ArrayList<GantryManagerBinWithParts> bins = new ArrayList<GantryManagerBinWithParts>();	///< ArrayList of bins
	private final int xCoor = 230;	///< X Coordinate of bins
	private int yCoor = 10;	///< Y Coordinate of the first bin
	
	// Robot
	private GantryManagerRobot robot;	///< Instance of class 'GantryManagerRobot'
		
	/**
	 * This constructor generates eight instances of bins and locate them.
	 * 
	 * @brief Constructor
	 * @param app : Instance of class 'GantryManagerApp'
	 */
	public GantryManagerGraphicsPanel(GantryManagerApp app){
		this.app = app;
		setBorder( new LineBorder( Color.red ));
		setPreferredSize(new Dimension(300,640));
		setLayout(null);
		
		for(int i=0 ; i<8 ; i++){
			newBinPic = new GantryManagerBinWithParts(xCoor, yCoor, i);
			bins.add(newBinPic);
			yCoor += 82;
			add(newBinPic);
		}
		
		robot = new GantryManagerRobot(100, 300);
		add(robot, new Integer(2));
	}
	
	/**
	 * 
	 * @return Instance of class 'GantryManagerRobot'
	 */
	public GantryManagerRobot getRobot(){
		return robot;
	}
}