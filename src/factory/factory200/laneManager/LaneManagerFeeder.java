package factory.factory200.laneManager;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

/**
 * This is a Graphics Panel for one feeder.
 * 
 * @brief Graphics Panel For Lane
 * @author Dongyoung Jung
 */
public class LaneManagerFeeder extends JLabel{
	
	private int feederNum;	///< Feeder number
	private LaneManagerLane rightLane;	///< Instance of class 'LaneManagerLane' : Left Lane
	private LaneManagerLane leftLane;	///< Instance of class 'LaneManagerLane' : Right Lane
	private LaneManagerApp laneManagerApp;	///< Instance of class 'LaneManagerApp'
	private static ImageIcon feederSwitchOffImage = new ImageIcon(".//pics//FeederOff.png");	/// Instance of ImageIcon
	private static ImageIcon feederSwitchOnImage = new ImageIcon(".//pics//FeederOn.png");	/// Instance of ImageIcon
	private static ImageIcon feederDivertLeftWithPartBoxImage = new ImageIcon(".//pics//FeederWithPartBox.png");	/// Instance of ImageIcon
	private static ImageIcon feederDivertRightWithPartBoxImage = new ImageIcon(".//pics//FeederWithPartBox.png");	/// Instance of ImageIcon
	
	/**
	 * This constructor sets up the default features of lane.
	 * 
	 * @brief Constructor
	 * @param xCoor : X Coordinate
	 * @param yCoor : Y Coordinate
	 * @param rightLane : Instance of class 'LaneManagerLane'
	 * @param leftLane : Instance of class 'LaneManagerLane'
	 * @param feederNum : Feeder number
	 */
	public LaneManagerFeeder(final int xCoor, final int yCoor, LaneManagerLane rightLane, LaneManagerLane leftLane, int feederNum){
		this.rightLane = rightLane;
		this.leftLane = leftLane;
		this.feederNum = feederNum;
		setSize(40,130);
		setLocation(xCoor, yCoor);
		setBorder( new LineBorder( Color. black ));
		setSwitchOff();
	}
	
	/**
	 * @brief Switch Off ImageIcon Setup
	 */
	public void setSwitchOff(){
		setIcon(feederSwitchOffImage);
	}
	
	/**
	 * @brief Switch On ImageIcon Setup
	 */
	public void setSwitchOn(){
		setIcon(feederSwitchOnImage);
	}
	
	/**
	 * @brief Dumped ImageIcon Setup
	 */
	public void setDumpedIntoFeeder(){
		setIcon(feederDivertLeftWithPartBoxImage);
	}

	/**
	 * This puts one part from inside the feeder onto left lane
	 * 
	 * @param partNum : part number
	 */
	public void feedPartOntoLaneLeft( int partNum ){
		leftLane.addPartOntoLane( partNum );
	}
	
	/**
	 * This puts one part from inside the feeder onto right lane
	 * 
	 * @param partNum : part number
	 */
	public void feedPartOntoLaneRight( int partNum ){
		rightLane.addPartOntoLane( partNum );
	}
}
