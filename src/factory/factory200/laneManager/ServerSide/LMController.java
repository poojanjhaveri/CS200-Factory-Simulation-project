package factory.factory200.laneManager.ServerSide;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;

/**
 * @brief Control Frame
 * @author Dongyoung Jung
 */
public class LMController extends JFrame {
	
	LMServerMain serverMain;
	
	private LMFeederForAgent agentFeeder;
	private LMLaneForAgent agentLane;
	private LMNestForAgent agentNest;
	private LMCameraForAgent agentNestCamera;
	private LMGantryRobotForAgent agentGantryRobot;
	private LMPartRobotForAgent agentPartRobot;
	
	private LMControllerTitle panel;
	private LMControlPanel1 panel1;
	private LMControlPanel2 panel2;
	private LMControlPanel3 panel3;
	private LMControlPanel4 panel4;
	private LMControlPanel5 panel5;
	private LMControlPanel6 panel6;
	private LMControlPanel7 panel7;
		
	private FlowLayout layout = new FlowLayout( FlowLayout.CENTER );
	
	public LMController(LMFeederForAgent agentFeeder, LMLaneForAgent agentLane, LMNestForAgent agentNest, LMCameraForAgent agentNestCamera, LMGantryRobotForAgent agentGantryRobot, LMPartRobotForAgent agentPartRobot, LMServerMain serverMain){
		this.serverMain = serverMain;
		this.agentFeeder = agentFeeder;
		this.agentLane = agentLane;
		this.agentNest = agentNest;
		this.agentNestCamera = agentNestCamera;
		this.agentGantryRobot = agentGantryRobot;
		this.agentPartRobot = agentPartRobot;
		
		setSize(500,750);
		setVisible(true);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLayout(layout);
		
		panel = new LMControllerTitle();
		panel1 = new LMControlPanel1(agentFeeder);
		panel2 = new LMControlPanel2(agentLane);
		panel3 = new LMControlPanel3(agentNest);
		panel4 = new LMControlPanel4(agentNestCamera);
		panel5 = new LMControlPanel5(agentGantryRobot);
		panel6 = new LMControlPanel6(agentPartRobot);
		panel7 = new LMControlPanel7(serverMain);
		
		add(panel);
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		add(panel6);
		add(panel5);
		add(panel7);
		
		validate();
	}
}