package factory.factory200.laneManager.ServerSide;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class LMControlPanel5 extends JPanel implements ActionListener{
	
	private LMGantryRobotForAgent agentGantryRobot;
	private Integer[] feederList = { 1, 2, 3, 4 };
	private String[] statusList = { "GOOD", "BAD" };
	private JComboBox feederNum = new JComboBox(feederList);
	private JComboBox partStatus = new JComboBox(statusList);
	private TitledBorder border = new TitledBorder("Gantry Robot");
	private JButton button1 = new JButton("Put Bin");
	private JButton button2 = new JButton("Purge Bin");
	private JTextField binInput = new JTextField(3);
	private JTextField quantityInput = new JTextField(3);
	private JLabel label1 = new JLabel("Part");
	private JLabel label2 = new JLabel("Quantity");
	private int chosenFeeder;
	
	public LMControlPanel5(LMGantryRobotForAgent agentGantryRobot){
		this.agentGantryRobot = agentGantryRobot;
		setBorder(border);
		setPreferredSize(new Dimension(480,70));
		
		add(feederNum);
		add(partStatus);
		button1.addActionListener(this);
		button2.addActionListener(this);
		add(button1);
		add(label1);
		add(binInput);
		add(label2);
		add(quantityInput);
		add(button2);
	}

	public void actionPerformed(ActionEvent ae){
		chosenFeeder = feederList[ feederNum.getSelectedIndex() ] - 1;
		
		if(ae.getSource() == button1){
			if( partStatus.getSelectedIndex() == 1 ){
				agentGantryRobot.putBadBin(Integer.parseInt(binInput.getText()), Integer.parseInt(quantityInput.getText()), chosenFeeder);
			}
			else if( partStatus.getSelectedIndex() == 0 ){
				agentGantryRobot.putBin(Integer.parseInt(binInput.getText()), Integer.parseInt(quantityInput.getText()), chosenFeeder);
			}
		}
		else if(ae.getSource() == button2){
			agentGantryRobot.purgeBin( chosenFeeder );
		}
	}
}
