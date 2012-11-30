package factory.general;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

/**
 * The Manager plays a pivotal role in server interaction by standardizing the
 * methods used to send up data to the Server (and eventually
 * FactoryProductionManager's simulation GUI).
 *
 * @brief used to standardize methods for all managers
 * @author David Zhang, YiWei Roy Zheng
 * @version 0.1
 */
public class Manager extends JFrame {

    public static final boolean VERBOSE = true;
    /**
     * Instance fields
     */
    // Connection fields
    protected ManagerConnection mcon;
    
    // People who code managers: change DEBUG to true if you just want quick debugging / don't want to connect to the server
    protected static final boolean DEBUG = false;
    private static final String DEBUG_NOTIFICATION = "Manager is in debug mode.";
    
    // Other fields
    public static Printer p = new Printer();

    /**
     * @brief main method to test Manager interaction with Server
     */
    public static void main(String[] args) {
        // EXAMPLE INTERACTION

        Manager manager = new Manager();
        
        // Test a server interaction
        manager.sendToServer(Message.TEST_SERVER);
        System.out.println("I'm here and about to exit");
        for (int i = 0; i != 100; i++) {
            System.out.println("I'm doing some random stuff");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Manager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // When a manager is about to exit, tell the server!
        manager.sendToServer(Message.CLIENT_EXITED);
    }

    /**
     * @brief important constructor that each manager calls
     * Java automatically makes subclasses the super class's default constructor,
     * so this is run at the beginning of the constructor for each manager.
     */
    public Manager() {
    	if (!DEBUG) {
	        /** Set actions on exiting the entire application */
	        // Force clients to tell the server that the client is exiting when the window closes
	        addWindowListener(new java.awt.event.WindowAdapter() {
	            @Override
	            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	                sendToServer(Message.CLIENT_EXITED);
	            }
	        });
	        
	        /** On instantiation of a manager, connect to the server */
	        this.mcon = new ManagerConnection(this);
	        this.mcon.connect();
	        (new Thread(this.mcon)).start(); // create new thread with this connection
    	}
    }

    /**
     * @brief send a message to the server
     * @param msg - the message to send. Append ":"+whateverInfoYouWant to send
     * specific parameters.
     */
    public void sendToServer(String msg) {
        if (DEBUG) {
        	System.out.println(DEBUG_NOTIFICATION);
        } else {
        	try {
        		this.mcon.out(msg);
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        }
    }

    /**
     * @brief processes a message. override this for specific functionality. always call super.processMessage() before
     * @param msg - the message to process. Use an if-statement to go through
     * the possibilities.
     */
    public void processMessage(String msg) {
        if(Manager.VERBOSE)
        {
            System.out.println("Received message: " + msg);
        }
    	if (DEBUG) {
    		System.out.println(DEBUG_NOTIFICATION);
    	} else { 
	        if (msg == null) {
	//            System.out.println("CRITICAL ERROR: MANAGER HAS RECEIVED A NULL MESSAGE FROM THE SERVER!");
	            return;
	        }
	        // Decide action based on message from server
	        if (msg.contains(Message.TEST_CLIENT)) {
	            System.out.println("Client test passed.");
	        }
	        // Example:
	//		else if (msg.contains(Message.SELECTED_PLAYER_OK)) {
	//			String answer = grabParameter(msg); 
	//			p.println("Answer from server: "+answer);
	//			if (answer.equals("true")) {
	//				// If the chosen player is OK
	//				lblPlayerName.setText(existingPlayerPanels.get(chosenPlayerIndex).getName());
	//	            JOptionPane.showMessageDialog(this, "Player selected", "Notification", JOptionPane.PLAIN_MESSAGE);
	//			} else {
	//	            // If not OK
	//	            chosenPlayerIndex = 0; // reset. 0 is default.
	//	            JOptionPane.showMessageDialog(this, "Player currently in use by other user", "Notification", JOptionPane.PLAIN_MESSAGE);
	//			}
	//		}
    	}
    }

    /**
     * @brief standard way to grab parameter data via protocol Use this method
     * in an if-statement in processMessage.
     * @param msg - the message to grab a parameter from.
     * @return the parameter from the received message
     */
    public String grabParameter(String msg) {
    	if (DEBUG) {
    		System.out.println(DEBUG_NOTIFICATION);
    	}
        return msg.substring(msg.indexOf(":") + 1);
    }
}