package factory.factory200.laneManager.ClientSide;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * @brief Lane GUI
 * @author Dongyoung Jung
 */
public class LMGUILane extends JPanel{
	
	private LMGUILanePanel lanePanel;
	private ArrayList<LMGUILanePanel> lanePanels = new ArrayList<LMGUILanePanel>();
	private JTabbedPane tab1  = new JTabbedPane();
	private JTabbedPane tab2 = new JTabbedPane();

	public LMGUILane(final int laneCount){
		setPreferredSize(new Dimension(300,640));
		setLayout(new GridLayout(2,1));
		
		// Add lanePanel to 'lanePanels' ArrayList ( top 4 panels )
		for(int i=0 ; i<laneCount/2 ; i++){
			lanePanel = new LMGUILanePanel();
			lanePanels.add(lanePanel);
			tab1.add("Lane" + (i+1),lanePanel);
		}
		add(tab1);
		
		// Add lanePanel to 'lanePanels' ArrayList ( bottom 4 panels )
		for(int i=4 ; i<laneCount ; i++){
			lanePanel = new LMGUILanePanel();
			lanePanels.add(lanePanel);
			tab2.add("Lane" + (i+1),lanePanel);
		}
		add(tab2);
	}

	public LMGUILanePanel getGUILaneArray(int laneNum){
		return lanePanels.get(laneNum);
	}
}