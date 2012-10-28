import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

// Nest
public class ControlPanel3 extends JPanel implements ActionListener{

	LaneManagerApp laneManagerApp;
	TitledBorder border = new TitledBorder("Nest");
	Integer[] nestList = { 0, 1, 2, 3, 4, 5, 6, 7 };
	JComboBox nestNum = new JComboBox(nestList);
	String message = "";
	int chosenNest;
	
	JButton b0 = new JButton("Switch Up");
	JButton b1 = new JButton("Switch Down");
	JButton b2 = new JButton("New Part From Lane");
	JButton b3 = new JButton("Robot Takes One");
	
	public ControlPanel3(LaneManagerApp laneManagerApp){
		this.laneManagerApp = laneManagerApp;
		setBorder(border);
		setPreferredSize(new Dimension(480,100));
		
		b0.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		add(nestNum);
		add(b0);
		add(b1);
		add(b2);
		add(b3);
		
		updateUI();
	}
	
	public void actionPerformed(ActionEvent ae){
		message = "&Nest&";
		chosenNest = nestList[ nestNum.getSelectedIndex() ];
		
		if(ae.getSource() == b0){
			message +=  "Switch Up" + chosenNest;
			laneManagerApp.verify.verify(message);
		}
		else if(ae.getSource() == b1){
			message +=  "Switch Down" + chosenNest;
			laneManagerApp.verify.verify(message);
		}
		else if(ae.getSource() == b2){
			message += "New Part From Lane" + chosenNest;
			laneManagerApp.verify.verify(message);
		}
		else if(ae.getSource() == b3){
			message += "Robot Takes One" + chosenNest;
			laneManagerApp.verify.verify(message);
		}
	}
}
