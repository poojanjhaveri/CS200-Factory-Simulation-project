package factory.factory200.laneManager;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LaneManagerCameraHandler {
	
	private LaneManagerApp app;
	private Executor executor;
	
	public LaneManagerCameraHandler(LaneManagerApp app){
		this.app = app;
		executor = Executors.newSingleThreadExecutor();
	}
	
	public void cameraShoot(final int cameraNum){
		// One time thread - after 0.5 second, the camera picture disappears
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
