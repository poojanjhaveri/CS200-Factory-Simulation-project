package factory.factory200.laneManager;

import java.util.ArrayList;

/**
 * This class contains all data for Gantry. Lane agent and lane manager use these data to process.
 * 
 * @brief Feeder data
 *	@author Dongyoung Jung
 */
public class ServerGantryManagerThreadGantry {
	
	private GantryManagerApp app;	///< Instance of class 'GantryManagerApp'
	private Boolean arrived = true;	///< Boolean 'arrived' telling if the robot should keep moving or arrived
	
	private int currentLocationX;	///< Current Location X Coordinate 
	private int currentLocationY;	///< Current Location Y Coordinate
	private int destinationX;	///< Destination X Coordinate
	private int destinationY;	///< Destination Y Coordinate
	private double incrementalX;	///< Incremental to Current Location X Coordinate
	private double incrementalY;	///< Incremental to Current Location Y Coordinate
	private double gapXSquared;	///< (currentLocationX - destinationX)^2
	private double gapYSquared;	///< (currentLocationY - destinationY)^2
	private double divisor;	///<	Distance between Destination and Current Position
	
	private final int xCoorFeeders = 0;	///< X Coordinate of Feeders
	private final int xCoorBins = 225;	///< X Coordinate of Bins
	private ArrayList<Integer> yCoorFeeders = new ArrayList<Integer>();	///< ArrayList of Y Coordinate of Feeders
	private ArrayList<Integer> yCoorBins = new ArrayList<Integer>();	///< ArrayList of Y Coordinate of Bins
	
	private String signalToGRM = "";	/// Signal to Gantry Robot Manager
	private int colorNum;	///< Color number
	
	/**
	 * This constructors generates Feeder Labels and locates them.
	 * 
	 * @brief Constructor
	 * @param app : Instance of class 'GantryManagerApp'
	 * @param currentLocationX : Current Location X Coordinate
	 * @param currentLocationY : Current Location Y Coordinate
	 */
	public ServerGantryManagerThreadGantry( GantryManagerApp app, int currentLocationX, int currentLocationY ){
		this.app = app;
		this.currentLocationX = currentLocationX;
		this.currentLocationY = currentLocationY;
		
		yCoorFeeders.add(75);
		yCoorFeeders.add(220);
		yCoorFeeders.add(380);
		yCoorFeeders.add(540);

		yCoorBins.add(25);
		yCoorBins.add(105);
		yCoorBins.add(185);
		yCoorBins.add(265);
		yCoorBins.add(350);
		yCoorBins.add(430);
		yCoorBins.add(510);
		yCoorBins.add(595);		
	}
	
	/**
	 * This function sets up the destination and generate informations to close to it.
	 * 
	 * @brief Go To Feeder
	 * @param feederNum : Feeder number
	 */
	public void goToFeeder( int feederNum ){
		destinationX = xCoorFeeders;
		destinationY = yCoorFeeders.get( feederNum );
		calculate();
		arrived = false;
	}
	
	/**
	 * This function sets up the destination and generate informations to close to it.
	 * 
	 * @brief Go To Bin
	 * @param binNum : Bin number
	 */
	public void goToBin( int binNum ){
		destinationX = xCoorBins;
		destinationY = yCoorBins.get( binNum );
		calculate();
		arrived = false;
	}
	
	/**
	 * @brief Direction & Vector Setup
	 */
	public void calculate(){
		gapXSquared = Math.pow(destinationX - currentLocationX,2);
		gapYSquared = Math.pow(destinationY - currentLocationY,2);
		divisor = Math.pow( gapXSquared + gapYSquared, 0.5) / 2;
		incrementalX = ( destinationX - currentLocationX ) / divisor;
		incrementalY = ( destinationY - currentLocationY ) / divisor;
		
		currentLocationX += incrementalX;
		currentLocationY += incrementalY;
	}
	
	/**
	 * This function sends signal to Gantry Robot Manager.
	 * Signal : "&Robot&Color Change&" + color number;
	 * Color number is what the gantry robot changes in after reaching a certain destination.
	 * 
	 * @brief Destination Check & Color Change
	 */
	public void checkDestination(){
		if( Math.abs(destinationX - currentLocationX) < 3 && Math.abs(destinationY - currentLocationY) < 3 ){
			
			// Robot Color Change
			// At Feeder
			colorNum = 0;
			
			// At Bin
			if( destinationX == 225 && destinationY == 25 ){ colorNum = 1; }
			else if( destinationX == 225 && destinationY == 105 ){ colorNum = 2; }
			else if( destinationX == 225 && destinationY == 185 ){ colorNum = 3; }
			else if( destinationX == 225 && destinationY == 265 ){ colorNum = 4; }
			else if( destinationX == 225 && destinationY == 350 ){ colorNum = 5; }
			else if( destinationX == 225 && destinationY == 430 ){ colorNum = 6; }
			else if( destinationX == 225 && destinationY == 510 ){ colorNum = 7; }
			else if( destinationX == 225 && destinationY == 595 ){ colorNum = 8; }
		
			// Signal to Gantry Robot Manager
			signalToGRM = "&Robot&Color Change&" + colorNum;
			
			// Send signal to Gantry Robot Manager
			app.getNetwork().getVerify().verify(signalToGRM);
			arrived = true;
		}
	}
	
	/**
	 * This function sends a signal to Gantry Robot Manager.
	 * Signal : "&Robot&Move" + currentLocationX + "**" + currentLocationY
	 * 
	 * @brief Robot Movement Signal 
	 */
	public void moveRobot(){				
		// Signal to Gantry Robot Manager
		signalToGRM = "&Robot&Move" + currentLocationX + "**" + currentLocationY;
		
		// Send signal to Gantry Robot Manager
		app.getNetwork().getVerify().verify(signalToGRM);
		
		checkDestination();
		calculate();
	}
	
	/**
	 * This function sends a signal to Gantry Robot Manager
	 * Signal :  "&Robot&PickBin"
	 * 
	 * @brief Bin Pickup
	 * @param binNum : Bin number
	 */
	public void pickUpBin( int binNum ){
		// Signal to Gantry Robot Manager
		signalToGRM = "&Robot&PickBin";
		
		// Send signal to Gantry Robot Manager
		app.getNetwork().getVerify().verify(signalToGRM);
	}
	
	/**
	 * This function sends a signal to Gantry Robot Manager
	 * Signal : "&Robot&PutOffBin"
	 * @brief Bin Putoff
	 */
	public void putOffBin(){
		// Signal to Gantry Robot Manager
		signalToGRM = "&Robot&PutOffBin";
		
		// Send signal to Gantry Robot Manager
		app.getNetwork().getVerify().verify(signalToGRM);
	}
	
	/**
	 * This is needed for agent since the agent does not have Timer.
	 * 
	 * @return Boolean arrived
	 */
	public Boolean getArrived(){
		return arrived;
	}
}
