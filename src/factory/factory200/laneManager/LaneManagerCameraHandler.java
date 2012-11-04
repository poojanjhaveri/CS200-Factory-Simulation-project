package factory.factory200.laneManager;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * The signal from server should be checked in class 'LaneManagerVerifySignalsFromServer'
 * Here, the signals get checked again and take an action to Graphics Panel or GUI Panel.
 * 
 * @brief Verifies signals from server and take an action
 * @author Dongyoung Jung
 */
public class LaneManagerCameraHandler {
	
	private LaneManagerApp app;	///< Instance of class 'LaneManagerApp'
	private Executor executor;	///<Instance of class 'Executor' : This is used for one-time thread
	
	/**
	 * @brief Constructor
	 * @param app : Instance of class 'LaneManagerApp'
	 */
	public LaneManagerCameraHandler(LaneManagerApp app){
		this.app = app;
		executor = Executors.newSingleThreadExecutor();
	}
	
	/**
	 * Once this function runs, the camera pictures shows visible for 0.5 second using one-time thread.
	 * 
	 * @brief Camera Shooting
	 * @param cameraNum : camera number
	 */
	public void cameraShoot(final int cameraNum){
		executor.execute(new Runnable() {
			
			// function inside Runnable class---------------------------------------------------------
			public void run(){
				app.getGraphicsPanel().getCameraArray(cameraNum).setVisible(true);
				try{
					Thread.sleep(500);
				} 
				catch( Exception e ){}
				app.getGraphicsPanel().getCameraArray(cameraNum).setVisible(false);
			}
			//-------------------------------------------------------------------------------------------------------
			
		});
	}
}
