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

    private KitRobot kitRobot;
    private NestInterface nestAgent;
    private List<Nest> nestList;
    private List<Kit> kitList;
    private Kit kitInfoFromPartsAgent;
    private List<Integer> kitRqmts;

    public CameraAgent(String name) {
        super(name);
        nestList = Collections.synchronizedList(new ArrayList<Nest>());
        kitList = Collections.synchronizedList(new ArrayList<Kit>());
        kitInfoFromPartsAgent = null;
        kitRqmts = new ArrayList<Integer>();
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
        synchronized (nestList) {
            nestList.add(nest);
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
        synchronized (kitList) {
            kitList.add(kit);
        }
        stateChanged();
    }

    @Override
    public void msgHereIsKitInfo(Kit kit) {
        kitInfoFromPartsAgent = kit;
    }

    // ********* SCHEDULER *********
    @Override
    public boolean pickAndExecuteAnAction() {
        if (kitInfoFromPartsAgent != null) {
            configureKitInfo(kitInfoFromPartsAgent);
            return true;
        }
        synchronized (kitList) {
            for (Kit k : kitList) {
                if (k.status == Kit.Status.full) {
                    inspectKit(k);
                    return true;
                }
            }
        }
        synchronized (nestList) {
            for (Nest n : nestList) {
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

        if (kit.parts.size() != kitRqmts.size()) {
            result = false;
        } else {
            List<Integer> list = new ArrayList<Integer>(this.kitRqmts);
            for (Part p : kit.parts) {
                if (list.contains(p.type)) {
                    list.remove(p.type);
                }
            }
            if (list.size() > 0) {
                result = false;
            }
        }

        DoInspectKit(kit);
        kitRobot.msgKitInspected(true);
        kitList.remove(kit);
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
        nestList.remove(nest);
        stateChanged();
    }

    private void configureKitInfo(Kit info) {
        kitRqmts.clear();
        for (int i = 0; i < info.getSize(); i++) {
            kitRqmts.add(info.getPart(i).type);
        }
        kitInfoFromPartsAgent = null;
    }

    // ************ MISC ***********
    public void setKitRobot(KitRobot agent) {
        kitRobot = agent;
    }

    public void setNestAgent(NestInterface agent) {
        nestAgent = agent;
    }

    public void setAll(KitRobot kitRobot, NestInterface nestAgent) {
        this.kitRobot = kitRobot;
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
