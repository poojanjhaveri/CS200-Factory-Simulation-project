package factory.factory200.laneManager.ServerSide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

class LMThreadTimer implements Runnable{
	
	private LMServerMain serverMain;
	private LMServer server;
	private LMSendPartSignal sendPartSignal;
	private Timer timer;
	private int feedingTiming;

	public LMThreadTimer(LMServer server, LMServerMain serverMain){
		this.server = server;
		this.serverMain = serverMain;
		sendPartSignal = new LMSendPartSignal(server, serverMain);
		timer = new Timer(30, new ServerTimer());
		timer.start();
	}
		
	public void run(){}

	public class ServerTimer implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			server.getClientHandler().sendToClient("&Timer&");
			serverMain.getPartData().shakePartsFree();
			if(++feedingTiming == 60){
				sendPartSignal.orderFeeding();
				sendPartSignal.feederPartLowSensor();
				feedingTiming = 0;
			}
		}
	}
}