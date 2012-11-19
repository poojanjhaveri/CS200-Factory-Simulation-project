package factory.factory200.factoryProductionManager.LaneManager;

import java.awt.Graphics2D;
import java.util.ArrayList;
import factory.factory200.factoryProductionManager.*;

/**
 * @brief Camera Drawings
 * @author Dongyoung Jung
 */
public class LMDrawableAllCamera {
	
	private LMDrawableCamera newCamera;
	private ArrayList<LMDrawableCamera> cameras = new ArrayList<LMDrawableCamera>();
	private final int cameraX = 495;
	private int cameraY = 55;
	
	public LMDrawableAllCamera(){		
		for(int i=0 ; i<4 ; i++){
			newCamera = new LMDrawableCamera(cameraX, cameraY);
			cameras.add(newCamera);
			cameraY += 150;
		}
	}
	
	public void paint(GraphicsPanel panel, Graphics2D graphics){
		for(int i=0 ; i<cameras.size() ; i++){
			cameras.get(i).paint(panel, graphics);
		}
	}
	
	public void cameraShoot(){
		for(int i=0 ; i<4 ; i++){
			cameras.get(i).cameraController();
		}
	}
	
	public LMDrawableCamera getCamera(int cameraNum){
		return cameras.get(cameraNum);
	}
}