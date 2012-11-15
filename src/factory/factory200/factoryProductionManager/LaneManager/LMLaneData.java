package factory.factory200.factoryProductionManager.LaneManager;

import java.util.ArrayList;

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
	
	public void shakePart(){
		randomlyChosenPart = (int)( Math.random() * parts.size() );
		parts.get(randomlyChosenPart).shake();
	}
	
	public void removeShakenPart(LMDrawablePart shakenPart){
		parts.remove(shakenPart);
	}
	
	public void checkNestStatus(int nestSize){
		for(int i=0 ; i<parts.size() ; i++){
			if(nestSize != 8){
				parts.get(i).setDestination(540, 65+75*laneNum);
				parts.get(i).setAvailabilityToNest(true);
			}
			
			if(nestSize == 8){
				parts.get(i).setDestination(545 + 20*i, 65+75*laneNum);
				parts.get(i).setAvailabilityToNest(false);
			}
		}
	}
}
