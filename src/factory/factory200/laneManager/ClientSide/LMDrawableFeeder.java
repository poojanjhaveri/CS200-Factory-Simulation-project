package factory.factory200.laneManager.ClientSide;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class LMDrawableFeeder{

	private ImageIcon feederImage;
	private int feederX, feederY;
	private static ImageIcon feederOnImage = new ImageIcon( LMDrawableFeeder.class.getResource("./pics/feederOn.png") );
	private static ImageIcon feederOffImage = new ImageIcon( LMDrawableFeeder.class.getResource("./pics/feederOff.png") );
	
	public LMDrawableFeeder(int feederX, int feederY){
		this.feederX = feederX;
		this.feederY = feederY;
		
		setSwitch(false);
	}
	 
	public void paint(LMGraphicsPanel panel, Graphics2D graphics){
		feederImage.paintIcon(panel, graphics, feederX, feederY);
	}
	
	public void setSwitch(Boolean switchBulb){
		if(switchBulb == true){
			feederImage = feederOnImage;
		}
		else if(switchBulb == false){
			feederImage = feederOffImage;
		}
	}
}