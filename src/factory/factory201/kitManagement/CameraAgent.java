package factory.factory201.kitManagement;

import agent.Agent;
import factory.factory201.interfaces.Camera;
import factory.factory201.partsManagement.NestAgent;
import factory.general.Kit;
import factory.general.Nest;
import java.util.ArrayList;
import java.util.List;

/**
 * Agent for the camera.
 *
 * This agent verifies the following: 1) All of the parts in a nest are of the
 * proper type; 2) All of the parts in a nest are in acceptable condition; 3)
 * Each completed kit meets the requirements of the configuration specified by
 * the FCS.
 *
 * @author Alex Young
 * @version 0
 *
 * @brief agent for the Camera
 */
public class CameraAgent extends Agent implements Camera {

    private KitRobotAgent kitRobotAgent;
    private NestAgent nestAgent;
    private List<Nest> nests = new ArrayList<Nest>();
    private List<Kit> kits = new ArrayList<Kit>();

    // ********** MESSAGES *********
    /**
     * Message called by NestAgent to inspect nest.
     *
     * @param nest Nest to be inspected
     * @brief Message called by NestAgent to inspect nest.
     */
    @Override
    public void msgNestIsFull(Nest nest) {
        nests.add(nest);
        stateChanged();
    }

    /**
     * Message called by KitRobotAgent to inspect kit.
     *
     * @param kit Kit to be inspected
     * @brief Message called by KitRobotAgent to inspect kit.
     */
    @Override
    public void msgKitIsFull(Kit kit) {
        kits.add(kit);
        stateChanged();
    }

    // ********* SCHEDULER *********
    @Override
    protected boolean pickAndExecuteAnAction() {
        for (Kit k : kits) {
            if (k.status == Kit.Status.full) {
                inspectKit(k);
                return true;
            }
        }
        for (Nest n : nests) {
            if (n.status == Nest.Status.full) {
                inspectNest(n);
                return true;
            }
        }
        return false;
    }

    // ********** ACTIONS **********
    /**
     * Inspects nests and returns result to kitRobot agent.
     *
     * @param kit Kit being inspected by camera
     * @brief Inspects nests and returns result to kitRobot agent.
     */
    public void inspectKit(Kit kit) {
//        Kit k = new Kit("1");
////        k = server.getKit();
//        if(k.equals(kit)) {
//            
//        } else {
//            
//        }
//        DoInspectKit(kit);
        kitRobotAgent.msgKitInspected(true);
        stateChanged();
    }

    /**
     * Inspects nests and returns result to nest agent.
     *
     * @param nest Nest being inspected by camera
     * @brief Inspects nests and returns result to nest agent.
     */
    public void inspectNest(Nest nest) {
//        Nest n = new Nest();
//        boolean flag = false;
//        for(Part p : n.parts) {
//            if(p.type != n.partType) {
//                flag = true;
//            }
//        }
//        DoInspectNest(nest.nestNum);
        kitRobotAgent.msgKitInspected(true);
        stateChanged();
    }

    // ************ MISC ***********
    public void setKitRobotAgent(KitRobotAgent agent) {
        kitRobotAgent = agent;
    }

    public void setNestAgent(NestAgent agent) {
        nestAgent = agent;
    }
}
