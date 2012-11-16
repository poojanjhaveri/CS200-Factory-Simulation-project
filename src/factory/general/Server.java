package factory.general;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import factory.factory201.feederManagement.FeederAgent;
import factory.factory201.feederManagement.GantryAgent;
import factory.factory201.feederManagement.LaneAgent;
import factory.factory201.kitManagement.CameraAgent;
import factory.factory201.kitManagement.ConveyorAgent;
import factory.factory201.kitManagement.KitRobotAgent;
import factory.factory201.partsManagement.NestAgent;
import factory.factory201.partsManagement.PartsAgent;
import factory.factory200.laneManager.ServerSide.*;

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
    
    // Kevin's
    private FeederAgent feederAgent;
    private GantryAgent gantryAgent;
    private LaneAgent laneAgent;
    // Alex's
    private KitRobotAgent kitRobotAgent;
    private ConveyorAgent conveyorAgent;
    private CameraAgent cameraAgent;
    // Patrick's
    private NestAgent nestAgent;
    private PartsAgent partsAgent;
    // Dongyoung's
    private LMServerMain serverLM;
    private Thread threadLM;
    // Connection fields
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

        // Dongyoung's
//		serverLM = new LMServerMain(this);
//    	threadLM = new Thread(serverLM);
//    	threadLM.start();

        // TODO: Instantiate agents
//    	feederAgent = new FeederAgent();
//        gantryAgent = new GantryAgent();
//        laneAgent = new LaneAgent();

        // Alex's
//        kitRobotAgent = new KitRobotAgent();
//        conveyorAgent = new ConveyorAgent();
//        cameraAgent = new CameraAgent();

        // Patrick's
//        nestAgent = new NestAgent();
//        partsAgent = new PartsAgent();

//        this.fstate = new FactoryState(); moved to HandleAManager
        
    	numClients = 0; // initial num clients is 0
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
    
    public void decrementNumClients() {
    	numClients--;
    }
    
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
}
