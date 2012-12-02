package factory.factory200.factoryProductionManager.LaneManager;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import factory.factory200.factoryProductionManager.*;

/**
 * @brief Feeder Drawing
 * @author Dongyoung Jung
 */
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
	 
	public void paint(GraphicsPanel panel, Graphics2D graphics){
		feederImage.paintIcon(panel, graphics, feederX, feederY);
	}
	
	public void setSwitch(boolean switchBulb){
		if(switchBulb == true){
			feederImage = feederOnImage;
		}
		else if(switchBulb == false){
			feederImage = feederOffImage;
		}
	}
}