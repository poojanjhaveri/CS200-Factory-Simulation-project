package factory.factory200.laneManager.ClientSide;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class LMDrawableBinHolder {

	private int binHolderX, binHolderY;
	private static ImageIcon binHolderImage = new ImageIcon( LMDrawableBinHolder.class.getResource("./pics/binHolder.png") );
	
	public LMDrawableBinHolder(int binHolderX, int binHolderY){
		this.binHolderX = binHolderX;
		this.binHolderY = binHolderY;
	}
	 
	public void paint(LMGraphicsPanel panel, Graphics2D graphics){
		binHolderImage.paintIcon(panel, graphics, binHolderX, binHolderY);
	}
}
