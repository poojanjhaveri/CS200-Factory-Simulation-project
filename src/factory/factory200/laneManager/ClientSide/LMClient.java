package factory.factory200.laneManager.ClientSide;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * This class sets up the server. In V0, there is no server requirements.
 * However instead, this creates instances of two classes like below.
 * class LaneManagerSetNetwork : Verifies signals from server
 * class LaneManagerSendSignalsToServer : Sends signals to server
 * 
 * @brief Server Setups ( In V0, it is a platform )
 *	@author Dongyoung Jung 
 */
public class LMClient{
	
	private Socket mySocket;
	private PrintWriter pw;
	private BufferedReader br;
	private LMApplication app;
	private LMSignalFromServerVerification verifyMessage;
	private HandleAClient hac;
	
	/**
	 * @brief Constructor
	 * @param app : Instance of class 'LaneManagerApp'
	 */
	public LMClient(LMApplication app){
		this.app = app;
		verifyMessage = new LMSignalFromServerVerification(app);
		connectToServer();
	}
	
	public void connectToServer(){
		try{
			mySocket = new Socket( "192.168.1.112", 36001 );
			hac = new HandleAClient( mySocket );
			new Thread(hac).start();
		}
		catch( Exception e ){
			e.printStackTrace();
			System.exit(0);
		}
	}

    class HandleAClient implements Runnable {
    	Socket mySocket;

    	public HandleAClient( Socket s ) {
    		mySocket = s;
    		
    		try {
        		pw = new PrintWriter( mySocket.getOutputStream(), true );
        		br = new BufferedReader( new InputStreamReader( mySocket.getInputStream() ) );
        	} 
        	catch (Exception e) {
    			e.printStackTrace();
    			System.exit(0);
        	}
    	}
    	
    	public void sendToFactory(String message){
    		pw.println(message);
    	}
    	
    	public void run() {
    		while( true ) {
    			try {
    				String order = br.readLine();
    				System.out.println("(CLIENT)Order from Server : " + order);
    				verifyMessage.verify(order);
    			} 
    			catch (Exception e) { e.printStackTrace(); 	System.exit(0); }
    		}
    	}
    }

	public LMApplication getApp(){
		return app;
	}
	
	public HandleAClient getThread(){
		return hac;
	}
}
