package factory.factory200.laneManager.ClientSide;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class LMGUIPanel extends JPanel{
	
	private final int feederCount = 4;	///< Fixed quantity of feeder
	private final int laneCount = 8;	///< Fixed quantity of lane
	private final int nestCount = 8;	///< Fixed quantity of nest
	
	private JTabbedPane tab  = new JTabbedPane();
	private LMGUILane laneGUI;
	private LMGUINest nestGUI;
	private LMGUIFeeder feederGUI;
	private LMGUINonNormativePanel nonNormativeGUI;

	public LMGUIPanel(){
		feederGUI = new LMGUIFeeder(feederCount);
		laneGUI = new LMGUILane(laneCount);
		nestGUI = new LMGUINest(nestCount);
		nonNormativeGUI = new LMGUINonNormativePanel();
		
		setPreferredSize(new Dimension(300,640));
		setLayout(new BorderLayout());
				
		tab.add("Feeder", feederGUI);
		tab.add("Lane", laneGUI);
		tab.add("Nest", nestGUI);
		tab.add("Non-normative", nonNormativeGUI);
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
