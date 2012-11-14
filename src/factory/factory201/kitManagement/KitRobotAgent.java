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
 * @version 0
 */
public class KitRobotAgent extends Agent implements KitRobot {

    private KitStand kitStand;
    public KitAssemblyManager KAM;
    private boolean partsAgentNeedsEmptyKit;
    private boolean requestedEmptyKit;
    private Conveyor conveyor;
    private Camera camera;
    private PartsInterface partsAgent;

    public KitRobotAgent(String name) {
        super(name);
        
        kitStand = new KitStand();
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
        print("msgHereIsEmptyKit");
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
    public void msgKitIsFull() {
        print("msgKitIsFull");
        kitStand.get(1).status = Kit.Status.full;
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
        kitStand.get(2).status = result ? Kit.Status.verified : Kit.Status.error;
        stateChanged();
    }

    // ********* SCHEDULER *********
    @Override
    public boolean pickAndExecuteAnAction() {
        if (!kitStand.isEmpty()) {
            if (!kitStand.isEmpty(2)) {
                if (kitStand.get(2).status == Kit.Status.verified) {
                    //if kit is ready to leave cell
                    sendVerifiedKitToConveyor();
                    return true;
                }
            }
            if(!kitStand.isEmpty(1)) {
                if (kitStand.get(1).status == Kit.Status.full) {
                    // if kit is ready for inspection
                    moveFullKitToInspection();
                    return true;
                }
            }
        } else {
            if (partsAgentNeedsEmptyKit && !requestedEmptyKit) {
                // if parts agent needs empty kit
                giveEmptyKitToPartsAgent();
                return true;
            } else if (kitStand.availability() > 0 && !requestedEmptyKit) {
                // if tempstand is empty
                getEmptyKitFromConveyor();
                return true;
            }
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

    private void moveFullKitToInspection() {
        while (!kitStand.isEmpty(2)) {
        }
        print("Moving the full kit: [" + kitStand.get(1).name + "] to the inspection stand.");
        DoMoveFullKitToInspection();
        kitStand.moveFullKitToInspection();
        camera.msgKitIsFull(kitStand.get(2));
        stateChanged();
    }

    private void giveEmptyKitToPartsAgent() {
        if (!kitStand.isEmpty()) {
            print("Notifying the parts agent that an empty kit: [" + kitStand.get(1).name + "] is ready.");
            partsAgent.msgEmptyKitReady(kitStand.get(1));
            partsAgentNeedsEmptyKit = false;
        } else {
            getEmptyKitFromConveyor();
        }
        stateChanged();
    }

    private void getEmptyKitFromConveyor() {
        print("Requesting an empty kit from the conveyor.");
        DoGetEmptyKit();
        conveyor.msgNeedEmptyKit();
        requestedEmptyKit = true;
        stateChanged();
    }

    // ************ MISC ***********
    
    /**
    * Sets the conveyor agent
    * @param agent Conveyor Agent to be set
    * @brief Sets the conveyor agent
    */	 
    public void setConveyor(Conveyor agent) {
        conveyor = agent;
    }

    /**
    * Sets the camera agent
    * @param agent Camera agent to be set
    * @brief Sets the camera agent 
    */
    public void setCamera(Camera agent) {
        camera = agent;
    }

    /**
    * Sets the parts agent
    * @param agent Parts agent to be set
    * @brief Sets the parts agent
    */
    public void setPartsAgent(PartsInterface agent) {
        partsAgent = agent;
    }

    /**
    * Sets the kit assembly manager
    * @param KAM Kit assembly manager
    * @brief Sets the kit assembly manager
    */    
    public void setKitAssemblyManager(KitAssemblyManager KAM) {
        this.KAM = KAM;
    }

    public void setAll(Camera camera, Conveyor conveyor, 
            PartsInterface partsAgent, KitAssemblyManager KAM) {
        this.camera = camera;
        this.conveyor = conveyor;
        this.partsAgent = partsAgent;
        this.KAM = KAM;
    }
    
    /**
    * Animation call for agent action
    */    
    private void DoRemoveVerifiedKit(Kit k) {
        KAM.getKitRobot().dropOffFullKit();
    }

    /**
    * Animation call for agent action
    */    
    private void DoMoveFullKitToInspection() {
        KAM.getKitRobot().moveActiveKitToInspection();
    }

    /**
    * Animation call for agent action
    */    
    private void DoGetEmptyKit() {
        KAM.getKitRobot().pickUpEmptyKit();
    }
}
