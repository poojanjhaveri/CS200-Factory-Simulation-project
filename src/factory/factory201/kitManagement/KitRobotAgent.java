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

    private KitStand kitStand = new KitStand();
    public KitAssemblyManager KAM;
    private boolean partsAgentNeedsEmptyKit = false;
    private boolean requestedEmptyKit = false;
    private Conveyor conveyor;
    private Camera camera;
    private PartsInterface partsAgent;

    // ********** MESSAGES *********
    /**
     * Message called by PartsRobotAgent when it needs an empty kit.
     *
     * @brief Message called by PartsRobotAgent when it needs an empty kit.
     */
    @Override
    public void msgNeedEmptyKit() {
        partsAgentNeedsEmptyKit = true;
        requestedEmptyKit = false;
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
        kitStand.addKit(kit);
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
    protected boolean pickAndExecuteAnAction() {
        if (!kitStand.isEmpty()) {
            if (!kitStand.inspectionStandIsEmpty()) {
                if (kitStand.get(2).status == Kit.Status.verified) {
                    //if kit is ready to leave cell
                    sendVerifiedKitToConveyor();
                    return true;
                }
            }
            if (kitStand.get(1).status == Kit.Status.full) {
                // if kit is ready for inspection
                moveFullKitToInspection();
                return true;
            }
        } else {
            if (partsAgentNeedsEmptyKit && !requestedEmptyKit) {
                // if parts agent needs empty kit
                giveEmptyKitToPartsAgent();
                return true;
            } else if (kitStand.availability() > 0) {
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
        DoRemoveVerifiedKit(k);
        conveyor.msgHereIsVerifiedKit(k);
        stateChanged();
    }

    private void moveFullKitToInspection() {
        while (!kitStand.inspectionStandIsEmpty()) {
        }
        DoMoveFullKitToInspection();
        kitStand.moveFullKitToInspection();
        camera.msgKitIsFull(kitStand.get(2));
        stateChanged();
    }

    private void giveEmptyKitToPartsAgent() {
        if (!kitStand.isEmpty()) {
            partsAgent.msgEmptyKitReady(kitStand.get(1));
            partsAgentNeedsEmptyKit = false;
        } else {
            getEmptyKitFromConveyor();
            requestedEmptyKit = true;
        }
        stateChanged();
    }

    private void getEmptyKitFromConveyor() {
        DoGetEmptyKit();
        conveyor.msgNeedEmptyKit();
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
        KAM.getKitRobot().moveActiveKitToInspection();}
    }

    /**
    * Animation call for agent action
    */    
    private void DoGetEmptyKit() {
        KAM.getKitRobot().pickUpEmptyKit();}
    }
}
