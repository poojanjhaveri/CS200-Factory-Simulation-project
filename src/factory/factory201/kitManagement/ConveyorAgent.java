package factory.factory201.kitManagement;

import agent.Agent;
import factory.factory201.interfaces.Conveyor;
import factory.factory201.interfaces.KitRobot;
import factory.general.Kit;
import factory.general.Message;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

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
    private List<Kit> kitList = Collections.synchronizedList(new ArrayList<Kit>());
    private int kitRequestsFromKitRobot;
    private boolean genKit = false;
    private int num;

    public ConveyorAgent(String name) {
        super(name);

        kitRequestsFromKitRobot = 0;
    }

    // ********** MESSAGES *********

    @Override
    public void msgNeedEmptyKit() {
        print("received request for empty kit!!");
        kitRequestsFromKitRobot++;
        //for every incremented value, scheduler is called.
        stateChanged();
    }

    public void msgGenerateKit(int number) {
        genKit = true;
        num = number;
        stateChanged();
        //generateKit(num);
    }

    @Override
    public void msgHereIsVerifiedKit(Kit kit) {
        print("Accepting a verified kit: [" + kit.name + "] from the kits robot");
        synchronized (kitList) {
            kitList.add(kit);
        }
        stateChanged();
    }

    public void msgRemoveKit() {
    }

    // ********* SCHEDULER *********
    @Override
    public boolean pickAndExecuteAnAction() {
        if (genKit == true) {
            generateKit(num);
            genKit = false;
            return true;
        }
        if (kitRequestsFromKitRobot > 0) {
            print("kitRequest number is " + kitRequestsFromKitRobot);
            giveEmptyKit();
            kitRequestsFromKitRobot--;
            return true;
        }
        return false;
    }

    // ********** ACTIONS **********
    private void giveEmptyKit() {

        for (Kit k : kitList) {
            if (k.status == Kit.Status.empty) {
                print("Notifying the kit robot that an empty kit: [" + k.name + "] is ready.");
                kitRobotAgent.msgHereIsEmptyKit(k);
                kitList.remove(k);
                break;
            }
        }

        stateChanged();
    }

    // ************ MISC ***********
    private void generateKit(int num) {
        //num--;
        for (int i = 0; i < num; i++) {
            Kit k = new Kit("Kit " + i);
            kitList.add(k);
            DoAddKit(k);
        }
        stateChanged();
    }

    private void generateKit(String name) {
        Kit k = new Kit("Kit " + name);
        kitList.add(k);
        DoAddKit(k);
    }

    private void removeKit(String name) {
        for (Kit k : kitList) {
            if (k.name.equals(name)) {
                kitList.remove(k);
                break;
            }
        }
    }

    private void removeAllVerifiedKits() {
        for (Kit k : kitList) {
            if (k.status == Kit.Status.verified) {
                kitList.remove(k);
            }
        }
    }

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

    public void testAddKit(Kit k) {
        kitList.add(k);
    }
}
