package factory.factory200.laneManager;

import java.util.ArrayList;

public class ServerForAgentGantry {
	
	private GantryManagerApp app;
	private ServerGantryManagerThreadGantry gantry;
	
	public ServerForAgentGantry(GantryManagerApp app){
		this.app = app;
		gantry = new ServerGantryManagerThreadGantry(app, 100, 300);
	}
	
	// For Agent
	public void goToFeeder( int feederNum ){
		gantry.goToFeeder(feederNum);
	}
	
	
	// For Agent
	public void goToBin( int binNum ){
		gantry.goToBin(binNum);
	}
	
	// For Agent
	public void pickUpBin( int binNum ){
		gantry.pickUpBin(binNum);
	}
	
	// For Agent
	public Boolean returnArrived(){
		return gantry.getArrived();
	}
	
	public void putOffBin(){
		gantry.putOffBin();
	}
	
	// Don't touch this!!!
	public void moveTimer(){
		//System.out.println( "Arrived? " + gantry.getArrived() );
		if( gantry.getArrived() == false ){
			gantry.moveRobot();
		}
	}
}
