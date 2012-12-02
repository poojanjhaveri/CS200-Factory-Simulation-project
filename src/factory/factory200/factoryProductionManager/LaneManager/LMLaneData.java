package factory.factory200.factoryProductionManager.LaneManager;

import java.util.ArrayList;

/**
 * @brief Lane Data
 * @author Dongyoung Jung
 */
public class LMLaneData {
	
	private LMApplication app;
	private ArrayList<LMDrawablePart> parts = new ArrayList<LMDrawablePart>();
	private ArrayList<LMDrawablePart> jammedLaneParts = new ArrayList<LMDrawablePart>();
	private int laneNum;
	private Boolean jammedLane = false;
	
	public LMLaneData(int laneNum, LMApplication app){
		this.laneNum = laneNum;
		this.app = app;
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
	
	public void switchJammedLane(Boolean jammedLaneSwitch){
		jammedLane = jammedLaneSwitch;
		if( jammedLaneSwitch == true ){
			for( int i = parts.size()-1 ; i >= 0 ; i-- ){
				if( parts.get(i).getX() > 700 ){
					jammedLaneParts.add( parts.remove(i) );
				}
			}
		}
		else if( jammedLaneSwitch == false ){
			for(int i=0 ; i<jammedLaneParts.size() ; i++){
				parts.add( jammedLaneParts.remove(0) );
			}
		}
		app.getAllPart().laneUpdate();
	}

	/**
	 * @brief Nest Status Checker
	 * @param nestSize : Quantity of Parts on Nest
	 */
	public void checkNestStatus(int nestSize){
		if( jammedLane == false ){
			for(int i=0 ; i<parts.size() ; i++){
				if(nestSize < 8){
					parts.get(i).setDestination(540, 40+75*laneNum);
					parts.get(i).setAvailabilityToNest(true);
				}
				else if(nestSize == 8){
					parts.get(i).setDestination(545 + 20*i, 40+75*laneNum);
					parts.get(i).setAvailabilityToNest(false);
				}
			}
		}
		
		else if( jammedLane == true ){
			for( int i=0 ; i<jammedLaneParts.size() ; i++ ){
				if( i == 0 ){
					jammedLaneParts.get(0).setDestination(660, 40+75*laneNum-5);
				}
				else if( i == 1 ){
					jammedLaneParts.get(1).setDestination(662, 40+75*laneNum);
				}
				else if( i == 2 ){
					jammedLaneParts.get(2).setDestination(664, 40+75*laneNum+10);
				}
				else if( i == 3 ){
					jammedLaneParts.get(3).setDestination(672, 40+75*laneNum-10);
				}
				else if( i == 4 ){
					jammedLaneParts.get(4).setDestination(670, 40+75*laneNum-5);
				}
				else{
					jammedLaneParts.get(i).setDestination(660+20*(i-4), 40+75*laneNum);
				}
			}
		}
	}	
}