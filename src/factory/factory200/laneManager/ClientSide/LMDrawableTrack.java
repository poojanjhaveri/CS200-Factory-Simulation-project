package factory.factory200.laneManager.ClientSide;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;

/**
 * @brief Track on Lane Drawings
 * @author Dongyoung Jung
 */
public class LMDrawableTrack {
		
	private static ImageIcon trackImage = new ImageIcon( LMDrawableTrack.class.getResource("./pics/oneTrack.png") );
	private int trackX, trackY;

	public LMDrawableTrack(int trackX, int trackY){
		this.trackX = trackX;
		this.trackY = trackY;
	}

	public void paint(LMGraphicsPanel panel, Graphics2D graphics, int newTrackY){
		trackImage.paintIcon(panel, graphics, trackX, newTrackY);
	}

	/**
	 * One track picture reaches the end of lane, it is painted from feeder again.
	 */
	public void trackMove(){
		trackX-=2;
		if(trackX == 8){
			trackX = 310;
		}
	}
}
