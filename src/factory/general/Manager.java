package factory.general;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

import factory.general.Server;
import factory.general.Message;
import factory.general.Printer;

/**
 * The Manager plays a pivotal role in server interaction by standardizing the
 * methods used to send up data to the Server (and eventually
 * FactoryProductionManager simulation GUI).
 *
 * @brief used to standardize methods for all managers
 * @author David Zhang, YiWei Roy Zheng
 * @version 0.1
 */
public class Manager extends JFrame {

    /**
     * Instance fields
     */
    // Connection fields
    private ManagerConnection mcon;
    
    // Other fields
    public static Printer p = new Printer();

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

    public Manager() {
        // On instantiation of a major, connect to the server
        this.mcon = new ManagerConnection(this);
        this.mcon.connect();
        (new Thread(this.mcon)).start(); // create new thread with this connection
    }

    /**
     * @brief send a message to the server
     * @param msg - the message to send. Append ":"+whateverInfoYouWant to send
     * specific parameters.
     */
    public void sendToServer(String msg) {
        try {
            this.mcon.out(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief processes a message
     * @param msg - the message to process. Use an if-statement to go through
     * the possibilities.
     */
    public void processMessage(String msg) {
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

    /**
     * @brief standard way to grab parameter data via protocol Use this method
     * in an if-statement in processMessage.
     * @param msg - the message to grab a parameter from.
     * @return the parameter from the received message
     */
    public String grabParameter(String msg) {
        return msg.substring(msg.indexOf(":") + 1);
    }
}