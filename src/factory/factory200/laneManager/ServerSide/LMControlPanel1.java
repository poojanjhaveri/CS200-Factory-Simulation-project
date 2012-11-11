package factory.factory200.laneManager.ServerSide;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * @brief Control Panel For Feeder
 * @author Dongyoung Jung
 */
public class LMControlPanel1 extends JPanel implements ActionListener{

	private LMFeederForAgent agentFeeder;
	private Integer[] feederList = { 1, 2, 3, 4 };
	private JComboBox feederNum = new JComboBox(feederList);
	
	private TitledBorder border = new TitledBorder("Feeder");
	private int chosenFeeder;
	
	private JPanel tempPanel = new JPanel(new GridLayout(1,3));
	private JPanel tempPanel2 = new JPanel(new GridLayout(1,3));

	private JButton b0 = new JButton("Switch On");
	private JButton b1 = new JButton("Switch Off");
	private JButton b2 = new JButton("Low Sensor On");
	private JButton b3 = new JButton("Low Sensor Off");
	private JButton b4 = new JButton("Feed Switch On");
	private JButton b5 = new JButton("Feed Switch Off");
	private JButton b6 = new JButton("Fed Counter Increase");
	private 	JButton b7 = new JButton("Lowered Rear Gate");
	private JButton b8 = new JButton("Raised Rear Gate");
	private JButton b9 = new JButton("Purge On");
	private JButton b10 = new JButton("Purge Off");
	private JButton b11 = new JButton("Divert To Left");
	private JButton b12 = new JButton("Divert To Right");
	
	public LMControlPanel1(LMFeederForAgent agentFeeder){
		this.agentFeeder = agentFeeder;
		setBorder(border);
		setPreferredSize(new Dimension(480,160));
		
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

		add(feederNum);
		add(b0);add(b1);add(b2);add(b3);add(b4);add(b5);add(b6);add(b7);add(b8);add(b9);add(b10);add(b11);add(b12);
		
		updateUI();
	}

	public void actionPerformed(ActionEvent ae){
		chosenFeeder = feederList[ feederNum.getSelectedIndex() ] - 1;
		
		if(ae.getSource() == b0){
			agentFeeder.setSwitchOn( chosenFeeder );
		}
		
		else if(ae.getSource() == b1){
			agentFeeder.setSwitchOff( chosenFeeder );
		}
		
		else if(ae.getSource() == b2){
			agentFeeder.setPartLowSensorOn( chosenFeeder );
		}
		
		else if(ae.getSource() == b3){
			agentFeeder.setPartLowSensorOff( chosenFeeder );
		}
		
		else if(ae.getSource() == b4){
			agentFeeder.setFeedPartsSwitchOn( chosenFeeder );
		}
		
		else if(ae.getSource() == b5){
			agentFeeder.setFeedPartsSwitchOff( chosenFeeder );
		}
		
		else if(ae.getSource() == b6){
			agentFeeder.setPartFedCounterIncrease( chosenFeeder );
		}
		
		else if(ae.getSource() == b7){
			agentFeeder.setRearGateSwitchLower( chosenFeeder );
		}
		
		else if(ae.getSource() == b8){
			agentFeeder.setRearGateSwitchRaise( chosenFeeder );
		}
		
		else if(ae.getSource() == b9){
			agentFeeder.setPurgeBinSwitchOn( chosenFeeder );
		}
		
		else if(ae.getSource() == b10){
			agentFeeder.setPurgeBinSwitchOff( chosenFeeder );
		}
		
		else if(ae.getSource() == b11){
			agentFeeder.setDiverterSwitchLeft( chosenFeeder );
		}
		
		else if(ae.getSource() == b12){
			agentFeeder.setDiverterSwitchRight( chosenFeeder );
		}
	}
}
