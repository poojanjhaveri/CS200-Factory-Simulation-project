package factory.factory200.laneManager.ServerSide;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 * @brief Timer in Server Side
 * @author Dongyoung Jung
 */
class LMThreadTimer implements Runnable{

	private LMServerMain serverMain;
	private LMSendPartSignal sendPartSignal;
	private Timer timer;
	private int feedingTiming;

	public LMThreadTimer(LMServerMain serverMain){
		this.serverMain = serverMain;
		sendPartSignal = new LMSendPartSignal(serverMain);
		timer = new Timer(20, new ServerTimer());	
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
			if(++feedingTiming == 50){
				sendPartSignal.orderFeeding();
				feedingTiming = 0;
			}
		}
	}
}