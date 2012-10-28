
package factory.kitManagement;

import agent.Agent;
import factory.Kit;
import factory.interfaces.Conveyor;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is the agent for the {@link Conveyor} which brings empty
 * {@link Kit}s into the kitting cell and takes complete kits out of the kitting
 * cell.
 *
 * @author Alex Young
 * @version 0
 */
public class ConveyorAgent extends Agent implements Conveyor {

    private enum Event {

        emptyKit, verifiedKit
    };
    private List<Event> eventQueue = new ArrayList<Event>();
    private List<Kit> kits = new ArrayList<Kit>();
    private Kit tempKit;
    private KitRobotAgent kitRobotAgent;

    // ********** MESSAGES *********
    /**
     * This message is called by the {@link KitRobotAgent} when an empty
     * {@link Kit} is required.
     */
    @Override
    public void msgNeedEmptyKit() {
        eventQueue.add(Event.emptyKit);
        stateChanged();
    }

    /**
     * This message is called by the {@link KitRobotAgent} when a verified
     * {@link Kit} is ready to be taken away.
     *
     * @param kit This is a verified and complete kit.
     */
    @Override
    public void msgHereIsVerifiedKit(Kit kit) {
        eventQueue.add(Event.verifiedKit);
        tempKit = kit;
        stateChanged();
    }

    // ********* SCHEDULER *********
    /**
     * Scheduler
     *
     * @return Returns true if action
     */
    @Override
    protected boolean pickAndExecuteAnAction() {
        for (Event e : eventQueue) {
            if (e == Event.emptyKit) {
                giveEmptyKit();
                return true;
            } else if (e == Event.verifiedKit) {
                acceptVerifiedKit();
                return true;
            }
        }

        return false;
    }

    // ********** ACTIONS **********
    /**
     * Tells the {@link KitRobotAgent} the location of the next available empty
     * kit.
     */
    private void giveEmptyKit() {
//        DoGiveEmptyKit();
//        Location loc = kits.get(0).getLocation();
//        kitRobotAgent.msgHereIsKit(loc);
        stateChanged();
    }

    /**
     * Adds the completed kit to the list of completed kits (...which will
     * eventually be taken out of the kitting cell).
     */
    private void acceptVerifiedKit() {
//        DoAcceptVerifiedKit();
        kits.add(tempKit);
        stateChanged();
    }

    // ************ MISC ***********
    /**
     * Sets the KitRobotAgent
     *
     * @param agent KitRobotAgent
     */
    public void setKitRobotAgent(KitRobotAgent agent) {
        kitRobotAgent = agent;
    }
}
