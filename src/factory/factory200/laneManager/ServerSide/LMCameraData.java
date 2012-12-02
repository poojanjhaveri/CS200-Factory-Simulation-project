package factory.factory200.laneManager.ServerSide;

/**
 * @brief Camera Signal Sends(Server Side)
 * @author Dongyoung Jung
 */
public class LMCameraData {
	
	private LMServerMain serverMain;
	private String signal = "";
	//private boolean PiledPart = false;
	private int nestCameraNum;

	public LMCameraData( int nestCameraNum, LMServerMain serverMain ){
		this.nestCameraNum = nestCameraNum;
		this.serverMain = serverMain;
	}

	public void cameraShoot(){
		// Send To Managers
		signal = nestCameraNum + "&Camera&" + "Shoot";
		serverMain.sendToLM(signal);
		serverMain.sendToFPM(signal);
		
		// Non-normative Scenario : Signal to Agent(Bad Parts)
		if( serverMain.getPartData().checkAllBadParts(nestCameraNum) == true ){
			System.out.println("BAD PARTS ON NEST " + nestCameraNum );
			//serverMain.getCameraAgent().msgAllPartsBad(nestCameraNum);
		}
	}
}
