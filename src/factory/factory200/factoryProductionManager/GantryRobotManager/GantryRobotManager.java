package factory.factory200.factoryProductionManager.GantryRobotManager;
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

import factory.factory200.factoryProductionManager.*;
import factory.general.Message;

import java.awt.Graphics2D;

public class GantryRobotManager{
   
    GRMGraphicPanel graphics;    
    ///<class which includes Gantry Robot Manager Methods
   
//    public void receiveFromServer();///<pull data from server
//   GUIGantryManager gui;///<break the nonormative situations
    public static final Integer FEED0X = 1000;///<x-coordinate of feeder 0
    public static final Integer FEED0Y = 70;///<y-coordinate of feeder 0
    public static final Integer FEED1X = 1000;///<x-coordinate of feeder 1
    public static final Integer FEED1Y = 220;///<y-coordinate of feeder 1
    public static final Integer FEED2X = 1000;///<x-coordinate of feeder 2
    public static final Integer FEED2Y = 370;///<y-coordinate of feeder 2
    public static final Integer FEED3X = 1000;///<x-coordinate of feeder 3
    public static final Integer FEED3Y = 530;///<y-coordinate of feeder 3

    public static final Integer DUMPX = 1160;///<x-coordinate of dump 
    public static final Integer DUMPY = 600;///<y-coordinate of dump 

    public static final Integer BIN_X = 1280;///<x coordinate of all bin locations
    public static final Integer BIN0Y = 1*80-70;///<y coordinate of bin0
    public static final Integer BIN1Y = 2*80-70;///<y coordinate of bin1
    public static final Integer BIN2Y = 3*80-70;///<y coordinate of bin2
    public static final Integer BIN3Y = 4*80-70;///<y coordinate of bin3
    public static final Integer BIN4Y = 5*80-70;///<y coordinate of bin4
    public static final Integer BIN5Y = 6*80-70;///<y coordinate of bin5
    public static final Integer BIN6Y = 7*80-70;///<y coordinate of bin6
    public static final Integer BIN7Y = 8*80-70;///<y coordinate of bin7

    public static final Integer ROBOT_INITIAL_X = 1100;///<spawn x coordinate of gantrybot
    public static final Integer ROBOT_INITIAL_Y = 10;///<spawn y coordinate of gantrybot
	public static final Integer ROBOT_VELOCITY_X = 2;
	public static final Integer ROBOT_VELOCITY_Y = 2;
	public static final Double ROBOT_TURN_RATE = 0.0;
    
    public GantryRobotManager() {
        graphics = new GRMGraphicPanel();
     }
	
	public void paint(GraphicsPanel panel, Graphics2D g){
		graphics.paint(panel, g);
		//System.out.println("printing");
	}
	
	public GRMGraphicPanel getGraph(){
		return graphics;
	}

	public void processMessage(String msg) {
		//		   super.processMessage(msg);
			   if(msg.contains(Message.MOVE_GANTRY_TO_BIN))
			   {
				   this.graphics.gbot.moveToBinCommand(Integer.parseInt(this.grabParameter(msg)));
			   }
			   else if(msg.contains(Message.GANTRY_CARRY_A_BIN))
			   {
				   pickUpBin(Integer.parseInt(this.grabParameter(msg)));
			   }
			   else if(msg.contains(Message.MOVE_GANTRY_TO_FEEDER))
			   {
				   this.graphics.gbot.moveToFeederCommand(Integer.parseInt(this.grabParameter(msg)));
			   }
			   else if(msg.contains(Message.SUPPLY_PART_ON_FEEDER)){
				   this.graphics.gbot.supplyPartOnFeederCommand(Integer.parseInt(this.grabParameter(msg)));
			   }
			   
			   else if(msg.contains(Message.MOVE_GANTRY_TO_DUMP)){
				   purgeBin();//this.ganbot.purgeBinCommand();
			   }
			   else if(msg.contains(Message.FREEZE_GANTRY_ROBOT))
		       {
			   this.graphics.gbot.setBreakState(true);
		       }else if(msg.contains(Message.UNFREEZE_GANTRY_ROBOT))
		       {
		    	   this.graphics.gbot.setBreakState(false);
		       }
                           
                               
		   }
	
	
	/**
     * ad hoc addition, copy-paste from manager :/
     * @brief standard way to grab parameter data via protocol Use this method
     * in an if-statement in processMessage.
     * @param msg - the message to grab a parameter from.
     * @return the parameter from the received message
     */
    public String grabParameter(String msg) {
        return msg.substring(msg.indexOf(":") + 1);
    }
    
    public void moveToBin(Integer binIndex){
		   /*ganbot.moveToBin(binIndex);
		   if(!ganbot.moving()){//arrivedAtBin(binIndex)){
			   ganbot.pickUpBin(binIndex);
		   }*/
		  this.graphics.gbot.moveToBinCommand(binIndex);
	   }

	   public void moveToFeeder(Integer feederIndex){
		   /*ganbot.moveToFeeder(feederIndex);
		   if(ganbot.arrivedAtFeeder(feederIndex)){
			   ganbot.supplyPartOnFeeder();
		   }*/
		   this.graphics.gbot.moveToFeederCommand(feederIndex);
	   }

	   public void pickUpBin(Integer binIndex){
		   this.graphics.gbot.pickUpBin(binIndex);
		   this.graphics.binIsCarried(binIndex);
	   }
	   public void purgeBin(){
		 // Integer binIndex=this.graphics.getBinCarriedIndex();		 
		  this.graphics.gbot.purgeBinCommand();		  
	   }
	   
}