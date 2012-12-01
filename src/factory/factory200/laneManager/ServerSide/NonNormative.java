package factory.factory200.laneManager.ServerSide;

import java.util.ArrayList;

public class NonNormative {

	private LMServerMain serverLM;
	private NonNormativeLane nonNormativeLane;
	private NonNormativeNest nonNormativeNest; 
	private ArrayList<NonNormativeLane> nonNormativeLanes = new ArrayList<NonNormativeLane>();
	private ArrayList<NonNormativeNest> nonNormativeNests = new ArrayList<NonNormativeNest>();
	
	public NonNormative(LMServerMain serverLM){
		this.serverLM = serverLM;
		
		for(int i=0 ; i<8 ; i++){
			nonNormativeLane = new NonNormativeLane(serverLM, i);
			nonNormativeLanes.add(nonNormativeLane);
			nonNormativeNest = new NonNormativeNest(serverLM, i);
			nonNormativeNests.add(nonNormativeNest);
		}
	}
	
	public void timerAction(){
		for(int i=0 ; i<8 ; i++){
			nonNormativeLanes.get(i).timerAction();
			nonNormativeNests.get(i).timerAction();
		}
	}
	
	public ArrayList<NonNormativeLane> getNonNormativeLanes(){
		return nonNormativeLanes;
	}
		
	public ArrayList<NonNormativeNest> getNonNormativeNests(){
		return nonNormativeNests;
	}	
}
