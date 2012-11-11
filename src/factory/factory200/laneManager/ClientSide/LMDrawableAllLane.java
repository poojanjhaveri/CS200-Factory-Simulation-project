package factory.factory200.laneManager.ClientSide;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class LMDrawableAllLane {
	
	private LMDrawableLane newLane;
	private ArrayList<LMDrawableLane> lanes = new ArrayList<LMDrawableLane>();
	private final int laneX = 51;
	private int laneY = 25;
	
	public LMDrawableAllLane(){		
		for(int i=0 ; i<8 ; i++){
			newLane = new LMDrawableLane(laneX, laneY);
			lanes.add(newLane);
			laneY += 78;
		}
	}
	
	public void paint(LMGraphicsPanel panel, Graphics2D graphics){
		for(int i=0 ; i<lanes.size() ; i++){
			lanes.get(i).paint(panel, graphics);
		}
	}
	
	public void laneMove(){
		for(int i=0 ; i<8 ; i++){
			lanes.get(i).laneMove();
		}
	}
	
	public LMDrawableLane getLane(int laneNum){
		return lanes.get(laneNum);
	}
}