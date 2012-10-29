package factory.controlGUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import factory.Message;
import factory.Printer;

/**
 * The Manager plays a pivotal role in server interaction by standardizing the
 * methods used to send up data to the Server (and eventually FactoryProductionManager simulation GUI).
 * @brief used to standardize methods for all managers
 * @author YiWei Roy Zheng, David Zhang
 */
public class Manager extends JFrame {
    /** Instance fields */
	// Connection fields
    private String hostName;
    private int portNum;
    private PrintWriter out;
    private BufferedReader in;
    private Socket socket;
    
    // Other fields
    private Printer p;
	
    /**
     * @brief attempts to connect to the server 
     */
	public void connect() {
    	p.println("host name: "+hostName);
    	p.println("port num: "+portNum);
    	
		System.out.println("Connecting...");
		socket = null;
        out = null;
        in = null;

        try {
            socket = new Socket(hostName, portNum);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection.");
            System.exit(1);
        }

        System.out.println("Done connecting");
	}
    
	/**
	 * @brief send a message to the server
	 * @param msg - the message to send. Append ":"+whateverInfoYouWant to send specific parameters.
	 */
	public void sendToServer(String msg) {
		try {
			out.println(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @brief listen for a message from the server
	 */
	public void listenToServer() {
		p.println("Listening to the server");
		try {
			String msg = in.readLine();
			processMessage(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	/**
	 * @brief processes a message
	 * @param msg - the message to process. Use an if-statement to go through the possibilities.
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
	 * @brief standard way to grab parameter data via protocol
	 * Use this method in an if-statement in processMessage. 
	 * @param msg - the message to grab a parameter from.
	 * @return the parameter from the received message
	 */
	public String grabParameter(String msg) {
		return msg.substring(msg.indexOf(":")+1);
	}
    
}