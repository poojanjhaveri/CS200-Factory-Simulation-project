package factory.factory200.laneManager.ClientSide;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class LMDrawableFeedingBulb{

	private int symbolX, symbolY;
	private ImageIcon feedingBulbImage;
	private int bulbX, bulbY;
	private ImageIcon feedingSymbolImage = new ImageIcon( LMDrawableFeedingBulb.class.getResource("./pics/feedin.png") );
	private static ImageIcon bulbOnImage = new ImageIcon( LMDrawableFeedingBulb.class.getResource("./pics/bulbOn.png") );
	private static ImageIcon bulbOffImage = new ImageIcon( LMDrawableFeedingBulb.class.getResource("./pics/bulbOff.png") );
	
	public LMDrawableFeedingBulb(int symbolX, int symbolY, int bulbX, int bulbY){
		this.symbolX = symbolX;
		this.symbolY = symbolY;
		this.bulbX = bulbX;
		this.bulbY = bulbY;
		
		setSwitch(false);
	}
	 
	public void paint(LMGraphicsPanel panel, Graphics2D graphics){
		feedingSymbolImage.paintIcon(panel, graphics, symbolX, symbolY);
		feedingBulbImage.paintIcon(panel, graphics, bulbX, bulbY);
	}
	
	public void setSwitch(Boolean switchBulb){
		if(switchBulb == true){
			feedingBulbImage = bulbOnImage;
		}
		else if(switchBulb == false){
			feedingBulbImage = bulbOffImage;
		}
	}
}
