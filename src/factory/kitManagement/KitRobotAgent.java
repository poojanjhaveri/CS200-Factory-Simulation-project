package factory.kitManagement;

import agent.Agent;
import factory.Kit;
import factory.Location;
import factory.interfaces.KitRobot;
import java.util.ArrayList;
import java.util.List;

/**
 * @brief This class is the agent for the Kit Robot which gets empty kits from the
 * conveyor and puts it on a palette. It also moves unverified kits onto the
 * verification palette and once verified, moves the complete kit onto the
 * conveyor.
 *
 * @author Alex Young
 * @version 0
 */
public class KitRobotAgent extends Agent implements KitRobot {

    List<myKit> kits = new ArrayList<myKit>();
    
    enum KitStatus {empty, complete, verified, error};
    private class myKit {
        Kit kit;
        KitStatus status;
        int conveyorLoc;
        int kittingStandNum;
        
        public myKit(Kit kit, int conveyorLoc) {
            this.kit = kit;
            this.status = KitStatus.empty;
            this.conveyorLoc = conveyorLoc;
            this.kittingStandNum = -1;
        }
        
    }
    boolean needEmptyKit = false;
    
    
    // ********** MESSAGES *********

    /**
     * Message called by PartsRobotAgent when it needs an empty kit.
     * @brief Message called by PartsRobotAgent when it needs an empty kit.
     */
    @Override
    public void msgNeedEmptyKit() {
        needEmptyKit = true;
        stateChanged();
    }
    
    /**
     * Message called by ConveyorAgent to give kit.
     * @param kit Kit given by conveyor
     * @param loc Location of kit for animation purposes
     * @brief Message called by ConveyorAgent to give kit.
     */
    @Override
    public void msgHereIsEmptyKit(Kit kit, int loc) {
        kits.add(new myKit(kit, loc));
        stateChanged();
    }
    
    /**
     * Message called by PartsAgent when kit is complete.
     * @param kit Kit that is complete
     * @brief Message called by PartsAgent when kit is complete.
     */
    @Override
    public void msgKitIsComplete(Kit kit) {
        for(myKit k: kits){
            if(kit == k.kit) {
                k.status = KitStatus.complete;
                break;
            }
        }
        stateChanged();
    }
    
    /**
     * Message called by camera agent after kit inspection.
     * @brief Message called by camera agent after kit inspection.
     * @param kit Kit being inspected
     * @param result Result of inspection
     */
    @Override
    public void msgKitInspected(Kit kit, boolean result) {
        for(myKit k: kits){
            if(kit == k.kit) {
                if(result) {
                    k.status = KitStatus.verified;
                    break;
                } else {
                    k.status = KitStatus.error;
                    break;
                }
            }
        }
        stateChanged();
    }

    
    // ********* SCHEDULER *********
    
    @Override
    protected boolean pickAndExecuteAnAction() {
        if(!kits.isEmpty()) {
            for(myKit k : kits) {
                if(k.status == KitStatus.verified) {
                    //
                    return true;
                }
            }
            for(myKit k : kits) {
                if(k.status == KitStatus.complete) {
                    //
                    return true;
                }
            }
            for(myKit k : kits) {
                if(k.status == KitStatus.empty) {
                    //
                    return true;
                }
            }
        } else if(needEmptyKit) {
            //
            return true;
        }
        
        return false;
        
    }
    
    
    // ********** ACTIONS **********
    // ************ MISC ***********
}
