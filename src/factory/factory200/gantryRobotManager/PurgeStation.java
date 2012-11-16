package factory.factory200.gantryRobotManager;

import javax.swing.ImageIcon;

public class PurgeStation {
	ImageIcon image;
	int x, y;// x, y coordinate of purgeStation
	
	public PurgeStation(){
		image = new ImageIcon("pics/purgestation.png");
	}
	
	public ImageIcon getImage(){
		return image;
	}
}
