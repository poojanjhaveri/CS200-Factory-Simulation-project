package factory.general;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @brief handles each new thread for a client.
 * Creates Socket, PrintWriter, etc.; has key methods for sending messages back and forth
 */
public class HandleAManager implements Runnable {

    private volatile boolean running = true; // volatile = var modified by different threads
    // Prepare connection and communication variables
    private Socket mySocket;
    private PrintWriter pw; // To speak to a client
    private BufferedReader br; // To listen to a client
    private Printer p = new Printer();
    private FactoryState fstate;
    private Server server;
    /**
     * Used to identify a HandleAManager's connection.
     * 0 - GantryRobotManager
     * 1 - KitAssemblyManager
     * 2 - LaneManager
     */
    Integer id;

    public HandleAManager(Socket s, Server aServer) {
        server = aServer;
        mySocket = s;
        id = -1;
        this.fstate = new FactoryState();
    }

    public void debugMessage() {
        System.out.println("Sending test message to client...");
        pw.println(Message.TEST_CLIENT);
    }
    /*DEPRECATED SEE SENDMESSAGE
    public void sendToClient(String msg) {
        pw.println(msg);
    }
    */
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
        if (msg == null) {
            System.out.println("ERROR: ATTEMPTING TO SEND A NULL MESSAGE FROM THE SERVER!");
            return;
        }
        this.pw.println(msg);
    }

    /**
     * @brief Processes a given message and executes the proper method from
     * an agent.
     * @param msg - the String message from a client
     */
    private void processMessage(String msg) {
        if (msg == null) {
            p.println("NULL MESSAGE RECEIVED ON THE SERVER.");
            return;
        }
        // Decide action based on message from client
        if (msg.contains(Message.TEST_SERVER)) {
            System.out.println("Server test passed. Testing client...");
            this.sendMessage(Message.TEST_CLIENT);
        } else if (msg.contains(Message.CLIENT_EXITED)) { // This is how we exit the server
            stopThread();
            this.server.decrementNumClients();
            if (server.getNumClients() == 0) {
                System.out.println("Number of clients is 0; exiting Server");
                System.exit(0);
            }
        } else if(msg.contains(Message.IDENTIFY_LANEMANAGER)) {
        	p.println("SERVER HAS IDENTIFIED A LANEMANAGER");
        	this.id = 2;
//        	this.server.getServerLM().setClient(this); // NEED THIS, DONGYOUNG
    	} else if (msg.contains(Message.IDENTIFY_GANTRYROBOTMANAGER)) {
            p.println("SERVER HAS IDENTIFIED A GANTRYROBOTMANAGER");
            this.id = 0;
            this.server.setGantryAgentClient(this); // make sure client is initialized - SOLVE THIS BY INSTANTIATING AGENTS ON SERVER ALREADY
        } else if (msg.contains(Message.IDENTIFY_KITASSEMBLYMANAGER)) {
            p.println("SERVER HAS IDENTIFIED A KITASSEMBLYMANAGER");
            this.id = 1;
            this.server.setKitRobotAgentClient(this);
            this.server.setCameraAgentClient(this);
            this.server.setConveyerAgentClient(this);
            this.server.setPartsAgentClient(this);
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