package factory.factory200.gantryRobotManager;
/**
 * Gantry Robot Manager takes care of movement of gantry robot, bins, purge
 * station, feeders. Inner classes are GantryRobot,
 * GUIGantryManager,GraphicGantryPanel, GantryState, Feeder and Bins
 * GUIGantryManager extends a JPanel with JButtons, JMenu and JLabels
 * GraphicGantryPanel extends a JPanel which shows animation of every process
 * before feeders GantryState stores data needed to do animation for bins,
 * feeders,robot GantryRobot handles the movement of gantry robot <img
 * src="../img/image01.png" /> <img src="../img/image12.png" />
 *
 * @brief takes care of movement of gantry robot, bins, purge station, feeders.
 * @author Yuting Liu
 */

import factory.factory200.gantryRobotManager.GUIBin;
import factory.factory200.gantryRobotManager.GUIGantryRobot;
import factory.factory200.gantryRobotManager.GRMGraphicPanel;
import factory.general.Manager;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GantryRobotManager extends Manager implements ActionListener {
   
    GRMGraphicPanel graphics;    
    GantryState gs;
    GUIBin bin;
   // GUIGantryRobot gbot;///<class which includes Gantry Robot Manager Methods
    int purgeStationx;//x coordinate of purgeStation
    int purgeStationy;//y coordinate of purgeStation

//    public void receiveFromServer();///<pull data from server
//   GUIGantryManager gui;///<break the nonormative situations
    public static final Integer FEED0X = 0;///<x-coordinate of feeder 0
    public static final Integer FEED0Y = 0;///<y-coordinate of feeder 0
    public static final Integer FEED1X = 0;///<x-coordinate of feeder 1
    public static final Integer FEED1Y = 0;///<y-coordinate of feeder 1
    public static final Integer FEED2X = 0;///<x-coordinate of feeder 2
    public static final Integer FEED2Y = 0;///<y-coordinate of feeder 2
    public static final Integer FEED3X = 0;///<x-coordinate of feeder 3
    public static final Integer FEED3Y = 0;///<y-coordinate of feeder 3

    public static final Integer DUMPX = 0;///<x-coordinate of dump 
    public static final Integer DUMPY = 0;///<y-coordinate of dump 

    public static final Integer BIN_X = 0;///<x coordinate of all bin locations
    public static final Integer BIN0Y = 0;///<y coordinate of bin0
    public static final Integer BIN1Y = 0;///<y coordinate of bin1
    public static final Integer BIN2Y = 0;///<y coordinate of bin2
    public static final Integer BIN3Y = 0;///<y coordinate of bin3
    public static final Integer BIN4Y = 0;///<y coordinate of bin4
    public static final Integer BIN5Y = 0;///<y coordinate of bin5
    public static final Integer BIN6Y = 0;///<y coordinate of bin6
    public static final Integer BIN7Y = 0;///<y coordinate of bin7

    public static final Integer ROBOT_INITIAL_X = 0;///<spawn x coordinate of gantrybot
    public static final Integer ROBOT_INITIAL_Y = 0;///<spawn y coordinate of gantrybot
    
    public GantryRobotManager() {
        graphics= new GRMGraphicPanel();        
        setLayout(new GridLayout(1, 2));       
        add(graphics);
        int x = 500;
        setSize(500 + x, 700);
        graphics.setVisible(true);
        add(TestPanel());
        //change TEST to just graphicPanel (above)

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    JButton gantryRobot;
    JButton gantryRobotFull;
    JButton purgeStation;
    //JButton moveKit;
      public JPanel TestPanel() {
        JPanel tester = new JPanel();
        tester.setLayout(new BoxLayout(tester, BoxLayout.Y_AXIS));
        gantryRobotFull = new JButton("Move Gantry Robot (bin -> feederBin)");
        gantryRobotFull.addActionListener(this);
        tester.add(gantryRobotFull);
        gantryRobot = new JButton("Move Gantry Robot to parts bin");
        gantryRobot.addActionListener(this);
        tester.add(gantryRobot);
        purgeStation = new JButton("Purge this empty bin");
        purgeStation.addActionListener(this);
        tester.add(purgeStation);

        return tester;
    }
    
    /**
     * @brief Inner class GUIGantryManager
     */

    public class GantryState {

//        Feeder feeder;
//        Bin bins;

        /**
         * update Feeder coordinate or status
         */
        public void updateFeeder() {
        }

        /**
         * update Bins coordinates or status;
         */
        public void updateBins() {
        }

        /**
         * update gantryRobot infomation
         */
        public void updateGantryRobot() {
        }

        /**
         * update purgestation. move empty bin to (purgeStationx, purgeStationy)
         */
        public void updatePurgeStation() {
        }
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	  public static void main(String[] args){
	       GantryRobotManager mgr = new GantryRobotManager();   
	      }
	  
}


