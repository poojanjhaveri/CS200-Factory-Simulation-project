package factory.factory200.factoryProductionManager.LaneManager;

import java.util.ArrayList;

/**
 * @brief Lane Data
 * @author Dongyoung Jung
 */
public class LMLaneData {
	
	private ArrayList<LMDrawablePart> parts = new ArrayList<LMDrawablePart>();
	private ArrayList<LMDrawablePart> jammedLaneParts = new ArrayList<LMDrawablePart>();
	private int laneNum;
	private int maxSize = 8;
	private boolean jammedLane = false;
	private int randomPartNum;
	private int randomPartX;
	private LMDrawableAllPart allPartClass;

	
	public LMLaneData(int laneNum, LMDrawableAllPart allPartClass){
		this.laneNum = laneNum;
		this.allPartClass = allPartClass;
	}
	
	public void addPart(LMDrawablePart newPart){
		if( jammedLane == false ){
			parts.add(newPart);
		}
		else if( jammedLane == true ){
			jammedLaneParts.add(newPart);
		}
	}

	public ArrayList<LMDrawablePart> getLanePartArray(){
		return parts;
	}

	public ArrayList<LMDrawablePart> getLanePartArrayNonNormative(){
		return jammedLaneParts;
	}
	
	public void switchJammedLane(boolean newSwitch, int randomPartNum){
		jammedLane = newSwitch;
		if( newSwitch == true ){
			if( parts.size() != 0 ){
				randomPartX = parts.get(randomPartNum).getX();
			}
			for( int i=randomPartNum ; i<parts.size() ; i++ ){
				jammedLaneParts.add( parts.remove(randomPartNum) );
			}
		}
		else if( newSwitch == false ){
			for(int i=0 ; i<jammedLaneParts.size() ; i++){
				parts.add( jammedLaneParts.remove(0) );
			}
		}
	}
	
	public void switchPartPiled(boolean switchNonNormative){
		if( switchNonNormative == false ){
			maxSize = 8;
		}
		else if( switchNonNormative == true ){
			maxSize = 16;
		}
		allPartClass.laneUpdate();
	}

	/**
	 * @brief Nest Status Checker
	 * @param nestSize : Quantity of Parts on Nest
	 */
	public void checkNestStatus(int nestSize){
		if( jammedLane == false ){
			for(int i=0 ; i<parts.size() ; i++){
				if(nestSize < maxSize){
					parts.get(i).setDestination(540, 40+75*laneNum);
					parts.get(i).setAvailabilityToNest(true);
				}
				else if(nestSize == maxSize){
					parts.get(i).setDestination(545 + 20*i, 40+75*laneNum);
					parts.get(i).setAvailabilityToNest(false);
				}
			}
		}
		
		else if( jammedLane == true ){
			for( int i=0 ; i<jammedLaneParts.size() ; i++ ){
				if( i == 0 ){
					jammedLaneParts.get(0).setDestination(randomPartX, 40+75*laneNum-5);//150
				}
				else if( i == 1 ){
					jammedLaneParts.get(1).setDestination(randomPartX+2, 40+75*laneNum);//152
				}
				else if( i == 2 ){
					jammedLaneParts.get(2).setDestination(randomPartX+4, 40+75*laneNum+10);//154
				}
				else if( i == 3 ){
					jammedLaneParts.get(3).setDestination(randomPartX+12, 40+75*laneNum-10);//162
				}
				else if( i == 4 ){
					jammedLaneParts.get(4).setDestination(randomPartX+10, 40+75*laneNum-5);//160
				}
				else{
					jammedLaneParts.get(i).setDestination(randomPartX+20*(i-4), 40+75*laneNum);//155+20*(i-4)
				}
			}
		}
	}	
}