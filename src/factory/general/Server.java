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
    private int numClients;
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
    private FactoryState fstate;
    private ServerSocket ss = null;
    private Socket s = null;
    private HandleAManager hac;

    // needed to handle multiple clients 
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

        this.fstate = new FactoryState();
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
                this.hac = new HandleAManager(s);
                new Thread(hac).start(); // Create the thread
            } catch (Exception e) {
                System.out.println("got an exception" + e.getMessage());
            }
            System.out.println("A client has connected");
        }
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
     * @brief Internal class that handles each new thread for a client. Creates
     * Socket, PrintWriter, etc.
     */
    class HandleAManager implements Runnable {

        private volatile boolean running = true; // volatile = var modified by different threads
        // Prepare connection and communication variables
        Socket mySocket;
        PrintWriter pw; // To speak to a client
        BufferedReader br; // To listen to a client
        /**
         * Used to identify a HandleAManager's connection.
         * 0 - GantryRobotManager
         */
        Integer id;

        public HandleAManager(Socket s) {
            mySocket = s;
            id = -1;
        }

        public void debugMessage() {
            System.out.println("Sending test message to client...");
            pw.println(Message.TEST_CLIENT);
        }

        public void sendToClient(String msg) {
            pw.println(msg);
        }

        // Key method of Runnable; when this method ends, the thread stops
        public void run() {
            try {
                // Create the 2 streams for talking to the client
                pw = new PrintWriter(mySocket.getOutputStream(), true);
                br = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }

            // This thread loops forever to receive a client message and echo it back
            while (running) {
                String message = null;
                try {
                    // Listen for interaction via protocol
                    message = br.readLine();
                    if (message == null) {
                        throw new Exception("A manager class must do manager.sendToServer(Message.CLIENT_EXITED);");
                    }

                    processMessage(message);
                    p.println("Processed message in client thread");
                } catch (Exception e) {
                    p.print("Client exited prematurely; shutting down");
                    System.exit(0);
                }
            }
        }

        public Integer getID() {
            return this.id;
        }
/**
         * @brief sends a message to the client
         * @param msg the message being sent
         */
        public void sendMessage(String msg) {
            this.pw.println(msg);
        }

        /**
         * @brief Processes a given message and executes the proper method from
         * an agent.
         * @param msg - the String message from a client
         */
        private void processMessage(String msg) {
            // Decide action based on message from client
            if (msg.contains(Message.TEST_SERVER)) {
                System.out.println("Server test passed. Testing client...");

                pw.println(Message.TEST_CLIENT);
            } else if (msg.contains(Message.CLIENT_EXITED)) {
                stopThread();
                numClients--;
                if (numClients == 0) {
                    System.out.println("Number of clients is 0; exiting Server");
                    System.exit(0);
                }
            } else if (msg.contains(Message.IDENTIFY_GANTRYROBOTMANAGER)) {
                this.id = 0;
            } else if (msg.contains(Message.PULL_KITS_LIST)) {
                //TODO THIS IS AD HOC NEED TO RETRIEVE MASTER BLUEPRINTKITS FROM FACTORY STATE
                pw.println(Message.PUSH_KITS_LIST + ":" + fstate.getBlueprintKits().serialize());
            } else if (msg.contains(Message.PULL_PARTS_LIST)) {
                //TODO THIS IS AD HOC, NEED TO RETRIEVE MASTER BLUEPRINTPARTS FROM FACTORY STATE
                pw.println(Message.PUSH_PARTS_LIST + ":" + fstate.getBlueprintParts().serialize());
            } else if (msg.contains(Message.DEFINE_NEW_PART)) {
                Part p = Part.deserialize(this.grabParameter(msg));
                fstate.getBlueprintParts().add(p);
                fstate.getBlueprintParts().save();
                System.out.println("Defined new part: " + p.serialize());
            } else if (msg.contains(Message.DEFINE_NEW_KIT)) {
                Kit k = Kit.deserialize(this.grabParameter(msg));
                fstate.getBlueprintKits().add(k);
                fstate.getBlueprintKits().save();
                System.out.println("Defined new kit:" + k.serialize());
            } else if (msg.contains(Message.UNDEFINE_PART)) {
                fstate.removePartById(Integer.parseInt(this.grabParameter(msg)));
            } else if (msg.contains(Message.UNDEFINE_KIT)) {
                fstate.removeKitById(Integer.parseInt(this.grabParameter(msg)));
            }
        }

        /**
         * @brief Standard way to grab parameter data via protocol
         * @param msg - the message
         * @return message status string
         */
        private String grabParameter(String msg) {
            return msg.substring(msg.indexOf(":") + 1);
        }

        /**
         * @brief Stops the loop of the server, letting the server turn off.
         * This stops the entire program.
         */
        private void stopThread() {
            running = false;
        }
    }
}
