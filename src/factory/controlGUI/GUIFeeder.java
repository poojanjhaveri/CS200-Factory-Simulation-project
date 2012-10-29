import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
	@brief contains GUIFeederPanel
	   		   uses JTabbedPane
	@author Dongyoung Jung
*/
public class GUIFeeder extends JPanel{

	private GUIFeederPanel feederPanel;
	private ArrayList<GUIFeederPanel> feederPanels = new ArrayList<GUIFeederPanel>();
	private JTabbedPane tab  = new JTabbedPane();
	
	public GUIFeeder(final int feederCount){
		setPreferredSize(new Dimension(300,640));
		setLayout(new BorderLayout());
		
		// Add feederPanel to 'feederPanels' ArrayList
		for(int i=0 ; i<feederCount ; i++){
			feederPanel = new GUIFeederPanel();
			feederPanels.add(feederPanel);
			tab.add("Feeder"+(i+1),feederPanel);			
		}
		
		add(tab, BorderLayout.CENTER);
	}
	
	public GUIFeederPanel getGUIFeederArray(int feederNum){
		return feederPanels.get(feederNum);
	}
}