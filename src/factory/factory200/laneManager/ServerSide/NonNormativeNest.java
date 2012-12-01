package factory.factory200.laneManager.ServerSide;

public class NonNormativeNest{

	private LMServerMain serverMain;
	private boolean piledPart = false;
	private boolean badPart = false;
	private int nestNum;
	private int piledPartTimer;
	private int badPartTimer;
	
	public NonNormativeNest(LMServerMain serverMain, int nestNum){
		this.serverMain = serverMain;
		this.nestNum = nestNum;
	}
	
	public void timerAction(){
		if( piledPart == true || badPart == true ){
			if( badPart == true ){
				badPartTimer++;
			}
			if( piledPart == true ){
				piledPartTimer++;
			}
			checkTimer();
		}
	}
	
	public void checkTimer(){
		if( badPartTimer == 200 ){
			System.out.println("Help! Agent! Nest " + nestNum + " has 8 bad parts!!!");
			
			// Send signal to Camera Agent
			serverMain.getCameraAgent().msgAllPartsBad(nestNum);
			
			// Reset
			badPartTimer = 0;
			badPart = false;
		}
		if( piledPartTimer == 200 ){
			System.out.println("Help! Agent! Nest " + nestNum + " has piled parts!!!");
			
			// Send signal to Camera Agent
			serverMain.getCameraAgent().msgPartsPiledUp(nestNum);
			
			// Reset
			piledPartTimer = 0;
			piledPart = false;
		}
	}
	
	public void startBadPart(){
		badPart = true;
	}
	
	public void startPiledPart(){
		piledPart = true;
	}
}