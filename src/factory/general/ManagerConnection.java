package factory.general;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * A standard issue to all classes that extend Manager. All server interactions are done by this class. Runs on its own thread so nonblocking.
 * @brief connection to server from manager
 * @author YiWei Roy Zheng, David Zhang
 * @version 0.1
 */
public class ManagerConnection implements Runnable {

    private Manager mgr;///<reference to parent
    private PrintWriter out;///<data out
    private BufferedReader in;///<data in
    private Socket socket;///<socket connection
    private static final Printer p = new Printer();

    public ManagerConnection(Manager ref) {
        p.println("ManagerConnection created");
        socket = null;
        out = null;
        in = null;
        this.mgr = ref;
        if (ref == null) {
            System.out.println("ManagerConnection initialized with a null Manager object. This is bad. Fix it!");
        }
    }

    /**
     * @brief attempts to connect to the server
     */
    public void connect() {
        p.println("host name: " + Server.HOST_NAME);
        p.println("port num: " + Server.PORT_NUMBER);

        System.out.println("Connecting...");
        socket = null;
        out = null;
        in = null;

        try {
            socket = new Socket(Server.HOST_NAME, Server.PORT_NUMBER);
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
     * @brief continually listens to the server
     * @param msg - the message to send. Append ":"+whateverInfoYouWant to send
     * specific parameters.
     */
    public void run() {
        while (true) {
            this.listenToServer();
        }
    }

    /**
     * @brief send a message to the server
     * @param msg - the message to send. Append ":"+whateverInfoYouWant to send
     * specific parameters.
     */
    public void out(String o) {
        if(o == null)
        {
            System.out.println("CRITICAL ERROR: ATTEMPTING TO SEND A NULL STRING FROM MANAGER");
            return;
        }
        this.out.println(o);
    }

    /**
     * @brief listen for a message from the server
     */
    public void listenToServer() {
        p.println("Listening to the server (ManagerConnection)...");
        try {
            String msg = in.readLine();
            this.mgr.processMessage(msg);
        } catch (NullPointerException e) {
        	System.out.println("A client has exited prematurely while other clients were still connected.");
        	System.out.println("Shutting down.");
        	System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}