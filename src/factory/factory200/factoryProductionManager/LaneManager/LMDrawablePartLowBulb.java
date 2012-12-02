package factory.factory200.factoryProductionManager.LaneManager;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import factory.factory200.factoryProductionManager.*;

/**
 * @brief Part Low Bulb Display
 * @author Dongyoung Jung
 */
public class LMDrawablePartLowBulb{

	private int symbolX, symbolY;
	private ImageIcon partLowBulbImage;
	private int bulbX, bulbY;
	private ImageIcon partLowSymbolImage = new ImageIcon( LMDrawablePartLowBulb.class.getResource("./pics/partmissing.png") );
	private static ImageIcon bulbOnImage = new ImageIcon( LMDrawablePartLowBulb.class.getResource("./pics/bulbOn.png") );
	private static ImageIcon bulbOffImage = new ImageIcon( LMDrawablePartLowBulb.class.getResource("./pics/bulbOff.png") );
	
	public LMDrawablePartLowBulb(int symbolX, int symbolY, int bulbX, int bulbY){
		this.symbolX = symbolX;
		this.symbolY = symbolY;
		this.bulbX = bulbX;
		this.bulbY = bulbY;
		
		setSwitch(true);
	}
	 
	public void paint(GraphicsPanel panel, Graphics2D graphics){
		partLowSymbolImage.paintIcon(panel, graphics, symbolX, symbolY);
		partLowBulbImage.paintIcon(panel, graphics, bulbX, bulbY);
	}
	
	public void setSwitch(boolean switchBulb){
		if(switchBulb == true){
			partLowBulbImage = bulbOnImage;
		}
		else if(switchBulb == false){
			partLowBulbImage = bulbOffImage;
		}
	}
}