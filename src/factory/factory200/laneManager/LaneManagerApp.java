package factory.factory200.laneManager;

import java.awt.FlowLayout;
import javax.swing.JFrame;

/**
 * @brief JFrame Setting
 * @author Dongyoung Jung
 */
public class LaneManagerApp extends JFrame{
	
	private LaneManagerSetNetwork network = new LaneManagerSetNetwork(this);	///< Instance of class 'LaneManagerSetNetwork'
	private ServerMain server;	///< Instance of class 'ServerMain'
	
	private final int feederCount = 4;	///< Fixed quantity of feeder
	private final int laneCount = 8;	///< Fixed quantity of lane
	private final int nestCount = 8;	///< Fixed quantity of net
	private final int cameraCount = 4;	///< Fixed quantity of camera
	
	private LaneManagerGUIPanel panelGUI = new LaneManagerGUIPanel(feederCount, laneCount, nestCount, this);	///< Instance of class 'LaneManagerGUIPanel'
	private LaneManagerGraphicsPanel panelGraphics = new LaneManagerGraphicsPanel(feederCount, laneCount, nestCount, cameraCount, this);	///< Instance of class 'LaneManagerGraphicsPanel'	
	
	/**
	 * @brief Constructor
	 * @param server : Instance of ServerMain
	 */
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
	
	/**
	 * @brief Getter
	 * @return Instance of class 'LaneManagerGraphicsPanel'
	 */
	public LaneManagerGraphicsPanel getGraphicsPanel(){
		return panelGraphics;
	}
	
	/**
	 * @brief Getter
	 * @return Instance of class 'LaneManagerGUIPanel'
	 */
	public LaneManagerGUIPanel getGUIPanel(){
		return panelGUI;
	}
	
	/**
	 * @brief Getter
	 * @return Instance of class 'LaneManagerSetNetwork'
	 */
	public LaneManagerSetNetwork getNetwork(){
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
