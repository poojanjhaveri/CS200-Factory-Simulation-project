package factory.factory200.laneManager.ClientSide;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * @brief Feeder GUI
 * @author Dongyoung Jung
 */
public class LMGUIFeeder extends JPanel{

	private LMGUIFeederPanel feederPanel;
	private ArrayList<LMGUIFeederPanel> feederPanels = new ArrayList<LMGUIFeederPanel>();
	private JTabbedPane tab  = new JTabbedPane();

	public LMGUIFeeder(final int feederCount){
		setPreferredSize(new Dimension(300,640));
		setLayout(new BorderLayout());
		
		// Add feederPanel to 'feederPanels' ArrayList
		for(int i=0 ; i<feederCount ; i++){
			feederPanel = new LMGUIFeederPanel();
			feederPanels.add(feederPanel);
			tab.add("Feeder"+(i+1),feederPanel);			
		}
		
		add(tab, BorderLayout.CENTER);
	}

	public LMGUIFeederPanel getGUIFeederArray(int feederNum){
		return feederPanels.get(feederNum);
	}
}