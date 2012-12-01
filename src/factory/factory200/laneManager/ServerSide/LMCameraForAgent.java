package factory.factory200.laneManager.ServerSide;

import java.util.ArrayList;

/**
 * @brief Camera Functions for Agent
 * @author Dongyoung Jung
 */
public class LMCameraForAgent {

	private LMServerMain serverMain;
	private LMCameraData newNestCamera;
	private ArrayList<LMCameraData> nestCameras = new ArrayList<LMCameraData>();

	public LMCameraForAgent(LMServerMain serverMain){
		this.serverMain = serverMain;

		for(int i=0 ; i<8 ; i++){
			newNestCamera = new LMCameraData( i, serverMain );
			nestCameras.add(newNestCamera);
		}
	}

	public void cameraShoot( int nestCameraNum ){
		nestCameras.get( nestCameraNum ).cameraShoot();
	}
	
	public void startPiledPart( int nestNum ){
		nestCameras.get( nestNum ).startPiledPart();
	}
	
	public void stopPiledPart( int nestNum ){
		nestCameras.get( nestNum ).stopPiledPart();
	}
}
