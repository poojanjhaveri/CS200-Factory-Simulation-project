package factory.factory200.laneManager;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 * This class contains JFrame that shows Robot's movement and Bins' images.
 * 
 * @brief Gantry Robot JFrame
 * @author Dongyoung Jung
 */
public class GantryManagerApp extends JFrame {
	
	private GantryManagerSetNetwork network = new GantryManagerSetNetwork(this);	///< Instance of class 'GantryManagerSetNetwork'
	private ServerMain server;	///< Instance of class 'ServerMain'
	
	private final int binCount = 8;	///< Bin Total Quantity
	
	private GantryManagerGraphicsPanel panelGraphics = new GantryManagerGraphicsPanel(this);	///< Instance of class 'GantryManagerGraphicsPanel'
	
	/**
	 * @brief Constructor
	 * @param server : instance of class 'ServerMain'
	 */
	public GantryManagerApp(ServerMain server){
		this.server = server;
		setTitle("Gantry Robot Manager");
		setSize(300,700);
		setLocation(1050,0);
		setVisible(true);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLayout(new BorderLayout());
		setResizable(false);
		
		add( panelGraphics );
		panelGraphics.updateUI();
	}
	
	/**
	 * @brief Getter
	 * @return Instance of class 'GantryManagerGraphicsPanel'
	 */
	public GantryManagerGraphicsPanel getGraphicsPanel(){
		return panelGraphics;
	}

	/**
	 * @brief Getter
	 * @return Instance of class 'GantryManagerSetNetwork'
	 */
	public GantryManagerSetNetwork getNetwork(){
		return network;
	}
	
	/**
	 * @brief Getter
	 * @return Instance of class 'ServerMain'
	 */
	public ServerMain getServer(){
		return server;
	}
}
