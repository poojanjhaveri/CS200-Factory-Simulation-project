package factory.factory200.factoryProductionManager.LaneManager;

import java.awt.Graphics2D;
import factory.factory200.factoryProductionManager.*;

public class LMApplication {

	private LMDrawableAllFeeder paintAllFeeder;
	private LMDrawableAllNest paintAllNest;
	private LMDrawableAllLane paintAllLane;
	private LMDrawableAllCamera paintAllCamera;
	private LMDrawableAllPart paintAllPart;
	private LMDrawableAllBin paintAllBin;
	private LMSignalFromServerVerification serverVerify;
	
	public LMApplication(){
		paintAllFeeder = new LMDrawableAllFeeder();
		paintAllNest = new LMDrawableAllNest();
		paintAllLane = new LMDrawableAllLane();
		paintAllCamera = new LMDrawableAllCamera();
		paintAllPart = new LMDrawableAllPart(this);
		paintAllBin = new LMDrawableAllBin();
		serverVerify = new LMSignalFromServerVerification(this);
	}
	
	public void paint(GraphicsPanel panel, Graphics2D graphics){
		paintAllLane.paint(panel, graphics);
		paintAllNest.paint(panel, graphics);
		paintAllPart.paint(panel, graphics);
		paintAllFeeder.paint(panel, graphics);
		paintAllCamera.paint(panel, graphics);
		paintAllBin.paint(panel, graphics);
	}
	
	public void timerAction(){
		serverVerify.timerAction();
	}
	
	public LMDrawableAllFeeder getAllFeeder(){
		return paintAllFeeder;
	}
	
	public LMDrawableAllNest getAllNest(){
		return paintAllNest;
	}
	
	public LMDrawableAllLane getAllLane(){
		return paintAllLane;
	}
	
	public LMDrawableAllCamera getAllCamera(){
		return paintAllCamera;
	}
	
	public LMDrawableAllPart getAllPart(){
		return paintAllPart;
	}
	
	public LMDrawableAllBin getAllBin(){
		return paintAllBin;
	}
	
	public LMSignalFromServerVerification getServerVerify(){
		return serverVerify;
	}
}
