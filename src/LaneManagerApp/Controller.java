import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;


public class Controller extends JFrame {
	
	LaneManagerApp laneManagerApp;
	ControllerTitle panel;
	ControlPanel0 panel0;
	ControlPanel1 panel1;
	ControlPanel2 panel2;
	ControlPanel3 panel3;
	ControlPanel4 panel4;
	
	public Controller(LaneManagerApp laneManagerApp){
		this.laneManagerApp = laneManagerApp;
		setSize(500,800);
		setVisible(true);
		setLayout(new FlowLayout());
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	
		
		panel = new ControllerTitle();
		panel0 = new ControlPanel0(laneManagerApp);
		panel1 = new ControlPanel1(laneManagerApp);
		panel2 = new ControlPanel2(laneManagerApp);
		panel3 = new ControlPanel3(laneManagerApp);
		panel4 = new ControlPanel4(laneManagerApp);
		
		add(panel);
		add(panel0);
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		
		revalidate();
	}
}

