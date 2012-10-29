import java.io.PrintWriter;

public class SendSignalsToServer {

	private PrintWriter pw;
	
	public SendSignalsToServer(PrintWriter pw){
		this.pw = pw;
	}
	
	public void sendToServer(String signal){
		pw.write(signal);
	}
}
