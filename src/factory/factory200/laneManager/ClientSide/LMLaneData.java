package factory.factory200.laneManager.ClientSide;

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
				if( parts.get(i).getX() > 170 ){
					jammedLaneParts.add( parts.remove(i) );
				}
			}
		}
		else if( jammedLaneSwitch == false ){
			for(int i=0 ; i<jammedLaneParts.size() ; i++){
				parts.add( jammedLaneParts.remove(0) );
			}
		}
		app.getGraphicsPanel().getAllPart().laneUpdate();
	}

	/**
	 * This keeps checking depending on the number of parts on nest(Normative)
	 * @param nestSize
	 */
	public void checkNestStatus(int nestSize){
		if( jammedLane == false ){
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
		
		else if( jammedLane == true ){
			for( int i=0 ; i<jammedLaneParts.size() ; i++ ){
				if( i == 0 ){
					jammedLaneParts.get(0).setDestination(170, 65+75*laneNum-5);
				}
				else if( i == 1 ){
					jammedLaneParts.get(1).setDestination(172, 65+75*laneNum);
				}
				else if( i == 2 ){
					jammedLaneParts.get(2).setDestination(174, 65+75*laneNum+10);
				}
				else if( i == 3 ){
					jammedLaneParts.get(3).setDestination(182, 65+75*laneNum-10);
				}
				else if( i == 4 ){
					jammedLaneParts.get(4).setDestination(180, 65+75*laneNum-5);
				}
				else{
					jammedLaneParts.get(i).setDestination(170+20*(i-4), 65+75*laneNum);
				}
			}
		}
	}
}