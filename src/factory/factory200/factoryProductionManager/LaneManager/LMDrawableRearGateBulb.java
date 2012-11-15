package factory.factory200.factoryProductionManager.LaneManager;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import factory.factory200.factoryProductionManager.*;

public class LMDrawableRearGateBulb{

	private int symbolX, symbolY;
	private ImageIcon rearGateBulbImage;
	private int bulbX, bulbY;
	private ImageIcon rearGateSymbolImage = new ImageIcon( LMDrawableRearGateBulb.class.getResource("./pics/rear.png") );
	private static ImageIcon bulbOnImage = new ImageIcon( LMDrawableRearGateBulb.class.getResource("./pics/bulbOn.png") );
	private static ImageIcon bulbOffImage = new ImageIcon( LMDrawableRearGateBulb.class.getResource("./pics/bulbOff.png") );
	
	public LMDrawableRearGateBulb(int symbolX, int symbolY, int bulbX, int bulbY){
		this.symbolX = symbolX;
		this.symbolY = symbolY;
		this.bulbX = bulbX;
		this.bulbY = bulbY;
		
		setSwitch(false);
	}
	 
	public void paint(GraphicsPanel panel, Graphics2D graphics){
		rearGateSymbolImage.paintIcon(panel, graphics, symbolX, symbolY);
		rearGateBulbImage.paintIcon(panel, graphics, bulbX, bulbY);
	}
	
	public void setSwitch(Boolean switchBulb){
		if(switchBulb == true){
			rearGateBulbImage = bulbOnImage;
		}
		else if(switchBulb == false){
			rearGateBulbImage = bulbOffImage;
		}
	}
}