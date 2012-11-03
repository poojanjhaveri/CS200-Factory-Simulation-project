package factory.factory200.laneManager;
import java.util.ArrayList;

/**
 * Note : This is done for agent's codes to integrate with manager's codes in a easier manner.
 * Agent just runs any function that changes the graphics or the GUI panel.
 * 
 * @author Dongyoung Jung
 */
public class ServerForAgentNestCamera {
	
	private LaneManagerApp app;	///< Instance of class 'LaneManagerApp'
	private final int nestCameraQuantity = 4;	///< Fixed quantity of cameras above nests
	private ServerLaneManagerThreadNestCamera newNestCamera;	///< Instance of class 'ServerLaneManagerThreadNestCamera'
	private ArrayList<ServerLaneManagerThreadNestCamera> nestCameras = new ArrayList<ServerLaneManagerThreadNestCamera>();	///< ArrayList of cameras
	
	/**
	 * @brief Camera generations
	 * @param app : Instance of 'LaneManagerApp'
	 */
	public ServerForAgentNestCamera(LaneManagerApp app){
		this.app = app;

		for(int newNestCameraNum=0 ; newNestCameraNum<nestCameraQuantity ; newNestCameraNum++){
			newNestCamera = new ServerLaneManagerThreadNestCamera( newNestCameraNum, app );
			nestCameras.add(newNestCamera);
		}
	}
	
	/**
	 * Agent runs this function to display images on graphics panel.
	 * 
	 * @brief Camera Shooting( Integration with agent )
	 * @param nestCameraNum : camera number
	 */
	public void cameraShoot( int nestCameraNum ){
		nestCameras.get( nestCameraNum ).cameraShoot();
	}
}
