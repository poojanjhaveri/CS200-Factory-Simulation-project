package factory.factory200.laneManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class ControlPanel5 extends JPanel implements ActionListener{
	
	private ServerForAgentGantry agentGantry;
	private ServerForAgentFeeder agentFeeder;
	
	private Integer[] feederList = { 1, 2, 3, 4 };
	private Integer[] binList = { 1, 2, 3, 4, 5, 6, 7, 8 };
	private JComboBox feederNum = new JComboBox(feederList);
	private JComboBox binNum = new JComboBox(binList);
	private TitledBorder border = new TitledBorder("Gantry Robot");

	private int chosenFeeder;
	private int chosenBin;
	
	private JPanel panel1 = new JPanel(new GridLayout(1,3));
	private JPanel panel2 = new JPanel(new GridLayout(1,3));
	
	private JButton b1 = new JButton("Move To Feeder");
	private JButton b2 = new JButton("Move To Bin");
	private JButton b3 = new JButton("Dump in a feeder");
	private JButton b4 = new JButton("Pick up a bin");
	private JButton b5 = new JButton("Put off a bin");
	
	public ControlPanel5(ServerForAgentGantry agentGantry, ServerForAgentFeeder agentFeeder){
		this.agentGantry = agentGantry;
		this.agentFeeder = agentFeeder;
		setBorder(border);
		setPreferredSize(new Dimension(480,100));
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		
		panel1.setBorder( new LineBorder( Color.red ));
		panel2.setBorder( new LineBorder( Color.red ));
		
		panel1.add(feederNum);
		panel1.add(b1);
		panel1.add(b3);
		
		panel2.add(binNum);
		panel2.add(b2);
		panel2.add(b4);
		
		add(panel1);
		add(panel2);
		add(b5);
	}
	
	public void actionPerformed(ActionEvent ae){
		if( ae.getSource() == b1 ){
			chosenFeeder = feederList[ feederNum.getSelectedIndex() ] - 1;
			agentGantry.goToFeeder( chosenFeeder );
		}
		else if( ae.getSource() == b2 ){
			chosenBin = binList[ binNum.getSelectedIndex() ] - 1;
			agentGantry.goToBin( chosenBin );
		}
		else if( ae.getSource() == b3 ){
			chosenFeeder = feederList[ feederNum.getSelectedIndex() ] - 1;
			agentFeeder.dumpBinBoxIntoFeeder( chosenFeeder );
		}
		else if( ae.getSource() == b4 ){
			chosenBin = binList[ binNum.getSelectedIndex() ] - 1;
			agentGantry.pickUpBin( chosenBin );
		}
		else if( ae.getSource() == b5 ){
			chosenBin = binList[ binNum.getSelectedIndex() ] - 1;
			agentGantry.putOffBin();
		}
	}
}
