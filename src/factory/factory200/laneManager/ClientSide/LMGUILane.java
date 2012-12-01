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
	private JTabbedPane tab;

	public LMGUILane(){
		setPreferredSize(new Dimension(300,640));
		setLayout(new GridLayout(2,1));
		
		// Add lanePanel to 'lanePanels' ArrayList ( top 4 panels )
		for(int i=0 ; i<8 ; i++){
			lanePanel = new LMGUILanePanel();
			lanePanels.add(lanePanel);
			tab = new JTabbedPane();
			tab.add("Lane"+(i+1), lanePanel);
			add(tab);
		}
	}

	public LMGUILanePanel getGUILaneArray(int laneNum){
		return lanePanels.get(laneNum);
	}
}