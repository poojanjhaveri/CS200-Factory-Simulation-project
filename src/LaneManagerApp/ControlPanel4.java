import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class ControlPanel4 extends JPanel implements ActionListener{
	
	LaneManagerApp laneManagerApp;
	Integer[] cameraList = { 0, 1, 2, 3 };
	JComboBox cameraNum = new JComboBox(cameraList);
	TitledBorder border = new TitledBorder("Camera");
	JButton button = new JButton("Camera Shoot");
	int chosenCamera;
	
	public ControlPanel4(LaneManagerApp laneManagerApp){
		this.laneManagerApp = laneManagerApp;
		setBorder(border);
		setPreferredSize(new Dimension(480,100));
		
		add(cameraNum);
		button.addActionListener(this);
		add(button);
	}
	
	public void actionPerformed(ActionEvent ae){
		chosenCamera = cameraList[ cameraNum.getSelectedIndex() ];
		
		if(ae.getSource() == button){
			laneManagerApp.verify.verify("&Camera&" + chosenCamera);
		}
	}
}
