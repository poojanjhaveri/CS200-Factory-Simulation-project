//PLEASE DO NOT FORMAT MY CODE IN ANYTHING OTHER THAN ASTYLE
package factory.factory200.gantryRobotManager;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JPanel;

import factory.general.MovingDrawable;
import factory.general.Part;
/**
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
public class GUIGantryRobot extends MovingDrawable{
    public static final String IMAGE_PATH = "pics/robots/gantryrobot.png";

    GUIBin bin;///<null if no bin, otherwise contains the information on the bin
    Boolean extended;///<whether or not the robot has arms extended
    Boolean hasbin;///<whether or not the robot is carrying a bin
    Integer moveto;///<where the gantry robot is heading towards

    GUIGantryRobot() {
	super(GantryRobotManager.ROBOT_INITIAL_X,GantryRobotManager.ROBOT_INITIAL_Y,0.0,GUIGantryRobot.IMAGE_PATH);
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
    	this.bin = new GUIBin(this.getX(),this.getY(),0.0,"pics/binBox"+(num+1)+".png",num+1);
    }
    
    ///<moveToFeeder i which is assigned by agents in Server
    public void moveToFeeder(Integer i) {
        switch(i)
        {
        case 0:
	    this.moveto = 0;
            this.moveTo(GantryRobotManager.FEED0X+40,GantryRobotManager.FEED0Y+50);
            break;
        case 1:
	    this.moveto = 1;
            this.moveTo(GantryRobotManager.FEED1X+40,GantryRobotManager.FEED1Y+50);
            break;
        case 2:
	    this.moveto = 2;
            this.moveTo(GantryRobotManager.FEED2X+40,GantryRobotManager.FEED2Y+50);
            break;
        case 3:
	    this.moveto = 3;
            this.moveTo(GantryRobotManager.FEED3X+40,GantryRobotManager.FEED3Y+50);
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
    
    public boolean hasArrivedAtPurge(){
    	if(this.getX()==GantryRobotManager.DUMPX && this.getY()==GantryRobotManager.DUMPY)
    		return true;
    	else
    		return false;
    }
    
    public void binPurged(){
    	this.bin = null;
    }
    /**
    @brief sets the new destination to the bin dump site
    sets the new destination to the bin dump site
    */
    
    public void moveToDump() {
        this.moveTo(GantryRobotManager.DUMPX,GantryRobotManager.DUMPY);
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
    
    /**
     * paint function
     * @param panel
     * @param g
     */
     public void paintMe(JPanel panel, Graphics2D g){
    	
    	if(this.bin != null){
    		this.bin.getImage().paintIcon(panel, g, this.getCoordinate().getX()-30, this.getCoordinate().getY()-50);
    		if(this.bin.getPart()!=null){
    			this.bin.getPart().getGUIPart().getImage().paintIcon(panel, g, this.getCoordinate().getX()-15,this.getCoordinate().getY()-35);
    		}
        }
    		
    	this.getImage().paintIcon(panel, g, this.getCoordinate().getX(), this.getCoordinate().getY());
    		    		     
     }
            
}
