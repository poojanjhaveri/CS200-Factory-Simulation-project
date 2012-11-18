package factory.factory201.kitManagement;

import agent.Agent;
import factory.factory201.interfaces.Conveyor;
import factory.factory201.interfaces.KitRobot;
import factory.general.Kit;
import factory.general.Message;
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
 * @version 1
 */
public class ConveyorAgent extends Agent implements Conveyor {

    private enum Event {

        emptyKit, verifiedKit
    };
    private List<Kit> fullKits;
    private List<Kit> kits;
    private Kit tempKit;
    private KitRobot kitRobotAgent;
    private Timer removeKits;
    private int kitRobotNeedsKit;

    public ConveyorAgent(String name) {
        super(name);

        kits = Collections.synchronizedList(new ArrayList<Kit>());
        fullKits = new ArrayList<Kit>();
        removeKits = new Timer(1000, new ActionListener() {
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

        removeKits.start();
        removeKits.stop();
        kitRobotNeedsKit = 0;
    }

    // ********** MESSAGES *********
    /**
     * This message is called by the {@link KitRobotAgent} when an empty
     * {@link Kit} is required.
     */
    @Override
    public void msgNeedEmptyKit() {
        kitRobotNeedsKit++;
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
        fullKits.add(kit);
        stateChanged();
    }

    // ********* SCHEDULER *********
    /**
     * Scheduler
     *
     * @return Returns true if action
     */
    @Override
    public boolean pickAndExecuteAnAction() {
        if (kitRobotNeedsKit > 0) {
            giveEmptyKit();
            return true;
        }
        if (!fullKits.isEmpty()) {
            acceptVerifiedKit();
            return true;
        }
        return false;
    }

    // ********** ACTIONS **********
    /**
     * Tells the {@link KitRobotAgent} the location of the next available empty
     * kit.
     */
    private void giveEmptyKit() {
        synchronized(kits) {
            for (Kit k : kits) {
                if (k.status == Kit.Status.empty) {
                    print("Notifying the kit robot that an empty kit: [" + k.name + "] is ready.");
                    kitRobotAgent.msgHereIsEmptyKit(k);
                    kitRobotNeedsKit--;
                    kits.remove(k);
                    break;
                }
            }
        }
        stateChanged();
    }

    /**
     * Adds the completed kit to the list of completed kits (...which will
     * eventually be taken out of the kitting cell).
     */
    private void acceptVerifiedKit() {
        print("Accepting a verified kit: [" + fullKits.get(0).name + "] from the kits robot");
        kits.add(fullKits.remove(0));
        stateChanged();
    }

    // ************ MISC ***********
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

    /**
     * Sets the KitRobotAgent
     *
     * @param agent KitRobotAgent
     */
    public void setKitRobot(KitRobot agent) {
        kitRobotAgent = agent;
    }

    private void DoAddKit(Kit k) {
//	this.client.sendMessage(Message.KAM_ADD_KIT);
    }
    
    private void DoRemoveKit(Kit k) {
//        this.client.sendMessage(Message.KAM_REMOVE_KIT);
    }
}
