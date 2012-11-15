package factory.factory200.factoryProductionManager.LaneManager;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import factory.factory200.factoryProductionManager.*;

public class LMDrawableTrack {
		
	private static ImageIcon trackImage = new ImageIcon( LMDrawableTrack.class.getResource("./pics/oneTrack.png") );
	private int trackX, trackY;

	public LMDrawableTrack(int trackX, int trackY){
		this.trackX = trackX;
		this.trackY = trackY;
	}

	public void paint(GraphicsPanel panel, Graphics2D graphics){
		trackImage.paintIcon(panel, graphics, trackX, trackY);
	}

	public void trackMove(){
		trackX-=2;
		if(trackX == 498){
			trackX = 800;
		}
	}
}
