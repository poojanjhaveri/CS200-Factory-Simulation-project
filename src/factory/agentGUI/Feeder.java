package factory.agentGUI;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
	@brief creates feeder image on graphic panel
	@author Dongyoung Jung
*/
public class Feeder extends JLabel{
	
	private LaneManagerApp laneManagerApp;
	private Lane rightLane;
	private Lane leftLane;
	private static ImageIcon offFeederImage = new ImageIcon(".//pics//FeederOff.png");
	private static ImageIcon onFeederImage = new ImageIcon(".//pics//FeederOn.png");
	private static ImageIcon feederWithPartBoxImage = new ImageIcon(".//pics//FeederWithPartBox.png");

	// true - left, false - right
	private Boolean divertLeftRight = false;
	
	public Feeder(final int xCoor, final int yCoor, Lane rightLane, Lane leftLane, LaneManagerApp laneManagerApp){
		this.laneManagerApp = laneManagerApp;
		this.rightLane = rightLane;
		this.leftLane = leftLane;
		setSize(40,130);
		setLocation(xCoor, yCoor);
		setBorder( new LineBorder( Color. black ));
		setSwitchOff();
	}
	
	// 'Switch Off' Image Setup
	public void setSwitchOff(){
		setIcon(offFeederImage);
	}
	
	// 'Switch On' Image Setup
	public void setSwitchOn(){
		setIcon(onFeederImage);
	}
	
	// 'Part Box is in feeder now' Image Setup
	public void setPartBoxInFeeder(){
		setIcon(feederWithPartBoxImage);
	}
	
	public void feedPartOntoLaneLeft(){
		leftLane.addPartOntoLane();
	}
	
	public void feedPartOntoLaneRight(){
		rightLane.addPartOntoLane();
	}
}
