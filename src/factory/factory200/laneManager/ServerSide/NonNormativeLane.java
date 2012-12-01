package factory.factory200.laneManager.ServerSide;

public class NonNormativeLane{

	private LMServerMain serverMain;
	private boolean jammedLane = false;
	private int laneNum;
	private int jammedLaneTimer;
	
	public NonNormativeLane(LMServerMain serverMain, int laneNum){
		this.serverMain = serverMain;
		this.laneNum = laneNum;
	}
	
	public void timerAction(){
		if( jammedLane == true ){
			jammedLaneTimer++;
			checkTimer();
		}
	}
	
	public void checkTimer(){
		if( jammedLaneTimer == 200 ){
			System.out.println("Help! Agent! Lane " + laneNum + " is JAMMED!!!");

			// Send signal to Lane Agent

			serverMain.getFeederAgents()[laneNum / 2].msgLaneJammed(laneNum);

			
			// Reset
			jammedLaneTimer = 0;
			jammedLane = false;
		}
	}
	
	public void startJammedLane(){
		jammedLane = true;
	}
}