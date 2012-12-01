package factory.factory201.kitManagement;

import agent.Agent;
import factory.factory201.interfaces.Camera;
import factory.factory201.interfaces.Conveyor;
import factory.factory201.interfaces.KitRobot;
import factory.factory201.interfaces.PartsInterface;
import factory.general.Kit;
import factory.general.Message;
import java.util.concurrent.Semaphore;

/**
 * Agent for the Kit Robot.
 *
 * This class is the agent for the Kit Robot which gets empty kits from the
 * conveyor and puts it on a palette This class is the agent for the Kit Robot
 * which gets empty kits from the conveyor and puts it on a palette. It also
 * moves unverified kits onto the verification palette and once verified, moves
 * the complete kit onto the conveyor.
 *
 * @author Alex Young
 * @version 1
 * @brief Agent for the Kit Robot
 */
public class KitRobotAgent extends Agent implements KitRobot {

    private Conveyor conveyor;
    private Camera camera;
    private PartsInterface partsAgent;
    private KitStand kitStand;
    private int kitRequestsFromPartsAgent;
    private boolean requestedKitFromConveyor;
    private Semaphore animation = new Semaphore(0, true);
    private boolean factoryRunning = false;

    public KitRobotAgent(String name) {
        super(name);
        kitStand = new KitStand(this);
        requestedKitFromConveyor = false;
        kitRequestsFromPartsAgent = 0;
    }

    // ********** AGENT MESSAGES *********
    @Override
    public void msgNeedEmptyKit() {
        kitRequestsFromPartsAgent++;
        stateChanged();
    }

    @Override
    public void msgHereIsEmptyKit(Kit kit) {
        synchronized (kitStand) {
            kitStand.addKit(kit);
        }
        requestedKitFromConveyor = false;
        stateChanged();
    }

    @Override
    public void msgKitIsFull(Kit kit) {
        kit.status = Kit.Status.full;
        stateChanged();
    }

    @Override
    public void msgKitInspected(boolean result) {
        synchronized (kitStand) {
            kitStand.get(2).status = result ? Kit.Status.verified : Kit.Status.error;
        }
        stateChanged();
    }

    // ********** MISC. MESSAGES *********
    public void msgStartFactory() {
        factoryRunning = true;
        stateChanged();
    }

    public void msgAnimationComplete() {
        print("msgAnimationComplete");
        animation.release();
    }

    // ********* SCHEDULER *********
    @Override
    public boolean pickAndExecuteAnAction() {
        if (!kitStand.isEmpty(2) && kitStand.get(2).status == Kit.Status.verified) { //there is a verified kit waiting on [2]
            sendVerifiedKitToConveyor();
            return true;
        }
        if (kitStand.isEmpty(2)) { //[2] is empty
            if (!kitStand.isEmpty(1) && kitStand.get(1).status == Kit.Status.full) { //there is a full kit on [1]
                moveFullKitToInspection(kitStand.get(1));
                return true;
            }
            if (!kitStand.isEmpty(0) && kitStand.get(0).status == Kit.Status.full) { //there is a full kit on [0]
                moveFullKitToInspection(kitStand.get(0));
                return true;
            }
        }
        if (factoryRunning && !requestedKitFromConveyor && (kitStand.isEmpty(0) || kitStand.isEmpty(1)) && kitStand.isEmpty(2)) { //kit stand has empty spot
            requestEmptyKitFromConveyor();
            return true;
        }
        if (kitRequestsFromPartsAgent > 0) { //parts agent has requested at least one kit
            if ((!kitStand.isEmpty(1)) && kitStand.get(1).status == Kit.Status.empty) { //there is an empty kit on [1]
                giveEmptyKitToPartsAgent(1);
                kitRequestsFromPartsAgent--;
                return true;
            } else if ((!kitStand.isEmpty(0)) && kitStand.get(0).status == Kit.Status.empty) { //there is an empty kit on [0]
                giveEmptyKitToPartsAgent(0);
                kitRequestsFromPartsAgent--;
                return true;
            }
        }
        return false;
    }

    // ********** ACTIONS **********
    private void sendVerifiedKitToConveyor() {
        Kit k;
        synchronized (kitStand) {
            k = kitStand.remove(2);
        }
        print("Sending verified kit: [" + k.name + "] to the conveyor.");
        DoMoveKitFrom2ToConveyor(k);
        conveyor.msgHereIsVerifiedKit(k);
        stateChanged();
    }

    private void moveFullKitToInspection(Kit kit) {
        print("Moving the full kit: [" + kit.name + "] to the inspection stand.");
        kitStand.moveFullKitToInspection(kit);
        kit.beingUsedByPartsAgent = false;
        camera.msgKitIsFull(kitStand.get(2));
        stateChanged();
    }

    private void giveEmptyKitToPartsAgent(int num) {
        //might not work because the kitStand maybe modified in messages (running in sender's thread). 

        //if (kitStand.availableToGive(1) || kitStand.availableToGive(0)) {
        //   int i = kitStand.availableToGive(1) ? 1 : 0;
        print("Notifying the parts agent that an empty kit: [" + kitStand.get(num).name + "] is ready. Num : " + num);
        partsAgent.msgEmptyKitReady(kitStand.get(num));
        kitStand.get(num).beingUsedByPartsAgent = true;

        // }
        stateChanged();
    }

    private void requestEmptyKitFromConveyor() {
        print("Requesting an empty kit from the conveyor.");
        conveyor.msgNeedEmptyKit();
        requestedKitFromConveyor = true;
        stateChanged();
    }

    // ************ MISC ***********
    public KitStand getKitStand() {
        return this.kitStand;
    }

    public void setAll(Camera camera, Conveyor conveyor, PartsInterface partsAgent) {
        this.camera = camera;
        this.conveyor = conveyor;
        this.partsAgent = partsAgent;
    }

    public void DoMoveKitFromConveyorTo0() {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_PICK_UP_EMPTY_KIT);
            this.fpm.sendMessage(Message.KAM_PICK_UP_EMPTY_KIT);
            try {
                print("semaphore acquired ");
                this.animation.acquire();
//                Thread.sleep(3000);
                print("semaphore released ");
            } catch (InterruptedException ex) {
            }
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }

    }

    public void DoMoveKitFromConveyorTo1() {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_PICK_UP_EMPTY_KIT_TO_ACTIVE);
            this.fpm.sendMessage(Message.KAM_PICK_UP_EMPTY_KIT_TO_ACTIVE);
            try {
                print("semaphore acquired 1");
                this.animation.acquire();
//                Thread.sleep(3000);
                print("semaphore released 1");
            } catch (InterruptedException ex) {
            }
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }

    public void DoMoveKitFrom0to1() {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_MOVE_EMPTY_KIT_TO_ACTIVE);
            this.fpm.sendMessage(Message.KAM_MOVE_EMPTY_KIT_TO_ACTIVE);
            try {
                this.animation.acquire();
//            Thread.sleep(3000);
                print("semaphore released 1");
            } catch (InterruptedException ex) {
            }
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }

    public void DoMoveKitFrom1to2() {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_MOVE_ACTIVE_KIT_TO_INSPECTION);
            this.fpm.sendMessage(Message.KAM_MOVE_ACTIVE_KIT_TO_INSPECTION);
            try {
                this.animation.acquire();
//            Thread.sleep(3000);  //adding back coz the semaphore isn't being released by the animation
            } catch (InterruptedException ex) {
            }
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }

    }

    public void DoMoveKitFrom0to2() {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_MOVE_FROM_0_TO_2);
            this.fpm.sendMessage(Message.KAM_MOVE_FROM_0_TO_2);
            try {
                this.animation.acquire();
//                Thread.sleep(3000);
            } catch (InterruptedException ex) {
            }
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }

    public void DoMoveKitFrom2ToConveyor(Kit k) {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_DROP_OFF_FULL_KIT);
            this.fpm.sendMessage(Message.KAM_DROP_OFF_FULL_KIT);
            try {
                this.animation.acquire();
//            Thread.sleep(3000);
            } catch (InterruptedException ex) {
            }
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }
}
