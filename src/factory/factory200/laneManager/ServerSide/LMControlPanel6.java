package factory.factory200.laneManager.ServerSide;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class LMControlPanel6 extends JPanel implements ActionListener{
	
	private LMPartRobotForAgent agentPartRobot;
	private Integer[] nestList = { 1, 2, 3, 4, 5, 6, 7, 8 };
	private JComboBox nestNum = new JComboBox(nestList);
	private TitledBorder border = new TitledBorder("Part Robot");
	private JButton button = new JButton("Take Part From Nest");
	private int chosenNest;
	
	public LMControlPanel6(LMPartRobotForAgent agentPartRobot){
		this.agentPartRobot = agentPartRobot;
		setBorder(border);
		setPreferredSize(new Dimension(235,70));
		
		add(nestNum);
		button.addActionListener(this);
		add(button);
	}

	public void actionPerformed(ActionEvent ae){
		chosenNest = nestNum.getSelectedIndex();
		
		if(ae.getSource() == button){
			agentPartRobot.takePartFromNest( chosenNest );
		}
	}
}
