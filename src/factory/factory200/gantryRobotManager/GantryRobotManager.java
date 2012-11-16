package factory.factory200.gantryRobotManager;
/**
 * Gantry Robot Manager takes care of movement of gantry robot, bins, purge
 * station and feeders. Instances are GUIGantryRobot,
 * GUIGantryManager,GRMGraphicPanel, GUIBin, GUIFeeder.
 * GUIGantryManager extends JPanel with JButtons, JMenu and JLabels to do non-normative condition in V2.
 * GraphicGantryPanel extends Manager which shows animation of every process:
 * 1.Gantry robot move to bin(n)
 * 2.Gantry robot picks up the bin
 * 3.Gantry robot move the bin to feeder told by agents
 * 4.Gantry robot dump the parts in the bin into feeder
 * 5.Gantry robot purge the bin
 * APIs are:
 * -PickUpBin(Integer binNumber)
 * -doSupplyPart(Integer binNumber, Integer feederNumber)///<use moveTo functions in GUIGantryRobot class)
 * -doSupply
 *  <img src="../pics/gantryrobot.png" /> <img src="../pics/bin.png" /> <img src="../pics/feeder.png" />
 *
 * @brief GantryRobotManger takes care of movement of gantry robot, bins, purge station, feeders.
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
    public GUIGantryRobot ganbot; ///<class which includes Gantry Robot Manager Methods

    int purgeStationx;//x coordinate of purgeStation
    int purgeStationy;//y coordinate of purgeStation

//   public void receiveFromServer();///<pull data from server
//   GUIGantryManager gui;///<break the nonormative situations
   
    public static final Integer FEED0X = 0;///<x-coordinate of feeder 0
    public static final Integer FEED0Y = 50;///<y-coordinate of feeder 0
    public static final Integer FEED1X = 0;///<x-coordinate of feeder 1
    public static final Integer FEED1Y = 200;///<y-coordinate of feeder 1
    public static final Integer FEED2X = 0;///<x-coordinate of feeder 2
    public static final Integer FEED2Y = 350;///<y-coordinate of feeder 2
    public static final Integer FEED3X = 0;///<x-coordinate of feeder 3
    public static final Integer FEED3Y = 500;///<y-coordinate of feeder 3

    public static final Integer DUMPX = 260;///<x-coordinate of dump 
    public static final Integer DUMPY = 600;///<y-coordinate of dump 

    public static final Integer BIN_X = 450;///<x coordinate of all bin locations
    public static final Integer BIN0Y = 30;///<y coordinate of bin0
    public static final Integer BIN1Y = 110;///<y coordinate of bin1
    public static final Integer BIN2Y = 190;///<y coordinate of bin2
    public static final Integer BIN3Y = 270;///<y coordinate of bin3
    public static final Integer BIN4Y = 350;///<y coordinate of bin4
    public static final Integer BIN5Y = 430;///<y coordinate of bin5
    public static final Integer BIN6Y = 510;///<y coordinate of bin6
    public static final Integer BIN7Y = 590;///<y coordinate of bin7

    public static final Integer ROBOT_INITIAL_X = 300;///<spawn x coordinate of gantrybot
    public static final Integer ROBOT_INITIAL_Y = 10;///<spawn y coordinate of gantrybot
    
    public static final Integer ROBOT_VELOCITY_X = 1;
    public static final Integer ROBOT_VELOCITY_Y = 1;
    public static final Double ROBOT_TURN_RATE = 0.0;
    
    /**
     * set graphic panel
     */
    public GantryRobotManager() {
        graphics= new GRMGraphicPanel();        
        setLayout(new GridLayout(1, 2));  
        ganbot = graphics.getGantryRobot();
        add(graphics);
        int x = 500;
        setSize(500 + x, 700);
        graphics.setVisible(true);
        add(TestPanel());
        //change TEST to just graphicPanel (above)

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    JButton toFeeder;
    JButton toBin;
    JButton purgeStation;
    JButton dumpPart;
    JButton pickBin;
    //JButton moveKit;
      public JPanel TestPanel() {
        JPanel tester = new JPanel();
        tester.setLayout(new BoxLayout(tester, BoxLayout.Y_AXIS));
        toBin = new JButton("Move Gantry Robot to Bin");///<bin1 to feeder2
        toBin.addActionListener(this);
        tester.add(toBin);
        
        toFeeder = new JButton("Move Gantry Robot to feeder");///<go to feeder
        toFeeder.addActionListener(this);
        tester.add(toFeeder);
        
        pickBin = new JButton("pickBin");///<pick up Bin
        pickBin.addActionListener(this);
        tester.add(pickBin);
        
        dumpPart = new JButton("Dump part");///<when arrived at feeder ,dump part
        dumpPart.addActionListener(this);
        tester.add(dumpPart);
        
        purgeStation = new JButton("Move Gantry Robot to Purge");///<purge
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
/*
    public void processMessage(String msg) {
        // Decide action based on message from server
        if (msg.contains(Message.TEST_CLIENT)) {
            System.out.println("Client test passed.");
        } else if (msg.contains(Message.MOVE_GANTRY_TO_FEEDER)) {
            // Should have received a message like Message.MOVE_GANTRY_TO_FEEDER + ":" + 5
            try {
                int param = Integer.parse(grabParameter(msg));
            } catch (Exception e) {
                System.out.println("Bad message from server");
            }
        }
    }
	*/
public void processMessage(String msg)
{
super.processMessage(msg);
if(msg.contains(Message.MOVE_GANTRY_TO_BIN))
    {
	this.ganbot.moveToBin(Integer.parseInt(this.grabParameter(msg)));
    }else if(msg.contains(Message.GANTRY_CARRY_A_BIN))
    {
	this.ganbot.carryABin(Integer.parseInt(this.grabParameter(msg)));
    }else if(msg.contains(Message.MOVE_GANTRY_TO_FEEDER))
    {
	this.ganbot.moveToFeeder(Integer.parseInt(this.grabParameter(msg)));
    }
}

	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
	
		
		if (ae.getSource() ==toFeeder){
			ganbot.moveToFeeder(2);
		}
		
		if (ae.getSource() ==toBin){
			ganbot.moveToBin(5);
		}
		if (ae.getSource() ==purgeStation){
			ganbot.moveToDump();
			if(ganbot.hasArrivedAtPurge()){
				ganbot.binPurged();
			}
		}
		if (ae.getSource() ==dumpPart){
			//ganbot.supplyPartOnFeeder();
		}

		if (ae.getSource() ==pickBin){
			ganbot.carryABin(5);
		}
	}
	
	/*public void doSupplyPart(int binNum, int feederNum){
		ganbot= graphics.getGantryRobot();
		
		ganbot.moveToBin(binNum);
		ganbot.carryABin(binNum);////////////////////////
		ganbot.moveToFeeder(feederNum);
		ganbot.moveToDump();
	}
	*/
	  public static void main(String[] args){
	       GantryRobotManager mgr = new GantryRobotManager();   
	      }
	  
}


