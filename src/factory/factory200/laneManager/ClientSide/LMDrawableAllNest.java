package factory.factory200.laneManager.ClientSide;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class LMDrawableAllNest {
	
	private LMDrawableNest newNest;
	private ArrayList<LMDrawableNest> nests = new ArrayList<LMDrawableNest>();
	private final int nestX = 10;// DONE
	private int nestY = 75/2;// DONE
	
	public LMDrawableAllNest(){
		for(int i=0 ; i<8 ; i++){
			newNest = new LMDrawableNest(nestX, nestY);
			nests.add(newNest);
			nestY += 75;// DONE
		}
	}
	
	public void paint(LMGraphicsPanel panel, Graphics2D graphics){
		for(int i=0 ; i<nests.size() ; i++){
			nests.get(i).paint(panel, graphics);
		}
	}
	
	public LMDrawableNest getNest(int nestNum){
		return nests.get(nestNum);
	}
}
