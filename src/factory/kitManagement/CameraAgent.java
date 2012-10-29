package factory.kitManagement;

import agent.Agent;
import factory.Kit;
import factory.Nest;
import factory.interfaces.Camera;
import factory.partsManagement.NestAgent;
import java.util.HashMap;
import java.util.Map;

/**
 * Agent for the camera.
 * 
 * This agent verifies the following: 1) All of the parts in a nest are 
 * of the proper type; 2) All of the parts in a nest are in acceptable 
 * condition; 3) Each completed kit meets the requirements of the 
 * configuration specified by the FCS.
 *
 * @author Alex Young
 * @version 0
 *
 * @brief agent for the Camera 
 */
public class CameraAgent extends Agent implements Camera {

    private Map<Nest, Integer> nests = new HashMap<Nest, Integer>();
    private Map<Kit, Integer> kits = new HashMap<Kit, Integer>();
    private NestAgent nestAgent;
    private KitRobotAgent kitRobotAgent;

    // ********** MESSAGES *********
    
    /**
     * Message called by NestAgent to inspect nest.
     * @param nest Nest to be inspected
     * @param nestNum Specifies location of nest for animation purposes
     * @brief Message called by NestAgent to inspect nest.
     */
    @Override
    public void msgNestIsFull(Nest nest, int nestNum) {
        nests.put(nest, nestNum);
        stateChanged();
    }

    /**
     * Message called by KitRobotAgent to inspect kit.
     * @param kit Kit to be inspected
     * @param kitNum Specifies location of kit for animation purposes
     * @brief Message called by KitRobotAgent to inspect kit.
     */
    @Override
    public void msgKitIsFull(Kit kit, int kitNum) {
        kits.put(kit, kitNum);
        stateChanged();
    }

    
    // ********* SCHEDULER *********
    
    @Override
    protected boolean pickAndExecuteAnAction() {
        for (Map.Entry<Kit, Integer> entry : kits.entrySet()) {
            inspectKit(entry.getKey(), entry.getValue());
            return true;
        }
        for (Map.Entry<Nest, Integer> entry : nests.entrySet()) {
            inspectNest(entry.getKey(), entry.getValue());
        }
        
        return false;
    }
    
    // ********** ACTIONS **********
    
    /**
     * Inspects nests and returns result to kitRobot agent.
     * @param kit Kit being inspected by camera
     * @param kitNum Specifies location of kit for animation purposes
     * @brief Inspects nests and returns result to kitRobot agent.
     */
    private void inspectKit(Kit kit, int kitNum) {
//        DoInspectKit(kitNum);
        //check if all the correct parts
        kitRobotAgent.msgKitInspected(kit, true);
        stateChanged();
    }
    
    /**
     * Inspects nests and returns result to nest agent.
     * @param nest Nest being inspected by camera
     * @param nestNum Specifies location of nest for animation purposes
     * @brief Inspects nests and returns result to nest agent.
     */
    private void inspectNest(Nest nest, int nestNum) {
//        DoInspectNest(nestNum);
        //check if all the correct parts
        nestAgent.msgNestInspected(true);
        stateChanged();
    }
    
    
    // ************ MISC ***********
    
    /**
     * Setter for NestAgent.
     * @param agent NestAgent
     * @brief Setter for NestAgent.
     */
    public void setNestAgent(NestAgent agent) {
        nestAgent = agent;
    }
    
    /**
     * Setter for KitRobotAgent.
     * @param agent KitRobotAgent
     * @brief Setter for KitRobotAgent.
     */
    public void setKitRobotAgent(KitRobotAgent agent) {
        kitRobotAgent = agent;
    }
}
