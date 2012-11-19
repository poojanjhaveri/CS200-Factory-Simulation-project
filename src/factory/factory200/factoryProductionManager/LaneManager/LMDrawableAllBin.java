package factory.factory200.factoryProductionManager.LaneManager;

import java.awt.Graphics2D;
import java.util.ArrayList;
import factory.factory200.factoryProductionManager.*;

/**
 * @brief Bin Drawings
 * @author Dongyoung Jung
 */
public class LMDrawableAllBin {

	private LMDrawableBin newBin;
	private ArrayList<LMDrawableBin> bins = new ArrayList<LMDrawableBin>();
	private int binX = 940;
	private int binY = 53; 
	
	public LMDrawableAllBin(){
		for(int i=0 ; i<4 ; i++){
			newBin = new LMDrawableBin(binX, binY);
			bins.add(newBin);
			binY += 150;
		}
	}
	 
	public void paint(GraphicsPanel panel, Graphics2D graphics){
		for(int i=0 ; i<4 ; i++){
			bins.get(i).paint(panel, graphics);
		}
	}
	
	public LMDrawableBin getBin(int binNum){
		return bins.get(binNum);
	}
}
