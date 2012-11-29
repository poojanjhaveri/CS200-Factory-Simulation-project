package factory.factory200.laneManager.ClientSide;

import java.util.ArrayList;

/**
 * @brief Lane Data
 * @author Dongyoung Jung
 */
public class LMLaneData {
	
	private ArrayList<LMDrawablePart> parts = new ArrayList<LMDrawablePart>();
	private int laneNum;
	private int randomlyChosenPart;
	private boolean nonNormativePartPiled = false;
	
	public LMLaneData(int laneNum){
		this.laneNum = laneNum;
	}
	
	public void switchNonNormativePartPiled(boolean switchNonNormative){
		nonNormativePartPiled = switchNonNormative;
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
	 * This keeps checking depending on the number of parts on nest.
	 * @param nestSize
	 */
	public void checkNestStatus(int nestSize){
		if( nonNormativePartPiled == false ){
			for(int i=0 ; i<parts.size() ; i++){
				if(nestSize < 8){
					parts.get(i).setDestination(50, 65+75*laneNum);
					parts.get(i).setAvailabilityToNest(true);
				}
				
				else if(nestSize == 8){
					parts.get(i).setDestination(55 + 20*i, 65+75*laneNum);
					parts.get(i).setAvailabilityToNest(false);
				}
			}
		}
	}
}
