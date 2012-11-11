package factory.factory200.laneManager.ClientSide;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class LMApplication extends JFrame{
	
	private LMGraphicsPanel panelGraphics;	///< Instance of class 'LaneManagerGraphicsPanel'
	private LMGUIPanel panelGUI;	///< Instance of class 'LaneManagerGUIPanel'
	private LMClient client;
	
	public LMApplication(){
		setTitle("Lane Manager");
		setSize(1000,700);
		setLocation(0,0);
		setVisible(true);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		setResizable(false);

		panelGraphics = new LMGraphicsPanel(this);
		panelGUI = new LMGUIPanel();
		client = new LMClient(this);
		
		add( panelGraphics );
		add( panelGUI );
		
		panelGUI.updateUI();
		panelGraphics.updateUI();
	}
	
	public LMGraphicsPanel getGraphicsPanel(){
		return panelGraphics;
	}

	public LMGUIPanel getGUIPanel(){
		return panelGUI;
	}

	public LMClient getClient(){
		return client;
	}
}