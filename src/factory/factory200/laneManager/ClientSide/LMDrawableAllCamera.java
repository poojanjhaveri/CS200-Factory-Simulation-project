package factory.factory200.laneManager.ClientSide;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class LMDrawableAllCamera {
	
	private LMDrawableCamera newCamera;
	private ArrayList<LMDrawableCamera> cameras = new ArrayList<LMDrawableCamera>();
	private final int laneX = 45;
	private int laneY = 53;
	
	public LMDrawableAllCamera(){		
		for(int i=0 ; i<4 ; i++){
			newCamera = new LMDrawableCamera(laneX, laneY);
			cameras.add(newCamera);
			laneY += 156;
		}
	}
	
	public void paint(LMGraphicsPanel panel, Graphics2D graphics){
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