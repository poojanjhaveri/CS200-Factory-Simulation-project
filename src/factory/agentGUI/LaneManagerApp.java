import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.net.Socket;
import javax.swing.JFrame;

/**
@brief shows the lane manager section with graphical Panel and GUI panel
@author Dongyoung Jung
*/
public class LaneManagerApp extends JFrame {
	
	// Network setting
	private SetNetwork setNetwork = new SetNetwork(this);
	VerifySignalsFromServer verify = new VerifySignalsFromServer(this);
	
	// Feeder, Lane, Nest
	private final int feederCount = 4;
	private final int laneCount = 8;
	private final int nestCount = 8;
	private final int cameraCount = 4;
	
	// GUI panel
	private GUIPanel panelGUI = new GUIPanel(feederCount, laneCount, nestCount, this);
	
	// Graphics panel
	private GraphicsPanel panelGraphics = new GraphicsPanel(feederCount, laneCount, nestCount, cameraCount, this);
	
	public LaneManagerApp(){
		setSize(880,700);
		//setLocationRelativeTo(null);
		setLocation(450,50);
		setVisible(true);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		setResizable(false);
		
		add( panelGraphics, BorderLayout.CENTER );
		add( panelGUI, BorderLayout.EAST );
		
		panelGUI.updateUI();
		panelGraphics.updateUI();
	}
	
	public GraphicsPanel getGraphicsPanel(){
		return panelGraphics;
	}
	
	public GUIPanel getGUIPanel(){
		return panelGUI;
	}
	
	public static void main(String[] args){
		new LaneManagerApp();
	}
}
