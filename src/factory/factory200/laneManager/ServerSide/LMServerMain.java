package factory.factory200.laneManager.ServerSide;

import javax.swing.JFrame;

public class LMServerMain extends JFrame{
	
	private LMServer server;
	
	private LMFeederForAgent agentFeeder; ///<Instance of class 'ServerForAgentFeeder'
	private LMLaneForAgent agentLane;	///<Instance of class 'ServerForAgentLane'
	private LMNestForAgent agentNest;	///<Instance of class ServerForAgentNest
	private LMCameraForAgent agentNestCamera;	///<Instance of class 'ServerForAgentNestCamera'
	private LMGantryRobotForAgent agentGantryRobot;
	private LMPartRobotForAgent agentPartRobot;
	private LMPartData partData;
	
	private LMThreadTimer threadTimer;	///<Instance of class 'Thread_Timer'
	private LMController controller;
	
	public LMServerMain(){
		server = new LMServer(this);
		agentFeeder = new LMFeederForAgent(server, this); 
		agentLane = new LMLaneForAgent(server, this);
		agentNest = new LMNestForAgent(server, this);
		agentNestCamera = new LMCameraForAgent(server, this);
		agentGantryRobot = new LMGantryRobotForAgent(server, this); 
		agentPartRobot = new LMPartRobotForAgent(server, this); 
		partData = new LMPartData(server, this);
		
		threadTimer = new LMThreadTimer(server, this);		
		new Thread(threadTimer).start();
		
		controller = new LMController(agentFeeder, agentLane, agentNest, agentNestCamera, agentGantryRobot, agentPartRobot, this);
	}

	public LMLaneForAgent getForAgentLane(){
		return agentLane;
	}
	
	public LMFeederForAgent getForAgentFeeder(){
		return agentFeeder;
	}
	
	public LMCameraForAgent getForAgentNestCamera(){
		return agentNestCamera;
	}

	public LMNestForAgent getForAgentNest(){
		return agentNest;
	}
	
	public LMPartData getPartData(){
		return partData;
	}
}