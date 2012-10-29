import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

// Feeder
public class ControlPanel1 extends JPanel implements ActionListener{

	LaneManagerApp main;
	Integer[] feederList = { 0, 1, 2, 3 };
	JComboBox feederNum = new JComboBox(feederList);
	TitledBorder border = new TitledBorder("Feeder");
	String message = "";
	int chosenFeeder;
	
	JButton b0 = new JButton("Switch On");
	JButton b1 = new JButton("Switch Off");
	JButton b2 = new JButton("Low Sensor On");
	JButton b3 = new JButton("Low Sensor Off");
	JButton b4 = new JButton("Feed Switch On");
	JButton b5 = new JButton("Feed Switch Off");
	JButton b6 = new JButton("Fed Counter Up");
	JButton b7 = new JButton("Lowered Rear Gate");
	JButton b8 = new JButton("Raised Rear Gate");
	JButton b9 = new JButton("Purge On");
	JButton b10 = new JButton("Purge Off");
	JButton b11 = new JButton("Divert To Left");
	JButton b12 = new JButton("Divert To Right");
	JButton b13 = new JButton("Robot Dump PartBox In Feeder");
	JButton b14 = new JButton("Feed Part Onto Lane Left");;
	JButton b15 = new JButton("Feed Part Onto Lane Right");;
	
	public ControlPanel1(LaneManagerApp main){
		this.main = main;
		setBorder(border);
		setPreferredSize(new Dimension(480,250));
		
		b0.addActionListener(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		b10.addActionListener(this);
		b11.addActionListener(this);
		b12.addActionListener(this);
		b13.addActionListener(this);
		b14.addActionListener(this);
		b15.addActionListener(this);
		
		add(feederNum);
		add(b0);add(b1);add(b2);add(b3);add(b4);add(b5);add(b6);add(b7);add(b8);add(b9);add(b10);add(b11);add(b12);add(b13);add(b14);add(b15);
		
		updateUI();
	}
	
	public void actionPerformed(ActionEvent ae){
		message = "&Feeder&";
		chosenFeeder = feederList[ feederNum.getSelectedIndex() ];
		
		// DONE
		if(ae.getSource() == b0){
			message += "Feeder On" + chosenFeeder;
			main.verify.verify(message);
		}
		// DONE
		else if(ae.getSource() == b1){
			message += "Feeder Off" + chosenFeeder;
			main.verify.verify(message);
		}
		// DONE
		else if(ae.getSource() == b2){
			message += "Part Low Sensor On" + chosenFeeder;
			main.verify.verify(message);
		}
		// DONE
		else if(ae.getSource() == b3){
			message += "Part Low Sensor Off" + chosenFeeder;
			main.verify.verify(message);
		}
		// DONE
		else if(ae.getSource() == b4){
			message += "Feed Parts Switch On" + chosenFeeder;
			main.verify.verify(message);
		}
		// DONE
		else if(ae.getSource() == b5){
			message += "Feed Parts Switch Off" + chosenFeeder;
			main.verify.verify(message);
		}
		// DONE
		else if(ae.getSource() == b6){
			message += "Part Fed Counter Increment" + chosenFeeder;
			main.verify.verify(message);
		}
		// DONE
		else if(ae.getSource() == b7){
			message += "Lowered Rear Gate" + chosenFeeder;
			main.verify.verify(message);
		}
		// DONE
		else if(ae.getSource() == b8){
			message += "Raised Rear Gate" + chosenFeeder;
			main.verify.verify(message);
		}
		// DONE
		else if(ae.getSource() == b9){
			message += "Purge Bin Switch On" + chosenFeeder;
			main.verify.verify(message);
		}
		// DONE
		else if(ae.getSource() == b10){
			message += "Purge Bin Switch Off" + chosenFeeder; 
			main.verify.verify(message);
		}
		// DONE
		else if(ae.getSource() == b11){
			message += "Divert To Left" + chosenFeeder;
			main.verify.verify(message);
		}
		// DONE
		else if(ae.getSource() == b12){
			message += "Divert To Right" + chosenFeeder;
			main.verify.verify(message);
		}
		// DONE
		else if(ae.getSource() == b13){
			message += "Robot Dump Part Box" + chosenFeeder;
			main.verify.verify(message);
		}
		// DONE
		else if(ae.getSource() == b14){
			message += "Feed Part Onto Lane Left" + chosenFeeder;
			main.verify.verify(message);
		}
		// DONE
		else if(ae.getSource() == b15){
			message += "Feed Part Onto Lane Right" + chosenFeeder;
			main.verify.verify(message);
		}
	}
}
