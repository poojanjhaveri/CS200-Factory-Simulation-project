package factory.kitManagement;

import agent.Agent;
import factory.interfaces.Camera;

/**
 * This class is the agent for the Camera which verifies the following: 1) All
 * of the parts in a nest are of the proper type; 2) All of the parts in a nest
 * are in acceptable condition; 3) Each completed kit meets the requirements of
 * the configuration specified by the FCS.
 *
 * @author Alex Young
 * @version 0
 */
public class CameraAgent extends Agent implements Camera {

    // ********** MESSAGES *********
    // ********* SCHEDULER *********
    @Override
    protected boolean pickAndExecuteAnAction() {
        return false;
    }
    // ********** ACTIONS **********
    // ************ MISC ***********
}
