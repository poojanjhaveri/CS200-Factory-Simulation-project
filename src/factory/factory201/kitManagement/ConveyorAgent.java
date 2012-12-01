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
 * Agent for the conveyor.
 *
 * This class is the agent for the Conveyor which brings empty Kits into the
 * kitting cell and takes complete kits out of the kitting cell.
 *
 * @author Alex Young
 * @version 1
 * @brief Agent for the conveyor
 */
public class ConveyorAgent extends Agent implements Conveyor {

    private KitRobot kitRobotAgent;
    private List<Kit> kitList;
    private int kitRequestsFromKitRobot;
    private int generateKits;

    public ConveyorAgent(String name) {
        super(name);
        kitList = Collections.synchronizedList(new ArrayList<Kit>());
        kitRequestsFromKitRobot = 0;
        generateKits = 0;
    }

    // ********** MESSAGES *********
    @Override
    public void msgNeedEmptyKit() {
        kitRequestsFromKitRobot++;
        stateChanged();
    }

    public void msgGenerateKit(int numKits) {
        generateKits = numKits;
        stateChanged();
    }

    @Override
    public void msgHereIsVerifiedKit(Kit kit) {
        synchronized (kitList) {
            kitList.add(kit);
        }
        stateChanged();
    }

    // ********* SCHEDULER *********
    @Override
    public boolean pickAndExecuteAnAction() {
        if (generateKits > 0) {
            generateKits();
            return true;
        }
        if (kitRequestsFromKitRobot > 0) {
            giveEmptyKit();
            return true;
        }
        return false;
    }

    // ********** ACTIONS **********
    private void giveEmptyKit() {
        for (Kit k : kitList) {
            if (k.status == Kit.Status.empty) {
                kitRobotAgent.msgHereIsEmptyKit(k);
                print("Notifying the kit robot that an empty kit: [" + k.name + "] is ready.");
                kitList.remove(k);
                break;
            }
        }

        kitRequestsFromKitRobot--;
        stateChanged();
    }

    private void generateKits() {
        for (int i = 0; i < generateKits; i++) {
            Kit k = new Kit("Kit " + i);
            kitList.add(k);
            print("Generating a new empty kit: [" + k.name + "] is ready.");
            DoAddKit(k);
        }
        generateKits = 0;
        stateChanged();
    }

    // ************ MISC ***********

    public void setKitRobot(KitRobot agent) {
        kitRobotAgent = agent;
    }

    public List<Kit> getKitList() {
        return this.kitList;
    }

    private void DoAddKit(Kit k) {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_ADD_KIT);
            this.fpm.sendMessage(Message.KAM_ADD_KIT);
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }
}
