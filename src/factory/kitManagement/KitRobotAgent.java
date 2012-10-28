package factory.kitManagement;

import agent.Agent;
import factory.Location;
import factory.Part;
import factory.interfaces.KitRobot;

/**
 * This class is the agent for the Kit Robot which gets empty kits from the
 * conveyor and puts it on a palette. It also moves unverified kits onto the
 * verification palette and once verified, moves the complete kit onto the
 * conveyor.
 *
 * @author Alex Young
 * @version 0
 */
public class KitRobotAgent extends Agent implements KitRobot {

    // ********** MESSAGES *********
    @Override
    public void msgNeedPart(Part part) {
    }

    @Override
    public void msgHereAreParts(Part part, int quantity) {
    }

    public void msgHereIsKit(Location loc) {
    }

    // ********* SCHEDULER *********
    @Override
    protected boolean pickAndExecuteAnAction() {
        return false;
    }
    // ********** ACTIONS **********
    // ************ MISC ***********
}
