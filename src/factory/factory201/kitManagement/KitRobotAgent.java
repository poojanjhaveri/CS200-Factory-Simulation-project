package factory.factory201.kitManagement;

import agent.Agent;
import factory.factory201.interfaces.Camera;
import factory.factory201.interfaces.Conveyor;
import factory.factory201.interfaces.KitRobot;
import factory.factory201.interfaces.PartsInterface;
import factory.general.Kit;
import factory.general.Message;

/**
 * @brief This class is the agent for the Kit Robot which gets empty kits from
 * the conveyor and puts it on a palette This class is the agent for the Kit
 * Robot which gets empty kits from the conveyor and puts it on a palette. It
 * also moves unverified kits onto the verification palette and once verified,
 * moves the complete kit onto the conveyor.
 *
 * @author Alex Young
 * @version 1
 */
public class KitRobotAgent extends Agent implements KitRobot {

    private KitStand kitStand;
    private int emptyKitRequestsFromPartsAgent;
    private boolean requestedEmptyKit;
    private Conveyor conveyor;
    private Camera camera;
    private PartsInterface partsAgent;

    public KitRobotAgent(String name) {
        super(name);

        kitStand = new KitStand(this);
        requestedEmptyKit = false;
        emptyKitRequestsFromPartsAgent = 0;
    }

    // ********** MESSAGES *********
    /**
     * Message called by PartsRobotAgent when it needs an empty kit.
     *
     * @brief Message called by PartsRobotAgent when it needs an empty kit.
     */
    @Override
    public void msgNeedEmptyKit() {
        print("msgNeedEmptyKit");
        emptyKitRequestsFromPartsAgent++;
        stateChanged();
    }

    /**
     * Message called by ConveyorAgent to give kit.
     *
     * @param kit Kit given by conveyor
     * @param loc Location of kit for animation purposes
     * @brief Message called by ConveyorAgent to give kit.
     */
    @Override
    public void msgHereIsEmptyKit(Kit kit) {
//        print("msgHereIsEmptyKit");
        kitStand.addKit(kit);
        requestedEmptyKit = false;
        stateChanged();
    }

    /**
     * Message called by PartsAgent when kit is complete.
     *
     * @param kit Kit that is complete
     * @brief Message called by PartsAgent when kit is complete.
     */
    @Override
    public void msgKitIsFull(Kit kit) {
//        print("msgKitIsFull: " + kitStand.get(1).name);
        kit.status = Kit.Status.full;
        kit.beingUsedByPartsAgent = false;
        stateChanged();
    }

    /**
     * Message called by camera agent after kit inspection.
     *
     * @brief Message called by camera agent after kit inspection.
     * @param kit Kit being inspected
     * @param result Result of inspection
     */
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
        if (emptyKitRequestsFromPartsAgent > 0) {
            // if parts agent needs empty kit
            giveEmptyKitToPartsAgent();
            return true;
        }
        if (kitStand.availability() > 0 && !requestedEmptyKit) {
            // if tempstand is empty
            requestEmptyKitFromConveyor();
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
        print("Moving the full kit: [" + kitStand.get(1).name + "] to the inspection stand.");
        kitStand.moveFullKitToInspection(kit);
        camera.msgKitIsFull(kitStand.get(2));
        stateChanged();
    }

    private void giveEmptyKitToPartsAgent() {
        if(kitStand.availableToGive(1) || kitStand.availableToGive(0)) {
            int i = kitStand.availableToGive(1) ? 1 : 0;
            print("Notifying the parts agent that an empty kit: [" + kitStand.get(i).name + "] is ready.");
            partsAgent.msgEmptyKitReady(kitStand.get(i));
            kitStand.get(i).beingUsedByPartsAgent = true;
            emptyKitRequestsFromPartsAgent--;
        } else if(!requestedEmptyKit){
            requestEmptyKitFromConveyor();
        }
        stateChanged();
    }

    private void requestEmptyKitFromConveyor() {
        print("Requesting an empty kit from the conveyor.");
        conveyor.msgNeedEmptyKit();
        requestedEmptyKit = true;
        stateChanged();
    }

    // ************ MISC ***********
    /**
     * Sets the conveyor agent
     *
     * @param agent Conveyor Agent to be set
     * @brief Sets the conveyor agent
     */
    public void setConveyor(Conveyor agent) {
        conveyor = agent;
    }

    /**
     * Sets the camera agent
     *
     * @param agent Camera agent to be set
     * @brief Sets the camera agent
     */
    public void setCamera(Camera agent) {
        camera = agent;
    }

    /**
     * Sets the parts agent
     *
     * @param agent Parts agent to be set
     * @brief Sets the parts agent
     */
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
	this.client.sendMessage(Message.PICK_UP_EMPTY_KIT);
	this.fpm.sendMessage(Message.PICK_UP_EMPTY_KIT);
    }
    public void DoMoveKitFromConveyorTo1() {
        this.client.sendMessage(Message.PICK_UP_EMPTY_KIT_TO_ACTIVE);
	this.fpm.sendMessage(Message.PICK_UP_EMPTY_KIT_TO_ACTIVE);
    }
    public void DoMoveKitFrom0to1() {
	this.client.sendMessage(Message.MOVE_EMPTY_KIT_TO_ACTIVE);
	this.fpm.sendMessage(Message.MOVE_EMPTY_KIT_TO_ACTIVE);
    }
    public void DoMoveKitFrom1to2() {
	this.client.sendMessage(Message.KAM_MOVE_ACTIVE_KIT_TO_INSPECTION);
	this.fpm.sendMessage(Message.KAM_MOVE_ACTIVE_KIT_TO_INSPECTION);
    }
    public void DoMoveKitFrom0to2() {
	this.client.sendMessage(Message.KAM_MOVE_FROM_0_TO_2);
	this.fpm.sendMessage(Message.KAM_MOVE_FROM_0_TO_2);
    }
    public void DoMoveKitFrom2ToConveyor(Kit k) {
	this.client.sendMessage(Message.KAM_DROP_OFF_FULL_KIT);
	this.fpm.sendMessage(Message.KAM_DROP_OFF_FULL_KIT);
    } 
}
