package factory.factory200.laneManager.ClientSide;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;

/**
 * @brief Part Low Sensor Drawings
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
		
		setSwitch(false);
	}
	 
	public void paint(LMGraphicsPanel panel, Graphics2D graphics){
		partLowSymbolImage.paintIcon(panel, graphics, symbolX, symbolY);
		partLowBulbImage.paintIcon(panel, graphics, bulbX, bulbY);
	}
	
	public void setSwitch(Boolean switchBulb){
		if(switchBulb == true){
			partLowBulbImage = bulbOnImage;
		}
		else if(switchBulb == false){
			partLowBulbImage = bulbOffImage;
		}
	}
}