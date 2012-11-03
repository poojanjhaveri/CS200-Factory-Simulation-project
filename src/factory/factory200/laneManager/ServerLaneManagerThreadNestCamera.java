package factory.factory200.laneManager;
/**
 * This class sends a signal to Lane Manager to show that the picture is actually taken.
 * Signal : nest number + "&Camera&" + "Shoot"
 * 
 * @brief Class with nest camera data
 * @author Dongyoung Jung
 */
public class ServerLaneManagerThreadNestCamera {
	
	private LaneManagerApp app;	///< Instance of class 'LaneManagerApp'
	private String signalToLM = "";	///< Signal to Lane Manager
	private int nestCameraNum;	///< Number of nest that the camera is taken at
	
	/**
	 * @brief Assigns nest number that the camera is taken at. 
	 * @param nestCameraNum : Number of nest that the camera is taken at
	 * @param app : Instance of class 'LaneManagerApp'
	 */
	public ServerLaneManagerThreadNestCamera( int nestCameraNum, LaneManagerApp app ){
		this.nestCameraNum = nestCameraNum;
		this.app = app;
	}
	
	/**
	 * This class sends signal to Lane Manager.
	 * Signal : nest number + "&Camera&" + "Shoot"
	 * 
	 * @brief Signal to Lane Manager
	 */
	public void cameraShoot(){
		signalToLM = nestCameraNum + "&Camera&" + "Shoot";
		app.getNetwork().getVerify().verify(signalToLM);
	}
}
