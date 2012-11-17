package factory.factory200.laneManager.ClientSide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class LMTimerThread implements Runnable{
	
	Timer timer;
	LMSignalFromServerVerification serverVerification;
	
	public LMTimerThread(LMSignalFromServerVerification serverVerification){
		this.serverVerification = serverVerification;
		timer = new Timer(30, new TimerAction());
	}
	
	public void run(){}
	
	public void timerStart(){
		timer.start();
	}
	
	public void timerStop(){
		timer.stop();
	}
	
	class TimerAction implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			serverVerification.timerAction();
		}
	}
}
