package factory.factory200.laneManager.ClientSide;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * @brief Nest GUI
 * @author Dongyoung Jung
 */
public class LMGUINest extends JPanel{
	
	private LMGUINestPanel nestPanel;
	private ArrayList<LMGUINestPanel> nestPanels = new ArrayList<LMGUINestPanel>();
	private JTabbedPane tab;

	public LMGUINest(){
		setPreferredSize(new Dimension(300,640));
		setLayout(new GridLayout(2,1));
		
		// Add nestPanel to 'nestPanels' ArrayList ( top 4 panels )
		for(int i=0 ; i<8 ; i++){
			nestPanel = new LMGUINestPanel();
			nestPanels.add(nestPanel);
			tab = new JTabbedPane();
			tab.add("Nest"+(i+1), nestPanel);
			add(tab);
		}	
	}

	public LMGUINestPanel getGUINestArray(int nestNum){
		return nestPanels.get(nestNum);
	}
}
