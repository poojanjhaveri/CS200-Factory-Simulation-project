package factory.factory201.kitManagement;

import agent.Agent;
import factory.factory201.interfaces.KitRobot;
import factory.factory201.partsManagement.PartsAgent;
import factory.general.Kit;
import java.util.ArrayList;
import java.util.List;

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

    KitStand kitStand = new KitStand();

    private boolean partsAgentNeedsEmptyKit = false;
    private ConveyorAgent conveyor;
    private CameraAgent camera;
    private PartsAgent partsAgent;

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
        kits.add(kit);
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
        for (Kit k : kits) {
            if (kit == k) {
                k.status = Kit.Status.full;
                break;
            }
        }
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
    public void msgKitInspected(Kit kit, boolean result) {
        for (Kit k : kits) {
            if (kit == k) {
                if (result) {
                    k.status = Kit.Status.verified;
                    break;
                } else {
                    k.status = Kit.Status.error;
                    break;
                }
            }
        }
        stateChanged();
    }

    // ********* SCHEDULER *********
    @Override
    protected boolean pickAndExecuteAnAction() {
        if(!kitStand.isEmpty()) {
            /*
             * if kit ready to leave cell
             * else if kit ready for inspection
             * else if parts agent needs empty kit
             * else if tempstand is empty
             */
            if(kitStand.get(2).status == Kit.Status.verified) {
                removeVerifiedKit();
                return true;
            } else if(kitStand.get(1).status == Kit.Status.full) {
                return true;
            } else if(partsAgentNeedsEmptyKit) {
                return true;
            } else if(kitStand.availability() > 0) {
                return true;
            }
        }        
//        if (!kits.isEmpty()) {
//            for (Kit k : kits) {
//                if (k.status == Kit.Status.verified) {
//                    removeVerifiedKit(k);
//                    return true;
//                }
//            }
//            for (Kit k : kits) {
//                if (k.status == Kit.Status.full) {
//                    moveFullKitToInspection(k);
//                    return true;
//                }
//            }
//            for (Kit k : kits) {
//                if (k.status == Kit.Status.empty) {
//                    giveEmptyKit(k);
//                    return true;
//                }
//            }
//        } else if (partsAgentNeedsEmptyKit) {
//            getEmptyKit();
//            return true;
//        }

        return false;

    }

    // ********** ACTIONS **********
    private void removeVerifiedKit() {
        Kit k = kitStand.remove(2);
//        DoRemoveVerifiedKit(k);
        conveyor.msgHereIsVerifiedKit(k);
        stateChanged();
    }

    private void moveFullKitToInspection(Kit k) {
//        camera.msgKitIsFull(k, k.kittingStandNum);
        stateChanged();
    }

    private void giveEmptyKit(Kit k) {
//        partsAgent.msgEmptyKitReady(k.kittingStandNumber);
        stateChanged();
    }

    private void getEmptyKit() {
//        DoGetEmptyKit();
        conveyor.msgNeedEmptyKit();
        stateChanged();
    }

    // ************ MISC ***********
    public void setConveyor(ConveyorAgent agent) {
        conveyor = agent;
    }

    public void setCamera(CameraAgent agent) {
        camera = agent;
    }

    public void setPartsAgent(PartsAgent agent) {
        partsAgent = agent;
    }
}
