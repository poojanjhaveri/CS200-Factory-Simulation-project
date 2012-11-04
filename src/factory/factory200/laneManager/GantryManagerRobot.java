package factory.factory200.laneManager;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

/**
 * 
 * 
 * @author Dongyoung Jung
 */
public class GantryManagerRobot extends JLabel{
	
	private int xCoor;	///< X Coordinate of Robot
	private int yCoor;	///< Y Coordinate of Robot
	
	/**
	 * @brief Constructor
	 * @param xCoor : X Coordinate of Robot
	 * @param yCoor : Y Coordinate of Robot
	 */
	public GantryManagerRobot( int xCoor, int yCoor ){
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		setSize(50,50);
		setLocation(xCoor, yCoor);
		
		//------------------------------------------
		// Image version
		//setIcon(robotWithoutBinImage);
		
		// No image version
		setOpaque(true);
		setBackground(Color.lightGray);
		setBorder(new LineBorder(Color.black));
		setText("ROBOT");
		//-----------------------------------------
	}
	
	/**
	 * Server keeps sending new location repeatedly.
	 * As signal comes in, robot sets its location to the new location.
	 * 
	 * @brief Relocation
	 * @param destX : X Coordinate of new location
	 * @param destY : Y Coordinate of new location
	 */
	public void moveTo( int destX, int destY ){
		setLocation(destX, destY);
	}
	
	/**
	 * As the robot reaches any location, the robot color changes.
	 * 
	 * @brief Color change
	 * @param colorNum : color number
	 */
	public void colorChange(int colorNum){
		// At Feeders
		if( colorNum == 0 ){ setBackground(Color.lightGray); }
		
		// At Bin
		else if( colorNum == 1 ){ setBackground(Color.red); }
		else if( colorNum == 2 ){ setBackground(Color.blue); }
		else if( colorNum == 3 ){ setBackground(Color.white);}
		else if( colorNum == 4 ){ setBackground(Color.pink);}
		else if( colorNum == 5 ){ setBackground(Color.green);}
		else if( colorNum == 6 ){ setBackground(Color.cyan);}
		else if( colorNum == 7 ){ setBackground(Color.yellow); }
		else if( colorNum == 8 ){ setBackground(Color.darkGray);}
	}
}
