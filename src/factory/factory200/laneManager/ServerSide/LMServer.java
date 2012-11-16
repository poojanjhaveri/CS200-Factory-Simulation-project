package factory.factory200.laneManager.ServerSide;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class LMServer {
	
	ArrayList<HandleAClient> clients = new ArrayList<HandleAClient>();
	private LMServerMain serverMain;
	private Socket socket = null;
	private ServerSocket serverSocket = null;
	private HandleAClient hac;
	
    public LMServer(LMServerMain serverMain){
    	this.serverMain = serverMain;
    	
    	try {  serverSocket = new ServerSocket( 30006 ); }
    	catch( Exception e ) {  e.printStackTrace(); System.exit(0);  }
    	
   		for(int i=0 ; i<2 ; i++){
   			try {
	       		socket = serverSocket.accept();
	   			hac = new HandleAClient( socket );
	       		new Thread(hac).start();
	       		clients.add(hac);
   			}catch( Exception e ) { e.printStackTrace(); System.exit(0); }
   		}
	}
	//--------------------------------------------------------------------------------------------------------------------------------
    class HandleAClient implements Runnable {
    	
    	private Socket mySocket;
    	private PrintWriter pw;
    	private BufferedReader br;
    	private String message = "";
    	private LMSignalFromAnimationVerification signalFromAnimation;
    	
    	public HandleAClient( Socket s ) {
    		signalFromAnimation = new LMSignalFromAnimationVerification(serverMain);
    		mySocket = s;
    		
    		try {
    			pw = new PrintWriter( mySocket.getOutputStream(), true );
    			br = new BufferedReader( new InputStreamReader( mySocket.getInputStream() ) );
    		} 
    		catch (Exception e) { e.printStackTrace(); System.exit(0); }
    	}

    	public void run(){
    		while(true){
    			try{  message = br.readLine(); signalFromAnimation.verify(message); }
    			catch(Exception e){}
    		}
    	}
    	
    	public void sendToClient(String order){
    		pw.println(order);
    		pw.flush();
    	}
	}
    
    public HandleAClient getClientHandler(){
    	return hac;
    }
}