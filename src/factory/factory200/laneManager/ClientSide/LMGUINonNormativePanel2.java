package factory.factory200.laneManager.ClientSide;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LMGUINonNormativePanel2 extends JPanel implements ActionListener{

	private LMApplication app;

	private Integer[] laneList = { 1, 2, 3, 4, 5, 6, 7, 8 };
	private JComboBox laneNumBox = new JComboBox(laneList);
	private JLabel label = new JLabel("Lane Number");
	
	private JPanel panel = new JPanel(); 
	private JButton button = new JButton("Lane Being Jammed");	
	
	private int laneNum;
	
	public LMGUINonNormativePanel2(LMApplication app){
		this.app = app;
		
		setPreferredSize(new Dimension(290,200));
		setLayout(new FlowLayout(FlowLayout.CENTER, 70, 60));
		
		panel.add(label);
		panel.add(laneNumBox);
		panel.setPreferredSize(new Dimension(270,40));
		add(panel);
		
		button.addActionListener(this);
		add(button);
	}
	
	public void actionPerformed(ActionEvent ae){
		if( ae.getSource() == button ){
			laneNum = laneNumBox.getSelectedIndex();
			app.getVerifyMessage().sendToServer( "" + laneNum + "LANE_JAMMED");
		}
	}
}