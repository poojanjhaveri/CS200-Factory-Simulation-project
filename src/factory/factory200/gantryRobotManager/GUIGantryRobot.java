//PLEASE DO NOT FORMAT MY CODE IN ANYTHING OTHER THAN ASTYLE
package factory.factory200.gantryRobotManager;

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
public class GUIGantryRobot {
    public static final String IMAGE_PAGE = "res/gantryrobot.png";

    GUIBin bin;///<null if no bin, otherwise contains the information on the bin
    Boolean extended;///<whether or not the robot has arms extended
    Boolean hasbin;///<whether or not the robot is carrying a bin
    Integer moveto;///<where the gantry robot is heading towards

    GUIGantryRobot() {
	super(GantryRobotManager.ROBOT_INITIAL_X,GantryRobotManager.ROBOT_INITIAL_Y,0.0,GUIGantryRobot.IMAGE_PATH);
	this.extended = false;
	this.hasbin = false;
	this.bin = null;
    }
    /**
    @brief sets a new feeder destination for the gantry robot
    sets a new feeder destination coordinate for gantry robot, the passed Integer specifies which feeder to head to
    @param i feeder number
    */
    public void moveToFeeder(Integer i) {
        switch(i)
        {
        case 0:
	    this.moveto = 0;
            this.moveTo(GantryRobotManager.FEED0X,GantryRobotManager.FEED0Y);
            break;
        case 1:
	    this.moveto = 1;
            this.moveTo(GantryRobotManager.FEED1X,GantryRobotManager.FEED1Y);
            break;
        case 2:
	    this.moveto = 2;
            this.moveTo(GantryRobotManager.FEED2X,GantryRobotManager.FEED2Y);
            break;
        case 3:
	    this.moveto = 3;
            this.moveTo(GantryRobotManager.FEED3X,GantryRobotManager.FEED3Y);
            break;
        default:
            System.out.println("ERROR: Attempting to move GuiGantryRobot to nonexistent feeder " + i);
        }
    }
    public void moveToBin(Integer i)
    {
	switch(i)
	    {
	    case 0:this.moveTo(GantryRobotManager.BIN_X,GantryRobotManager.BIN0Y);
            break;
	    case 1:this.moveTo(GantryRobotManager.BIN_X,GantryRobotManager.BIN1Y);
            break;
	    case 2:this.moveTo(GantryRobotManager.BIN_X,GantryRobotManager.BIN2Y);
            break;
	    case 3:this.moveTo(GantryRobotManager.BIN_X,GantryRobotManager.BIN3Y);
            break;
	    case 4:this.moveTo(GantryRobotManager.BIN_X,GantryRobotManager.BIN4Y);
            break;
	    case 5:this.moveTo(GantryRobotManager.BIN_X,GantryRobotManager.BIN5Y);
            break;
	    case 6:this.moveTo(GantryRobotManager.BIN_X,GantryRobotManager.BIN6Y);
            break;
	    case 7:this.moveTo(GantryRobotManager.BIN_X,GantryRobotManager.BIN7Y);
            break;
	    }
    }
    public void moveBinToFeeder(Integer b, Integer f)
    {

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
    public void armExtended()
    {
        return this.extended;
    }

    public void paintMe(JPanel panel, Graphics2D g){
        Graphics2D g2d = (Graphics2D)g.create();
	if(this.bin != null)
	    {
		this.bin.getImage().paintIcon(panel, g2d, this.getCoordinate().getX(), this.getCoordinate().getY());
	    }
	this.getImage().paintIcon(panel, g2d, this.getCoordinate().getX(), this.getCoordinate().getY());
        g2d.dispose();
    }
}
