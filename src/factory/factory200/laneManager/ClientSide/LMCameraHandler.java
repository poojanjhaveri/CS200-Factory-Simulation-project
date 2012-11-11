package factory.factory200.laneManager.ClientSide;

public class LMCameraHandler {
	
	private LMApplication app;	///< Instance of class 'LMApplication'
	private int cameraNum;

	public LMCameraHandler(LMApplication app){
		this.app = app;
	}

	public void cameraShoot(String message){
		cameraNum = message.charAt(0) - 48;
		app.getGraphicsPanel().getAllCamera().getCamera(cameraNum).cameraShoot();
	}
}
