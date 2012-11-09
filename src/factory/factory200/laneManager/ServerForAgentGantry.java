package factory.factory200.laneManager;

/**
 * Note : This is done for agent's codes to integrate with manager's codes in a easier manner.
 * Agent just runs any function that changes the graphics or the GUI panel.
 * 
 * @author Dongyoung Jung
 */
public class ServerForAgentGantry {
	
	private GantryManagerApp app;	///< Instance of class 'GantryManagerApp'
	private ServerGantryManagerThreadGantry gantry;	///< Instance of class 'ServerGantryManagerThreadGantry'
	
	public ServerForAgentGantry(GantryManagerApp app){
		this.app = app;
		gantry = new ServerGantryManagerThreadGantry(app, 100, 300);
	}
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * @brief Gantry Robot Movement To Feeder
	 * @param feederNum : feeder number
	 */
	public void goToFeeder( int feederNum ){
		gantry.goToFeeder(feederNum);
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * @brief Gantry Robot Movement To Bin
	 * @param binNum bin number
	 */
	public void goToBin( int binNum ){
		gantry.goToBin(binNum);
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * @brief Gantry Robot Bin Pickup
	 * @param binNum bin number
	 */
	public void pickUpBin( int binNum ){
		gantry.pickUpBin(binNum);
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent ------------------------------------------------------------------------------------------
	/**
	 * @brief Gantry Robot Destination Arrival
	 * @return if Robot arrived the destination
	 */
	public Boolean returnArrived(){
		return gantry.getArrived();
	}
	// ---------------------------------------------------------------------------------------------------------
	
	// For Agent -----------------------------------------------------------------------------------------
	/**
	 * @brief Gantry Robot BinBox Putoff
	 */
	public void putOffBin(){
		gantry.putOffBin();
	}
	// ---------------------------------------------------------------------------------------------------------
	
	/**
	 * This function controllers 'Timer' signal from server.
	 * If the robot is not at destination, the Boolean valuable 'arrived' is false.
	 * Then the signal keeps moving the truck.
	 * If 'arrived' is true, it blocks the signal from going through and actually making the robot move.
	 * 
	 * @brief Timer Signal Controller
	 */
	public void moveTimer(){
		if( gantry.getArrived() == false ){
			gantry.moveRobot();
		}
	}
}
