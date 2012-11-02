import java.awt.FlowLayout;
import javax.swing.JFrame;

public class Controller extends JFrame {
	
	private ServerForAgentFeeder agentFeeder;
	private ServerForAgentLane agentLane;
	private ServerForAgentNest agentNest;
	private ServerForAgentNestCamera agentNestCamera;
	private ServerForAgentGantry agentGantry;
	
	private ControllerTitle panel;
	private ControlPanel1 panel1;
	private ControlPanel2 panel2;
	private ControlPanel3 panel3;
	private ControlPanel4 panel4;
	private ControlPanel5 panel5;
	
	public Controller(ServerForAgentFeeder agentFeeder, ServerForAgentLane agentLane, ServerForAgentNest agentNest, ServerForAgentNestCamera agentNestCamera, ServerForAgentGantry agentGantry ){
		this.agentFeeder = agentFeeder;
		this.agentLane = agentLane;
		this.agentNest = agentNest;
		this.agentNestCamera = agentNestCamera;
		this.agentGantry = agentGantry;
		
		setSize(500,770);
		setVisible(true);
		setLayout(new FlowLayout());
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );	
		
		panel = new ControllerTitle();
		panel1 = new ControlPanel1(agentFeeder);
		panel2 = new ControlPanel2(agentLane);
		panel3 = new ControlPanel3(agentNest);
		panel4 = new ControlPanel4(agentNestCamera);
		panel5 = new ControlPanel5(agentGantry, agentFeeder);
		
		add(panel);
		add(panel1);
		add(panel2);
		add(panel3);
		add(panel4);
		add(panel5);
		
		revalidate();
	}
}

