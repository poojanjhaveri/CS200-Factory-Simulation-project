package factory.factory200.laneManager.ServerSide;

import java.util.ArrayList;

public class LMCameraForAgent {

	private LMServer server;
	private LMServerMain serverMain;
	private LMCameraData newNestCamera;	///< Instance of class 'ServerLaneManagerThreadNestCamera'
	private ArrayList<LMCameraData> nestCameras = new ArrayList<LMCameraData>();	///< ArrayList of cameras

	public LMCameraForAgent(LMServer server, LMServerMain serverMain){
		this.server = server;
		this.serverMain = serverMain;

		for(int i=0 ; i<4 ; i++){
			newNestCamera = new LMCameraData( i, server, serverMain );
			nestCameras.add(newNestCamera);
		}
	}

	public void cameraShoot( int nestCameraNum ){
		nestCameras.get( nestCameraNum ).cameraShoot();
	}
}
