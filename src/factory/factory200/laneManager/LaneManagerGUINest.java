import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * There are 8 nests, so two tabs with 4 nests for each are generated here.
 * 
 * @brief Composition For Nest Tab
 * @author Dongyoung Jung
 */
public class LaneManagerGUINest extends JPanel{
	
	private LaneManagerGUINestPanel nestPanel;
	private ArrayList<LaneManagerGUINestPanel> nestPanels = new ArrayList<LaneManagerGUINestPanel>();
	private JTabbedPane tab1  = new JTabbedPane();
	private JTabbedPane tab2  = new JTabbedPane();
	
	/**
	 * Each 4 nests make up one tab.
	 * 
	 * @brief Constructor
	 * @param nestCount : Quantity of nest
	 */
	public LaneManagerGUINest(final int nestCount){
		setPreferredSize(new Dimension(300,640));
		setLayout(new GridLayout(2,1));
		
		// Add nestPanel to 'nestPanels' ArrayList ( top 4 panels )
		for(int i=0 ; i<nestCount/2 ; i++){
			nestPanel = new LaneManagerGUINestPanel();
			nestPanels.add(nestPanel);
			tab1.add("Nest" + (i+1),nestPanel);	
		}
		add(tab1);
		
		// Add nestPanel to 'nestPanels' ArrayList ( bottom 4 panels )
		for(int i=4 ; i<nestCount ; i++){
			nestPanel = new LaneManagerGUINestPanel();
			nestPanels.add(nestPanel);
			tab2.add("Nest" + (i+1),nestPanel);	
		}
		add(tab2);
	}
	
	/**
	 * @brief Getter
	 * @param nestNum : nest number
	 * @return Instance of class 'LaneManagerGUINestPanel'
	 */
	public LaneManagerGUINestPanel getGUINestArray(int nestNum){
		return nestPanels.get(nestNum);
	}
}
