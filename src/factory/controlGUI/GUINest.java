package factory.controlGUI;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
	@brief contains 2 GUINestPanels since there are 8 tabs in total
	   		   uses JTabbedPane
	@author Dongyoung Jung
*/
public class GuiNest extends JPanel{
	
	private GUINestPanel nestPanel;
	private ArrayList<GUINestPanel> nestPanels = new ArrayList<GUINestPanel>();
	private JTabbedPane tab1  = new JTabbedPane();
	private JTabbedPane tab2  = new JTabbedPane();
	
	public GUINest(final int nestCount){
		setPreferredSize(new Dimension(300,640));
		setLayout(new GridLayout(2,1));
		
		// Add nestPanel to 'nestPanels' ArrayList ( top 4 panels )
		for(int i=0 ; i<nestCount/2 ; i++){
			nestPanel = new GUINestPanel();
			nestPanels.add(nestPanel);
			tab1.add("Nest" + (i+1),nestPanel);	
		}
		add(tab1);
		
		// Add nestPanel to 'nestPanels' ArrayList ( bottom 4 panels )
		for(int i=4 ; i<nestCount ; i++){
			nestPanel = new GUINestPanel();
			nestPanels.add(nestPanel);
			tab2.add("Nest" + (i+1),nestPanel);	
		}
		add(tab2);
	}
	
	public GUINestPanel getGUINestArray(int nestNum){
		return nestPanels.get(nestNum);
	}
}
