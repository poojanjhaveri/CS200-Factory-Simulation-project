package factory.factory200.laneManager;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * There are 8 lanes, so two tabs with 4 lanes for each are generated here.
 * 
 * @brief Composition For Lane Tab
 * @author Dongyoung Jung
 */
public class LaneManagerGUILane extends JPanel{
	
	private LaneManagerGUILanePanel lanePanel;
	private ArrayList<LaneManagerGUILanePanel> lanePanels = new ArrayList<LaneManagerGUILanePanel>();
	private JTabbedPane tab1  = new JTabbedPane();
	private JTabbedPane tab2 = new JTabbedPane();
	
	/**
	 * Each 4 lanes make up one tab.
	 * 
	 * @brief Constructor
	 * @param nestCount : Quantity of lane
	 */
	public LaneManagerGUILane(final int laneCount){
		setPreferredSize(new Dimension(300,640));
		setLayout(new GridLayout(2,1));
		
		// Add lanePanel to 'lanePanels' ArrayList ( top 4 panels )
		for(int i=0 ; i<laneCount/2 ; i++){
			lanePanel = new LaneManagerGUILanePanel();
			lanePanels.add(lanePanel);
			tab1.add("Lane" + (i+1),lanePanel);
		}
		add(tab1);
		
		// Add lanePanel to 'lanePanels' ArrayList ( bottom 4 panels )
		for(int i=4 ; i<laneCount ; i++){
			lanePanel = new LaneManagerGUILanePanel();
			lanePanels.add(lanePanel);
			tab2.add("Lane" + (i+1),lanePanel);
		}
		add(tab2);
	}
	
	/**
	 * @brief Getter
	 * @param laneNum : lane number
	 * @return Instance of class 'LaneManagerGUILanePanel'
	 */
	public LaneManagerGUILanePanel getGUILaneArray(int laneNum){
		return lanePanels.get(laneNum);
	}
}