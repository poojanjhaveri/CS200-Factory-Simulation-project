package factory.factory200.laneManager.ClientSide;

import java.util.ArrayList;

public class LMNestData {
	
	private ArrayList<LMDrawablePart> parts = new ArrayList<LMDrawablePart>();
	private int nestNum;
	
	public LMNestData(int nestNum){
		this.nestNum = nestNum;
	}
	
	public void addPart(LMDrawablePart newPart){
		parts.add(newPart);
	}
	
	public void removePart(){
		if(parts.size() != 0){  parts.remove(0);  }
	}
	
	public int getSize(){
		return parts.size();
	}
	
	public ArrayList<LMDrawablePart> getNestPartArray(){
		return parts;
	}
	
	public void nestDown(){
		parts.clear();
	}
	
	public void reorganize(){
		for(int i=0 ; i<parts.size() ; i++){
			if( i % 2 == 0 ){
				parts.get(i).setDestination(55, 5+10*i+78*nestNum);
			}
			else if( i % 2 == 1 ){
				parts.get(i).setDestination(75, 5+10*(i-1)+78*nestNum);
			}
		}	
	}
}
