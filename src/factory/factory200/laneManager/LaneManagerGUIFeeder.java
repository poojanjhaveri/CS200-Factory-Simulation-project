package factory.factory200.laneManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * There are 4 feeders.
 * 
 * @brief Composition For Feeder Tab
 * @author Dongyoung Jung
 */
public class LaneManagerGUIFeeder extends JPanel{

	private LaneManagerGUIFeederPanel feederPanel;
	private ArrayList<LaneManagerGUIFeederPanel> feederPanels = new ArrayList<LaneManagerGUIFeederPanel>();
	private JTabbedPane tab  = new JTabbedPane();
	
	/**
	 * @brief Constructor
	 * @param feederCount : Quantity of feeders
	 */
	public LaneManagerGUIFeeder(final int feederCount){
		setPreferredSize(new Dimension(300,640));
		setLayout(new BorderLayout());
		
		// Add feederPanel to 'feederPanels' ArrayList
		for(int i=0 ; i<feederCount ; i++){
			feederPanel = new LaneManagerGUIFeederPanel();
			feederPanels.add(feederPanel);
			tab.add("Feeder"+(i+1),feederPanel);			
		}
		
		add(tab, BorderLayout.CENTER);
	}
	
	/**
	 * @brief Getter
	 * @param feederNum : Feeder number
	 * @return Instance of class 'LaneManagerGUIFeederPanel'
	 */
	public LaneManagerGUIFeederPanel getGUIFeederArray(int feederNum){
		return feederPanels.get(feederNum);
	}
}