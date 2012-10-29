package factory.agentGUI;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.LineBorder;

/**
	@brief creates lane image on graphic panel
	@author Dongyoung Jung
*/
public class Lane extends JLayeredPane{
	
	private Nest nest;
	private int xCoor, yCoor;
	
	// Track
	private int insertNewLabelTerm = 0;
	private LaneTrack newLaneTrack;
	private ArrayList<LaneTrack> laneTracks = new ArrayList<LaneTrack>();
	private int vibrateAmplitude;
	private double randomVibrate;
	private double randomVibrate2;
	
	// Part
	private Part newPart;
	private ArrayList<Part> parts = new ArrayList<Part>();
	
	// Nest
	Boolean nestOpen = true;
	
	public Lane(final int xCoor, final int yCoor, Nest nest){
		this.nest = nest;
		setSize(400, 60);
		setLocation(xCoor, yCoor);
		setLayout(null);
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		for(int i=0 ; i<205 ; i++){
			moveLane();
		}
	}
	
	// Lane movement - as 10 pixels, new JLabel is inserted
	public void moveLane(){
		// Tracks move
		for(int i=0 ; i<laneTracks.size() ; i++){
			laneTracks.get(i).trackMove();
		}
			
		// Insert New Track
		if(++insertNewLabelTerm == 5){
			newLaneTrack = new LaneTrack();
			add(newLaneTrack, new Integer(0));
			laneTracks.add(newLaneTrack);
			insertNewLabelTerm = 0;
		}
		
		//Part move && Move part onto nest
		for(int i=parts.size()-1 ; i>=0 ; i--){
			// Part Move
			parts.get(i).partMove();			
		}
		
		laneVibrateAmplitudeControl();
		nestOpenCheck();
		movePartToNest();	
		updateUI();
	}
	
	public void movePartToNest(){		
		// Nest is open - pass a part to Nest
		if( nestOpen == true ){
			for(int i=parts.size()-1 ; i>=0 ; i--){
				if( parts.get(i).getX() == 0 ){
					parts.get(i).setDisappear();
					nest.receivePartFromLane( parts.remove(i) );
				}
			}
		}
		
		// Nest is closed - Set Limit for each part on lane now
		else if( nestOpen == false ){
			for(int i=0 ; i<parts.size() ; i++){
				parts.get(i).setLimit(35*i);
			}
		}
	}
	
	public void nestOpenCheck(){
		// Nest Open? or Closed?
		if( nest.getPartQuantityInNest() < 8 ){
			nestOpen = true;
		}
		else if( nest.getPartQuantityInNest() == 8 ){
			nestOpen = false;
		}
	}
	
	// Lane vibration
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
	
	public void setLaneVibrateAmplitude(int newAmplitude){
		vibrateAmplitude = newAmplitude;
	}
	
	public void setLocationToOriginWhenOff(){
		setLocation( xCoor, yCoor );
	}
	
	public void addPartOntoLane(){
		newPart = new Part(this);
		parts.add(newPart);
		add(newPart, new Integer(2));
	}
	
	public ArrayList<Part> getPartsArray(){
		return parts;
	}
	
	// Track Class -------------------------------------------------
	class LaneTrack extends JLabel{
		
		private int xCoor = 400;
		
		public LaneTrack(){
			setSize(10,60);
			
			// White JLabel with Black JPanel
			setBorder(new LineBorder( Color.black, 3 ));
			setLocation(xCoor,0);
		}
		
		public void trackMove(){
			xCoor -= 2;
			setLocation(xCoor,0);
			
			// Remove track outside lane
			if( xCoor < 0 ){
				laneTracks.remove(this);
			}	
		}
	}
	// -------------------------------------------------------------------------------
}

