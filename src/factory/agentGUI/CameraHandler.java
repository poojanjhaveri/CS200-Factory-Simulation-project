package factory.agentGUI;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
	@brief enables to draw camera image when agents order to take a picture
	@author Dongyoung Jung
*/
public class CameraHandler {
	
	private LaneManagerApp laneManagerApp;
	private Executor executor;
	
	public CameraHandler(LaneManagerApp laneManagerApp){
		this.laneManagerApp = laneManagerApp;
		executor = Executors.newSingleThreadExecutor();
	}
	
	public void cameraShoot(final int cameraNum){
		// One time thread - after 0.5 second, the camera picture disappears
		executor.execute(new Runnable() {
			
			// function inside Runnable class---------------------------------------------------------
			public void run(){
				laneManagerApp.getGraphicsPanel().getCameraArray(cameraNum).setVisible(true);
				try{
					Thread.sleep(500);
				} 
				catch( Exception e ){}
				laneManagerApp.getGraphicsPanel().getCameraArray(cameraNum).setVisible(false);
			}
			//-------------------------------------------------------------------------------------------------------
			
		});
	}
}
