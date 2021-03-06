package factory.factory200.laneManager.ClientSide;

import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;

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
	
	private final int feederX = 310;
	private final int diversionBulbX = 320;
	private final int feedingBulbX = 400;
	private final int partLowBulbX = 400;
	private final int purgingBulbX = 400;
	private final int rearGateBulbX = 400;
	private final int binHolderX = 440;
	
	private int feederY = 52;
	private int diversionBulbY = 68;
	private int feedingBulbY = 60;
	private int partLowBulbY = 90;
	private int purgingBulbY = 120;
	private int rearGateBulbY = 150;
	private int binHolderY = 65;
	
	public LMDrawableAllFeeder(){
		for(int i=0 ; i<4 ; i++){
			newFeeder = new LMDrawableFeeder(feederX, feederY);
			newDiversionBulb = new LMDrawableDiversionBulb(diversionBulbX, diversionBulbY, diversionBulbX, diversionBulbY+76);// DONE
			newFeedingBulb = new LMDrawableFeedingBulb(feedingBulbX-35, feedingBulbY, feedingBulbX, feedingBulbY);// DONE
			newPartLowBulb = new LMDrawablePartLowBulb(partLowBulbX-35, partLowBulbY, partLowBulbX, partLowBulbY);// DONE
			newPurgingBulb = new LMDrawablePurgingBulb(purgingBulbX-35, purgingBulbY, purgingBulbX, purgingBulbY);// DONE
			newRearGateBulb = new LMDrawableRearGateBulb(rearGateBulbX-35, rearGateBulbY, rearGateBulbX, rearGateBulbY);// DONE
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

	public void paint(LMGraphicsPanel panel, Graphics2D graphics){
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
