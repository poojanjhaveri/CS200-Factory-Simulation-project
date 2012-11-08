package factory.factory200.laneManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * This is the main GUI panel with one big tab.
 * Inside the tab, there are 4 small tabs.
 * Tab 1 : laneGUI from class 'LaneManagerGUILane'
 * Tab 2 : nestGUI from class 'LaneManagerGUILane'
 * Tab 3 : feederGUI from class 'LaneManagerGUILane'
 * Tab 4 : nonNormativeGUI from class 'LaneManagerGUILane'
 * 
 * @brief Control GUI Panel
 * @author Dongyoung Jung
 */
public class LaneManagerGUIPanel extends JPanel{
	
	private final int feederCount = 4;	///< Fixed quantity of feeder
	private final int laneCount = 8;	///< Fixed quantity of lane
	private final int nestCount = 8;	///< Fixed quantity of nest
	
	private LaneManagerApp app;
	private JTabbedPane tab  = new JTabbedPane();
	private LaneManagerGUILane laneGUI;
	private LaneManagerGUINest nestGUI;
	private LaneManagerGUIFeeder feederGUI;
	private LaneManagerGUINonNormativePanel nonNormativeGUI;
	
	/**
	 * @brief Constructor
	 * @param feederCount : Quantity of feeder ( Here, it is 4 )
	 * @param laneCount : Quantity of lane ( Here, it is 8 )
	 * @param nestCount : Quantity of nest ( Here, it is 8 )
	 * @param app : Instance of class 'LaneManagerApp'
	 */
	public LaneManagerGUIPanel(LaneManagerApp app){
		this.app = app;
		feederGUI = new LaneManagerGUIFeeder(feederCount);
		laneGUI = new LaneManagerGUILane(laneCount);
		nestGUI = new LaneManagerGUINest(nestCount);
		nonNormativeGUI = new LaneManagerGUINonNormativePanel();
		
		setPreferredSize(new Dimension(300,640));
		setLayout(new BorderLayout());
				
		tab.add("Feeder", feederGUI);
		tab.add("Lane", laneGUI);
		tab.add("Nest", nestGUI);
		tab.add("Non-normative", nonNormativeGUI);
		add(tab, BorderLayout.CENTER);		
	}
	
	/**
	 * @brief Getter
	 * @return Instance of class 'LaneManagerGUINest'
	 */
	public LaneManagerGUINest getGUINest(){
		return nestGUI;
	}
	
	/**
	 * @brief Getter
	 * @return Instance of class 'LaneManagerGUILane'
	 */
	public LaneManagerGUILane getGUILane(){
		return laneGUI;
	}
	
	/**
	 * @brief Getter
	 * @return Instance of class 'LaneManagerGUIFeeder'
	 */
	public LaneManagerGUIFeeder getGUIFeeder(){
		return feederGUI;
	}
}
