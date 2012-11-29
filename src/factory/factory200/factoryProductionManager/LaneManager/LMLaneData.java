package factory.factory200.factoryProductionManager.LaneManager;

import java.util.ArrayList;

/**
 * @brief Lane Data
 * @author Dongyoung Jung
 */
public class LMLaneData {
	
	private ArrayList<LMDrawablePart> parts = new ArrayList<LMDrawablePart>();
	private int laneNum;
	private int randomlyChosenPart;
	
	public LMLaneData(int laneNum){
		this.laneNum = laneNum;
	}
	
	public void addPart(LMDrawablePart newPart){
		parts.add(newPart);
	}
	
	public LMDrawablePart removePart(){
		return parts.remove(0);
	}
	
	public void removeShakePart(int partNum){
		parts.remove(partNum);
	}
	
	public int getSize(){
		return parts.size();
	}
	
	public ArrayList<LMDrawablePart> getLanePartArray(){
		return parts;
	}
	
	/**
	 * @brief Nest Status Checker
	 * @param nestSize : Quantity of Parts on Nest
	 */
	public void checkNestStatus(int nestSize){
		for(int i=0 ; i<parts.size() ; i++){
			if(nestSize != 8){
				parts.get(i).setDestination(540, 40+75*laneNum);
				parts.get(i).setAvailabilityToNest(true);
			}
			
			if(nestSize == 8){
				parts.get(i).setDestination(545 + 20*i, 40+75*laneNum);
				parts.get(i).setAvailabilityToNest(false);
			}
		}
	}
}
