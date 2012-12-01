
package factory.general;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
 * @author David Zhang, YiWei Roy Zheng, Alex Young
 */
public class Server {
    /**
     * Instance fields
     */
    public static final Integer PORT_NUMBER = 31417;
    public static final String HOST_NAME = "localhost";
    private static boolean SHOULD_DEBUG = false;
    private Printer p = new Printer();
    private int numClients; // accessible by Server and HandleAManager


    private FactoryState fstate;

    /** Agents */

    // Fields just for "AgentMain" stuff (Agent preparation)
    // If true, print statements for this 201 person are on
    private static final boolean PATRICK = true;
    private static final boolean KEVIN = true;
    private static final boolean ALEX = true;


    private static final int FEEDER = 4;
    private static final int LANE = 8;

    // Fields for agent setup
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

    private HandleAManager kitmanagerclient;///<connection to the kit manager
    private HandleAManager fpmclient;///<connection to the fpm

    public static void main(String[] args) {
        Server server = new Server(PORT_NUMBER);
        if (SHOULD_DEBUG) {
            server.debug();
        }
    }

    public void playMahMusicLols() {
    	TehMusix t = new TehMusix("bg.wav");
    	(new Thread(t)).start();
    }

    /**
     * @brief Instances Server, prompting the user for a port number and
     * beginning the loop that checks for clients.
     * @param portNumber - the port number to create the server on.
     */
    public Server(int portNumber) {
	this.playMahMusicLols();
        this.fstate = new FactoryState();
        initializeManagers(); // Something by Dongyoung

//		prepareAllAgents(); // Prepare all agents; based on AgentMain.java; commented out by Dongyoung to test animation; included after connections accepted by server
        // should happen AFTER initializeManagers(), according to Dongyoung

        numClients = 0; // Initialize num clients is 0
        start(portNumber); // Start listening for clients and making new HandleAManager instances
    }

    public FactoryState getFactoryState() {
        return this.fstate;
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
        prepareAllAgents(); // Prepare all agents; based on AgentMain.java      // For Testing By Dongyoung

//	    for (int i=0 ; i<2 ; i++){ // For Testing By Dongyoung, if want to need communicate n managers, change into for(int i=0 ; i<n ; i++)
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
   
    private void initializeManagers() { //by Dongyoung
        serverLM = new LMServerMain();
        new Thread(serverLM).start();
    }

    // Start Server-side Program(Lane Manager)

    /*********** Agent Preparation Code **********/
    /**
     * @brief prepares all agents; called when server constructor begins
     */
    private void prepareAllAgents() {
        declareAgents();
        turnOffAgentPrintStatements();
        connectAgentsAndManagers();
        startAgentThreads();
//            startInteractionSequence(); // this should be commented out in our final product
    }

    private void declareAgents() {
        /*========== Declare all agents and etc. ==========*/

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
            laneAgents[i] = new LaneAgent("Lane " + i,i);
        }
        
        // By Dongyoung
        serverLM.setNestAgents(nestAgent);
        serverLM.setFeederAgents(feederAgents);
    }

    private void connectAgentsAndManagers() {
        /*========== Pass proper agents to everyone ==========*/
        // Alex
        kitRobotAgent.setAll(cameraAgent, conveyorAgent, partsAgent);

        cameraAgent.setAll(kitRobotAgent, nestAgent);
        conveyorAgent.setKitRobot(kitRobotAgent);

        // Patrick
        partsAgent.setCamera(cameraAgent);

        partsAgent.setKitRobot(kitRobotAgent);
        partsAgent.setNestInterface(nestAgent);
        nestAgent.setCamera(cameraAgent);
        nestAgent.setPartsAgent(partsAgent);
        for (int i = 0; i < 8; i++) {
            nestAgent.getNest(i).setLane(laneAgents[i]);
        }

        // Kevin
        for (int i = 0, j = 0; i < FEEDER; i++, j++) {
            feederAgents[i].setGantry(gantryAgent);
            feederAgents[i].setRightLane(laneAgents[j]);
            feederAgents[i].setLeftLane(laneAgents[++j]);
            gantryAgent.setFeeder(feederAgents[i], i);
        }

        for (int i = 0; i < LANE; i += 2) {
            laneAgents[i].setFeeder(feederAgents[i / 2]);
            laneAgents[i].setNest(nestAgent);
            laneAgents[i + 1].setFeeder(feederAgents[i / 2]);
            laneAgents[i + 1].setNest(nestAgent);
        }
        for (int i = 0; i < LANE; i++) {
            if (i < FEEDER) {
                feederAgents[i].setServer(serverLM);
            }
            gantryAgent.setServer(serverLM);
            cameraAgent.setServer(serverLM);
            nestAgent.setServer(serverLM);
        }
    }

    private void startAgentThreads() {
        /*========== Start all of the threads ==========*/

        // Alex
        cameraAgent.startThread();
        conveyorAgent.startThread();
        kitRobotAgent.startThread();

        // Patrick
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

    /**
     * @brief This is only a test for 201 to debug animation and agent interaction.
     */
    @SuppressWarnings("unused")
    public void startInteractionSequence() {
        // Get kit from somewhere
        // *

        // THIS IS JUST EXAMPLE STUFF THAT 201 WAS DOING TO TEST
        Kit kit = new Kit("Test Kit","dsc"); // This is required for...
        for (int i = 0; i < 8; i++) {

//                kit.addPart(this.fstate.getPartCheat().clone());
            //kit.addPart(new Part("Part " + i, "p1")); // This is a kit that has actual parts...

            kit.addPart(new Part("lol","k","fame")); //testing- Kevin
        }
        ArrayList<Kit> kits = new ArrayList<Kit>();
        for(int i=0; i<9; i++)
            kits.add(kit);
        //kits.add(kit);
        //kits.add(kit);
        kits.get(0).debug();
        // TODO: *Put this wherever the FPM sends the signal to create (generate) kits
        conveyorAgent.msgGenerateKit(10); // * This generates 10 new kits, among other things if you pass string... *

        // Officially start the agent interaction sequence!
        partsAgent.msgHereIsKit(kits);
    }

        private void turnOffAgentPrintStatements() {
            if (PATRICK) {
                nestAgent.print = false;
                partsAgent.print = false;
            }
            if (KEVIN) {
                for (int i = 0; i < LANE; i++) {
                    if (i < FEEDER) {
                        feederAgents[i].print = false;
                    }
                    laneAgents[i].print = false;
                }
                gantryAgent.print = true;
            }
            if (ALEX) {
                kitRobotAgent.print = false;
                conveyorAgent.print = false;
                cameraAgent.print = false;
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

    public KitRobotAgent getKitRobotAgent() {
        return kitRobotAgent;
    }
    public GantryAgent getGantry(){
        return gantryAgent;
    }
    public PartsAgent getPartsAgent() {
        return this.partsAgent;
    }

    public LMServerMain getServerLM() { // Dongyoung's lane manager server...
        return this.serverLM;
    }

    public ConveyorAgent getConveyorAgent() {
        return this.conveyorAgent;
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


    /**
     * @brief These methods are called in HandleAManager when a client (manager) connects.
     * At that point, a message to IDENTIFY the client occurs. This is how agents get their client(s).
     */
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

    public void setFPMClient(HandleAManager in) {
        this.fpmclient = in;
    }

    public HandleAManager getFPMClient() {
        return this.fpmclient;
    }

    public void setKitManagerClient(HandleAManager in) {
        this.kitmanagerclient = in;
    }

    public HandleAManager getKitManagerClient() {
        return this.kitmanagerclient;
    }

    public void setFactoryProductionManagerToAll(HandleAManager in) {
        this.fpmclient = in;
        partsAgent.setFactoryProductionManagerClient(in);
        kitRobotAgent.setFactoryProductionManagerClient(in);
        cameraAgent.setFactoryProductionManagerClient(in);
        conveyorAgent.setFactoryProductionManagerClient(in);
        for (int i = 0; i != 4; i++)
            feederAgents[i].setFactoryProductionManagerClient(in);
        gantryAgent.setFactoryProductionManagerClient(in);
        for (int i = 0; i != 8; i++)
            laneAgents[i].setFactoryProductionManagerClient(in);
    }

}
