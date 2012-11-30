package factory.factory200.laneManager.ServerSide;

public class NonNormativeFix1{

	private LMServerMain serverMain;
	private boolean outOfOrder = false;
	private int timer;
	
	public NonNormativeFix1(LMServerMain serverMain){
		this.serverMain = serverMain;
	}
	
	public void start(){
		outOfOrder = true;
	}
	
	public void run(){
		if( outOfOrder == true ){
			timer++;
			readySignal();
		}
	}
	
	public void readySignal(){
		if( timer == 400 ){
			System.out.println("Help! Agent! Non-normative Scenario 1 happened!");
			timer = 0;
			outOfOrder = false;
		}
	}
}