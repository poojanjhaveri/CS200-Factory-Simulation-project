package factory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import factory.feederManagement.FeederAgent;
import factory.feederManagement.GantryAgent;
import factory.feederManagement.LaneAgent;

/**
 * @brief This class is critical to the integration of GUI classes, agents, etc.
 * It uses a protocol established with the Message class. It contains instances
 * of agents and calls their methods appropriately based on received messages
 * from clients. So, this class runs a loop that constantly checks for new
 * clients and creates a new thread for each.
 * @author David Zhang
 *
 */
// Status: Just finished matching my serverclientpractice's Server
public class Server {

    /**
     * Instance fields
     */
    private Printer p = new Printer();
    private int numClients;
    private FeederAgent feederAgent;
    private GantryAgent gantryAgent;
    private LaneAgent laneAgent;
    // Agents
//	private FeederAgent feederAgent; 
    // Connection fields
    private ServerSocket ss = null;
    private Socket s = null;

    public static void main(String[] args) {
        int portNum = 31415;
        Scanner in = new Scanner(System.in);
        System.out.print("Enter port number (default: 31415): ");
        try {
            String nextLine = in.nextLine();
            portNum = Integer.parseInt(nextLine);
        } catch (Exception e) {
            System.out.println("Using default port number");
            portNum = 31415;
        }

        Server server = new Server(portNum);
    }

    /**
     * @brief Instances Server, prompting the user for a port number and
     * beginning the loop that checks for clients.
     * @param portNumber - the port number to create the server on.
     */
    public Server(int portNumber) {
        numClients = 0; // initial num clients is 0
        p.println("Port number: " + portNumber);
        try {
            ss = new ServerSocket(portNumber);
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
                HandleAManager hac = new HandleAManager(s);
                new Thread(hac).start(); // Create the thread
            } catch (Exception e) {
                System.out.println("got an exception" + e.getMessage());
            }
            System.out.println("A client has connected");
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
                try {
                    // Listen for interaction via protocol
                    String message = br.readLine();
                    processMessage(message);
                    p.println("Processed message in client thread");
                } catch (Exception e) {
                    p.print("Caught: ");
                    e.printStackTrace();
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
                    System.out.println("Number of clients is 0");
                }
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