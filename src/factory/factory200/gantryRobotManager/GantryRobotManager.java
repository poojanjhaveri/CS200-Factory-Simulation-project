package factory.factory200.gantryRobotManager;
/**
 * Gantry Robot Manager takes care of movement of gantry robot, bins, purge
 * station and feeders. Instances in this manager are GUIGantryRobot,
 * GRMGraphicPanel, GUIBin, GUIFeeder, PurgeStation
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
import factory.general.Message;
import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class GantryRobotManager extends Manager implements ActionListener {
   
    GRMGraphicPanel graphics;  
     JTabbedPane tabbedPane;
     JButton breakGantryRobot;

 //   GantryState gs;
    GUIBin bin;
    public GUIGantryRobot ganbot; ///<class which includes Gantry Robot Manager Methods
    PurgeStation ps;
    int purgeStationx;//x coordinate of purgeStation
    int purgeStationy;//y coordinate of purgeStation

//   public void receiveFromServer();///<pull data from server
//   GUIGantryManager gui;///<break the nonormative situations
   
    public static final Integer FEED0X = 75;///<x-coordinate of feeders' right side
    public static final Integer FEED0Y = 50;///<y-coordinate of feeder 0
    public static final Integer FEED1X = 75;///<x-coordinate of feeder 1
    public static final Integer FEED1Y = 200;///<y-coordinate of feeder 1
    public static final Integer FEED2X = 75;///<x-coordinate of feeder 2
    public static final Integer FEED2Y = 350;///<y-coordinate of feeder 2
    public static final Integer FEED3X = 75;///<x-coordinate of feeder 3
    public static final Integer FEED3Y = 500;///<y-coordinate of feeder 3

    public static final Integer DUMPX = 260;///<x-coordinate of dump 
    public static final Integer DUMPY = 700;///<y-coordinate of dump 

    public static final Integer BIN_X = 400;///<x coordinate of all bin locations
    public static final Integer BIN0Y = 30;///<y coordinate of bin0
    public static final Integer BIN1Y = 110;///<y coordinate of bin1
    public static final Integer BIN2Y = 190;///<y coordinate of bin2
    public static final Integer BIN3Y = 270;///<y coordinate of bin3
    public static final Integer BIN4Y = 350;///<y coordinate of bin4
    public static final Integer BIN5Y = 430;///<y coordinate of bin5
    public static final Integer BIN6Y = 510;///<y coordinate of bin6
    public static final Integer BIN7Y = 590;///<y coordinate of bin7

    public static final Integer ROBOT_INITIAL_X = 150;///<spawn x coordinate of gantrybot
    public static final Integer ROBOT_INITIAL_Y = 10;///<spawn y coordinate of gantrybot
    
    public static final Integer ROBOT_VELOCITY_X = 2;
    public static final Integer ROBOT_VELOCITY_Y = 2;
    public static final Double ROBOT_TURN_RATE = 0.0;
    
    /**
     * set graphic panel
     */
    public GantryRobotManager() {
        graphics= new GRMGraphicPanel(this);        
        setLayout(new GridLayout(1, 2));  
        ganbot = graphics.getGantryRobot();
        
         //@author : Poojan
        
        this.tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Animation", this.graphics);
        GUINonNormGAM nonGUI = new GUINonNormGAM();
        
        tabbedPane.addTab("Non-normative", nonGUI);
        tabbedPane.setPreferredSize(new Dimension(600,800));
        this.add(tabbedPane);
        
        
        //   this.add(graphics);

      //  add(graphics);
        ps = new PurgeStation();
        int x = 500;
        setSize(480,750);//+ x, 700);
        graphics.setVisible(true);
        //add(TestPanel());
        //change TEST to just graphicPanel (above)

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.sendToServer(Message.IDENTIFY_GANTRYROBOTMANAGER);
        
        breakGantryRobot= new JButton();
    }
    
   /* toFeeder;
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
                
        pickBin = new JButton("pickBin");///<pick up Bin
        pickBin.addActionListener(this);
        tester.add(pickBin);
        
        toFeeder = new JButton("Move Gantry Robot to feeder");///<go to feeder
        toFeeder.addActionListener(this);
        tester.add(toFeeder);
        dumpPart = new JButton("Dump part");///<when arrived at feeder ,dump part
        dumpPart.addActionListener(this);
        tester.add(dumpPart);
        
        purgeStation = new JButton("Move Gantry Robot to Purge");///<purge
        purgeStation.addActionListener(this);
        tester.add(purgeStation);

        return tester;
    }*/
    
    /*
     * process message sent by server
     */
     public void processMessage(String msg) {
	   super.processMessage(msg);
           System.out.println(msg);
	   if(msg.contains(Message.MOVE_GANTRY_TO_BIN))
	   {
		   this.ganbot.moveToBinCommand(Integer.parseInt(this.grabParameter(msg)));
	   }
	   else if(msg.contains(Message.GANTRY_CARRY_A_BIN))
	   {
		   pickUpBin(Integer.parseInt(this.grabParameter(msg)));
	   }
	   else if(msg.contains(Message.MOVE_GANTRY_TO_FEEDER))
	   {
		   this.ganbot.moveToFeederCommand(Integer.parseInt(this.grabParameter(msg)));
	   }
	   else if(msg.contains(Message.SUPPLY_PART_ON_FEEDER)){
		   this.ganbot.supplyPartOnFeederCommand(Integer.parseInt(this.grabParameter(msg)));
	   }
	   
	   else if(msg.contains(Message.MOVE_GANTRY_TO_DUMP)){
		   purgeBin();//this.ganbot.purgeBinCommand();
	   }
   }
   
     /*
      * default actionPerformed
      * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
      */
	public void actionPerformed(ActionEvent ae) {
		
		if (ae.getSource()==breakGantryRobot){
			this.graphics.gbot.setBreakState();
		}
	/*	if (ae.getSource() ==toFeeder){
			ganbot.moveToFeederCommand(0);		}		
		if (ae.getSource() ==toBin){
			ganbot.moveToBinCommand(1);		
		}
		if (ae.getSource() ==purgeStation){
			this.purgeBin();//ganbot.purgeBinCommand();
			}		
		if (ae.getSource() ==dumpPart){
			ganbot.supplyPartOnFeederCommand(0);
		//Integer binIndex=this.graphics.getBinCarriedIndex();
			//this.graphics.binIsPurged(binIndex);
		}
		if (ae.getSource() ==pickBin){
			pickUpBin(1);		
		}
		*/
}
	
	
	  public static void main(String[] args){
	       GantryRobotManager mgr = new GantryRobotManager();   
	      }
	  

	   public void moveToBin(Integer binIndex){
		   /*ganbot.moveToBin(binIndex);
		   if(!ganbot.moving()){//arrivedAtBin(binIndex)){
			   ganbot.pickUpBin(binIndex);
		   }*/
		  ganbot.moveToBinCommand(binIndex);
	   }

	   public void moveToFeeder(Integer feederIndex){
		   /*ganbot.moveToFeeder(feederIndex);
		   if(ganbot.arrivedAtFeeder(feederIndex)){
			   ganbot.supplyPartOnFeeder();
		   }*/
		   ganbot.moveToFeederCommand(feederIndex);
	   }

	   public void pickUpBin(Integer binIndex){
		   ganbot.pickUpBin(binIndex);
		   this.graphics.binIsCarried(binIndex);
	   }
	   public void purgeBin(){
		 // Integer binIndex=this.graphics.getBinCarriedIndex();		 
		  ganbot.purgeBinCommand();		  
	   }

}


