package factory.agentGUI;

import java.io.PrintWriter;

/**	
@brief Sends signals to server
	   Signals to server
		Non-normative scenario
		Part addition to nest from lane
@author Dongyoung Jung
*/
public class SendSignalsToServer {

	private PrintWriter pw;
	
	public SendSignalsToServer(PrintWriter pw){
		this.pw = pw;
	}
	
	public void sendToServer(String signal){
		pw.write(signal);
	}
}
