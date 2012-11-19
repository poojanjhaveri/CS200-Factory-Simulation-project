package factory.factory200.factoryProductionManager.LaneManager;

/**
 * @brief Camera Handler
 * @author Dongyoung Jung
 */
public class LMCameraHandler {
	
	private LMApplication app;
	private int cameraNum;

	public LMCameraHandler(LMApplication app){
		this.app = app;
	}

	/**
	 * @brief Server Message Interpreter
	 * @param message : Message from Server
	 */
	public void cameraShoot(String message){
		cameraNum = message.charAt(0) - 48;
		app.getAllCamera().getCamera(cameraNum).cameraShoot();
	}
}
