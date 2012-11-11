package factory.general;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

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
//    private FeederAgent feederAgent;
//    private GantryAgent gantryAgent;
//    private LaneAgent laneAgent;
    // Connection fields
    private ServerSocket ss = null;
    private Socket s = null;
    private HandleAManager hac;
    
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

        public HandleAManager(Socket s) {
            mySocket = s;
        }
        
        public void debugMessage() {
            System.out.println("Sending test message to client...");
            pw.println(Message.TEST_CLIENT);
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
            }else if(msg.contains(Message.PULL_KITS_LIST))
		     {//TODO THIS IS AD HOC NEED TO RETRIEVE MASTER BLUEPRINTKITS FROM FACTORY STATE
        BlueprintKits bp = new BlueprintKits();
	Kit k = new Kit("kit1","im the nyan kit");
	Part p = new Part("part1","im a part dawg");
	p.setFilename("part.png");
	k.addPart(p);
	p = new Part("party2","i party a lot");
	p.setFilename("party.png");
	k.addPart(p);
        bp.add(k);
	pw.println(Message.PUSH_KITS_LIST+":"+bp.serialize());
		     }else if(msg.contains(Message.PULL_PARTS_LIST))
		     {
			 //TODO THIS IS AD HOC, NEED TO RETRIEVE MASTER BLUEPRINTPARTS FROM FACTORY STATE
			 Part p = new Part("part1","is a part");
        p.setFilename("part1.png");
        BlueprintParts bp = new BlueprintParts();
        bp.add(p);
        p = new Part("part2","is (not) a part");
        p.setFilename("part2.png");
        bp.add(p);
        p = new Part("alfalfa","heyo");
        p.setFilename("gogo.png");
        bp.add(p);
	pw.println(Message.PUSH_PARTS_LIST+":"+bp.serialize());
		     }
//			else if (msg.contains(Message.CHECK_SELECTED_PLAYER)) {
//				String index = grabParameter(msg); // Standard way to grab parameter data via protocol
//				if (playerIndices.contains(index))
//					pw.println(Message.SELECTED_PLAYER_OK + ":" + "false");
//				else {
//					playerIndices.add(index);
//					pw.println(Message.SELECTED_PLAYER_OK + ":" + "true");
//				}
//			}
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
