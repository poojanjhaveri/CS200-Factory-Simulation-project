package factory.factory200.laneManager.ClientSide;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LMGUINonNormativePanel1 extends JPanel implements ActionListener{

	private LMApplication app;
	
	private Integer[] feederList = { 1, 2, 3, 4 };
	private JComboBox feederNumBox = new JComboBox(feederList);
	private JLabel label1 = new JLabel("Feeder Number");
	private JPanel panel1 = new JPanel();
	private JButton button = new JButton("Bad Parts Insertion");
	
	private int feederNum, partNum;
	
	public LMGUINonNormativePanel1(LMApplication app){
		this.app = app;
		
		setPreferredSize(new Dimension(290,200));
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		
		panel1.add(label1);
		panel1.add(feederNumBox);
		panel1.setPreferredSize(new Dimension(270,40));
		add(panel1);
		
		button.addActionListener(this);
		add(button);
	}
	
	public void actionPerformed(ActionEvent ae){
		if( ae.getSource() == button ){
			feederNum = feederNumBox.getSelectedIndex();
			partNum = 0;
			app.getVerifyMessage().sendToServer("" + feederNum + partNum + "BAD_PART_INSERTION");
		}
	}
}