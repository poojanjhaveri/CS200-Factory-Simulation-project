package factory.factory200.factoryProductionManager.LaneManager;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import factory.factory200.factoryProductionManager.*;

/**
 * @brief Track Drawings
 * @author Dongyoung Jung
 */
public class LMDrawableTrack {
		
	private static ImageIcon trackImage = new ImageIcon( LMDrawableTrack.class.getResource("./pics/oneTrack.png") );
	private int trackX, trackY;

	public LMDrawableTrack(int trackX, int trackY){
		this.trackX = trackX;
		this.trackY = trackY;
	}

	public void paint(GraphicsPanel panel, Graphics2D graphics, int newTrackY){
		trackImage.paintIcon(panel, graphics, trackX, newTrackY);
	}

	public void trackMove(){
		trackX-=2;
		if(trackX == 498){
			trackX = 800;
		}
	}
}
