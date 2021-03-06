package factory.factory201.kitManagement;

import agent.Agent;
import factory.factory201.interfaces.Camera;
import factory.factory201.interfaces.Conveyor;
import factory.factory201.interfaces.KitRobot;
import factory.factory201.interfaces.PartsInterface;
import factory.general.Kit;
import factory.general.Message;
import factory.general.Part;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Agent for the Kit Robot.
 *
 * This class is the agent for the Kit Robot which gets empty kits from the
 * conveyor and puts it on a palette This class is the agent for the Kit Robot
 * which gets empty kits from the conveyor and puts it on a palette. It also
 * moves unverified kits onto the verification palette and once verified, moves
 * the complete kit onto the conveyor.
 *
 * @author Alex Young
 * @version 2
 * @brief Agent for the Kit Robot
 */
public class KitRobotAgent extends Agent implements KitRobot {

    private Conveyor conveyor;
    private Camera camera;
    private PartsInterface partsAgent;
    private KitStand kitStand;
    private int kitRequestsFromPartsAgent;
    private boolean requestedKitFromConveyor;
    public Semaphore animation = new Semaphore(0, true);
    private boolean factoryRunning = false;
    private boolean msgTest=false;
    public Kit shitkit = null;
    public KitRobotAgent(String name) {
        super(name);
        kitStand = new KitStand(this);
        requestedKitFromConveyor = false;
        kitRequestsFromPartsAgent = 0;
    }

    // ********** AGENT MESSAGES *********
    @Override
    public void msgNeedEmptyKit() {
        //print("kit request received");
        kitRequestsFromPartsAgent++;
        stateChanged();
    }

    @Override
    public void msgHereIsEmptyKit(Kit kit) {
        synchronized (kitStand) {
            kitStand.addKit(kit);
        }
        requestedKitFromConveyor = false;
        stateChanged();
    }

    @Override
    public void msgKitIsFull(Kit kit) {
        kit.status = Kit.Status.full;
        stateChanged();
    }

    @Override
    public void msgKitInspectedNoError() {
        synchronized (kitStand) {
            kitStand.get(2).status = Kit.Status.verified;
        }
        stateChanged();
    }

    @Override
    public void msgKitInspectedError(List<Part> missingParts) {
        print("ASDFGHJKLASDFGHJKL");
        synchronized (kitStand) {
            kitStand.get(2).status = Kit.Status.error;
            kitStand.get(2).missingParts = missingParts;
        }
        msgTest = true;
        stateChanged();
    }

    // ********** MISC. MESSAGES *********
    public void msgStartFactory() {
        factoryRunning = true;
        stateChanged();
    }

    public void msgAnimationComplete() {
        //print("msgAnimationComplete");
        animation.release();
    }

    // ********* SCHEDULER *********
    @Override
    public boolean pickAndExecuteAnAction() {
        if (!kitStand.isEmpty(2)) { // there is a kit being inspected on [2]
            if (kitStand.get(2).status == Kit.Status.verified) { // kit is verified
                sendVerifiedKitToConveyor();
                return true;
            }
            if (kitStand.get(2).status == Kit.Status.error) { // kit has error
                sendInspectedKitWithErrorBack();
                return true;
            }
        }
        if (kitStand.isEmpty(2)) { //[2] is empty
            if (!kitStand.isEmpty(1) && kitStand.get(1).status == Kit.Status.full) { //there is a full kit on [1]
                moveFullKitToInspection(kitStand.get(1));
                return true;
            }
            if (!kitStand.isEmpty(0) && kitStand.get(0).status == Kit.Status.full) { //there is a full kit on [0]
                moveFullKitToInspection(kitStand.get(0));
                return true;
            }
        }
        if (!msgTest && factoryRunning && !requestedKitFromConveyor && (kitStand.isEmpty(0) || kitStand.isEmpty(1)) && kitStand.isEmpty(2)) { //kit stand has empty spot
            if (kitStand.isEmpty(0))
                print("KIT STAND [0] is EMPTY");
                else
                print("KIT STAND [1] is EMPTY");
            requestEmptyKitFromConveyor();
            return true;
        }
        //print("kit requests is " + kitRequestsFromPartsAgent);
        if (kitRequestsFromPartsAgent > 0) { //parts agent has requested at least one kit
            //  print("need to give empty kit to the parts agent");
            if ((!kitStand.isEmpty(1)) && kitStand.get(1).status == Kit.Status.empty) { //there is an empty kit on [1]
                giveEmptyKitToPartsAgent(1);
                kitRequestsFromPartsAgent--;
                return true;
            } else if ((!kitStand.isEmpty(0)) && kitStand.get(0).status == Kit.Status.empty) { //there is an empty kit on [0]
                giveEmptyKitToPartsAgent(0);
                kitRequestsFromPartsAgent--;
                return true;
            }
        }
        return false;
    }

    
    // ********** ACTIONS **********
   
    private void sendInspectedKitWithErrorBack() {
        DoMoveKitFrom2to0();
        Kit k;
        synchronized (kitStand) {
            k = kitStand.remove(2);
        }
        kitStand.addKitWithError(k);
//        if(kitStand.get(0).equals(k)) {
//            print("EQUALSEQUALSEQUALSEQUALSEQUALSEQUALS");
//        } else {
//            print("NOTEQUALSNOTEQUALSNOTEQUALSNOTEQUALS");
//        }
        shitkit = k;
//        print("Moving the kit [" + k.name + "] with parts missing back to the kit stand");
        //partsAgent.msgPartsMissing(k.missingParts, k);
        msgTest = false;
        stateChanged();
    }

    private void sendVerifiedKitToConveyor() {
        Kit k;
        synchronized (kitStand) {
            k = kitStand.remove(2);
        }
        print("Sending verified kit: [" + k.name + "] to the conveyor.");
        DoMoveKitFrom2ToConveyor(k);
        conveyor.msgHereIsVerifiedKit(k);
        stateChanged();
    }

    private void moveFullKitToInspection(Kit kit) {
        print("Moving the full kit: [" + kit.name + "] to the inspection stand.");
        kitStand.moveFullKitToInspection(kit);
        kit.beingUsedByPartsAgent = false;
        camera.msgKitIsFull(kitStand.get(2));
        if(shitkit!=null) {
        partsAgent.msgPartsMissing(shitkit.missingParts, shitkit);
shitkit = null;
        }
        stateChanged();
    }

    private void giveEmptyKitToPartsAgent(int num) {
        //might not work because the kitStand maybe modified in messages (running in sender's thread). 

        //if (kitStand.availableToGive(1) || kitStand.availableToGive(0)) {
        //   int i = kitStand.availableToGive(1) ? 1 : 0;
        print("Notifying the parts agent that an empty kit: [" + kitStand.get(num).name + "] is ready. Num : " + num);
        partsAgent.msgEmptyKitReady(kitStand.get(num));
        kitStand.get(num).beingUsedByPartsAgent = true;

        // }
        stateChanged();
    }

    private void requestEmptyKitFromConveyor() {
        print("Requesting an empty kit from the conveyor.");
        conveyor.msgNeedEmptyKit();
        requestedKitFromConveyor = true;
        stateChanged();
    }

    // ************ MISC ***********
    public KitStand getKitStand() {
        return this.kitStand;
    }

    public void setAll(Camera camera, Conveyor conveyor, PartsInterface partsAgent) {
        this.camera = camera;
        this.conveyor = conveyor;
        this.partsAgent = partsAgent;
    }

    public void DoMoveKitFromConveyorTo0() {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_PICK_UP_EMPTY_KIT);
            this.fpm.sendMessage(Message.KAM_PICK_UP_EMPTY_KIT);
            try {
                //      print("semaphore acquired ");
                this.animation.acquire();
//                Thread.sleep(3000);
                //    print("semaphore released ");
            } catch (InterruptedException ex) {
            }
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }

    }

    public void DoMoveKitFromConveyorTo1() {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_PICK_UP_EMPTY_KIT_TO_ACTIVE);
            this.fpm.sendMessage(Message.KAM_PICK_UP_EMPTY_KIT_TO_ACTIVE);
            try {
                //  print("semaphore acquired 1");
                this.animation.acquire();
//                Thread.sleep(3000);
                //print("semaphore released 1");
            } catch (InterruptedException ex) {
            }
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }

    public void DoMoveKitFrom0to1() {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_MOVE_EMPTY_KIT_TO_ACTIVE);
            this.fpm.sendMessage(Message.KAM_MOVE_EMPTY_KIT_TO_ACTIVE);
            try {
                this.animation.acquire();
//            Thread.sleep(3000);
                //print("semaphore released 1");
            } catch (InterruptedException ex) {
            }
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }

    public void DoMoveKitFrom1to2() {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_MOVE_ACTIVE_KIT_TO_INSPECTION);
            this.fpm.sendMessage(Message.KAM_MOVE_ACTIVE_KIT_TO_INSPECTION);
            try {
                this.animation.acquire();
//            Thread.sleep(3000);  //adding back coz the semaphore isn't being released by the animation
            } catch (InterruptedException ex) {
            }
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }

    }

    public void DoMoveKitFrom0to2() {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_MOVE_FROM_0_TO_2);
            this.fpm.sendMessage(Message.KAM_MOVE_FROM_0_TO_2);
            try {
                this.animation.acquire();
//                Thread.sleep(3000);
            } catch (InterruptedException ex) {
            }
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }

    public void DoMoveKitFrom2ToConveyor(Kit k) {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_DROP_OFF_FULL_KIT);
            this.fpm.sendMessage(Message.KAM_DROP_OFF_FULL_KIT);
            try {
              print("acquiring semaphore for " + k.standNum);
                this.animation.acquire();
              print("semaphore has been released for " + k.standNum);
//            Thread.sleep(3000);
            } catch (InterruptedException ex) {
            }
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }

    public void DoMoveKitFrom2to0() {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_MOVE_FROM_2_TO_0);
            this.fpm.sendMessage(Message.KAM_MOVE_FROM_2_TO_0);
            try {
                this.animation.acquire();
//            Thread.sleep(3000);
            } catch (InterruptedException ex) {
            }
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }
}
