package factory.factory200.factoryProductionManager;

//@author Dongyoung Jung

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class TimerThread implements Runnable{
	
	Timer timer;
	GraphicsPanel gfx;
	
	public TimerThread(GraphicsPanel gfx){
		this.gfx = gfx;
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
			gfx.timerAction();
		}
	}
}
