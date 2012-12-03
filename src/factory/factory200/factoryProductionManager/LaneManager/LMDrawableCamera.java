package factory.factory200.factoryProductionManager.LaneManager;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import factory.factory200.factoryProductionManager.*;

/**
 * @brief Camera Drawing
 * @author Dongyoung Jung
 */
public class LMDrawableCamera{

	private int cameraX, cameraY;
	private static ImageIcon cameraImage = new ImageIcon( LMDrawableCamera.class.getResource("./pics/camera.png") );
	private int timer;
	private Boolean cameraSwitch = false;
	
	public LMDrawableCamera(int cameraX, int cameraY){
		this.cameraX = cameraX;
		this.cameraY = cameraY;
	}
	 
	public void paint(GraphicsPanel panel, Graphics2D graphics){
		if( cameraSwitch == true ){
			cameraImage.paintIcon(panel, graphics, cameraX, cameraY);
		}
	}
	
	public void cameraShoot(){
		cameraSwitch = true;
	}
	
	/**
	 * @brief camera shooting( shows for 0.5 sec )
	 */
	public void cameraController(){
		if( cameraSwitch == true ){
			timer++;
			if( timer == 10 ){
				timer = 0;
				cameraSwitch = false;
			}
		}
	}
}