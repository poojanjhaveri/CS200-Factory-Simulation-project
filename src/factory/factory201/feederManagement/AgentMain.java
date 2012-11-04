package factory.factory201.feederManagement;
import factory.factory200.laneManager.*;

public class AgentMain implements Runnable { // BY DONGYOUNG this class is RUNNABLE(THREAD)
	
	ServerMain serverMain;
	
	FeederAgent feederAgent;
	LaneAgent laneAgent;
	GantryAgent gantryAgent;
	
	public AgentMain( ServerMain serverMain ){
		this.serverMain = serverMain;
		
		//feederAgent = new FeederAgent( index ,serverMain.getForAgentFeeder() ); // BY DONGYOUNG I have no idea about 'index'
		//laneAgent = new LaneAgent( num, serverMain.getForAgentLane() ); // BY DONGYOUNG I have no idea about 'num'
		//gantryAgent = new GantryAgent( serverMain.getForAgentGantry() );
		// BY DONGYOUNG these three statements launch the constructors of each class.
	}
	
	// you can use this run() function.
	public void run(){
		while(true){
			System.out.println("sdfsdfs");
		}
	}
}
