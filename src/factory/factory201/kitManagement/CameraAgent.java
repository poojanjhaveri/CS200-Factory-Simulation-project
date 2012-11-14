package factory.factory201.kitManagement;

import agent.Agent;
import factory.factory200.kitAssemblyManager.KitAssemblyManager;
import factory.factory201.interfaces.Camera;
import factory.factory201.interfaces.KitRobot;
import factory.factory201.interfaces.NestInterface;
import factory.general.Kit;
import factory.general.Nest;
import factory.general.Part;
import java.util.ArrayList;
import java.util.Collections;
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

    private KitRobot kitRobotAgent;
    private NestInterface nestAgent;
    private KitAssemblyManager KAM;
    private List<Nest> nests;
    private List<Kit> kits;
    private Kit tempKit;
    private List<Part.Type> kitInfo;
    
    public CameraAgent(String name) {
        super(name);
        nests = Collections.synchronizedList(new ArrayList<Nest>());
        kits = Collections.synchronizedList(new ArrayList<Kit>());
        tempKit = null;
        kitInfo = new ArrayList<Part.Type>();
    }
    
    // ********** MESSAGES *********
    /**
     * Message called by NestAgent to inspect nest.
     *
     * @param nest Nest to be inspected
     * @brief Message called by NestAgent to inspect nest.
     */
    @Override
    public void msgNestIsFull(Nest nest) {
        synchronized(nests) {
            nests.add(nest);
        }
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
        synchronized(kits) {
            kits.add(kit);
        }
        stateChanged();
    }

    @Override
    public void msgHereIsKitInfo(Kit kit) {
        tempKit = kit;
    }
    
    
    // ********* SCHEDULER *********
    @Override
    protected boolean pickAndExecuteAnAction() {
        if(tempKit != null) {
            configureKitInfo(tempKit);
            return true;
        }
        synchronized(kits) {
            for (Kit k : kits) {
                if (k.status == Kit.Status.full) {
                    inspectKit(k);
                    return true;
                }
            }
        }
        synchronized(kits) {
            for (Nest n : nests) {
                if (n.status == Nest.Status.full) {
                    inspectNest(n);
                    return true;
                }
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
        print("Inspecting kit: [" + kit.name + "].");
//        Kit k = new Kit("1");
////        k = server.getKit();
//        if(k.equals(kit)) {
//            
//        } else {
//            
//        }
        DoInspectKit(kit);
        kitRobotAgent.msgKitInspected(true);
        kits.remove(kit);
        stateChanged();
    }

    /**
     * Inspects nests and returns result to nest agent.
     *
     * @param nest Nest being inspected by camera
     * @brief Inspects nests and returns result to nest agent.
     */
    public void inspectNest(Nest nest) {
        print("Inspecting nest: [" + nest.name + "].");
//        Nest n = new Nest();
//        boolean flag = false;
//        for(Part p : n.parts) {
//            if(p.type != n.partType) {
//                flag = true;
//            }
//        }
        DoInspectNest(nest);
        nestAgent.msgNestInspected(nest, true);
        nests.remove(nest);
        stateChanged();
    }

    public void configureKitInfo(Kit info) {
        kitInfo.clear();
        for(int i = 0; i < info.getSize(); i++) {
            kitInfo.add(info.getPart(i).type);
        }
        info = null;
    }
    
    
    // ************ MISC ***********
    public void setKitRobotAgent(KitRobot agent) {
        kitRobotAgent = agent;
    }

    public void setNestAgent(NestInterface agent) {
        nestAgent = agent;
    }
    
    public void setKitAssemblyManager(KitAssemblyManager KAM) {
        this.KAM = KAM;
    }
    
    public void setAll(KitAssemblyManager KAM, KitRobot kitRobot, 
            NestInterface nestAgent) {
        this.KAM = KAM;
        this.kitRobotAgent = kitRobot;
        this.nestAgent = nestAgent;
    }
    

    private void DoInspectKit(Kit kit) {
        KAM.flashKitCamera();
    }

    private void DoInspectNest(Nest nest) {
        KAM.flashNestCamera(nest.nestNum);
    }
}
