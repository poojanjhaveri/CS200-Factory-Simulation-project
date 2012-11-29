package factory.factory200.laneManager.ClientSide;

import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 * @brief Part Drawings
 * @author Dongyoung Jung
 */
public class LMDrawableAllPart {
	
	private LMApplication app;
	private LMGraphicsPanel graphicsPanel; 
	private LMDrawablePart newPart;
	private LMNestData newNestData;
	private LMLaneData newLaneData;
	private ArrayList<LMNestData> nestDatas = new ArrayList<LMNestData>();
	private ArrayList<LMLaneData> laneDatas = new ArrayList<LMLaneData>();
	
	public LMDrawableAllPart(LMApplication app, LMGraphicsPanel graphicsPanel){
		this.graphicsPanel = graphicsPanel;
		this.app = app;
		
		for(int i=0 ; i<8 ; i++){
			newNestData = new LMNestData(i);
			newLaneData = new LMLaneData(i);
			nestDatas.add(newNestData);
			laneDatas.add(newLaneData);
		}
	}

	public void paint(LMGraphicsPanel panel, Graphics2D graphics){
		for(int i=0 ; i<8 ; i++){
			for(int j=0 ; j<laneDatas.get(i).getSize() ; j++){
				laneDatas.get(i).getLanePartArray().get(j).paint(panel, graphics);
			}
			for(int j=0 ; j<nestDatas.get(i).getSize() ; j++){
				nestDatas.get(i).getNestPartArray().get(j).paint(panel, graphics);
			}
		}			
	}

	public void partMove(){
		for(int i=0 ; i<8 ; i++){
			for(int j=0 ; j<laneDatas.get(i).getSize() ; j++){
				laneDatas.get(i).getLanePartArray().get(j).partMove();
			}
			for(int j=0 ; j<nestDatas.get(i).getSize() ; j++){
				nestDatas.get(i).getNestPartArray().get(j).partMove();
			}
		}		
		laneUpdate();
		nestUpdate();
	}
	
	public void addPartFromFeederToLane(int laneNum, int partNum, int partStatus){
		newPart = new LMDrawablePart(app, this, laneNum, partNum, 320, 65+75*laneNum, 50, 65+75*laneNum, partStatus);
		laneDatas.get(laneNum).addPart(newPart);
	}
	
	public void addPartFromLaneToNest(int laneNum){
		nestDatas.get(laneNum).addPart( laneDatas.get(laneNum).removePart() );
	}
	
	/**
	 * Nest updates frequently parts' locations depending on the number of parts on nest.
	 */
	public void laneUpdate(){
		for(int i=0 ; i<8 ; i++){  laneDatas.get(i).checkNestStatus( nestDatas.get(i).getSize() );  }
	}
	
	/**
	 * Nest updates frequently parts' locations.
	 */
	public void nestUpdate(){
		for(int i=0 ; i<8 ; i++){  nestDatas.get(i).reorganize();  }
	}
	
	public void nestDown(int nestNum){
		nestDatas.get(nestNum).nestDown();
	}
	
	public void partRobotTakeOne(int nestNum){
		nestDatas.get(nestNum).removePart();
	}

	public LMLaneData getLane(int laneNum){
		return laneDatas.get(laneNum);
	}
}