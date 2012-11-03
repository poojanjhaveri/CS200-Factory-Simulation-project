package factory.factory200.laneManager;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GantryManagerGraphicsPanel extends JPanel {
	
	private GantryManagerApp app;
	
	// Bins
	private GantryManagerBinWithParts newBinPic;
	private ArrayList<GantryManagerBinWithParts> bins = new ArrayList<GantryManagerBinWithParts>();
	private final int xCoor = 230;
	private int yCoor = 10;
	
	// Robot
	private GantryManagerRobot robot;
		
	public GantryManagerGraphicsPanel(GantryManagerApp app){
		this.app = app;
		setBorder( new LineBorder( Color.red ));
		setPreferredSize(new Dimension(300,640));
		setLayout(null);
		
		for(int i=0 ; i<8 ; i++){
			newBinPic = new GantryManagerBinWithParts(xCoor, yCoor, i);
			bins.add(newBinPic);
			yCoor += 82;
			add(newBinPic);
		}
		
		robot = new GantryManagerRobot(100, 300);
		add(robot);
	}
	
	public GantryManagerRobot getRobot(){
		return robot;
	}
}