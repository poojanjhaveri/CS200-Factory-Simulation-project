package factory.factory200.factoryProductionManager.LaneManager;

import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import factory.factory200.factoryProductionManager.*;

/**
 * @brief Feeder Drawings
 * @author Dongyoung Jung
 */
public class LMDrawableAllFeeder extends JPanel{

	private LMDrawableFeeder newFeeder;
	private LMDrawableDiversionBulb newDiversionBulb;
	private LMDrawableFeedingBulb newFeedingBulb;
	private LMDrawablePartLowBulb newPartLowBulb;
	private LMDrawablePurgingBulb newPurgingBulb;
	private LMDrawableRearGateBulb newRearGateBulb;
	private LMDrawableBinHolder newBinHolder;
	
	private ArrayList<LMDrawableFeeder> feeders = new ArrayList<LMDrawableFeeder>();
	private ArrayList<LMDrawableDiversionBulb> diversionBulbs = new ArrayList<LMDrawableDiversionBulb>();
	private ArrayList<LMDrawableFeedingBulb> feedingBulbs = new ArrayList<LMDrawableFeedingBulb>();
	private ArrayList<LMDrawablePartLowBulb> partLowBulbs = new ArrayList<LMDrawablePartLowBulb>();
	private ArrayList<LMDrawablePurgingBulb> purgingBulbs = new ArrayList<LMDrawablePurgingBulb>();
	private ArrayList<LMDrawableRearGateBulb> rearGateBulbs = new ArrayList<LMDrawableRearGateBulb>();
	private ArrayList<LMDrawableBinHolder> binHolders = new ArrayList<LMDrawableBinHolder>();
	
	private final int feederX = 800;
	private final int diversionBulbX = 810;
	private final int feedingBulbX = 890;
	private final int partLowBulbX = 890;
	private final int purgingBulbX = 890;
	private final int rearGateBulbX = 890;
	private final int binHolderX = 930;
	
	private int feederY = 27;
	private int diversionBulbY = 43;
	private int feedingBulbY = 35;
	private int partLowBulbY = 65;
	private int purgingBulbY = 95;
	private int rearGateBulbY = 125;
	private int binHolderY = 40;
	
	public LMDrawableAllFeeder(){
		for(int i=0 ; i<4 ; i++){
			newFeeder = new LMDrawableFeeder(feederX, feederY);
			newDiversionBulb = new LMDrawableDiversionBulb(diversionBulbX, diversionBulbY, diversionBulbX, diversionBulbY+76); 
			newFeedingBulb = new LMDrawableFeedingBulb(feedingBulbX-35, feedingBulbY, feedingBulbX, feedingBulbY);
			newPartLowBulb = new LMDrawablePartLowBulb(partLowBulbX-35,    partLowBulbY, partLowBulbX, partLowBulbY);
			newPurgingBulb = new LMDrawablePurgingBulb(purgingBulbX-35, purgingBulbY, purgingBulbX, purgingBulbY);
			newRearGateBulb = new LMDrawableRearGateBulb(rearGateBulbX-35, rearGateBulbY, rearGateBulbX, rearGateBulbY);
			newBinHolder = new LMDrawableBinHolder(binHolderX, binHolderY);
			
			feeders.add(newFeeder);
			diversionBulbs.add(newDiversionBulb);
			feedingBulbs.add(newFeedingBulb);
			partLowBulbs.add(newPartLowBulb);
			purgingBulbs.add(newPurgingBulb);
			rearGateBulbs.add(newRearGateBulb);
			binHolders.add(newBinHolder);
			
			feederY += 150;
			binHolderY += 150;
			diversionBulbY += 150;
			feedingBulbY += 150;
			partLowBulbY += 150;
			purgingBulbY += 150;
			rearGateBulbY += 150;
		}
	}

	public void paint(GraphicsPanel panel, Graphics2D graphics){
		for(int i=0 ; i<feeders.size() ; i++){
			feeders.get(i).paint(panel, graphics);
			diversionBulbs.get(i).paint(panel, graphics);
			feedingBulbs.get(i).paint(panel, graphics);
			partLowBulbs.get(i).paint(panel, graphics);
			purgingBulbs.get(i).paint(panel, graphics);
			rearGateBulbs.get(i).paint(panel, graphics);
			binHolders.get(i).paint(panel, graphics);
		}
	}
	
	public LMDrawableFeeder getFeeder(int feederNum){
		return feeders.get(feederNum);
	}
	
	public LMDrawableDiversionBulb getDiversionBulb(int feederNum){
		return diversionBulbs.get(feederNum);
	}
	
	public LMDrawableFeedingBulb getFeedingBulb(int feederNum){
		return feedingBulbs.get(feederNum);
	}
	
	public LMDrawablePartLowBulb getPartLowBulb(int feederNum){
		return partLowBulbs.get(feederNum);
	}
	
	public LMDrawablePurgingBulb getPurgingBulb(int feederNum){
		return purgingBulbs.get(feederNum);
	}
	
	public LMDrawableRearGateBulb getRearGateBulb(int feederNum){
		return rearGateBulbs.get(feederNum);
	}
}
