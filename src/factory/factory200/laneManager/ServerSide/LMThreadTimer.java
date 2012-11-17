package factory.factory200.laneManager.ServerSide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import factory.general.Server;
import factory.general.*;

class LMThreadTimer implements Runnable{

	private Server server;
	private LMServerMain serverMain;
	private LMSendPartSignal sendPartSignal;
	private Timer timer;
	private int feedingTiming;
	private String signal = "&Timer&";

	public LMThreadTimer(Server server, LMServerMain serverMain){
		this.server = server;
		this.serverMain = serverMain;
		sendPartSignal = new LMSendPartSignal(server, serverMain);
		timer = new Timer(30, new ServerTimer());	
	}
		
	public void run(){}
	
	public void timerStart(){
		timer.start();
	}
	
	public void timerStop(){
		timer.stop();
	}

	public class ServerTimer implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			serverMain.getPartData().shakePartsFree();
			serverMain.getPartData().laneVibrationController();
			if(++feedingTiming == 50){
				sendPartSignal.orderFeeding();
				sendPartSignal.feederPartLowSensor();
				feedingTiming = 0;
			}
		}
	}
}