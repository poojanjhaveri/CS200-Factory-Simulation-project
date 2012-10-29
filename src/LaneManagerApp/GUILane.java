import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class GUILane extends JPanel{
	
	private GUILanePanel lanePanel;
	private ArrayList<GUILanePanel> lanePanels = new ArrayList<GUILanePanel>();
	private JTabbedPane tab1  = new JTabbedPane();
	private JTabbedPane tab2 = new JTabbedPane();
	
	public GUILane(final int laneCount){
		setPreferredSize(new Dimension(300,640));
		setLayout(new GridLayout(2,1));
		
		// Add lanePanel to 'lanePanels' ArrayList ( top 4 panels )
		for(int i=0 ; i<laneCount/2 ; i++){
			lanePanel = new GUILanePanel();
			lanePanels.add(lanePanel);
			tab1.add("Lane" + (i+1),lanePanel);
		}
		add(tab1);
		
		// Add lanePanel to 'lanePanels' ArrayList ( bottom 4 panels )
		for(int i=4 ; i<laneCount ; i++){
			lanePanel = new GUILanePanel();
			lanePanels.add(lanePanel);
			tab2.add("Lane" + (i+1),lanePanel);
		}
		add(tab2);
	}
	
	public GUILanePanel getGUILaneArray(int laneNum){
		return lanePanels.get(laneNum);
	}
}