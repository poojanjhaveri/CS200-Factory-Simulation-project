package factory.factory200.factoryProductionManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import factory.factory200.factoryProductionManager.GantryRobotManager.*;
import factory.factory200.factoryProductionManager.KitsAssemblyManager.*;
import factory.factory200.factoryProductionManager.LaneManager.*;

public class GraphicsPanel extends JPanel{
	
	private GantryRobotManager gantryRobotManager = new GantryRobotManager();
	private KitsAssemblyManager kitsAssemblyManager = new KitsAssemblyManager();
	private LMApplication laneManager = new LMApplication();
	private Timer timer;
	private ImageIcon backgroundImage = new ImageIcon( GraphicsPanel.class.getResource("background.png") );
	
	public GraphicsPanel(){
		setBorder(new LineBorder( Color.red ));
		timer = new Timer(30, new ServerTimer());
		timer.start();
	}

	public void paint(Graphics graphics){
		backgroundImage.paintIcon(this, graphics, 0, 0);
		gantryRobotManager.paint(this, (Graphics2D)graphics);
		kitsAssemblyManager.paint(this, (Graphics2D)graphics);
		laneManager.paint(this, (Graphics2D)graphics);
	}
	
	public class ServerTimer implements ActionListener{
		public void actionPerformed(ActionEvent ae){
			repaint();
		}
	}
}
