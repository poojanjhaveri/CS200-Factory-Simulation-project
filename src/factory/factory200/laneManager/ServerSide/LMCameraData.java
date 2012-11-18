package factory.factory200.laneManager.ServerSide;

public class LMCameraData {
	
	private LMServerMain serverMain;
	private String signal = "";	///< Signal to Lane Manager
	private int nestCameraNum;	///< Number of nest that the camera is taken at

	public LMCameraData( int nestCameraNum, LMServerMain serverMain ){
		this.nestCameraNum = nestCameraNum;
		this.serverMain = serverMain;
	}

	public void cameraShoot(){
		// Send To Managers
		signal = nestCameraNum + "&Camera&" + "Shoot";
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
	}
}
