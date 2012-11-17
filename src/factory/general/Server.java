package factory.general;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import factory.factory200.gantryRobotManager.GantryRobotManager;
import factory.factory200.kitAssemblyManager.KitAssemblyManager;
import factory.factory200.laneManager.ServerSide.LMServerMain;
import factory.factory201.feederManagement.FeederAgent;
import factory.factory201.feederManagement.GantryAgent;
import factory.factory201.feederManagement.LaneAgent;
import factory.factory201.kitManagement.CameraAgent;
import factory.factory201.kitManagement.ConveyorAgent;
import factory.factory201.kitManagement.KitRobotAgent;
import factory.factory201.partsManagement.NestAgent;
import factory.factory201.partsManagement.PartsAgent;

/**
 * @brief This class is critical to the integration of GUI classes, agents, etc.
 * It uses a protocol established with the Message class. It contains instances
 * of agents and calls their methods appropriately based on received messages
 * from clients. So, this class runs a loop that constantly checks for new
 * clients and creates a new thread for each.
 * @author David Zhang, YiWei Roy Zheng
 */
public class Server { // KitAssemblyAgent

	/**
	 * Instance fields
	 */
	public static final Integer PORT_NUMBER = 31415;
	public static final String HOST_NAME = "localhost";
	private static boolean SHOULD_DEBUG = false;
	private Printer p = new Printer();
	private int numClients; // accessible by Server and HandleAManager

	/** Agents */
    // Fields just for "AgentMain" stuff (Agent preparation) 
    private static final boolean TEST_MODE = true;
    private static final int FEEDER = 4;
    private static final int LANE = 8;

    // Fields for agent setup
    private KitAssemblyManager KAM; // *
    private GantryRobotManager GRM; // *
	private NestAgent nestAgent;
	private PartsAgent partsAgent;
    private KitRobotAgent kitRobotAgent;
    private CameraAgent cameraAgent;
    private ConveyorAgent conveyorAgent;
    private FeederAgent[] feederAgents;
    private GantryAgent gantryAgent;
    private LaneAgent[] laneAgents;
	
    // Dongyoung's
    private LMServerMain serverLM;
    private Thread threadLM;
	
	/** Connection fields */
	private ServerSocket ss = null;
	private Socket s = null;
	private HandleAManager hac;

	// needed to handle multiple clients?
	// private ArrayList<HandleAClient> clients = new ArrayList<HandleAClient>();
	public static void main(String[] args) {
		Server server = new Server(PORT_NUMBER);
		if (SHOULD_DEBUG) {
			server.debug();
		}
	}

	/**
	 * @brief Instances Server, prompting the user for a port number and
	 * beginning the loop that checks for clients.
	 * @param portNumber - the port number to create the server on.
	 */
	public Server(int portNumber) {
        // TODO: uncomment when ready
		prepareAllAgents(); // Prepare all agents; based on AgentMain.java
		numClients = 0; // Initialize num clients is 0
		start(portNumber); // Start listening for clients and making new HandleAManager instances
	}

	/**
	 * @brief Starts the server, listening for clients and making new HandleAManager instances (threads) appropriately
	 * Contains the central loop. We break out of this loop by forcing System.exit(0) in HandleAManager.
	 */
	private void start(int portNumber) {
		System.out.println("Port number: " + portNumber);
		try {
			ss = new ServerSocket(portNumber);
			System.out.println("Server started; waiting for clients");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}

		while (true) {
			// Continuously check for a new client for which to create a thread
			try {
				s = ss.accept(); // Wait for a client (program halts here until connection occurs)
				numClients++;
				p.println("num clients: " + numClients);
				this.hac = new HandleAManager(s, this);
				new Thread(hac).start(); // Create the thread
			} catch (Exception e) {
				System.out.println("got an exception" + e.getMessage());
			}
			System.out.println("A client has connected");
		}
	}


	/*********** Agent Preparation Code **********/
	/**
	 * @brief prepares all agents; called when server constructor begins
	 */
	private void prepareAllAgents() {
		declareAgents();
		connectAgentsAndManagers();
		startAgentThreads();
		startInteractionSequence();
		debugIfNecessaryForAgents();
	}
	
	private void declareAgents() {
		/*========== Declare all agents and etc. ==========*/
		// Misc - pass in the appropriate KAM and GRM
//        KAM = new KitAssemblyManager(); // *
//        GRM = new GantryRobotManager(); // *

        // Alex
        kitRobotAgent = new KitRobotAgent("Kit Robot");
        cameraAgent = new CameraAgent("Camera");
        conveyorAgent = new ConveyorAgent("Conveyor");

        // Patrick
        partsAgent = new PartsAgent("Parts Agent");
        nestAgent = new NestAgent("Nest Agent");

        // Kevin
        feederAgents = new FeederAgent[FEEDER];
        gantryAgent = new GantryAgent(8, "Gantry");
        laneAgents = new LaneAgent[LANE];
        
        for (int i = 0; i < LANE; i++) {
            if (i < FEEDER) {
                feederAgents[i] = new FeederAgent("Feeder " + i, i);
            }
            laneAgents[i] = new LaneAgent("Lane " + i);
        }
	}

	private void connectAgentsAndManagers() {
		/*========== Pass proper agents to everyone ==========*/
		// Alex
        kitRobotAgent.setAll(cameraAgent, conveyorAgent, partsAgent);
        cameraAgent.setAll(kitRobotAgent, nestAgent);
        conveyorAgent.setKitRobot(kitRobotAgent);

        // Patrick
        partsAgent.setCamera(cameraAgent);
        partsAgent.setKitAssemblyManager(KAM);
        partsAgent.setKitRobot(kitRobotAgent);
        partsAgent.setNestInterface(nestAgent);
        nestAgent.setCamera(cameraAgent);
        nestAgent.setPartsAgent(partsAgent);
        for (int i = 0; i < 8; i++) {
            nestAgent.getNest(i).setLane(laneAgents[i]);
        }

        // Kevin
        gantryAgent.setGantryRobotManager(GRM);
        for (int i = 0, j = 0; i < FEEDER; i++, j++) {
            
            feederAgents[i].setGantry(gantryAgent);
            feederAgents[i].setLeftLane(laneAgents[j]);
            feederAgents[i].setRightLane(laneAgents[++j]);
            gantryAgent.setFeeder(feederAgents[i], i);
        }

        for (int i = 0; i < LANE; i += 2) {
            laneAgents[i].setFeeder(feederAgents[i / 2]);
            laneAgents[i].setNest(nestAgent);
            laneAgents[i + 1].setFeeder(feederAgents[i / 2]);
            laneAgents[i + 1].setNest(nestAgent);
        }
	}

	private void startAgentThreads() {
		/*========== Start all of the threads ==========*/

        // Alex
        cameraAgent.startThread();
        conveyorAgent.startThread();
        
        // *Change this!
        conveyorAgent.generateKit(10); // * This generates 10 new kits, among other things if you pass string... *
        
        kitRobotAgent.startThread();

        //Patrick
        partsAgent.startThread();
        nestAgent.startThread();

        // Kevin
        gantryAgent.startThread();
        for (int i = 0; i < LANE; i++) {
            if (i < FEEDER) {
                feederAgents[i].startThread();
            }
            laneAgents[i].startThread();
        }
	}

	private void startInteractionSequence() {
		// Get kit from somewhere
		// * 
		Kit kit = new Kit("Test Kit"); // This is required for...
		for (int i = 1; i < 9; i++) {
			kit.addPart(new Part(i)); // This is a kit that has actual parts...
		}

		// Officially start the agent interaction sequence!
		partsAgent.msgHereIsKit(kit); // The primary agent
	}

	private void debugIfNecessaryForAgents() {
		/*========== Turn on or off debugging (print statements) ==========*/
		// Just for debugging; put 'true' to turn off print statements
		if (!TEST_MODE) {
			for (int i = 0; i < LANE; i++) {
				if (i < FEEDER) {
					feederAgents[i].print = false;
				}
				laneAgents[i].print = false;
			}
			gantryAgent.print = false;
			nestAgent.print = false;
			partsAgent.print = false;
		}
	}

	/** Methods used by HandleAManager, which has a pointer to the server*/
	/**
	 * @brief decreases num clients
	 */
	public void decrementNumClients() {
		numClients--;
	}
	/**
	 * @return numClients
	 */
	public int getNumClients() {
		return numClients;
	}

	/**
	 * @brief method to help debug
	 */
	public void debug() {
		try {
			for (int i = 0; i != 100; i++) {
				Thread.sleep(3000);
				hac.debugMessage();
			}
		} catch (InterruptedException ex) {
			Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
		}
	}    	

	public void setCameraAgentClient(HandleAManager in) {
		this.cameraAgent.setClient(in);
	}
	public void setConveyerAgentClient(HandleAManager in) {
		this.conveyorAgent.setClient(in);
	}
	public void setKitRobotAgentClient(HandleAManager in) {
		this.kitRobotAgent.setClient(in);
	}
	public void setGantryAgentClient(HandleAManager in) {
		this.gantryAgent.setClient(in);
	}
	public void setPartsAgentClient(HandleAManager in) {
		this.partsAgent.setClient(in);
	}

	// here...
	public LMServerMain getServerLM() {
		return this.serverLM;
	}

}
