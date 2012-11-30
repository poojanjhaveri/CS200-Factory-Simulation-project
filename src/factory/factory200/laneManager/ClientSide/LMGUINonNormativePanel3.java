package factory.factory200.laneManager.ClientSide;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LMGUINonNormativePanel3 extends JPanel implements ActionListener{
	
	private LMApplication app;
	
	private Integer[] nestList = { 1, 2, 3, 4, 5, 6, 7, 8 };
	private JComboBox nestNumBox = new JComboBox(nestList);
	private JLabel label = new JLabel("Nest Number");
	
	private JPanel panel = new JPanel(); 
	
	private JButton button = new JButton("Parts Being Piled");	
	
	private int nestNum;
	
	public LMGUINonNormativePanel3(LMApplication app){
		this.app = app;
		
		setPreferredSize(new Dimension(290,200));
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		panel.add(label);
		panel.add(nestNumBox);
		panel.setPreferredSize(new Dimension(270,40));
		add(panel);
		
		button.addActionListener(this);
		add(button);
	}
	
	public void actionPerformed(ActionEvent ae){
		if( ae.getSource() == button ){
			nestNum = nestNumBox.getSelectedIndex();
			app.getVerifyMessage().sendToServer( nestNum + "PART_PILED" );
		}
	}
}