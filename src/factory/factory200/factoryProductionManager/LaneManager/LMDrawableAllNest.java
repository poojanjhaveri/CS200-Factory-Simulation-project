package factory.factory200.factoryProductionManager.LaneManager;

import java.awt.Graphics2D;
import java.util.ArrayList;
import factory.factory200.factoryProductionManager.*;

public class LMDrawableAllNest {
	
	private LMDrawableNest newNest;
	private ArrayList<LMDrawableNest> nests = new ArrayList<LMDrawableNest>();
	private final int nestX = 500;
	private int nestY = 75 / 2;
	
	public LMDrawableAllNest(){
		for(int i=0 ; i<8 ; i++){
			newNest = new LMDrawableNest(nestX, nestY);
			nests.add(newNest);
			nestY += 75;
		}
	}
	
	public void paint(GraphicsPanel panel, Graphics2D graphics){
		for(int i=0 ; i<nests.size() ; i++){
			nests.get(i).paint(panel, graphics);
		}
	}
	
	public LMDrawableNest getNest(int nestNum){
		return nests.get(nestNum);
	}
}
