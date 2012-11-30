package factory.factory200.factoryProductionManager.LaneManager;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import factory.factory200.factoryProductionManager.*;

/**
 * @brief Part Drawing
 * @author Dongyoung Jung
 */
public class LMDrawablePart {
	
	private LMApplication app;
	
	private LMDrawableAllPart getAllPart; 
	private static ImageIcon goodpart1 = new ImageIcon( LMDrawablePart.class.getResource("./pics/part1.png") );
	private static ImageIcon goodpart2 = new ImageIcon( LMDrawablePart.class.getResource("./pics/part2.png") );
	private static ImageIcon goodpart3 = new ImageIcon( LMDrawablePart.class.getResource("./pics/part3.png") );
	private static ImageIcon goodpart4 = new ImageIcon( LMDrawablePart.class.getResource("./pics/part4.png") );
	private static ImageIcon goodpart5 = new ImageIcon( LMDrawablePart.class.getResource("./pics/part5.png") );
	private static ImageIcon goodpart6 = new ImageIcon( LMDrawablePart.class.getResource("./pics/part6.png") );
	private static ImageIcon goodpart7 = new ImageIcon( LMDrawablePart.class.getResource("./pics/part7.png") );
	private static ImageIcon goodpart8 = new ImageIcon( LMDrawablePart.class.getResource("./pics/part8.png") );
	private static ImageIcon badpart = new ImageIcon( LMDrawablePart.class.getResource("./pics/badpart.png") );
	
	private ImageIcon partImage;
	private int laneNestNum;
	private int partNum;
	private int partStatus;
	private int randomX, randomY;
	private String message = "";
	
	private int currentLocationX, currentLocationY;
	private int destinationX, destinationY;
	private double incrementalX, incrementalY;
	private double gapXSquared, gapYSquared;
	private double divisor;
	private Boolean arrived = false;
	private Boolean availableToNest = true;
	private Boolean arrivedToNest = false;
	private Boolean nonNormative = false;
	
	public LMDrawablePart(LMApplication app, LMDrawableAllPart getAllPart, int laneNestNum, int partNum, int currentLocationX, int currentLocationY, int endOfLaneX, int endOfLaneY, int partStatus){
		this.app = app;
		this.getAllPart = getAllPart;
		this.laneNestNum = laneNestNum;
		this.currentLocationX = currentLocationX;
		this.currentLocationY = currentLocationY;
		this.destinationX = endOfLaneX;
		this.destinationY = endOfLaneY;
		this.partNum = partNum;
		this.partStatus = partStatus;
		
		if( partStatus == 0 ){
			partImage = badpart;
		}
		else if( partStatus == 1 ){
			if(partNum == 0){  partImage = goodpart1;  }
			else if(partNum == 1){  partImage = goodpart2;  }
			else if(partNum == 2){  partImage = goodpart3;  }
			else if(partNum == 3){  partImage = goodpart4;  }
			else if(partNum == 4){  partImage = goodpart5;  }
			else if(partNum == 5){  partImage = goodpart6;  }
			else if(partNum == 6){  partImage = goodpart7;  }
			else if(partNum == 7){  partImage = goodpart8;  }
		}
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
		}
		else{ arrived = false; }
	}
	
	public void setDestination(int destinationX, int destinationY){
		this.destinationX = destinationX;
		this.destinationY = destinationY;
	}
	
	public void setDestinationNonNormative(){
		if( nonNormative == false ){
			randomX = (int)(Math.random() * 20) + 503;
			randomY = (int)(Math.random() * 60) + 10 + 75*laneNestNum;
			setDestination(randomX, randomY);
			nonNormative = true;
		}
	}
	
	public Boolean getArrived(){
		return arrived;
	}
	
	public void setAvailabilityToNest(Boolean availableToNest){
		this.availableToNest = availableToNest;
	}
}