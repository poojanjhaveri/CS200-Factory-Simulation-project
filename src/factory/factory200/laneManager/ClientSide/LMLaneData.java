package factory.factory200.laneManager.ClientSide;

import java.util.ArrayList;

/**
 * @brief Lane Data
 * @author Dongyoung Jung
 */
public class LMLaneData {
	
	private ArrayList<LMDrawablePart> parts = new ArrayList<LMDrawablePart>();
	private int laneNum;
	private int maxSize = 8;
	private LMDrawableAllPart allPartClass;
	
	public LMLaneData(int laneNum, LMDrawableAllPart allPartClass){
		this.laneNum = laneNum;
		this.allPartClass = allPartClass;
	}
	
	public void addPart(LMDrawablePart newPart){
		parts.add(newPart);
	}
	
	public LMDrawablePart removePart(){
		return parts.remove(0);
	}
	
	public int getSize(){
		return parts.size();
	}
	
	public ArrayList<LMDrawablePart> getLanePartArray(){
		return parts;
	}

	public void switchNonNormativePartPiled(boolean newSwitch){
		if( newSwitch == false ){
			maxSize = 8;
		}
		else if( newSwitch == true ){
			maxSize = 16;
		}
		allPartClass.laneUpdate();
	}

	/**
	 * This keeps checking depending on the number of parts on nest(Normative)
	 * @param nestSize
	 */
	public void checkNestStatus(int nestSize){
		for(int i=0 ; i<parts.size() ; i++){
			if(nestSize < maxSize){
				parts.get(i).setDestination(50, 65+75*laneNum);
				parts.get(i).setAvailabilityToNest(true);
			}
			
			else if(nestSize == maxSize){
				parts.get(i).setDestination(55 + 20*i, 65+75*laneNum);
				parts.get(i).setAvailabilityToNest(false);
			}
		}
	}
}