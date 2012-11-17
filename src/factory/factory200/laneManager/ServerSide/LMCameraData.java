package factory.factory200.laneManager.ServerSide;

import factory.general.*;

public class LMCameraData {
	
	private Server server;
	private LMServerMain serverMain;
	private String signalToLM = "";	///< Signal to Lane Manager
	private int nestCameraNum;	///< Number of nest that the camera is taken at

	public LMCameraData( int nestCameraNum, Server server, LMServerMain serverMain ){
		this.nestCameraNum = nestCameraNum;
		this.server = server;
		this.serverMain = serverMain;
	}

	public void cameraShoot(){
		signalToLM = nestCameraNum + "&Camera&" + "Shoot";
		System.out.println(signalToLM);
		
		//-----------------------------------------------------For Test
		server.sendMessage(signalToLM);
		//------------------------------------------------------------------
	}
}
