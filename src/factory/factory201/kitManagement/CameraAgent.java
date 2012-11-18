package factory.factory201.kitManagement;

import agent.Agent;
import factory.factory201.interfaces.Camera;
import factory.factory201.interfaces.KitRobot;
import factory.factory201.interfaces.NestInterface;
import factory.general.Kit;
import factory.general.Message;
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
 * @version 1
 *
 * @brief agent for the Camera
 */
public class CameraAgent extends Agent implements Camera {

    private KitRobot kitRobotAgent;
    private NestInterface nestAgent;
    private List<Nest> nests;
    private List<Kit> kits;
    private Kit tempKit;
    private List<Integer> kitInfo;

    public CameraAgent(String name) {
        super(name);
        nests = Collections.synchronizedList(new ArrayList<Nest>());
        kits = Collections.synchronizedList(new ArrayList<Kit>());
        tempKit = null;
        kitInfo = new ArrayList<Integer>();
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
//        print("Received msgNestIsFull that " + nest.nestNum + " is full.");
        synchronized (nests) {
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
//        print("Received msgKitIsFull from Kit Robot Agent");
        synchronized (kits) {
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
    public boolean pickAndExecuteAnAction() {
        if (tempKit != null) {
            configureKitInfo(tempKit);
            return true;
        }
        synchronized (kits) {
            for (Kit k : kits) {
                if (k.status == Kit.Status.full) {
                    inspectKit(k);
                    return true;
                }
            }
        }
        synchronized (nests) {
            for (Nest n : nests) {
                if (n.status == Nest.Status.gettingInspected) {
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
    private void inspectKit(Kit kit) {
        print("Inspecting kit: [" + kit.name + "].");
        boolean result = true;

        if (kit.parts.size() != kitInfo.size()) {
            result = false;
        } else {
            List<Integer> kitRequirements = new ArrayList<Integer>(this.kitInfo);
            for (Part p : kit.parts) {
                if(kitRequirements.contains(p.type)) {
                    kitRequirements.remove(p.type);
                }
            }
            if(kitRequirements.size() > 0) {
                result = false;
            }
        }

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
    private void inspectNest(Nest nest) {
        print("Inspecting nest: [Nest " + nest.nestNum + "].");
        Integer type = nest.part.type;
        boolean result = true;
        for (Part p : nest.parts) {
            if (p.type != type) {
                result = false;
                break;
            }
        }
        DoInspectNest(nest);
        nestAgent.msgNestInspected(nest, true);
        nests.remove(nest);
        stateChanged();
    }

    private void configureKitInfo(Kit info) {
        kitInfo.clear();
        for (int i = 0; i < info.getSize(); i++) {
            kitInfo.add(info.getPart(i).type);
        }
        tempKit = null;
    }

    // ************ MISC ***********
    public void setKitRobot(KitRobot agent) {
        kitRobotAgent = agent;
    }

    public void setNestAgent(NestInterface agent) {
        nestAgent = agent;
    }

    public void setAll(KitRobot kitRobot, NestInterface nestAgent) {
        this.kitRobotAgent = kitRobot;
        this.nestAgent = nestAgent;
    }

    private void DoInspectKit(Kit kit) {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_FLASH_KIT_CAMERA);
            this.fpm.sendMessage(Message.KAM_FLASH_KIT_CAMERA);
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }

    private void DoInspectNest(Nest nest) {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_FLASH_NEST_CAMERA + ":" + nest.nestNum);
            this.fpm.sendMessage(Message.KAM_FLASH_NEST_CAMERA + ":" + nest.nestNum);
            this.fpm.sendMessage(Message.ALERT_FPM_KIT_INSPECTED);
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }
}
