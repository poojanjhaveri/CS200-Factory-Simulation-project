package factory.factory200.laneManager.ClientSide;

import java.util.ArrayList;

public class LMLaneData {
	
	private ArrayList<LMDrawablePart> parts = new ArrayList<LMDrawablePart>();
	private int laneNum;
	
	public LMLaneData(int laneNum){
		this.laneNum = laneNum;
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
	
	public void checkNestStatus(int nestSize){
		for(int i=0 ; i<parts.size() ; i++){
			if(nestSize != 8){
				parts.get(i).setDestination(90, 36+78*laneNum);
				parts.get(i).setAvailabilityToNest(true);
			}
			
			if(nestSize == 8){
				parts.get(i).setDestination(95 + 20*i, 36+78*laneNum);
				parts.get(i).setAvailabilityToNest(false);
			}
		}
	}
}
