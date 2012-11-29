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
	private Integer[] partList = { 1, 2, 3, 4, 5, 6, 7, 8 };
	private JComboBox feederNumBox = new JComboBox(feederList);
	private JComboBox partNumBox = new JComboBox(partList);
	private JLabel label1 = new JLabel("Feeder Number");
	private JLabel label2 = new JLabel("Part Number");
	
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel(); 
	
	private JButton button = new JButton("Bad Parts Insertion");	
	
	public LMGUINonNormativePanel1(LMApplication app){
		this.app = app;
		
		setPreferredSize(new Dimension(290,200));
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		
		panel1.add(label1);
		panel1.add(feederNumBox);
		panel1.add(label2);
		panel1.add(partNumBox);
		panel1.setPreferredSize(new Dimension(270,40));
		add(panel1);
		
		button.addActionListener(this);
		add(button);
	}
	
	public void actionPerformed(ActionEvent ae){
		if( ae.getSource() == button ){
			
		}
	}
}