package factory.factory200.laneManager.ClientSide;

import java.awt.Graphics2D;
import javax.swing.ImageIcon;

/**
 * @brief Camera Drawings
 * @author Dongyoung Jung
 */
public class LMDrawableCamera{

	private int cameraX, cameraY;
	private static ImageIcon cameraImage = new ImageIcon( LMDrawableCamera.class.getResource("./pics/camera.png") );
	private int timer;
	private boolean cameraSwitch = false;
	
	public LMDrawableCamera(int cameraX, int cameraY){
		this.cameraX = cameraX;
		this.cameraY = cameraY;
	}
	 
	public void paint(LMGraphicsPanel panel, Graphics2D graphics){
		if( cameraSwitch == true ){
			cameraImage.paintIcon(panel, graphics, cameraX, cameraY);
		}
	}
	
	public void cameraShoot(){
		cameraSwitch = true;
	}
	
	/**
	 * When Camera Shoot, the pictures stays on animation for a certain time.
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