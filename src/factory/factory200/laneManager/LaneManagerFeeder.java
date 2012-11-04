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
	
	//--------------------------------------------------------------------------------------------------------------------------------------
	// Image version
	//private static ImageIcon feederSwitchOffImage = new ImageIcon(".//pics//FeederOff.png");	/// Instance of ImageIcon
	//private static ImageIcon feederSwitchOnImage = new ImageIcon(".//pics//FeederOn.png");	/// Instance of ImageIcon
	//private static ImageIcon feederDivertLeftWithPartBoxImage = new ImageIcon(".//pics//FeederWithPartBox.png");	/// Instance of ImageIcon
	//private static ImageIcon feederDivertRightWithPartBoxImage = new ImageIcon(".//pics//FeederWithPartBox.png");	/// Instance of ImageIcon
	
	// No image version
	private static String feederOffString = new String("OFF");
	private static String feederOnLeftString = new String("LEFT");
	private static String feederOnRightString = new String("RIGHT");
	//--------------------------------------------------------------------------------------------------------------------------------------
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
		//----------------------------------------------------
		// Image version
		//setSize(40,130);
		
		// No image version
		setOpaque(true);
		setSize(55,130);
		setBackground(Color.lightGray);
		//----------------------------------------------------
		setLocation(xCoor, yCoor);
		setBorder( new LineBorder( Color. black ));
		setSwitchOff();
	}
	
	//------------------------------------------------------
	// Image version
	/**
	 * @brief Switch Off ImageIcon Setup
	 */
	public void setSwitchOffImage(){ // public void setSwitchOff();
		//setIcon(feederSwitchOffImage);
	}

	// No image version
	public void setSwitchOff(){
		setText(feederOffString);
	}
	//------------------------------------------------------
	
	//------------------------------------------------------
	// Image version
	/**
	 * @brief Switch On ImageIcon Setup
	 */
	public void setSwitchOnImage(){ // setSwitchOn();
		//setIcon(feederSwitchOnImage);
	}
	
	// No image version
	public void setSwitchOn(){
		setText(feederOnLeftString);
	}
	//------------------------------------------------------
	
	
	//------------------------------------------------------
	// Image version
	/**
	 * @brief Dumped ImageIcon Setup
	 */
	public void setDumpedIntoFeederImage(){ // setDumpedIntoFeeder();
		//setIcon(feederDivertLeftWithPartBoxImage);
	}
	
	// No image version
	public void setDumpedIntoFeeder(int colorNum){
		
		if( colorNum == 0 ){ setBackground(Color.red); }
		else if( colorNum == 1 ){ setBackground(Color.blue); }
		else if( colorNum == 2 ){ setBackground(Color.white);}
		else if( colorNum == 3 ){ setBackground(Color.pink);}
		else if( colorNum == 4 ){ setBackground(Color.green);}
		else if( colorNum == 5 ){ setBackground(Color.cyan);}
		else if( colorNum == 6 ){ setBackground(Color.yellow); }
		else if( colorNum == 7 ){ setBackground(Color.darkGray);}
		
	}
	//------------------------------------------------------
	
	
	//------------------------------------------------------
	// Image version
	/**
	 * @brief Dumped ImageIcon Setup
	 */
	public void setDivertToLeftImage(){ // setDivertToLeftImage();
		//setIcon();
	}
	
	// No image version
	public void setDivertToLeft(){
		setText(feederOnLeftString);
	}
	//------------------------------------------------------
	
	//------------------------------------------------------
	// Image version
	/**
	 * @brief Dumped ImageIcon Setup
	 */
	public void setDivertToRightImage(){ // setDivertToRightImage();
		//setIcon();
	}
	
	// No image version
	public void setDivertToRight(){
		setText(feederOnRightString);
	}
	//------------------------------------------------------
	
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