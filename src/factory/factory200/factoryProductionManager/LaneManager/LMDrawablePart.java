package factory.factory200.factoryProductionManager.LaneManager;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import factory.factory200.factoryProductionManager.*;

public class LMDrawablePart {
	
	private LMApplication app;
	
	private LMDrawableAllPart getAllPart; 
	private static ImageIcon part1 = new ImageIcon( LMDrawablePart.class.getResource("./pics/part1.png") );
	private static ImageIcon part2 = new ImageIcon( LMDrawablePart.class.getResource("./pics/part2.png") );
	private static ImageIcon part3 = new ImageIcon( LMDrawablePart.class.getResource("./pics/part3.png") );
	private static ImageIcon part4 = new ImageIcon( LMDrawablePart.class.getResource("./pics/part4.png") );
	private static ImageIcon part5 = new ImageIcon( LMDrawablePart.class.getResource("./pics/part5.png") );
	private static ImageIcon part6 = new ImageIcon( LMDrawablePart.class.getResource("./pics/part6.png") );
	private static ImageIcon part7 = new ImageIcon( LMDrawablePart.class.getResource("./pics/part7.png") );
	private static ImageIcon part8 = new ImageIcon( LMDrawablePart.class.getResource("./pics/part8.png") );
	private ImageIcon partImage;
	private int laneNestNum;
	private int partNum;
	private String message = "";
	
	private int currentLocationX, currentLocationY;
	private int destinationX, destinationY;
	private double incrementalX, incrementalY;
	private double gapXSquared, gapYSquared;
	private double divisor;
	private Boolean arrived = false;
	private Boolean availableToNest = true;
	private Boolean arrivedToNest = false;
	private Boolean shaken = false;
	
	public LMDrawablePart(LMApplication app, LMDrawableAllPart getAllPart, int laneNestNum, int partNum, int currentLocationX, int currentLocationY, int endOfLaneX, int endOfLaneY){
		this.app = app;
		this.getAllPart = getAllPart;
		this.laneNestNum = laneNestNum;
		this.currentLocationX = currentLocationX;
		this.currentLocationY = currentLocationY;
		this.destinationX = endOfLaneX;
		this.destinationY = endOfLaneY;
		this.partNum = partNum;
		
		if(partNum == 0){  partImage = part1;  }
		else if(partNum == 1){  partImage = part2;  }
		else if(partNum == 2){  partImage = part3;  }
		else if(partNum == 3){  partImage = part4;  }
		else if(partNum == 4){  partImage = part5;  }
		else if(partNum == 5){  partImage = part6;  }
		else if(partNum == 6){  partImage = part7;  }
		else if(partNum == 7){  partImage = part8;  }
	}
	
	public void paint(GraphicsPanel panel, Graphics2D graphics){
		partImage.paintIcon(panel, graphics, currentLocationX, currentLocationY);
	}
	
	public void partMove(){
		if( currentLocationX < 543 & arrivedToNest == false ){
			if( availableToNest == true ){
				arrivedToNest = true;
				getAllPart.addPartFromLaneToNest(laneNestNum);				
			}
		}
		calculate();
		checkDestination();
	}
	
	public void calculate(){
		if( arrived == false && app.getAllLane().getLane(laneNestNum).getSwitch() == true){
			gapXSquared = Math.pow(destinationX - currentLocationX,2);
			gapYSquared = Math.pow(destinationY - currentLocationY,2);
			
			divisor = Math.pow( gapXSquared + gapYSquared, 0.5) / 2;
			
			incrementalX = ( destinationX - currentLocationX ) / divisor;
			incrementalY = ( destinationY - currentLocationY ) / divisor;

			currentLocationX += incrementalX;
			currentLocationY += incrementalY;
		}
	}
	
	public void checkDestination(){
		if( Math.abs(destinationX - currentLocationX) < 2 && Math.abs(destinationY - currentLocationY) < 2 ){  
			arrived = true;
			if(shaken == true){
				getAllPart.getLane(laneNestNum).removeShakenPart(this);
			}
		}
		else{ arrived = false; }
	}
	
	public void setDestination(int destinationX, int destinationY){
		if( shaken  == false ){
			this.destinationX = destinationX;
			this.destinationY = destinationY;
		}
	}
	
	public Boolean getArrived(){
		return arrived;
	}
	
	public void setAvailabilityToNest(Boolean availableToNest){
		this.availableToNest = availableToNest;
	}
	
	public void shake(){
		setDestination(currentLocationX, currentLocationY + 30);
		shaken = true;
	}
}