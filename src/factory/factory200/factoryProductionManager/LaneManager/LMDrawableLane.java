package factory.factory200.factoryProductionManager.LaneManager;

import java.awt.Graphics2D;
import java.util.ArrayList;
import factory.factory200.factoryProductionManager.*;

/**
 * @brief Lane Drawing
 * @author Dongyoung Jung
 */
public class LMDrawableLane {
	
	private LMDrawableTrack newTrack;
	private ArrayList<LMDrawableTrack> tracks = new ArrayList<LMDrawableTrack>();
	private Boolean laneSwitch = false;
	private int vibrationAmplitude = 0;
	private int laneY, newLaneY;
	private double randomVar;
	
	public LMDrawableLane(int laneX, int laneY){	
		this.laneY = laneY;
		this.newLaneY = laneY;
		
		for(int i=0 ; i<10 ; i++){
			newTrack = new LMDrawableTrack(laneX+40*i, laneY);
			tracks.add(newTrack);
		}
	}
	
	public void paint(GraphicsPanel panel, Graphics2D graphics){
		randomVar = Math.random();
		// Vibration Amplitude
		if( vibrationAmplitude == 0 ){
			if( randomVar < 0.005 ){
				newLaneY = laneY + 1;
			}
			else if( randomVar > 0.995 ){
				newLaneY = laneY - 1;
			}
		}
		
		else if( vibrationAmplitude == 1 ){
			if( randomVar < 0.5 ){
				newLaneY = laneY + 1;
			}
			else if( randomVar > 0.5 ){
				newLaneY = laneY - 1;
			}
		}
		
		for(int i=0 ; i<tracks.size() ; i++){
			tracks.get(i).paint(panel, graphics, newLaneY);
		}
	}
	
	public void setSwitch(Boolean laneSwitch){
		this.laneSwitch = laneSwitch;
	}
	
	public Boolean getSwitch(){
		return laneSwitch;
	}

	public void setLaneVibrationAmplitudeNormal(){
		vibrationAmplitude = 0;
	}

	public void setLaneVibrationAmplitudeStrong(){
		vibrationAmplitude = 1;
	}
	
	public void laneMove(){
		if(laneSwitch == true){
			for(int i=0 ; i<tracks.size() ; i++){
				tracks.get(i).trackMove();
			}
		}
	}
}
