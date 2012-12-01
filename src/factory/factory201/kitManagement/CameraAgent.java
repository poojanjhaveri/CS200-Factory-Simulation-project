package factory.factory201.kitManagement;

import agent.Agent;
import factory.factory200.laneManager.ServerSide.LMServerMain;
import factory.factory201.interfaces.Camera;
import factory.factory201.interfaces.KitRobot;
import factory.factory201.interfaces.NestInterface;
import factory.general.Kit;
import factory.general.Message;
import factory.general.Nest;
import factory.general.Part;
import factory.general.Result;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * @brief agent for the Camera
 */
public class CameraAgent extends Agent implements Camera {

    private KitRobot kitRobot;
    private NestInterface nestAgent;
    private LMServerMain LMServer;
    private List<Nest> nestList;
    private List<Kit> kitList;
    private Kit kitInfoFromPartsAgent;
    private List<Integer> kitRqmts;
    private Map<Integer, Result.Is> nestErrors;

    public CameraAgent(String name) {
        super(name);
        nestList = Collections.synchronizedList(new ArrayList<Nest>());
        kitList = Collections.synchronizedList(new ArrayList<Kit>());
        kitInfoFromPartsAgent = null;
        kitRqmts = new ArrayList<Integer>();
        nestErrors = new HashMap<Integer, Result.Is>();
    }

    public void msgAllPartsBad(int nestNum) {
        nestErrors.put(nestNum, Result.Is.badParts);
    }

    public void msgPartsPiledUp(int nestNum) {
        nestErrors.put(nestNum, Result.Is.piledParts);
    }
    
    // added by Kevin
    public void msgPartsShaking(int nestNum){
          nestErrors.put(nestNum, Result.Is.unstableParts);
    }
    
    @Override
    public void msgNestIsFull(Nest nest) {
        synchronized (nestList) {
            nestList.add(nest);
        }
        stateChanged();
    }

    @Override
    public void msgKitIsFull(Kit kit) {
        synchronized (kitList) {
            kitList.add(kit);
        }
        stateChanged();
    }

    @Override
    public void msgHereIsKitInfo(Kit kit) {
        kitInfoFromPartsAgent = kit;
        stateChanged();
    }

    // ********* SCHEDULER *********
    @Override
    public boolean pickAndExecuteAnAction() {
        if (kitInfoFromPartsAgent != null) {
            configureKitInfo();
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
    public void inspectKit(Kit kit) {
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
        kitRobot.msgKitInspected(result);
        String strResult = result ? "NO ERROR" : "ERROR";
        print("Inspected kit: [" + kit.name + "] with result: " + strResult + ".");
        synchronized (kitList) {
            kitList.remove(kit);
        }
        stateChanged();
    }

    private void inspectNest(Nest nest) {
        Integer type = nest.part.type;
        boolean result = true;
        for (Part p : nest.parts) {
            if (p.type != type) {
                result = false;
                break;
            }
        }
        try {
            Thread.sleep(2000); // For Dongyung
        } catch (InterruptedException ex) {
        }
        
       
        
        DoInspectNest(nest);
        Result.Is is = result ? Result.Is.verified : Result.Is.badParts;
         
        /* @kevin- check for nest errors and look for unstable parts if any, set the result to unstable */
        for(int i=0;i<nestErrors.size();i++){
        if(nestErrors.get(i).equals(Result.Is.unstableParts))
            {
            System.out.println("unstable parts have been detected. ");
             is= Result.Is.unstableParts;
            }
        }
        
        
        nestAgent.msgNestInspected(nest, new Result(is));
        String strResult = result ? "NO ERROR" : "ERROR";
        print("Inspecting nest: [Nest " + nest.nestNum + "] with result: " + strResult + ".");
        synchronized (nestList) {
            nestList.remove(nest);
        }
        stateChanged();
    }

    private void configureKitInfo() {
        kitRqmts.clear();
        for (int i = 0; i < kitInfoFromPartsAgent.getSize(); i++) {
            kitRqmts.add(kitInfoFromPartsAgent.getPart(i).type);
        }
        kitInfoFromPartsAgent = null;
        stateChanged();
    }

    // ************ MISC ***********
    public void setAll(KitRobot kitRobot, NestInterface nestAgent) {
        this.kitRobot = kitRobot;
        this.nestAgent = nestAgent;
    }

    public void setServer(LMServerMain LMServer) {
        this.LMServer = LMServer;
    }

    public List<Integer> getKitRqmts() {
        return this.kitRqmts;
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
            this.LMServer.getForAgentNestCamera().cameraShoot(nest.nestNum);
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }
}
