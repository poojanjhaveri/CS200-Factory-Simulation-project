package factory.factory200.laneManager;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class GantryManagerRobot extends JLabel{
	
	private int xCoor;//x coordinate of Gantry Robot
	private int yCoor;//y coordinate of Gantry Robot
	private static ImageIcon robotWithBinImage = new ImageIcon(".//pics//robotWithBin.png");
	private static ImageIcon robotWithoutBinImage = new ImageIcon(".//pics//robotWithoutBin.png");
	
	public GantryManagerRobot( int xCoor, int yCoor ){//pass gantryRobot info in and initialize
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		setIcon(robotWithoutBinImage);
		setSize(50,50);
		setLocation(xCoor, yCoor);
	}
	
	//pass in destination coordinate
	public void moveTo( int destX, int destY ){
		//set Gantry Robot coordinates
		setLocation(destX, destY);
	}
	
	// set image for gantry robot when it picks up a bin
	public void pickUpBin(){
		setIcon(robotWithBinImage); 
	}
	
	// show difference of image of gantry robot at the state without any bin
	public void putOffBin(){
		setIcon(robotWithoutBinImage);
	}
}
