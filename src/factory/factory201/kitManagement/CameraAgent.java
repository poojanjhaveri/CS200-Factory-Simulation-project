package factory.factory201.kitManagement;

import agent.Agent;
import factory.factory200.laneManager.ServerSide.LMServerMain;
import factory.factory201.feederManagement.FeederAgent;
import factory.factory201.interfaces.Camera;
import factory.factory201.interfaces.KitRobot;
import factory.factory201.interfaces.NestInterface;
import factory.general.BlueprintParts;
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
 * @version 2
 * @brief agent for the Camera
 */
public class CameraAgent extends Agent implements Camera {

    private KitRobot kitRobot;
    private NestInterface nestAgent;
    private FeederAgent feeder;
    private LMServerMain LMServer;
    private List<Nest> nestList;
    private List<Kit> kitList;
//    private Kit kitInfoFromPartsAgent;
    private List<Integer> kitRqmts;
    private Map<Integer, Result.Is> nestErrors;
    private List<Result.Is> otherErrors;
//    private Map<String, Integer> kitErrors;
    private boolean partsDropped;
    private List<Part> missingParts;
    private List<Part> kitInfoWithError;

    public CameraAgent(String name) {
        super(name);
        nestList = Collections.synchronizedList(new ArrayList<Nest>());
        kitList = Collections.synchronizedList(new ArrayList<Kit>());
//        kitInfoFromPartsAgent = null;
        kitRqmts = new ArrayList<Integer>();
        nestErrors = new HashMap<Integer, Result.Is>();
        otherErrors = new ArrayList<Result.Is>();
        partsDropped = false;
        feeder = null;
        kitInfoWithError = new ArrayList<Part>();


        // KEVIN USE THIS TO TEST
        missingParts = new ArrayList<Part>();
        missingParts.add(new Part(0));
       // missingParts.add(new Part(1));
       // missingParts.add(new Part(2));
//        missingParts.add(new Part(3));
        partsDropped = true;
    }

    // ********* MISC. MESSAGES *********
    public void msgWrongFeederAlgorithm(FeederAgent feeder, int nestNum) {
        print("wrong Feeder Algorithm Hit!! for nest " + nestNum);
        nestErrors.put(nestNum, Result.Is.partsMissing);
//        otherErrors.add(Result.Is.partsMissing);
//        nonNorm[2] = true;
        this.feeder = feeder;
    }

    public void msgPartsDroppedFromKit(List<Part> missingParts) {
        partsDropped = true;
        this.missingParts = missingParts;
    }

    public void msgAllPartsBad(int nestNum) {
        print("Bad Parts Scenario Hit");
        nestErrors.put(nestNum, Result.Is.badParts);
    }

    public void msgPartsPiledUp(int nestNum) {
        print("parts piled up hit");
        nestErrors.put(nestNum, Result.Is.piledParts);
    }

    public void msgPartsShaking(int nestNum) {
        print("parts shaking hit");
        nestErrors.put(nestNum, Result.Is.unstableParts);
    }

    public void msgPartsAgentIsInTheWay() {
        otherErrors.add(Result.Is.robotInTheWay);
//        nonNorm[1] = true;
    }

    // ********* AGENT MESSAGES *********
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
//        kitInfoFromPartsAgent = kit;
//        stateChanged();
    }

    // ********* SCHEDULER *********
    @Override
    public boolean pickAndExecuteAnAction() {
//        if (kitInfoFromPartsAgent != null) {
//            configureKitInfo();
//            return true;
//        }
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
        String strResult;
        if (partsDropped) {
            for (Part p : kit.parts) {
                for (Part m : missingParts) {
                    if (p.type == m.type) {
                        kit.parts.remove(p);
                    }
                }
            }
            DoInspectKit(kit.parts);
            kitRobot.msgKitInspectedError(missingParts);
            strResult = "Parts missing";
            partsDropped = false;
            missingParts = null;
        } //        boolean result = true;
        //            if (kit.parts.size() != kitRqmts.size()) {
        //                result = false;
        //            } else {
        //                List<Integer> list = new ArrayList<Integer>(this.kitRqmts);
        //                for (Part p : kit.parts) {
        //                    if (list.contains(p.type)) {
        //                        list.remove(p.type);
        //                    }
        //                }
        //                if (list.size() > 0) {
        //                    result = false;
        //                }
        //            }
        else {
            DoInspectKit();
            kitRobot.msgKitInspectedNoError();
            strResult = "No error";
        }

        print("Inspected kit: [" + kit.name + "] with result: " + strResult + ".");
        synchronized (kitList) {
            kitList.remove(kit);
        }
        stateChanged();
    }

    private void inspectNest(Nest nest) {
//        Integer type = nest.part.type;
//        boolean result = true;
//        for (Part p : nest.parts) {
//            if (p.type != type) {
//                result = false;
//                break;
//            }
//        }

        Result.Is is;
        if (nestErrors.containsKey(nest.nestNum)) {
            is = nestErrors.get(nest.nestNum);
            print("removing the error " + nestErrors.get(nest.nestNum));
            nestErrors.remove(nest.nestNum);
            print("after removing the error " + nestErrors.get(nest.nestNum));
        } else if (nest.parts.isEmpty()) {
            is = Result.Is.partsMissing;
        } else if (!otherErrors.isEmpty()) {
            is = otherErrors.remove(0);
        } else {
            is = Result.Is.verified;
        }
        try {
            Thread.sleep(2000); // For Dongyung
        } catch (InterruptedException ex) {
        }

        synchronized (nestList) {
            nestList.remove(nest);
        }
        DoInspectNest(nest);

        nestAgent.msgNestInspected(nest, new Result(is));
        if (feeder != null) {
            feeder.msgCorrectYourAlgorithm();
            feeder = null;
        }
//        String strResult = result ? "NO ERROR" : "ERROR";
        print("Inspecting nest: [Nest " + nest.nestNum + "] with result: " + is + ".");

        stateChanged();
    }

//    private void configureKitInfo() {
//        kitRqmts.clear();
//        for (int i = 0; i < kitInfoFromPartsAgent.getSize(); i++) {
//            kitRqmts.add(kitInfoFromPartsAgent.getPart(i).type);
//        }
//        kitInfoFromPartsAgent = null;
//        stateChanged();
//    }
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

    private void DoInspectKit() {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_FLASH_KIT_CAMERA);
            this.fpm.sendMessage(Message.KAM_FLASH_KIT_CAMERA);
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }

    private void DoInspectKit(List<Part> partsList) {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_FLASH_KIT_CAMERA);
            this.fpm.sendMessage(Message.KAM_FLASH_KIT_CAMERA);
            try {
                Thread.sleep(2000); // For Deepa
            } catch (InterruptedException ex) {
            }
            BlueprintParts bp = new BlueprintParts((ArrayList<Part>) partsList);
            this.client.sendMessage(Message.KAM_CHANGE_CONFIGURATION + ":" + bp.serialize());
            this.fpm.sendMessage(Message.KAM_CHANGE_CONFIGURATION + ":" + bp.serialize());
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
