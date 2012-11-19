package factory.factory200.factoryProductionManager.LaneManager;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import factory.factory200.factoryProductionManager.*;

/**
 * @brief Purging Bulb Drawing
 * @author Dongyoung Jung
 */
public class LMDrawablePurgingBulb{

	private int symbolX, symbolY;
	private ImageIcon purgingBulbImage;
	private int bulbX, bulbY;
	private ImageIcon purgingSymbolImage = new ImageIcon( LMDrawablePurgingBulb.class.getResource("./pics/purge.png") );
	private static ImageIcon bulbOnImage = new ImageIcon( LMDrawablePurgingBulb.class.getResource("./pics/bulbOn.png") );
	private static ImageIcon bulbOffImage = new ImageIcon( LMDrawablePurgingBulb.class.getResource("./pics/bulbOff.png") );
	
	public LMDrawablePurgingBulb(int symbolX, int symbolY, int bulbX, int bulbY){
		this.symbolX = symbolX;
		this.symbolY = symbolY;
		this.bulbX = bulbX;
		this.bulbY = bulbY;
		
		setSwitch(false);
	}
	 
	public void paint(GraphicsPanel panel, Graphics2D graphics){
		purgingSymbolImage.paintIcon(panel, graphics, symbolX, symbolY);
		purgingBulbImage.paintIcon(panel, graphics, bulbX, bulbY);
	}
	
	public void setSwitch(Boolean switchBulb){
		if(switchBulb == true){
			purgingBulbImage = bulbOnImage;
		}
		else if(switchBulb == false){
			purgingBulbImage = bulbOffImage;
		}
	}
}