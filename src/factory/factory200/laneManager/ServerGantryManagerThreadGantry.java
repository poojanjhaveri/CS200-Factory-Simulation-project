package factory.factory200.laneManager;

import java.awt.Color;
import java.util.ArrayList;

public class ServerGantryManagerThreadGantry {
	
	private GantryManagerApp app;
	private Boolean arrived = true;
	
	private int currentLocationX;
	private int currentLocationY;
	private int destinationX;
	private int destinationY;
	private double incrementalX;
	private double incrementalY;
	private double gapXSquared;
	private double gapYSquared;
	private double divisor;
	
	private final int xCoorFeeders = 0;
	private final int xCoorBins = 225;
	private ArrayList<Integer> yCoorFeeders = new ArrayList<Integer>();
	private ArrayList<Integer> yCoorBins = new ArrayList<Integer>();
	
	private String signalToGRM = "";
	private int colorNum;
	
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
	
	public void goToFeeder( int feederNum ){
		destinationX = xCoorFeeders;
		destinationY = yCoorFeeders.get( feederNum );
		calculate();
		arrived = false;
	}
	
	public void goToBin( int binNum ){
		destinationX = xCoorBins;
		destinationY = yCoorBins.get( binNum );
		calculate();
		arrived = false;
	}
	
	public void calculate(){
		gapXSquared = Math.pow(destinationX - currentLocationX,2);
		gapYSquared = Math.pow(destinationY - currentLocationY,2);
		divisor = Math.pow( gapXSquared + gapYSquared, 0.5) / 2;
		incrementalX = ( destinationX - currentLocationX ) / divisor;
		incrementalY = ( destinationY - currentLocationY ) / divisor;
		
		currentLocationX += incrementalX;
		currentLocationY += incrementalY;
	}
	
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
	
	public void moveRobot(){				
		// Signal to Gantry Robot Manager
		signalToGRM = "&Robot&Move" + currentLocationX + "**" + currentLocationY;
		
		// Send signal to Gantry Robot Manager
		app.getNetwork().getVerify().verify(signalToGRM);
		
		checkDestination();
		calculate();
	}
	
	public void pickUpBin( int binNum ){
		// Signal to Gantry Robot Manager
		signalToGRM = "&Robot&PickBin";
		
		// Send signal to Gantry Robot Manager
		app.getNetwork().getVerify().verify(signalToGRM);
	}
	
	public void putOffBin(){
		// Signal to Gantry Robot Manager
		signalToGRM = "&Robot&PutOffBin";
		
		// Send signal to Gantry Robot Manager
		app.getNetwork().getVerify().verify(signalToGRM);
	}
	
	public Boolean getArrived(){
		return arrived;
	}
}
