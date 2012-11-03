package factory.factory200.laneManager;
import java.awt.FlowLayout;
import javax.swing.JFrame;

public class LaneManagerApp extends JFrame {
	
	//Network setting
	private LaneManagerSetNetwork network = new LaneManagerSetNetwork(this);
	private ServerMain server;
	
	// Feeder, Lane, Nest
	private final int feederCount = 4;
	private final int laneCount = 8;
	private final int nestCount = 8;
	private final int cameraCount = 4;
	
	// GUI panel
	private LaneManagerGUIPanel panelGUI = new LaneManagerGUIPanel(feederCount, laneCount, nestCount, this);
	
	// Graphics panel
	private LaneManagerGraphicsPanel panelGraphics = new LaneManagerGraphicsPanel(feederCount, laneCount, nestCount, cameraCount, this);
	
	public LaneManagerApp(ServerMain server){
		this.server = server;
		setTitle("Lane Manager");
		setSize(870,700);
		setLocation(0,0);
		setVisible(true);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		setResizable(false);
		
		add( panelGraphics );
		add( panelGUI );
		
		panelGUI.updateUI();
		panelGraphics.updateUI();
	}
	
	public LaneManagerGraphicsPanel getGraphicsPanel(){
		return panelGraphics;
	}
	
	public LaneManagerGUIPanel getGUIPanel(){
		return panelGUI;
	}
	
	public LaneManagerSetNetwork getNetwork(){
		return network;
	}
	
	public ServerMain getServer(){
		return server;
	}
}
