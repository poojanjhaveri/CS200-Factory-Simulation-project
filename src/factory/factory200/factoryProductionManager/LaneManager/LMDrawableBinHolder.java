package factory.factory200.factoryProductionManager.LaneManager;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import factory.factory200.factoryProductionManager.*;

/**
 * @brief Bin Holder Drawing
 * @author Dongyoung Jung
 */
public class LMDrawableBinHolder {

	private int binHolderX, binHolderY;
	private static ImageIcon binHolderImage = new ImageIcon( LMDrawableBinHolder.class.getResource("./pics/binHolder.png") );
	
	public LMDrawableBinHolder(int binHolderX, int binHolderY){
		this.binHolderX = binHolderX;
		this.binHolderY = binHolderY;
	}
	 
	public void paint(GraphicsPanel panel, Graphics2D graphics){
		binHolderImage.paintIcon(panel, graphics, binHolderX, binHolderY);
	}
}
