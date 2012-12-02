package factory.factory200.laneManager.ClientSide;

import java.awt.Dimension;
import java.awt.GridLayout;
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
	private JTabbedPane tab;

	public LMGUIFeeder(){
		setPreferredSize(new Dimension(300,640));
		setLayout(new GridLayout(2,2));
		
		for(int i=0 ; i<4 ; i++){
			feederPanel = new LMGUIFeederPanel();
			feederPanels.add(feederPanel);
			tab = new JTabbedPane();
			tab.add("Feeder"+(i+1),feederPanel);
			add(tab);
		}
	}

	public LMGUIFeederPanel getGUIFeederArray(int feederNum){
		return feederPanels.get(feederNum);
	}
}