package factory.factory200.laneManager.ClientSide;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;

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
	private int randomX, randomY;
	private int positionInNestX, positionInNestY;
	private int randomVar;
	private String message = "";
	
	private int currentLocationX, currentLocationY;
	private int destinationX, destinationY;
	private double incrementalX, incrementalY;
	private double gapXSquared, gapYSquared;
	private double divisor;
	private boolean arrived = false;
	private boolean availableToNest = true;
	private boolean arrivedToNest = false;
	private boolean nonNormativePiled = false;
	private boolean nonNormativeToggling = false;
	
	public LMDrawablePart(LMApplication app, LMDrawableAllPart getAllPart, int laneNestNum, int partNum, int currentLocationX, int currentLocationY, int endOfLaneX, int endOfLaneY, int partStatus){
		this.app = app;
		this.getAllPart = getAllPart;
		this.laneNestNum = laneNestNum;
		this.currentLocationX = currentLocationX;
		this.currentLocationY = currentLocationY;
		this.destinationX = endOfLaneX;
		this.destinationY = endOfLaneY;
		
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
	
	public void paint(LMGraphicsPanel panel, Graphics2D graphics){
		if( nonNormativeToggling == true ){
			setDestinationToggling();
		}	
		partImage.paintIcon(panel, graphics, currentLocationX, currentLocationY);
	}
	
	public void partMove(){
		if( currentLocationX < 53 && arrivedToNest == false ){
			if( availableToNest == true ){
				arrivedToNest = true;
				getAllPart.addPartFromLaneToNest(laneNestNum);
				app.getGraphicsPanel().getAllPart().getNest(laneNestNum).reorganizeToggling();
				message = laneNestNum + "PART_TO_NEST_FROM_LANE";
				app.getVerifyMessage().sendToServer(message);
			}
		}
		calculate();
		checkDestination();
	}
	
	public void setPositionInNest(int newX, int newY){
		positionInNestX = newX;
		positionInNestY = newY;
	}
	
	public void calculate(){
		if( arrived == false && app.getGraphicsPanel().getAllLane().getLane(laneNestNum).getSwitch() == true){
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
	
	public void setDestinationPiled(){
		if( nonNormativePiled == false ){
			randomX = (int)(Math.random() * 20) + 13;
			randomY = (int)(Math.random() * 60) + 35 + 75*laneNestNum;
			setDestination(randomX, randomY);
			nonNormativePiled = true;
		}
	}
	
	public void setDestinationToggling(){
		randomVar = (int)(Math.random() * 8);
		if( randomVar < 1 ){  currentLocationX = positionInNestX - 2; currentLocationY = positionInNestY - 2; }
		else if( randomVar < 2 ){  currentLocationX = positionInNestX - 2; currentLocationY = positionInNestY + 2; }
		else if( randomVar < 3 ){  currentLocationX = positionInNestX - 2; currentLocationY = positionInNestY; }
		else if( randomVar < 4 ){  currentLocationX = positionInNestX + 2; currentLocationY = positionInNestY - 2; }
		else if( randomVar < 5 ){  currentLocationX = positionInNestX + 2; currentLocationY = positionInNestY + 2; }
		else if( randomVar < 6 ){  currentLocationX = positionInNestX + 2; currentLocationY = positionInNestY; }
		else if( randomVar < 7 ){  currentLocationX = positionInNestX; currentLocationY = positionInNestY + 2; }
		else if( randomVar <= 8 ){  currentLocationX = positionInNestX; currentLocationY = positionInNestY - 2; }
	}
	
	public void togglingSetup(){
		nonNormativeToggling = true;
	}
	
	public void stopToggling(){
		nonNormativeToggling = false;
	}
	
	public boolean getArrived(){
		return arrived;
	}

	public int getX(){
		return currentLocationX;
	}
	
	public void setAvailabilityToNest(boolean availableToNest){
		this.availableToNest = availableToNest;
	}
}