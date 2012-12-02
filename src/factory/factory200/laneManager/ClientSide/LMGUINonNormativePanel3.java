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
	
	private JButton button1 = new JButton("Parts Being Piled");	
	private JButton button2 = new JButton("Toggling Parts");
	private JButton button3 = new JButton("Undo Toggling Parts");
	
	private int nestNum;
	
	public LMGUINonNormativePanel3(LMApplication app){
		this.app = app;
		
		setPreferredSize(new Dimension(290,200));
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		
		panel.add(label);
		panel.add(nestNumBox);
		panel.setPreferredSize(new Dimension(270,40));
		add(panel);
		
		button1.addActionListener(this);
		button2.addActionListener(this);
		add(button1);
		add(button2);
	}
	
	public void actionPerformed(ActionEvent ae){
		if( ae.getSource() == button1 ){
			nestNum = nestNumBox.getSelectedIndex();
			app.getVerifyMessage().sendToServer( nestNum + "PART_PILED" );
		}
		else if( ae.getSource() == button2 ){
			nestNum = nestNumBox.getSelectedIndex();
			app.getVerifyMessage().sendToServer( nestNum + "PART_TOGGLING" );
		}
		else if( ae.getSource() == button3 ){
			nestNum = nestNumBox.getSelectedIndex();
			app.getVerifyMessage().sendToServer( nestNum + "PART_UNTOGGLING" );
		}
	}
}