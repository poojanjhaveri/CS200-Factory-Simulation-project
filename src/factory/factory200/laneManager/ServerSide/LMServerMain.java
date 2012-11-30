package factory.factory200.laneManager.ServerSide;

import javax.swing.JFrame;

import factory.factory201.feederManagement.FeederAgent;
import factory.factory201.feederManagement.GantryAgent;
import factory.general.HandleAManager;

/**
 * @brief Main in Server Side
 * @author Dongyoung Jung
 */
public class LMServerMain extends JFrame implements Runnable{
	
	private HandleAManager hacLM = null;
	private HandleAManager hacFPM = null;
	private HandleAManager hacKAM = null;
	private HandleAManager hacGRM = null;
	
	private LMFeederForAgent agentFeeder;
	private LMLaneForAgent agentLane;
	private LMNestForAgent agentNest;
	private LMCameraForAgent agentNestCamera;
	private LMGantryRobotForAgent agentGantryRobot;
	private LMPartRobotForAgent agentPartRobot;
	private LMPartData partData;
	
	private LMSignalFromAnimationVerification signalVerify;
	private LMThreadTimer threadTimer;
	private LMController controller;
	
	private FeederAgent[] feederAgents;
	
	private NonNormativeFix1 nonNormativeFix1;
	private NonNormativeFix2 nonNormativeFix2;
	private NonNormativeFix3 nonNormativeFix3;
	
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
		
		nonNormativeFix1 = new NonNormativeFix1(this);
		nonNormativeFix2 = new NonNormativeFix2(this);
		nonNormativeFix3 = new NonNormativeFix3(this);
		
		controller = new LMController(agentFeeder, agentLane, agentNest, agentNestCamera, agentGantryRobot, agentPartRobot, this);
	}
	
	public void checkFixItRunning(){
		nonNormativeFix1.run();
		nonNormativeFix2.run();
		nonNormativeFix3.run();
	}
	
	public void problemHappened(int nonNormativeScenarioNum){
		if( nonNormativeScenarioNum == 1 ){
			nonNormativeFix1.start();
		}
		else if( nonNormativeScenarioNum == 2 ){
			nonNormativeFix2.start();
		}
		else if( nonNormativeScenarioNum == 3 ){
			nonNormativeFix3.start();
		}
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
	
	public LMGantryRobotForAgent getForAgentGantryRobot(){
		return agentGantryRobot;
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
	
	public void setGRM(HandleAManager newHandleAManager) {
		hacGRM = newHandleAManager;
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
	
	public void sendToGRM(String signal){
		if( hacGRM != null ){
			hacGRM.sendMessage(signal);
		}
	}
	
	public void setFeederAgents(FeederAgent[] feederAgents){
		this.feederAgents = feederAgents;
	}
	
	public FeederAgent[] getFeederAgents(){
		return feederAgents;
	}
}
