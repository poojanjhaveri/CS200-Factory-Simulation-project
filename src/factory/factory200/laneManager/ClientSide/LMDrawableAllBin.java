package factory.factory200.laneManager.ClientSide;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * @brief Bins Drawings
 * @author Dongyoung Jung
 */
public class LMDrawableAllBin {

	private LMDrawableBin newBin;
	private ArrayList<LMDrawableBin> bins = new ArrayList<LMDrawableBin>();
	private int binX = 450;
	private int binY = 78;
	
	public LMDrawableAllBin(){
		for(int i=0 ; i<4 ; i++){
			newBin = new LMDrawableBin(binX, binY);
			bins.add(newBin);
			binY += 150;
		}
	}
	 
	public void paint(LMGraphicsPanel panel, Graphics2D graphics){
		for(int i=0 ; i<4 ; i++){
			bins.get(i).paint(panel, graphics);
		}
	}
	
	public LMDrawableBin getBin(int binNum){
		return bins.get(binNum);
	}
}
