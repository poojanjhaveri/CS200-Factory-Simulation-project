package factory.factory200.laneManager;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


public class GantryManagerRobot extends JLabel{
	
	private int xCoor;
	private int yCoor;
	private static ImageIcon robotWithBinImage = new ImageIcon(".//pics//robotWithBin.png");
	private static ImageIcon robotWithoutBinImage = new ImageIcon(".//pics//robotWithoutBin.png");
	
	public GantryManagerRobot( int xCoor, int yCoor ){
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		setIcon(robotWithoutBinImage);
		setSize(50,50);
		setLocation(xCoor, yCoor);
	}
	
	public void moveTo( int destX, int destY ){
		setLocation(destX, destY);
	}
	
	public void pickUpBin(){
		setIcon(robotWithBinImage);
	}
	
	public void putOffBin(){
		setIcon(robotWithoutBinImage);
	}
}
