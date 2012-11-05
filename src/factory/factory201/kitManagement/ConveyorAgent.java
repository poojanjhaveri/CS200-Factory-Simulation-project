package factory.factory201.kitManagement;

import agent.Agent;
import factory.factory200.kitAssemblyManager.KitAssemblyManager;
import factory.factory200.kitAssemblyManager.KitDeliveryStation;
import factory.factory201.interfaces.Conveyor;
import factory.factory201.interfaces.KitRobot;
import factory.general.Kit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Timer;

/**
 * @brief This class is the agent for the {@link Conveyor} which brings empty
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
    private List<Event> eventQueue = Collections.synchronizedList(new ArrayList<Event>());
    private List<Kit> kits = new ArrayList<Kit>();
    private Kit tempKit;
    private KitRobot kitRobotAgent;
    private Timer removeKits = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
            for (Kit k : kits) {
                if (k.status == Kit.Status.verified) {
                    kits.remove(k);
                    break;
                }
            }
        }
    });
    private KitAssemblyManager KAM;
    
    public ConveyorAgent() {
        removeKits.start();
        removeKits.stop();
    }

    // ********** MESSAGES *********
    /**
     * This message is called by the {@link KitRobotAgent} when an empty
     * {@link Kit} is required.
     */
    @Override
    public void msgNeedEmptyKit() {
        synchronized (eventQueue) {
            eventQueue.add(Event.emptyKit);
        }
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
        synchronized (eventQueue) {
            eventQueue.add(Event.verifiedKit);
        }
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
        synchronized (eventQueue) {
            for (Event e : eventQueue) {
                if (e == Event.emptyKit) {
                    giveEmptyKit();
                    return true;
                }
            }
            for (Event e : eventQueue) {
                if (e == Event.verifiedKit) {
                    acceptVerifiedKit();
                    return true;
                }
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
        for (Kit k : kits) {
            if (k.status == Kit.Status.empty) {
                kitRobotAgent.msgHereIsEmptyKit(k);
                kits.remove(k);
                break;
            }
        }
        stateChanged();
    }

    /**
     * Adds the completed kit to the list of completed kits (...which will
     * eventually be taken out of the kitting cell).
     */
    private void acceptVerifiedKit() {
        kits.add(tempKit);
        stateChanged();
    }

    // ************ MISC ***********
    /**
     * Sets the KitRobotAgent
     *
     * @param agent KitRobotAgent
     */
    public void setKitRobotAgent(KitRobot agent) {
        kitRobotAgent = agent;
    }

    public void generateKit(int num) {
        num--;
        for (int i = 0; i < num; i++) {
            Kit k = new Kit("Kit " + i);
            kits.add(k);
            DoAddKit(k);
        }
    }

    public void generateKit(String name) {
        Kit k = new Kit("Kit " + name);
        kits.add(k);
    }

    public void removeKit(String name) {
        for (Kit k : kits) {
            if (k.name.equals(name)) {
                kits.remove(k);
                break;
            }
        }
    }

    public void removeAllVerifiedKits() {
        for (Kit k : kits) {
            if (k.status == Kit.Status.verified) {
                kits.remove(k);
            }
        }
    }

    public void toggleAutoRemove() {
        if (removeKits.isRunning()) {
            removeKits.stop();
        } else {
            removeKits.restart();
        }
    }
    
    public void setKitAssemblyManager(KitAssemblyManager KAM) {
        this.KAM = KAM;
    }
    
    private void DoAddKit(Kit k) {
        //Needs to be done
    }
}
