package factory.factory200.laneManager.ClientSide;

import java.util.ArrayList;

/**
 * @brief Nest Data
 * @author Dongyoung Jung
 */
public class LMNestData {
	
	private ArrayList<LMDrawablePart> parts = new ArrayList<LMDrawablePart>();
	private int nestNum;
	private Boolean partPiled = false;
	private Boolean toggling = false;
	
	public LMNestData(int nestNum){
		this.nestNum = nestNum;
	}
	
	public void addPart(LMDrawablePart newPart){
		parts.add(newPart);
	}
	
	public void removePart(){
		if(parts.size() != 0){  parts.remove(0);  }
	}

	public ArrayList<LMDrawablePart> getNestPartArray(){
		return parts;
	}
	
	public void nestDown(){
		parts.clear();
	}
	
	public void switchPartPiled(Boolean partPiled){
		this.partPiled = partPiled;
	}
	
	public void switchToggling(Boolean toggling){
		this.toggling = toggling;
	}
	
	public void reorganizeToggling(){
		for(int i=0 ; i<parts.size() ; i++){
			if( i < 8 ){
				if( i < 4 ){
					parts.get(i).setPositionInNest(13, 35+20*i+75*nestNum);
				}
				else if( i >= 4 ){
					parts.get(i).setPositionInNest(33, 35+20*(i-4)+75*nestNum);
				}
			}
		}
	}

	/**
	 * This keeps organizing the parts on nest.
	 */
	public void reorganize(){
		if( partPiled == false && toggling == false ){
			for(int i=0 ; i<parts.size() ; i++){
				if( i < 8 ){
					if( i < 4 ){
						parts.get(i).setDestination(13, 35+20*i+75*nestNum);
					}
					else if( i >= 4 ){
						parts.get(i).setDestination(33, 35+20*(i-4)+75*nestNum);
					}
				}
			}
		}
		
		else if( partPiled == true ){
			for(int i=0 ; i<parts.size() ; i++){
				parts.get(i).setDestinationPiled();
			}
		}
		
		if( toggling == true ){
			for(int i=0 ; i<parts.size() ; i++){
				parts.get(i).togglingSetup();
			}
		}
		
		else if( toggling == false ){
			for(int i=0 ; i<parts.size() ; i++){
				parts.get(i).stopToggling();
			}
		}
	}
}