import java.awt.Color;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;

/**
 * This is a Graphics Panel for one lane.
 * 
 * @brief Graphics Panel For Lane
 * @author Dongyoung Jung
 */
public class LaneManagerLane extends JLayeredPane{
	
	private LaneManagerApp app;	///< Instance of class 'LaneManagerApp'
	private LaneManagerNest nest;	///< Instance of class 'LaneManagerNest'
	private int xCoor, yCoor;	///< X Location, Y Location
	
	private int laneNum;	///< Lane number
	private int insertNewLabelTerm = 0;	///< Frequency the track is inserted
	private LaneTrack newLaneTrack;	///< New instance of lane track
	private ArrayList<LaneTrack> laneTracks = new ArrayList<LaneTrack>();	///< ArrayList of tracks on lane
	private int vibrateAmplitude;	///< 0 : weak, 1 : normal, 2 : strong
	private double randomVibrate;	///< Possibility that the lane moves from the original position
	private double randomVibrate2;	///< 8 directions that the lane moves to
	private static ImageIcon trackImage = new ImageIcon(".//pics//track.png");	///< ImageIcon of track
	
	private LaneManagerPart newPart;	///< Instance of new part
	private ArrayList<LaneManagerPart> parts = new ArrayList<LaneManagerPart>();	///< ArrayList of parts
	private LaneManagerPart partToNest;	///< Instance of class 'LaneManagerPart'
	
	private Boolean nestOpen = true;	///< Boolean variable that says if the nest is full or not
	
	private String message = "";	///< Message to server
	
	/**
	 * This function sets basic features of lane.
	 * To display the track image all over the lane before start, it moves and puts the track 400 times.
	 * 
	 * @brief Constructor
	 * @param xCoor : X coordinate
	 * @param yCoor : Y coordinate
	 * @param nest : Nest adjacent to the lane
	 * @param laneNum : lane number
	 * @param app : Instance of class 'LaneManagerApp'
	 */
	public LaneManagerLane(final int xCoor, final int yCoor, LaneManagerNest nest, int laneNum, LaneManagerApp app){
		this.nest = nest;
		this.laneNum = laneNum;
		this.app = app;
		setSize(415, 40);
		setLocation(xCoor, yCoor);
		setLayout(null);
		setBorder(new LineBorder( Color.green));
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		
		for(int i=0 ; i<400 ; i++){
			moveLane();
		}
	}
	
	/**
	 * This function moves and inserts the tracks on lane.
	 * Also, this function moves the parts on lane.
	 * 
	 * @brief Lane Move
	 */
	public void moveLane(){
		for(int i=0 ; i<laneTracks.size() ; i++){
			laneTracks.get(i).trackMove();
		}
			
		// Insert New Track
		if(++insertNewLabelTerm == 190){
			newLaneTrack = new LaneTrack();
			add(newLaneTrack, new Integer(0));
			laneTracks.add(newLaneTrack);
			insertNewLabelTerm = 0;
		}
		
		//Part move && Move part onto nest
		for(int i=parts.size()-1 ; i>=0 ; i--){
			parts.get(i).partMove();			
		}
		
		laneVibrateAmplitudeControl();
		nestOpenCheck();
		movePartToNest();	
		updateUI();
	}
	
	/**
	 * This function transfers the part that reaches the end of lane.
	 * Also, this function sends a signal to server. 
	 * Signal : "lane number + "%Part To Nest%" + part number"
	 * Once the server receives this signal, the part is saved into 'Parts' ArrayList in class 'ServerLaneManagerThreadNest.
	 * If the nest is full, this function sets the limit of all parts so that they line up behind one another on lane.
	 * 
	 * @brief Part Transfer To Nest
	 */
	public void movePartToNest(){		
		// Nest is open - pass a part to Nest
		if( nestOpen == true ){
			for(int i=parts.size()-1 ; i>=0 ; i--){
				parts.get(i).setLimit(0);
				if( parts.get(i).getX() == 0 ){
					partToNest = parts.remove(i);
					partToNest.setDisappear();
					nest.receivePartFromLane( partToNest );
					
					// Signal To Server
					message = laneNum + "%Part To Nest%" + partToNest.getPartNum();
					app.getNetwork().getSendToServer().sendToServer(message);
				}
			}
		}
		
		// Nest is closed - Set Limit for each part on lane now
		else if( nestOpen == false ){
			for(int i=0 ; i<parts.size() ; i++){
				parts.get(i).setLimit(8+20*i);
			}
		}
	}
	
	/**
	 * This function checks if the nest is full or not
	 * 
	 * @brief Nest Check
	 */
	public void nestOpenCheck(){
		// Nest Open? or Closed?
		if( nest.getPartQuantityInNest() < 8 ){
			nestOpen = true;
		}
		else if( nest.getPartQuantityInNest() == 8 ){
			nestOpen = false;
		}
	}
	
	/**
	 * This function moves the lane panel depending on random variable 'randomVibrate'
	 * 
	 * @brief Lane Vibrate Amplitude Generator
	 */
	public void laneVibrateAmplitudeControl(){
		randomVibrate = Math.random();
		// Weak
		if( vibrateAmplitude == 0 ){
			// Weak possibility
			if( randomVibrate < 0.1 ){
				laneVibrateWithCoordinates();
			}
		}
		// Normal
		else if( vibrateAmplitude == 1 ){
			// Normal possibility
			if( randomVibrate < 0.5 ){
				laneVibrateWithCoordinates();
			}	
		}
		// Strong
		else if( vibrateAmplitude == 2 ){
			// Strong possibility
			if( randomVibrate < 1 ){
				laneVibrateWithCoordinates();
			}
		}
	}
	
	/**
	 * Once the lane moves, this function decides the directive it moves depending on random variable 'randomVibrate2'
	 * 
	 * @brief Lane Vibrate Amplitude Direction Controller
	 */
	public void laneVibrateWithCoordinates(){
		randomVibrate2 = Math.random() * 8;
		// to West North
		if( randomVibrate2 < 1 ){
			setLocation( xCoor-1, yCoor-1 );
		}
		// to West
		else if( 1 <= randomVibrate2 && randomVibrate2 < 2 ){
			setLocation( xCoor-1, yCoor );
		}
		// to West South
		else if( 2 <= randomVibrate2 && randomVibrate2 < 3 ){
			setLocation( xCoor-1, yCoor+1 );
		}
		// to North
		else if( 3 <= randomVibrate2 && randomVibrate2 < 4 ){
			setLocation( xCoor, yCoor-1 );
		}
		// to South
		else if( 4 <= randomVibrate2 && randomVibrate2 < 5 ){
			setLocation( xCoor, yCoor+1 );
		}
		// to East North
		else if( 5 <= randomVibrate2 && randomVibrate2 < 6 ){
			setLocation( xCoor+1, yCoor-1 );
		}
		// to East
		else if( 6 <= randomVibrate2 && randomVibrate2 < 7 ){
			setLocation( xCoor+1, yCoor );
		}
		// to East South
		else if( 7 <= randomVibrate2 ){
			setLocation( xCoor+1, yCoor+1);
		}
	}
	
	/**
	 * @brief Setter
	 * @param newAmplitude : New amplitude variable
	 */
	public void setLaneVibrateAmplitude(int newAmplitude){
		vibrateAmplitude = newAmplitude;
	}
	
	/**
	 * When the switch is off, the lane is back to its original position.
	 * 
	 * @brief Setter
	 */
	public void setLocationToOriginWhenOff(){
		setLocation( xCoor, yCoor );
	}
	
	/**
	 * This function puts part image on lane.
	 * 
	 * @param partNum : part number
	 */
	public void addPartOntoLane( int partNum ){
		newPart = new LaneManagerPart( partNum );
		parts.add(newPart);
		add(newPart, new Integer(2));
	}
	
	/**
	 * @brief Getter
	 * @return ArrayList of parts
	 */
	public ArrayList<LaneManagerPart> getPartsArray(){
		return parts;
	}
	
	/**
	 * This class is a track on lane.
	 * It extends JLabel with ImageIcon in it.
	 * Rather than painting, this class just moves its location.
	 * Once it is outside the lane panel, it disappears.
	 * 
	 * @brief Inner Class
	 * @author Dongyoung Jung
	 */
	class LaneTrack extends JLabel{
		
		private int xCoor = 400;
		
		/**
		 * @brief Track Default Setting
		 */
		public LaneTrack(){
			setSize(390,40);
			setIcon(trackImage);
			setLocation(xCoor,0);
		}
		
		/**
		 * If it is outside the lane panel, it disappears.
		 * 
		 * @brief Track Move
		 */
		public void trackMove(){
			xCoor -= 2;
			setLocation(xCoor,0);
			
			// Remove track outside lane
			if( xCoor <= -400 ){
				laneTracks.remove(this);
				setIcon(null);
			}	
		}
	}
	// -------------------------------------------------------------------------------
}

