//PLEASE DO NOT FORMAT MY CODE IN ANYTHING OTHER THAN ASTYLE
package factory.factory200.gantryRobotManager;
/**
 * @author Yuting Liu
 */
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;

import factory.factory200.kitAssemblyManager.KAMGraphicPanel;
import factory.general.GUIRobot;
import factory.general.MovingDrawable;
import factory.general.Part;
/**
 * 
 * Orders identification
 * 0-7 - go to bin [#]
 * 8-15 - pick up bin [#]
 * 16-19 - move to feeder [#]
 * 
 * 20 - move to default
 * 21-24 - drop off at feeder [#]
 * 25 -move to dump
 * 26 - purge bin
30 - finish bin command
31 - finish move to feeder command
32 - finish move to dump
 * 
 * The gantry robot has basic bin movement and emptying capabilities. In
 * relation to the simulation GUI, the gantry robot has <ul> <li>arm extension
 * functionality (purely aesthetic)</li> <li>bin picking functionality</li>
 * <li>bin dropping functionality</li> <li>bin opening (dumping)
 * functionality.</li> </ul> <img src="../img/image03.png" alt="unused Gantry
 * robot with arms retracted"/>
<img src="../img/image07.png" alt="Gantry robot in use and carring a bin"/>

 *
 * @brief shared Robot that manipulates part bins
 * @author YiWei Roy Zheng
 */
public class GUIGantryRobot extends GUIRobot{
    public static final String IMAGE_PATH = "pics/robots/gantryrobot.png";

    GUIBin bin;///<null if no bin, otherwise contains the information on the bin
    Boolean extended;///<whether or not the robot has arms extended
    Boolean hasbin;///<whether or not the robot is carrying a bin
    Integer moveto;///<where the gantry robot is heading towards

    public GUIGantryRobot() {
	super(GantryRobotManager.ROBOT_INITIAL_X,GantryRobotManager.ROBOT_INITIAL_Y,GUIGantryRobot.IMAGE_PATH);
	this.extended = false;
	this.hasbin = false;
	this.bin = null;
	this.setConstants(GantryRobotManager.ROBOT_VELOCITY_X, GantryRobotManager.ROBOT_VELOCITY_Y, GantryRobotManager.ROBOT_TURN_RATE);
    }
    /**
    @brief sets a new feeder destination for the gantry robot
    sets a new feeder destination coordinate for gantry robot, the passed Integer specifies which feeder to head to
    @param i feeder number
    */
    
    ///<Once gantry robot gets to a bin, it picks up the bin 
    public void pickUpBin(Integer num){
    	this.bin = new GUIBin(this.getX(),this.getY(),0.0, "pics/emptybox.png",num+1);
    	//
    	//"pics/binBox"+(num+1)+".png",num+1);
    }
    
    ///<moveToFeeder i which is assigned by agents in Server
    public void moveToFeeder(Integer i) {
        switch(i)
        {
        case 0:
	    this.moveto = 0;
            this.moveTo(GantryRobotManager.FEED0X,GantryRobotManager.FEED0Y+50);
            break;
        case 1:
	    this.moveto = 1;
            this.moveTo(GantryRobotManager.FEED1X,GantryRobotManager.FEED1Y+50);
            break;
        case 2:
	    this.moveto = 2;
            this.moveTo(GantryRobotManager.FEED2X,GantryRobotManager.FEED2Y+50);
            break;
        case 3:
	    this.moveto = 3;
            this.moveTo(GantryRobotManager.FEED3X,GantryRobotManager.FEED3Y+50);
            break;
        default:
            System.out.println("ERROR: Attempting to move GuiGantryRobot to nonexistent feeder " + i);
        }
    }
    
    ///<moveToBin i which is assigned by agents in Server
    public void moveToBin(Integer i)
    {
	switch(i)
	    {
	    case 0:this.moveTo(GantryRobotManager.BIN_X-100,GantryRobotManager.BIN0Y);
            break;
	    case 1:this.moveTo(GantryRobotManager.BIN_X-100,GantryRobotManager.BIN1Y);
            break;
	    case 2:this.moveTo(GantryRobotManager.BIN_X-100,GantryRobotManager.BIN2Y);
            break;
	    case 3:this.moveTo(GantryRobotManager.BIN_X-100,GantryRobotManager.BIN3Y);
            break;
	    case 4:this.moveTo(GantryRobotManager.BIN_X-100,GantryRobotManager.BIN4Y);
            break;
	    case 5:this.moveTo(GantryRobotManager.BIN_X-100,GantryRobotManager.BIN5Y);
            break;
	    case 6:this.moveTo(GantryRobotManager.BIN_X-100,GantryRobotManager.BIN6Y);
            break;
	    case 7:this.moveTo(GantryRobotManager.BIN_X-100,GantryRobotManager.BIN7Y);
            break;
	    }
    }
    
    
    /**
    @brief Another moving statement which has binNumber b, and feeder number f to move
    */
    
    public void moveBinToFeeder(Integer b, Integer f)
    {
    	moveTo(GantryRobotManager.FEED1X,GantryRobotManager.FEED1Y);
    }
   
    
    public void binPurged(){
    	this.bin = null;
    }
    /**
    @brief sets the new destination to the bin dump site
    sets the new destination to the bin dump site
    */
    

	public boolean arrivedAtFeeder(Integer feederNum){
		
		if(this.getCoordinate().getX()==75 && this.getCoordinate().getY() == 50+feederNum *150){
			return true;
		}
		else
			return false;
		
	}	
	
	public boolean arrivedAtBin(Integer binNum){
		
			if(this.getCoordinate().getX() == 350 && this.getCoordinate().getY() == 30 + 80*binNum){
				return true;
			}
			else return false;
	}
	
	public void supplyPartOnFeeder(Integer feederIndex){
		this.bin.setPartToNull();
		this.bin.setX(20);
		this.bin.setY(50+feederIndex*150);
	}
	
    public void moveToDump() {
    	this.bin.setX(this.cords.getX());
    	this.bin.setY(this.cords.getY());
        this.moveTo(210,550);//,GantryRobotManager.DUMPY);
    }
    
    
    public void RobotInitialization(){
    	this.moveTo(GantryRobotManager.ROBOT_INITIAL_X,GantryRobotManager.ROBOT_INITIAL_Y);
    }
    
    public boolean arrivedAtPurge(){
    	if(this.getCoordinate().getX()==210 && this.getCoordinate().getY()==550){
    		return true;
    	}
    	else 
    		return false;
    }
    /**
    brief extends the arm
    extends the arm
    */
    public void extend() {
        this.extended = true;
    }
    
    /**
    @brief retracts the arm
    retracts the arm
    */
    public void retract() {
        this.extended = false;
    }
    
    /**
    @brief switches arm state
    retracts if extended, extends if retracted
     */
    public void toggleArm()
    {
        this.extended = !this.extended;
    }
    
    public boolean armExtended()
    {
        return this.extended;
    }
    /**
     * @return GUIBin objects in gantry robot 
     */
    public GUIBin getBin(){
    	return bin;
    }
    
    public Boolean performOrder()
    {
    	if(this.getOrder() > -1 && this.getOrder() < 8){
    		this.moveToBin(this.getOrder());
    		this.popOrder();
    		return true;
    	}
    	/*if(this.getOrder() < 16 && this.getOrder() > 7)
		{
			this.pickUpBin(this.getOrder()-8);
			this.popOrder();
		}else if(this.getOrder() < 25 && this.getOrder() > 20)
		{
			this.supplyPartOnFeeder();//this.gbot.getOrder()-21);
			this.popOrder();
		}*/
    	if(this.getOrder() > 15 && this.getOrder() < 20)
    	{
    		this.moveToFeeder(this.getOrder()-16);
    		this.popOrder();
    		return true;
    	}
    	if(this.getOrder() == 25)
    	{    	
    		System.out.println("Performing move order to the Dump");
    		this.moveToDump();
    		this.popOrder();
    		return true;
    	}
    	
    	return false;
    	
    }
    public void moveToBinCommand(Integer index)
    {
    	this.orders.add(index);
	this.orders.add(30);
    	//this.orders.add(index+8);
    }
    
    public void pickUpBinCommand(Integer index){
    	this.orders.add(index+8);
    }
    public void moveToFeederCommand(Integer index)
    {
    	this.orders.add(index+16);
	this.orders.add(31);
    	//this.orders.add(index+21);
    }
    
    public void supplyPartOnFeederCommand(Integer index){
    	this.orders.add(index+21);
    }
    
    public void purgeBinCommand()
    {
    	this.orders.add(25);
    	this.orders.add(26);
	this.orders.add(32);
    }
    private void moveToDefault() {
        this.moveTo(GantryRobotManager.ROBOT_INITIAL_X, GantryRobotManager.ROBOT_INITIAL_Y);
    }
    
    public void checkDefault()
    {
        if(this.orders.size() == 0){
            
          this.orders.add(20);
        }
    }
    
    	
    
    /**
     * paint function
     * @param panel
     * @param g
     */
     public void paintMe(JPanel panel, Graphics2D g){
    	
    	if(this.bin != null){
    		this.bin.getImage().paintIcon(panel, g, this.getCoordinate().getX()-10, this.getCoordinate().getY()-30);
    		if(this.bin.getPart()!=null){
    			this.bin.getPart().getGUIPart().getImage().paintIcon(panel, g, this.getCoordinate().getX()+5,this.getCoordinate().getY()-15);
    		}
        }
    		
    	this.getImage().paintIcon(panel, g, this.getCoordinate().getX(), this.getCoordinate().getY());
    		    		     
     }
            
}
