package factory.general;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

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

        // This thread loops while 'running' to receive a client message and echo it back
        while (running) {
            String message = null;
            try {
                // Listen for interaction via protocol
                message = br.readLine();
                if (message == null) {
                	System.out.println("Message from client is null. A manager class should exit properly (i.e., by closing with the X in the corner)");
                    throw new Exception();
                }
                processMessage(message);
                p.println("Processed message in client thread");
            } catch (NullPointerException e) {
            	System.out.println("Nullpointer from HandleAManager. Shutting down. The agents may not have been initialized.");
                e.printStackTrace();
                System.exit(0);
            } catch (Exception e) {
            	e.printStackTrace();
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
        }else if(msg.contains(Message.IDENTIFY_KITMANAGER)) {
        	System.out.println("SERVER FOUND A KIT MANAGER");
        	this.server.setKitManagerClient(this);
	    }
        else if (msg.contains(Message.IDENTIFY_FACTORYPRODUCTIONMANAGER)) {
 			p.println("SERVER HAS IDENTIFIED A FACTORYPRODUCTIONMANAGER");
 			this.id = 3;
            this.server.setFactoryProductionManagerToAll(this);
            this.server.getServerLM().setFPM(this);
        }
        else if(msg.contains(Message.IDENTIFY_LANEMANAGER)) {
        	p.println("SERVER HAS IDENTIFIED A LANEMANAGER");
        	this.id = 2;
        	this.server.getServerLM().setLM(this);        	
    	} else if (msg.contains(Message.IDENTIFY_GANTRYROBOTMANAGER)) {
            p.println("SERVER HAS IDENTIFIED A GANTRYROBOTMANAGER");
            this.id = 0;
            this.server.setGantryAgentClient(this); // make sure client is initialized - SOLVE THIS BY INSTANTIATING AGENTS ON SERVER ALREADY
            this.server.getServerLM().setGRM(this);
        } else if (msg.contains(Message.IDENTIFY_KITASSEMBLYMANAGER)) {
            p.println("SERVER HAS IDENTIFIED A KITASSEMBLYMANAGER");
            this.id = 1;
            this.server.setKitRobotAgentClient(this);
            this.server.setCameraAgentClient(this);
            this.server.setConveyerAgentClient(this);
            this.server.setPartsAgentClient(this);
            this.server.getServerLM().setKAM(this);
        } else if (msg.contains(Message.PULL_KITS_LIST)) {
            pw.println(Message.PUSH_KITS_LIST + ":" + this.server.getFactoryState().getBlueprintKits().serialize());
        } else if (msg.contains(Message.PULL_PARTS_LIST)) {
            pw.println(Message.PUSH_PARTS_LIST + ":" + this.server.getFactoryState().getBlueprintParts().serialize());
        } else if (msg.contains(Message.DEFINE_NEW_PART)) {
            Part p = Part.deserialize(this.grabParameter(msg));
            this.server.getFactoryState().getBlueprintParts().add(p);
            this.server.getFactoryState().getBlueprintParts().save();
            System.out.println("Defined new part: " + p.serialize());
	    if (this.server.getKitManagerClient() != null){
	    	this.server.getKitManagerClient().sendMessage(Message.PUSH_PARTS_LIST + ":" + this.server.getFactoryState().getBlueprintParts().serialize());
	    System.out.println("Pushed latest list to KitManager");
	    }else{
		System.out.println("Unable to push latest list to KitManager because it has not yet been connected.");
	    }
        } else if (msg.contains(Message.DEFINE_NEW_KIT)) {
            Kit k = Kit.deserialize(this.grabParameter(msg));
            this.server.getFactoryState().getBlueprintKits().add(k);
            this.server.getFactoryState().getBlueprintKits().save();
            System.out.println("Defined new kit:" + k.serialize());
	    if(this.server.getFPMClient() != null)
		{
		    this.server.getFPMClient().sendMessage(Message.PUSH_KITS_LIST + ":" + this.server.getFactoryState().getBlueprintKits().serialize());
		    System.out.println("Pushed latest kits to FPM");
		}else{
		System.out.println("Unable to push latest list to FactoryProductionManager because it has not yet been connected.");
	    }
        } else if (msg.contains(Message.UNDEFINE_PART)) {
            Integer id = Integer.parseInt(this.grabParameter(msg));
            System.out.println("Undefining part " + id);
            this.server.getFactoryState().removePartById(id);
        } else if (msg.contains(Message.UNDEFINE_KIT)) {
        	Integer id = Integer.parseInt(this.grabParameter(msg));
        	System.out.println("Undefining part " + id);
        	this.server.getFactoryState().removeKitById(id);
        }else if(msg.contains(Message.PUSH_PRODUCTION_QUEUE)) {
        	
        	// TODO: THIS IS TEMPORARY JUST FOR TESTING THE WHOLE THING INTEGRATED; CHANGE TO REMOVE startInteractionSequence
        	ArrayList<Kit> queue = new ArrayList<Kit>();
        	ArrayList<String> deserialized = Util.deserialize(this.grabParameter(msg));
        	/*for(int i = 0; i != deserialized.size(); i++) {
        		queue.add(Kit.deepClone(this.server.getFactoryState().getKitById(Integer.parseInt(deserialized.get(i)))));
        		System.out.println(deserialized.get(i));
        	}*/
                for(int i = 0; i != deserialized.size(); i++){
Kit single = this.server.getFactoryState().getKitById(Integer.parseInt(deserialized.get(i))).cloneSelf();
                
         queue.add(single);       
        }
        	//this.server.getConveyorAgent().generateKit(queue.size()); // * This generates 10 new kits, among other things if you pass string... *
        	//this.server.getPartsAgent().msgHereIsKit(queue);
        	this.server.startInteractionSequence();
System.out.println(msg);
        	System.out.println("BEGINNING PRODUCTION CYCLE WOOOOOOT (size "+queue.size() + ")");
                        	queue.get(0).debug();

        }
        else if( msg.contains( Message.PART_TO_NEST_FROM_LANE ) ){
	    	server.getServerLM().getVerify().verify(msg);
	    }
        else if( msg.contains( Message.PART_TAKE_BY_PARTROBOT ) ){
        	server.getServerLM().getVerify().verify(msg);
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