package factory.factory200.laneManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;


/**
 * For V0, there is no server requirement, but this platform will be converted into server for a later version assignment.
 * 
 * @brief Platform among Gantry Robot Manager, Lane Manager and Agent
 * @author Dongyoung Jung
 */
class ServerMain extends JFrame{
	
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
	
	// Timer
	private ThreadTimer threadTimer;	///<Instance of class 'Thread_Timer'
	private Controller controller;	///<Instance of class 'Controller'
	
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
		agentFeeder = new ServerForAgentFeeder(laneApp); 
		agentLane = new ServerForAgentLane(laneApp);
		agentNest = new ServerForAgentNest(laneApp);
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
			timer = new Timer(10, new ServerTimer());
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
	
	///< Getter
	public ServerLaneManagerThreadReadFromManager getReadFromLaneManager(){
		return readFromLaneManager;
	}
	
	///< Getter
	public ServerGantryManagerThreadReadFromManager getReadFromGantryManager(){
		return readFromGantryManager;
	}
	
	///< Getter
	public ServerForAgentNest getNestAgent(){
		return agentNest;
	}
}
