package factory.factory200.laneManager.ClientSide;

import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class LMDrawableLane {
	
	private LMDrawableTrack newTrack;
	private ArrayList<LMDrawableTrack> tracks = new ArrayList<LMDrawableTrack>();
	private Boolean laneSwitch = false;
	
	public LMDrawableLane(int laneX, int laneY){		
		for(int i=0 ; i<13 ; i++){
			newTrack = new LMDrawableTrack(laneX+40*i, laneY);
			tracks.add(newTrack);
		}
	}
	
	public void paint(LMGraphicsPanel panel, Graphics2D graphics){
		for(int i=0 ; i<tracks.size() ; i++){
			tracks.get(i).paint(panel, graphics);
		}
	}
	
	public void setSwitch(Boolean laneSwitch){
		this.laneSwitch = laneSwitch;
	}
	
	public Boolean getSwitch(){
		return laneSwitch;
	}

	public void laneMove(){
		if(laneSwitch == true){
			for(int i=0 ; i<tracks.size() ; i++){
				tracks.get(i).trackMove();
			}
		}
	}
}
