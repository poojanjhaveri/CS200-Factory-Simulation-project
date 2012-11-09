package factory.factory200.laneManager;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 * This is a Graphics Panel for one feeder.
 * 
 * @brief Graphics Panel For Lane
 * @author Dongyoung Jung
 */
public class LaneManagerFeeder extends JLayeredPane{
	
	private int feederNum;	///< Feeder number
	private LaneManagerLane rightLane;	///< Instance of class 'LaneManagerLane' : Right Lane
	private LaneManagerLane leftLane;	///< Instance of class 'LaneManagerLane' : Left Lane
	private LaneManagerApp laneManagerApp;	///< Instance of class 'LaneManagerApp'
	
	// Feeder Switch Setup
	private JLabel feederSwitchLabel = new JLabel(feederOffImage);
	
	// Bin Holder Setup
	private JLabel binHolderLabel = new JLabel(binHolderImage);
	
	// Diversion Bulb Setups
	private JLabel divertToRightBulbLabel = new JLabel(bulbOffImage);
	private JLabel divertToLeftBulbLabel = new JLabel(bulbOffImage);
	
	// Feeding Bulb Setup
	private JLabel feedingSymbolLabel = new JLabel();
	private JLabel feedingBulbLabel = new JLabel(bulbOffImage);
	
	// Purging Bulb Setup
	private JLabel purgingSymbolLabel = new JLabel();
	private JLabel purgingBulbLabel = new JLabel(bulbOffImage);
	
	// Rear Gate Bulb Setup
	private JLabel rearGateSymbolLabel = new JLabel();
	private JLabel rearGateBulbLabel = new JLabel(bulbOffImage);
	
	// Part Low Sensor Setups
	private JLabel partLowSymbolLabel = new JLabel();
	private JLabel partLowSensorLabel = new JLabel(bulbOffImage);
	
	// Feeder Background Setup
	private JLabel backgroundLabel = new JLabel(feederOffImage);
	
	// Images
	private static ImageIcon feederOnImage = new ImageIcon( LaneManagerNest.class.getResource("./pics/feederOn.png") );	///< Image of feeder(on)
	private static ImageIcon feederOffImage = new ImageIcon( LaneManagerNest.class.getResource("./pics/feederOff.png") );	///< Image of feeder(off)
	private static ImageIcon bulbOnImage = new ImageIcon( LaneManagerNest.class.getResource("./pics/bulbOn.png") );	///< Image of bulb(on)
	private static ImageIcon bulbOffImage = new ImageIcon( LaneManagerNest.class.getResource("./pics/bulbOff.png") );	///< Image of bulb(off)
	private static ImageIcon loweredRearGateImage = new ImageIcon( LaneManagerNest.class.getResource("./pics/feederOn.png") );	///< Image of lowered rear gate
	private static ImageIcon raisedRearGateImage = new ImageIcon( LaneManagerNest.class.getResource("./pics/feederOff.png") );	///< Image of raised rear gate
	private static ImageIcon binHolderImage = new ImageIcon( LaneManagerFeeder.class.getResource("./pics/binHolder.png") );
	
	/**
	 * This constructor sets up the default features of lane.
	 * Also this constructor creates all bulbs from switches and sensors.
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
		
		// Feeder Background Setup
		setSize(185,130);
		setLocation(xCoor, yCoor);
		backgroundLabel.setBounds(0,0,200,130);
		add(backgroundLabel, new Integer(0));
		
		// Bin Holder Setup
		binHolderLabel.setBounds(115, 15, 60, 100);
		add(binHolderLabel, new Integer(1));
		
		// Diversion Bulb Setup
		divertToRightBulbLabel.setBounds(5,15,22,20);
		divertToLeftBulbLabel.setBounds(5,92,22,20);
		add(divertToRightBulbLabel, new Integer(1));
		add(divertToLeftBulbLabel, new Integer(1));
		
		// Feeding Bulb Setup
		feedingSymbolLabel.setBounds(55, 7, 25, 25);
		feedingSymbolLabel.setBorder(new LineBorder( Color.black ));
		add(feedingSymbolLabel, new Integer(1));
		feedingBulbLabel.setBounds(85, 10, 22, 20);
		//feedingBulbLabel.setBorder(new LineBorder( Color.black ));
		add(feedingBulbLabel, new Integer(1));
		
		// Purging Bulb Setup
		purgingSymbolLabel.setBounds(55, 38, 25, 25);
		purgingSymbolLabel.setBorder(new LineBorder( Color.red ));
		add(purgingSymbolLabel, new Integer(1));
		purgingBulbLabel.setBounds(85, 40, 22, 20);
		//purgingBulbLabel.setBorder(new LineBorder( Color.red ));
		add(purgingBulbLabel, new Integer(1));
		
		// Rear Gate Bulb Setup
		rearGateSymbolLabel.setBounds(55, 69, 25, 25);
		rearGateSymbolLabel.setBorder(new LineBorder( Color.blue ));
		add(rearGateSymbolLabel, new Integer(1));
		rearGateBulbLabel.setBounds(85, 72, 22, 20);
		//rearGateBulbLabel.setBorder(new LineBorder( Color.blue ));
		add(rearGateBulbLabel, new Integer(1));
		
		// Part Low Sensor Setups
		partLowSymbolLabel.setBounds(55, 100, 25, 25);
		partLowSymbolLabel.setBorder(new LineBorder( Color.pink ));
		add(partLowSymbolLabel, new Integer(1));
		partLowSensorLabel.setBounds(85, 102, 22, 20);
		//partLowSensorLabel.setBorder(new LineBorder( Color.pink ));
		add(partLowSensorLabel, new Integer(1));
	}
	
	/**
	 * @brief Feeder Switch Controller
	 * @param input : Boolean valuable
	 */
	public void setFeederSwitch(Boolean input){
		if( input == true ){
			feederSwitchLabel.setIcon(feederOnImage);
		}
		else if( input == false ){
			feederSwitchLabel.setIcon(feederOffImage);
		}
	}

	/**
	 * @brief Diversion To Left
	 */
	public void setDivertToLeft(){
		divertToRightBulbLabel.setIcon(bulbOffImage);
		divertToLeftBulbLabel.setIcon(bulbOnImage);
	}
	
	/**
	 * @brief Diversion To Right
	 */
	public void setDivertToRight(){
		divertToRightBulbLabel.setIcon(bulbOnImage);
		divertToLeftBulbLabel.setIcon(bulbOffImage);
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
	
	/**
	 * @brief Feeding Bulb Controller
	 * @param input : Boolean valuable
	 */
	public void setFeedingSwitch(Boolean input){
		if( input == true ){
			feedingBulbLabel.setIcon(bulbOnImage);
		}
		else if( input == false ){
			feedingBulbLabel.setIcon(bulbOffImage);			
		}
	}
	
	/**
	 * @brief Purging Bulb Controller
	 * @param input : Boolean valuable
	 */
	public void setPurgingSwitch(Boolean input){
		if( input == true ){
			purgingBulbLabel.setIcon(bulbOnImage);
		}
		else if( input == false ){
			purgingBulbLabel.setIcon(bulbOffImage);
		}
	}
	
	/**
	 * @brief Part Low Sensor Bulb Controller
	 * @param input : Boolean valuable
	 */
	public void setPartLowSensor(Boolean input){
		if( input == true ){
			partLowSensorLabel.setIcon(bulbOnImage);
		}
		else if( input == false ){
			partLowSensorLabel.setIcon(bulbOffImage);
		}
	}
	
	/**
	 * @brief Rear Gate Bulb Controller
	 * @param input : Boolean valuable
	 */
	public void setRearGateSwitch(Boolean input){
		if( input == true ){
			rearGateBulbLabel.setIcon(bulbOnImage);
		}
		else if( input == false ){
			rearGateBulbLabel.setIcon(bulbOffImage);
		}
	}
	
	//------------------------------------------------------
	// Image version
	/**
	 * @brief Makes Feeder Without Box
	 */
	public void setFeederWithoutBoxImage(){ // setFeederWithoutBox();
		//setIcon();
	}
	
	// No image version
	public void setFeederWithoutBox(){
		setBackground( new Color(255,160,45) );
	}
	//------------------------------------------------------
	
	public void setDumpedIntoFeeder(int colorNum){
		
	}
}
