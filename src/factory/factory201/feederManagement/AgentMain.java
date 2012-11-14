package factory.factory201.feederManagement;
import factory.factory200.laneManager.ServerSide.LMServerMain;
//import factory.factory201.partsManagement.NestAgent;

public class AgentMain implements Runnable { // BY DONGYOUNG this class is RUNNABLE(THREAD)
	
	LMServerMain serverMain;
	
	FeederAgent feeder1;
	FeederAgent feeder2;
	FeederAgent feeder3;
	FeederAgent feeder4;
	
	/* NEST AGENT NOT IN REQUIREMENT #3 FOR V0
	 * */
	//NestAgent nest1;
	//NestAgent nest2;
	//NestAgent nest3;
	//NestAgent nest4;
	//NestAgent nest5;
	//NestAgent nest6;
	//NestAgent nest7;
	//NestAgent nest8;
	
	LaneAgent lane1;
	LaneAgent lane2;
	LaneAgent lane3;
	LaneAgent lane4;
	LaneAgent lane5;
	LaneAgent lane6;
	LaneAgent lane7;
	LaneAgent lane8;
	
	GantryAgent gantry;
	
	public AgentMain( LMServerMain serverMain ){
		this.serverMain = serverMain;
	}
	
	public void run(){
		gantry=new GantryAgent(8,"Gantry",serverMain);
		
		lane1=new LaneAgent("Lane 1",1,2,serverMain);
		lane2=new LaneAgent("Lane 2",3,4,serverMain);
		lane3=new LaneAgent("Lane 3",5,6,serverMain);
		lane4=new LaneAgent("Lane 4",7,8,serverMain);
		//lane5=new LaneAgent(5,serverMain);
		//lane6=new LaneAgent(6,serverMain);
		//lane7=new LaneAgent(7,serverMain);
		//lane8=new LaneAgent(8,serverMain);
		
		feeder1=new FeederAgent("Feeder 1",1,serverMain);
		feeder2=new FeederAgent("Feeder 2",2,serverMain);
		feeder3=new FeederAgent("Feeder 3",3,serverMain);
		feeder4=new FeederAgent("Feeder 4",4,serverMain);
		
		//nest1=new NestAgent(0,lane1);
		//nest2=new NestAgent(1,lane2);
		//nest3=new NestAgent(2,lane3);
		//nest4=new NestAgent(3,lane4);
		//nest5=new NestAgent(4,lane5);
		//nest6=new NestAgent(5,lane6);
		//nest7=new NestAgent(6,lane7);
		//nest8=new NestAgent(7,lane8);
		
		lane1.setFeeder(feeder1);
		lane2.setFeeder(feeder2);

		lane3.setFeeder(feeder3);
		lane4.setFeeder(feeder4);

		//lane5.setFeeder(feeder3);
		//lane6.setFeeder(feeder3);

		//lane7.setFeeder(feeder4);
		//lane8.setFeeder(feeder4);
		
		//lane1.setNest(nest1);
		//lane2.setNest(nest2);
		//lane3.setNest(nest3);
		//lane4.setNest(nest4);
		//lane5.setNest(nest5);
		//lane6.setNest(nest6);
		//lane7.setNest(nest7);
		//lane8.setNest(nest8);
	
		feeder1.setGantry(gantry);
		feeder2.setGantry(gantry);
		feeder3.setGantry(gantry);
		feeder4.setGantry(gantry);
		
		//feeder1.setLeftLane(lane1);
		//feeder1.setRightLane(lane2);
		
		feeder1.setLane(lane1);
		feeder2.setLane(lane2);
		feeder3.setLane(lane3);
		feeder4.setLane(lane4);
		//feeder2.setLeftLane(lane3);
		//feeder2.setRightLane(lane4);
		
		//feeder3.setLeftLane(lane5);
		//feeder3.setRightLane(lane6);
		
		//feeder4.setLeftLane(lane7);
		//feeder4.setRightLane(lane8);
		
		gantry.setFeeder(feeder1,1);
		gantry.setFeeder(feeder2,2);
		gantry.setFeeder(feeder3,3);
		gantry.setFeeder(feeder4,4);
		
		gantry.startThread();
		
		feeder1.startThread();
		feeder2.startThread();
		feeder3.startThread();
		feeder4.startThread();
		
		lane1.startThread();
		lane2.startThread();
		lane3.startThread();
		lane4.startThread();
		//lane5.startThread();
		//lane6.startThread();
		//lane7.startThread();
		//lane8.startThread();
		
		
	
	}
}
