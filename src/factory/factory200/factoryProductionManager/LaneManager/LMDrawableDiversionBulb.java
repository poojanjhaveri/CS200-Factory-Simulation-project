package factory.factory200.factoryProductionManager.LaneManager;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import factory.factory200.factoryProductionManager.*;

/**
 * @brief Diversion Bulb Drawing
 * @author Dongyoung Jung
 */
public class LMDrawableDiversionBulb {

	private ImageIcon rightBulbImage;
	private int rightX, rightY;
	private ImageIcon leftBulbImage;
	private int leftX, leftY;
	private static ImageIcon bulbOnImage = new ImageIcon( LMDrawableDiversionBulb.class.getResource("./pics/arrowOn.png") );
	private static ImageIcon bulbOffImage = new ImageIcon( LMDrawableDiversionBulb.class.getResource("./pics/arrowOff.png") );
	private int diversion;
	
	public LMDrawableDiversionBulb(int rightX, int rightY, int leftX, int leftY){
		this.rightX = rightX;
		this.rightY = rightY;
		this.leftX = leftX;
		this.leftY = leftY;
		
		setDivertToRight();
	}
	 
	public void paint(GraphicsPanel panel, Graphics2D graphics){
		rightBulbImage.paintIcon(panel, graphics, rightX, rightY);
		leftBulbImage.paintIcon(panel, graphics, leftX, leftY);
	}
	
	/**
	 * @brief Diversion To Left
	 */
	public void setDivertToLeft(){
		rightBulbImage = bulbOffImage;
		leftBulbImage = bulbOnImage;
		diversion = 1;
	}
	
	/**
	 * @brief Diversion To Right
	 */
	public void setDivertToRight(){
		rightBulbImage = bulbOnImage;
		leftBulbImage = bulbOffImage;
		diversion = 0;
	}
	
	public int getDiversion(){
		return diversion;
	}
}
