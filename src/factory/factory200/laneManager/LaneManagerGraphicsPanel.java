package factory.factory200.laneManager;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;

/**
 * On this JPanel, lanes, nests and feeders are drawn.
 * 
 * @brief Graphics Panel
 * @author Dongyoung Jung
 */
public class LaneManagerGraphicsPanel extends JLayeredPane{
	
	private final int feederCount = 4;	///< Fixed quantity of feeder
	private final int laneCount = 8;	///< Fixed quantity of lane
	private final int nestCount = 8;	///< Fixed quantity of nest
	private final int cameraCount = 4;	///< Fixed quantity of camera
	
	private LaneManagerApp app;	///< Instance of class 'LaneManagerApp'
	
	private LaneManagerLane newLane;	///< Instance of class 'LaneManagerLane'
	private ArrayList<LaneManagerLane> lanes = new ArrayList<LaneManagerLane>();	///< ArrayList of 'LaneManagerLane'
	
	private LaneManagerNest newNest;	///< Instance of class 'LaneManagerNest'
	private ArrayList<LaneManagerNest> nests = new ArrayList<LaneManagerNest>();	///< ArrayList of 'LaneManagerNest'
	
	private LaneManagerFeeder newFeeder;	///< Instance of class 'LaneManagerFeeder'
	private ArrayList<LaneManagerFeeder> feeders = new ArrayList<LaneManagerFeeder>();	///< ArrayList of 'LaneManagerFeeder'
	
	private JLabel camera;	///< JLabel instance(Camera)
	private ImageIcon cameraImage = new ImageIcon( LaneManagerGraphicsPanel.class.getResource("./pics/camera.png") );	///< Camera image
	private ArrayList<JLabel> cameras = new ArrayList<JLabel>();	///< ArrayList of Camera JLabels

	private final int xCoorLane = 50, xCoorNest = 15, xCoorFeeder = 460, xCoorCamera = 0;	///< X Coordinate of lane, nest, feeder, and camera
	private int yCoorLane = 25, yCoorNest = 5, yCoorFeeder = 18, yCoorCamera = 45;	///< Y Coordinate of lane, nest, feeder, and camera
		
	/**
	 * This Constructor generates feeders, lanes, nests and cameras and put them into each ArrayList.
	 * 
	 * @brief Constructor
	 * @param feederCount : Quantity of feeder
	 * @param laneCount : Quantity of lane
	 * @param nestCount : Quantity of nest
	 * @param cameraCount : Quantity of camera
	 * @param app : Instance of class 'LaneManagerApp' 
	 */
	public LaneManagerGraphicsPanel(LaneManagerApp app){
		this.app = app;
		setLayout(null);
		setPreferredSize(new Dimension(670,640));
		setBorder(new LineBorder( Color.black ));
		setBackground( Color.white );
		
		// Add camera to 'cameras' ArrayList
		for(int i=0 ; i<cameraCount ; i++){
			camera = new JLabel( cameraImage );
			camera.setBounds(xCoorCamera, yCoorCamera, 80, 80);
			camera.setVisible(false);
			cameras.add(camera);
			add(camera, new Integer(2));
			yCoorCamera += 156;
		}
		
		// Add nest to 'nests' ArrayList
		for( int i=0 ; i<nestCount ; i++ ){
			newNest = new LaneManagerNest(xCoorNest, yCoorNest, i);
			nests.add(newNest);
			add(newNest, JLabel.CENTER);
			yCoorNest += 78;
		}
		
		// Add lane to 'lanes' ArrayList
		for( int i=0 ; i<laneCount ; i++ ){
			newLane = new LaneManagerLane(xCoorLane, yCoorLane, nests.get(i), i, app);
			lanes.add(newLane);
			add(newLane);
			yCoorLane += 78;
		}

		// Add feeder to 'feeders' ArrayList
		for( int i=0 ; i<feederCount ; i++ ){
			newFeeder = new LaneManagerFeeder(xCoorFeeder, yCoorFeeder, lanes.get(2*i), lanes.get(2*i+1), i);
			feeders.add(newFeeder);
			add(newFeeder, new Integer(3));
			yCoorFeeder += 156;
		}

	}
	
	/**
	 * @brief Getter
	 * @param feederNum : feeder number
	 * @return Size of ArrayList 'feeders'
	 */
	public LaneManagerFeeder getFeederArray(int feederNum){
		return feeders.get(feederNum);
	}
	
	/**
	 * @brief Getter
	 * @param laneNum : lane number
	 * @return Size of ArrayList 'lanes'
	 */
	public LaneManagerLane getLaneArray(int laneNum){
		return lanes.get(laneNum);
	}
	
	/**
	 * @brief Getter
	 * @param nestNum : nest number
	 * @return Size of ArrayList 'nests'
	 */
	public LaneManagerNest getNestArray(int nestNum){
		return nests.get(nestNum);
	}
	
	/**
	 * @brief Getter
	 * @param cameraNum : camera number
	 * @return One camera instance of ArrayList 'cameras'
	 */
	public JLabel getCameraArray(int cameraNum){
		return cameras.get(cameraNum);
	}
}