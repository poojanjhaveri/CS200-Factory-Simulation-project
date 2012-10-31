//PLEASE DO NOT FORMAT MY CODE IN ANYTHING OTHER THAN ASTYLE
package factory.agentGUI;

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
public class GuiGantry {

    GuiBin bin;///<null if no bin, otherwise contains the information on the bin
    Boolean extended;///<whether or not the robot has arms extended
    Boolean hasbin;///<whether or not the robot is carrying a bin
    Integer movetoX;///<X-coordinate destination of the gantry robot
    Integer movetoY;///<Y-coordinate destination of gantry robot

    GuiGantry() {
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
            this.moveTo(GantryRobotManager.FEED0X,GantryRobotManager FEED0Y);
            break;
        case 1:
            this.moveTo(GantryRobotManager.FEED1X,GantryRobotManager FEED1Y);
            break;
        case 2:
            this.moveTo(GantryRobotManager.FEED2X,GantryRobotManager FEED2Y);
            break;
        case 3;
            this.moveTo(GantryRobotManager.FEED3X,GantryRobotManager.FEED3Y);
            break;
        default:
            System.out.println("ERROR: Attempting to move GuiGantryRobot to nonexistent feeder " + i);
        }
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
}