package factory.factory200.laneManager.ServerSide;

import javax.swing.JFrame;
import factory.general.HandleAManager;

public class LMServerMain extends JFrame implements Runnable{
	
	private HandleAManager hacLM = null;
	private HandleAManager hacFPM = null;
	private HandleAManager hacKAM = null;
	
	private LMFeederForAgent agentFeeder; ///<Instance of class 'ServerForAgentFeeder'
	private LMLaneForAgent agentLane;	///<Instance of class 'ServerForAgentLane'
	private LMNestForAgent agentNest;	///<Instance of class ServerForAgentNest
	private LMCameraForAgent agentNestCamera;	///<Instance of class 'ServerForAgentNestCamera'
	private LMGantryRobotForAgent agentGantryRobot;
	private LMPartRobotForAgent agentPartRobot;
	private LMPartData partData;
	
	private LMSignalFromAnimationVerification signalVerify;
	private LMThreadTimer threadTimer;	///<Instance of class 'Thread_Timer'
	private LMController controller;
	
	public LMServerMain(){}
	
	public void run(){
		agentFeeder = new LMFeederForAgent(this); 
		agentLane = new LMLaneForAgent(this);
		agentNest = new LMNestForAgent(this);
		agentNestCamera = new LMCameraForAgent(this);
		agentGantryRobot = new LMGantryRobotForAgent(this); 
		agentPartRobot = new LMPartRobotForAgent(this); 
		partData = new LMPartData(this);
		
		signalVerify = new LMSignalFromAnimationVerification(this);
		threadTimer = new LMThreadTimer(this);		
		new Thread(threadTimer).start();
		threadTimer.timerStart();
		
		//controller = new LMController(agentFeeder, agentLane, agentNest, agentNestCamera, agentGantryRobot, agentPartRobot, this);
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
	
	public LMPartRobotForAgent getForAgentPartRobot(){
		return agentPartRobot;
	}
	
	public LMPartData getPartData(){
		return partData;
	}
	
	public LMSignalFromAnimationVerification getVerify(){
		return signalVerify;
	}
	
	public void setLM(HandleAManager newHandleAManager) {
		hacLM = newHandleAManager;
	}
	
	public void setFPM(HandleAManager newHandleAManager) {
		hacFPM = newHandleAManager;
	}
	
	public void setKAM(HandleAManager newHandleAManager) {
		hacKAM = newHandleAManager;
	}
	
	public void sendToFPM(String signal){
		if( hacFPM != null ){
			hacFPM.sendMessage(signal);
		}
	}
	
	public void sendToLM(String signal){
		if( hacLM != null ){
			hacLM.sendMessage(signal);
		}
	}
	
	public void sendToKAM(String signal){
		if( hacKAM != null ){
			hacKAM.sendMessage(signal);
		}
	}
}
