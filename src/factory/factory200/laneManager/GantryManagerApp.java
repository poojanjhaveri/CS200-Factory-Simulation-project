package factory.factory200.laneManager;
import java.awt.BorderLayout;
import javax.swing.JFrame;

public class GantryManagerApp extends JFrame {
	
	//Network setting
	private GantryManagerSetNetwork network = new GantryManagerSetNetwork(this);
	private ServerMain server;
	
	// Feeder, Lane, Nest
	private final int binCount = 8;
	
	// Graphics panel
	private GantryManagerGraphicsPanel panelGraphics = new GantryManagerGraphicsPanel(this);
	
	//gantryManagerApp constructor
	public GantryManagerApp(ServerMain server){
		this.server = server;
		setTitle("Gantry Robot Manager");
		setSize(300,700);
		setLocation(900,0);
		setVisible(true);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLayout(new BorderLayout());
		setResizable(false);
		
		add( panelGraphics );
		panelGraphics.updateUI();
	}
	
	//getter of graphicsPanel
	public GantryManagerGraphicsPanel getGraphicsPanel(){
		return panelGraphics;
	}

	//getter of network
	public GantryManagerSetNetwork getNetwork(){
		return network;
	}
	
	//getter of serverMain
	public ServerMain getServer(){
		return server;
	}
}
