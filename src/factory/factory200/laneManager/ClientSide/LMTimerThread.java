package factory.factory200.laneManager.ClientSide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import factory.general.Global;

/**
 * @brief Timer in ServerSide(Lane Manager)
 * @author Dongyoung Jung
 */
public class LMTimerThread implements Runnable{
	
	Timer timer;
	LMSignalFromServerVerification serverVerification;
	
	public LMTimerThread(LMSignalFromServerVerification serverVerification){
		this.serverVerification = serverVerification;
		timer = new Timer(Global.STANDARD_TIMER_SPEED, new TimerAction());
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
