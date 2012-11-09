package factory.factory200.laneManager;

import factory.factory201.feederManagement.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * For V0, there is no server requirement, but this platform will be converted into server for a later version assignment.
 * 
 * @brief Platform among Gantry Robot Manager, Lane Manager and Agent
 * @author Dongyoung Jung
 */
public class ServerMain extends JFrame{
	
	// Lane Manager
	private LaneManagerApp laneApp;	///<Instance of class 'LaneManagerLaneManagerApp'
	private ServerForAgentFeeder agentFeeder; ///<Instance of class 'ServerForAgentFeeder'
	private ServerForAgentLane agentLane;	///<Instance of class 'ServerForAgentLane'
	private ServerForAgentNest agentNest;	///<Instance of class ServerForAgentNest
	private ServerForAgentNestCamera agentNestCamera;	///<Instance of class 'ServerForAgentNestCamera'
	private ServerLaneManagerThreadReadFromManager readFromLaneManager;	///<Instance of class 'ServerLaneManagerThreadReadFromManager'
	
	// Gantry Manager
	private GantryManagerApp gantryApp;	///<Instance of class 'GantryManagerGantryManagerApp'
	private ServerForAgentGantry agentGantry;	///<Instance of class 'ServerForAgentGantry'
	private ServerGantryManagerThreadReadFromManager readFromGantryManager;	///<Instance of class 'ServerGantryManagerThreadReadFromManager'
	
	public Semaphore anim = new Semaphore(1,true);//binary semaphore, fair
	// Timer
	private ThreadTimer threadTimer;	///<Instance of class 'Thread_Timer'
	private Controller controller;	///<Instance of class 'Controller'
	
	// Agent Thread
	
	//private AgentMain agentMain = new AgentMain(this);	///< Runnable Class for Agent
	//private Thread agentThread = new Thread(agentMain);	///< Thread  for Agent
		
	/**
	 * This class creates a bunch of class instances.
	 * To run the timer constantly, this class put 'thread_Timer' instance in 'Thread' and start it.
	 * 
	 * @brief Instance generation 
	 */
	public ServerMain(){
		// Managers
		laneApp = new LaneManagerApp(this);
		gantryApp = new GantryManagerApp(this);
		
		// Agent(Lane Manager)
		agentFeeder = new ServerForAgentFeeder(laneApp, this); 
		agentLane = new ServerForAgentLane(laneApp);
		agentNest = new ServerForAgentNest(laneApp, this);
		agentNestCamera = new ServerForAgentNestCamera(laneApp);
		readFromLaneManager = new ServerLaneManagerThreadReadFromManager(this);
		
		// Agent(Gantry Robot Manager)
		agentGantry = new ServerForAgentGantry(gantryApp);
		readFromGantryManager = new ServerGantryManagerThreadReadFromManager(this);
		
		//Thread (Timer)
		threadTimer = new ThreadTimer();		
		new Thread(threadTimer).start();
		
		// Controller (For Test)
		controller = new Controller(agentFeeder, agentLane, agentNest, agentNestCamera, agentGantry);
		
		// Agent Thread start
		//agentThread.start();
	}
	
	/**
	 *  Server only has one timer and sends its signal to managers.
	 *  Managers use this signal to show lane, feeder, and gantry robot movement animations.
	 * 
	 *	@brief Inner class 'Thread_Timer' 
	 */
	class ThreadTimer implements Runnable{
	
		Timer timer;	///< Instance of 'Timer' class
		
		/**
		 * The signal frequency is fast in server.
		 * Each manager can ignore some signals to make its own speed slower than the signal speed from server.
		 * To haste the speed for each manager, the frequency of signal from server should increase by controlling this.
		 * 
		 * @brief Generation of instance 'timer' from class 'Timer'
		 */
		public ThreadTimer(){
			timer = new Timer(30, new ServerTimer());
			timer.start();
		}
		
		public void run(){}
		/**
		 * Once the timer starts, this 'actionPerformed' function keeps running.
		 * 
		 *	@brief ActionListener class
		 */
		public class ServerTimer implements ActionListener{
			public void actionPerformed(ActionEvent ae){
				agentFeeder.feedPartsTimer();
				agentLane.moveLaneTimer();
				agentGantry.moveTimer();
			}
		}
	}
	
	/**
	 * @brief Getter
	 * @return Instance of class 'ServerLaneManagerThreadReadFromManager'
	 */
	public ServerLaneManagerThreadReadFromManager getReadFromLaneManager(){
		return readFromLaneManager;
	}
	
	/**
	 * @brief Getter
	 * @return Instance of class 'ServerGantryManagerThreadReadFromManager'
	 */
	public ServerGantryManagerThreadReadFromManager getReadFromGantryManager(){
		return readFromGantryManager;
	}
	
	
	/**
	 * @brief Getter
	 * @return Instance of class 'ServerForAgentLane'
	 */
	public ServerForAgentLane getForAgentLane(){
		return agentLane;
	}
	
	
	/**
	 * @brief Getter
	 * @return Instance of class 'ServerForAgentFeeder'
	 */
	public ServerForAgentFeeder getForAgentFeeder(){
		return agentFeeder;
	}
	
	
	/**
	 * @brief Getter
	 * @return Instance of class 'ServerForAgentNestCamera'
	 */
	public ServerForAgentNestCamera getForAgentNestCamera(){
		return agentNestCamera;
	}
	
	/**
	 * @brief Getter
	 * @return Instance of class 'ServerForAgentNest'
	 */
	public ServerForAgentNest getForAgentNest(){
		return agentNest;
	}
	
	
	/**
	 * @brief Getter
	 * @return Instance of class 'ServerForAgentGantry'
	 */
	public ServerForAgentGantry getForAgentGantry(){
		return agentGantry;
	}
}
