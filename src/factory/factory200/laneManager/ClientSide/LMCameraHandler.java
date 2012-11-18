package factory.factory200.laneManager.ClientSide;

/**
 * @brief Server Signal Handler(Camera)
 * @author Dongyoung Jung
 */
public class LMCameraHandler {
	
	private LMApplication app;
	private int cameraNum;

	public LMCameraHandler(LMApplication app){
		this.app = app;
	}

	public void cameraShoot(String message){
		cameraNum = message.charAt(0) - 48;
		app.getGraphicsPanel().getAllCamera().getCamera(cameraNum).cameraShoot();
	}
}
