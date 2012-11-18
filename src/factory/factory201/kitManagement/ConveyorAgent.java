package factory.factory201.kitManagement;

import agent.Agent;
import factory.factory201.interfaces.Conveyor;
import factory.factory201.interfaces.KitRobot;
import factory.general.Kit;
import factory.general.Message;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @brief This class is the agent for the {@link Conveyor} which brings empty
 * {@link Kit}s into the kitting cell and takes complete kits out of the kitting
 * cell.
 *
 * @author Alex Young
 * @version 1
 */
public class ConveyorAgent extends Agent implements Conveyor {

    private KitRobot kitRobotAgent;
    private List<Kit> kitList;
    private int kitRequestsFromKitRobot;

    public ConveyorAgent(String name) {
        super(name);

        kitList = Collections.synchronizedList(new ArrayList<Kit>());
        kitRequestsFromKitRobot = 0;
    }

    // ********** MESSAGES *********
    /**
     * This message is called by the {@link KitRobotAgent} when an empty
     * {@link Kit} is required.
     */
    @Override
    public void msgNeedEmptyKit() {
        kitRequestsFromKitRobot++;
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
        print("Accepting a verified kit: [" + kit.name + "] from the kits robot");
        synchronized (kitList) {
            kitList.add(kit);
        }
        stateChanged();
    }

    // ********* SCHEDULER *********
    /**
     * Scheduler\
     *
     * @return Returns true if action
     */
    @Override
    public boolean pickAndExecuteAnAction() {
        if (kitRequestsFromKitRobot > 0) {
            giveEmptyKit();
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
        synchronized (kitList) {
            for (Kit k : kitList) {
                if (k.status == Kit.Status.empty) {
                    print("Notifying the kit robot that an empty kit: [" + k.name + "] is ready.");
                    kitRobotAgent.msgHereIsEmptyKit(k);
                    kitRequestsFromKitRobot--;
                    kitList.remove(k);
                    break;
                }
            }
        }
        stateChanged();
    }

    // ************ MISC ***********
    public void generateKit(int num) {
        num--;
        for (int i = 0; i < num; i++) {
            Kit k = new Kit("Kit " + i);
            kitList.add(k);
            DoAddKit(k);
        }
    }

    public void generateKit(String name) {
        Kit k = new Kit("Kit " + name);
        kitList.add(k);
    }

    public void removeKit(String name) {
        for (Kit k : kitList) {
            if (k.name.equals(name)) {
                kitList.remove(k);
                break;
            }
        }
    }

    public void removeAllVerifiedKits() {
        for (Kit k : kitList) {
            if (k.status == Kit.Status.verified) {
                kitList.remove(k);
            }
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
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_ADD_KIT);
            this.fpm.sendMessage(Message.KAM_ADD_KIT);
        } else {
//            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }

    private void DoRemoveKit(Kit k) {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_REMOVE_KIT);
            this.fpm.sendMessage(Message.KAM_REMOVE_KIT);
        } else {
//            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }
}
