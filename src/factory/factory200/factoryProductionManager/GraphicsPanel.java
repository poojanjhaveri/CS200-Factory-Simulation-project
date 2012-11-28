package factory.factory200.factoryProductionManager;

//@author Dongyoung Jung

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import factory.factory200.factoryProductionManager.LaneManager.*;
import factory.factory200.factoryProductionManager.GantryRobotManager.*;
import factory.factory200.factoryProductionManager.KitsAssemblyManager.*;

public class GraphicsPanel extends JPanel{
	
	private GantryRobotManager gantryRobotManager = new GantryRobotManager();
	private KitAssemblyManager kitsAssemblyManager = new KitAssemblyManager(this);
	private LMApplication laneManager = new LMApplication();
//	private ImageIcon backgroundImage = new ImageIcon( GraphicsPanel.class.getResource("background.png") );
	private TimerThread timer = new TimerThread(this);
	
	public GraphicsPanel() {
		new Thread(timer).start();
		timer.timerStart();
	}

	public void paint(Graphics graphics) {
	//	backgroundImage.paintIcon(this, graphics, 0, 0);
		
		kitsAssemblyManager.paint(this, (Graphics2D) graphics);
		gantryRobotManager.paint(this, (Graphics2D) graphics);
		laneManager.paint(this, (Graphics2D) graphics);
	}
	
	public void timerAction(){
		laneManager.timerAction();
		kitsAssemblyManager.getGraph().timerAction();
		
		//add timer action for KAM! 
		gantryRobotManager.getGraph().timerAction();
		repaint();
	}
	
	public void verifyMessage(String msg){
		if (msg == null) {
			System.out.println("Something has gone terribly wrong");
		}
		laneManager.getServerVerify().verify(msg);
		this.kitsAssemblyManager.processMessage(msg);
		this.gantryRobotManager.processMessage(msg);
        // System.out.println("GFX GOT MESSAGE: " + msg);
	}
}
