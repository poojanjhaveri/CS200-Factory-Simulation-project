package factory.factory200.laneManager.ClientSide;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * @brief GUI Panel
 * @author Dongyoung Jung
 */
public class LMGUIPanel extends JPanel{
	
	private LMApplication app;
	private JTabbedPane tab  = new JTabbedPane();
	private LMGUILane laneGUI;
	private LMGUINest nestGUI;
	private LMGUIFeeder feederGUI;

	public LMGUIPanel(LMApplication app){
		this.app = app;
		
		feederGUI = new LMGUIFeeder();
		laneGUI = new LMGUILane();
		nestGUI = new LMGUINest();
		
		setPreferredSize(new Dimension(300,650));
		setLayout(new BorderLayout());
				
		tab.add("Feeder", feederGUI);
		tab.add("Lane", laneGUI);
		tab.add("Nest", nestGUI);
		add(tab, BorderLayout.CENTER);		
	}
	
	public LMGUINest getGUINest(){
		return nestGUI;
	}
	
	public LMGUILane getGUILane(){
		return laneGUI;
	}
	
	public LMGUIFeeder getGUIFeeder(){
		return feederGUI;
	}
}
