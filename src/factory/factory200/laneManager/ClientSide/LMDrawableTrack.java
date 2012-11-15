package factory.factory200.laneManager.ClientSide;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class LMDrawableTrack {
		
	private static ImageIcon trackImage = new ImageIcon( LMDrawableTrack.class.getResource("./pics/oneTrack.png") );
	private int trackX, trackY;

	public LMDrawableTrack(int trackX, int trackY){
		this.trackX = trackX;
		this.trackY = trackY;
	}

	public void paint(LMGraphicsPanel panel, Graphics2D graphics){
		trackImage.paintIcon(panel, graphics, trackX, trackY);
	}

	public void trackMove(){
		trackX-=2;
		if(trackX == 8){// DONE
			trackX = 310;// DONE
		}
	}
}
