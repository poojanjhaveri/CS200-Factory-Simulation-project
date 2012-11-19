package factory.factory201.kitManagement;

import agent.Agent;
import factory.factory201.interfaces.Camera;
import factory.factory201.interfaces.Conveyor;
import factory.factory201.interfaces.KitRobot;
import factory.factory201.interfaces.PartsInterface;
import factory.general.Kit;
import factory.general.Message;

/**
 * Agent for the Kit Robot.
 * 
 * This class is the agent for the Kit Robot which gets empty kits from
 * the conveyor and puts it on a palette This class is the agent for the Kit
 * Robot which gets empty kits from the conveyor and puts it on a palette. It
 * also moves unverified kits onto the verification palette and once verified,
 * moves the complete kit onto the conveyor.
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

    public KitRobotAgent(String name) {
        super(name);
        kitStand = new KitStand(this);
        requestedKitFromConveyor = false;
        kitRequestsFromPartsAgent = 0;
    }

    @Override
    public void msgNeedEmptyKit() {
//        print("msgNeedEmptyKit");
        kitRequestsFromPartsAgent++;
        stateChanged();
    }

    @Override
    public void msgHereIsEmptyKit(Kit kit) {
//        print("msgHereIsEmptyKit");
        kitStand.addKit(kit);
        requestedKitFromConveyor = false;
        stateChanged();
    }

    @Override
    public void msgKitIsFull(Kit kit) {
//        print("msgKitIsFull: " + kit.name);
        kit.status = Kit.Status.full;
        stateChanged();
    }

    @Override
    public void msgKitInspected(boolean result) {
//        print("msgKitInspected");
        kitStand.get(2).status = result ? Kit.Status.verified : Kit.Status.error;
        stateChanged();
    }

    // ********* SCHEDULER *********
    @Override
    public boolean pickAndExecuteAnAction() {
        if (!kitStand.isEmpty(2) && kitStand.get(2).status == Kit.Status.verified) {
            //if kit is ready to leave cell
            sendVerifiedKitToConveyor();
            return true;
        }
        if (!kitStand.isEmpty(1) && kitStand.get(1).status == Kit.Status.full) {
            // if kit is ready for inspection
            moveFullKitToInspection(kitStand.get(1));
            return true;
        }
        if (!kitStand.isEmpty(0) && kitStand.get(0).status == Kit.Status.full) {
            // if kit is ready for inspection
            moveFullKitToInspection(kitStand.get(0));
            return true;
        }
        if (kitStand.availability() > 0 && !requestedKitFromConveyor) {
            // if tempstand is empty
            requestEmptyKitFromConveyor();
            return true;
        }
        if (kitRequestsFromPartsAgent > 0) {
            // if parts agent needs empty kit
            giveEmptyKitToPartsAgent();
            return true;
        }
        return false;

    }

    // ********** ACTIONS **********
    private void sendVerifiedKitToConveyor() {
        Kit k = kitStand.remove(2);
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

    private void giveEmptyKitToPartsAgent() {
        if (kitStand.availableToGive(1) || kitStand.availableToGive(0)) {
            int i = kitStand.availableToGive(1) ? 1 : 0;
            print("Notifying the parts agent that an empty kit: [" + kitStand.get(i).name + "] is ready.");
            partsAgent.msgEmptyKitReady(kitStand.get(i));
            kitStand.get(i).beingUsedByPartsAgent = true;
            kitRequestsFromPartsAgent--;
        }
        stateChanged();
    }

    private void requestEmptyKitFromConveyor() {
        print("Requesting an empty kit from the conveyor.");
        conveyor.msgNeedEmptyKit();
        requestedKitFromConveyor = true;
        stateChanged();
    }

    // ************ MISC ***********

    public void setConveyor(Conveyor agent) {
        conveyor = agent;
    }

    public void setCamera(Camera agent) {
        camera = agent;
    }

    public void setPartsAgent(PartsInterface agent) {
        partsAgent = agent;
    }

    public void setAll(Camera camera, Conveyor conveyor,
            PartsInterface partsAgent) {
        this.camera = camera;
        this.conveyor = conveyor;
        this.partsAgent = partsAgent;
    }

    public void DoMoveKitFromConveyorTo0() {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_PICK_UP_EMPTY_KIT);
            this.fpm.sendMessage(Message.KAM_PICK_UP_EMPTY_KIT);
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }

    }

    public void DoMoveKitFromConveyorTo1() {
        if (this.client != null) {

            this.client.sendMessage(Message.KAM_PICK_UP_EMPTY_KIT_TO_ACTIVE);
            this.fpm.sendMessage(Message.KAM_PICK_UP_EMPTY_KIT_TO_ACTIVE);
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }

    public void DoMoveKitFrom0to1() {
        if (this.client != null) {

            this.client.sendMessage(Message.KAM_MOVE_EMPTY_KIT_TO_ACTIVE);
            this.fpm.sendMessage(Message.KAM_MOVE_EMPTY_KIT_TO_ACTIVE);
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }

    public void DoMoveKitFrom1to2() {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_MOVE_ACTIVE_KIT_TO_INSPECTION);
            this.fpm.sendMessage(Message.KAM_MOVE_ACTIVE_KIT_TO_INSPECTION);
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }

    }

    public void DoMoveKitFrom0to2() {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_MOVE_FROM_0_TO_2);
            this.fpm.sendMessage(Message.KAM_MOVE_FROM_0_TO_2);
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }

    public void DoMoveKitFrom2ToConveyor(Kit k) {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_DROP_OFF_FULL_KIT);
            this.fpm.sendMessage(Message.KAM_DROP_OFF_FULL_KIT);
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }
}
