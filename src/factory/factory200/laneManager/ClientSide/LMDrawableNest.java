package factory.factory200.laneManager.ClientSide;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class LMDrawableNest {

	private ImageIcon nestImage;
	private static ImageIcon nestUpImage = new ImageIcon( LMDrawableNest.class.getResource("./pics/nest.png") );
	private static ImageIcon nestDownImage = new ImageIcon( LMDrawableNest.class.getResource("./pics/nestDown.png") );
	private int nestX, nestY;
	
	public LMDrawableNest(int nestX, int nestY){
		this.nestX = nestX;
		this.nestY = nestY;
		
		nestImage = nestUpImage;
	}
	 
	public void paint(LMGraphicsPanel panel, Graphics2D graphics){
		nestImage.paintIcon(panel, graphics, nestX, nestY);
	}
	
	public void nestDown(){
		nestImage = nestDownImage;
	}
	
	public void nestUp(){
		nestImage = nestUpImage;
	}
}
