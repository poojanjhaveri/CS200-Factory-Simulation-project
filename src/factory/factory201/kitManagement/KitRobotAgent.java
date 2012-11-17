package factory.factory201.kitManagement;

import agent.Agent;
import factory.factory200.kitAssemblyManager.KitAssemblyManager;
import factory.factory201.interfaces.Camera;
import factory.factory201.interfaces.Conveyor;
import factory.factory201.interfaces.KitRobot;
import factory.factory201.interfaces.PartsInterface;
import factory.general.Kit;

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
    private KitAssemblyManager KAM;
    private boolean partsAgentNeedsEmptyKit;
    private boolean requestedEmptyKit;
    private Conveyor conveyor;
    private Camera camera;
    private PartsInterface partsAgent;

    public KitRobotAgent(String name) {
        super(name);

        kitStand = new KitStand(this);
        partsAgentNeedsEmptyKit = false;
        requestedEmptyKit = false;
    }

    // ********** MESSAGES *********
    /**
     * Message called by PartsRobotAgent when it needs an empty kit.
     *
     * @brief Message called by PartsRobotAgent when it needs an empty kit.
     */
    @Override
    public void msgNeedEmptyKit() {
//        print("msgNeedEmptyKit");
        partsAgentNeedsEmptyKit = true;
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
        if (partsAgentNeedsEmptyKit) {
            // if parts agent needs empty kit
            giveEmptyKitToPartsAgent();
            return true;
        }
        if (kitStand.availability() > 0 && !requestedEmptyKit) {
            // if tempstand is empty
            getEmptyKitFromConveyor();
            return true;
        }
        return false;

    }

    // ********** ACTIONS **********
    private void sendVerifiedKitToConveyor() {
        Kit k = kitStand.remove(2);
        print("Sending verified kit: [" + k.name + "] to the conveyor.");
        DoRemoveVerifiedKit(k);
        conveyor.msgHereIsVerifiedKit(k);
        stateChanged();
    }

    @SuppressWarnings("empty-statement")
    private void moveFullKitToInspection(Kit kit) {
        while (!kitStand.isEmpty(2));
        print("Moving the full kit: [" + kitStand.get(1).name + "] to the inspection stand.");
        DoMoveKitFrom1to2();
        kitStand.moveFullKitToInspection(kit);
        camera.msgKitIsFull(kitStand.get(2));
        stateChanged();
    }

    private void giveEmptyKitToPartsAgent() {
        if (!kitStand.isEmpty()) {
            print("Notifying the parts agent that an empty kit: [" + kitStand.get(1).name + "] is ready.");
            partsAgent.msgEmptyKitReady(kitStand.get(1));
            partsAgentNeedsEmptyKit = false;
        } else {
            if(!requestedEmptyKit) {
                getEmptyKitFromConveyor();
            }
        }
        stateChanged();
    }

    private void getEmptyKitFromConveyor() {
        print("Requesting an empty kit from the conveyor.");
        DoMoveKitFromConveyorTo0();
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

    /**
     * Sets the kit assembly manager
     *
     * @param KAM Kit assembly manager
     * @brief Sets the kit assembly manager
     */
    public void setKitAssemblyManager(KitAssemblyManager KAM) {
        this.KAM = KAM;
//        this.kitStand.setKitAssemblyManager(KAM);
    }

    public void setAll(Camera camera, Conveyor conveyor,
            PartsInterface partsAgent, KitAssemblyManager KAM) {
        this.camera = camera;
        this.conveyor = conveyor;
        this.partsAgent = partsAgent;
        this.KAM = KAM;
    }


    private void DoRemoveVerifiedKit(Kit k) {

        KAM.KAMdropOffFullKit();
	//this.client.sendMessage(Message.KAM_DROP_OFF_FULL_KIT);
    }

    private void DoMoveKitFrom1to2() {
        KAM.getKitRobot().moveActiveKitToInspection();
	//this.client.sendMessage(Message.KAM_MOVE_ACTIVE_KIT_TO_INSPECTION);
    }

    private void DoMoveKitFromConveyorTo0() {
        KAM.getKitRobot().pickUpEmptyKit();
	//this.client.sendMessage(Message.PICK_UP_EMPTY_KIT);
    }
    
    private void DoMoveKitFromConveyorTo1() {
        //this.client.sendMessage(Message.PICK_UP_EMPTY_KIT_TO_ACTIVE);
    }
    
    private void DoMoveKitFrom0to1() {
        this.KAM.getKitRobot().moveEmptyKitToActive();
	//this.client.sendMessage(Message.MOVE_EMPTY_KIT_TO_ACTIVE);
    }
}

