
/**
 * Gantry Robot Manager takes care of movement of gantry robot, bins, purge
 * station, feeders. Inner classes are GantryRobot and GantryState, GantryState
 * has everything needed to have animation: Bins, Feeders, GantryRobot movement
 * info The capability to purge a bin when it is without any parts, to drop
 * parts from bin to feeder
 *
 * @brief Gantry Robot Manager takes care of movement of gantry robot, bins,
 * purge station, feeders.
 * @author Yuting Liu
 */
public class GantryRobotManager extends JFrame implements Manager {

    JPanel swingpanel;///<break the nonormative situations
    JPanel graphicalpanel;///<shows the animations of the gantry robot. has bins, gantry robot, and feeders.
    Socket s;///<connection to the server
    JButton purgeemptyparts;
    JButton breakFeeder;
    JButton breakbins;
    JButton breakgantryrobot;
    GantryRobot gbot;///<class which includes Gantry Robot Manager Methods
}
