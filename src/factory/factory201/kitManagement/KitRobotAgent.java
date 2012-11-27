package factory.factory201.kitManagement;

import agent.Agent;
import factory.factory201.interfaces.Camera;
import factory.factory201.interfaces.Conveyor;
import factory.factory201.interfaces.KitRobot;
import factory.factory201.interfaces.PartsInterface;
import factory.general.Kit;
import factory.general.Message;

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
 * @version 1
 * @brief Agent for the Kit Robot
 */

public class KitRobotAgent extends Agent implements KitRobot {

    private Conveyor conveyor;
    private Camera camera;
    private PartsInterface partsAgent;
    
    //initialize the kitting stand
    private KitStand kitStand;
    
    //check for kit requests from the parts agent
    private int kitRequestsFromPartsAgent;
    
    //requested kit from conveyer?
    private boolean requestedKitFromConveyor;

    
    public KitRobotAgent(String name) {
        super(name);
        kitStand = new KitStand(this);
        //nothing requested
        requestedKitFromConveyor = false;
        //initialize 0 requests
        kitRequestsFromPartsAgent = 0;
    }

    
    @Override
    public void msgNeedEmptyKit() {
//        print("msgNeedEmptyKit");
        
        //increment request count
        kitRequestsFromPartsAgent++;
        stateChanged();
    }

    @Override
    public void msgHereIsEmptyKit(Kit kit) {
//        print("msgHereIsEmptyKit");
        
        //add the kit to the kitting stand
        //make a synchronized addition so that it doesn't affect what happens in the actions
        print("testing location after two executions receiving kit? , 2 and 1 empty? " + kitStand.isEmpty(2) + kitStand.isEmpty(1));
        synchronized(kitStand){
        kitStand.addKit(kit);
        }
        //set to false after receiving the kit,
        requestedKitFromConveyor = false;
        stateChanged();
    }

    @Override
    public void msgKitIsFull(Kit kit) {
//        print("msgKitIsFull: " + kit.name);
        
        //change kit status to indicate it has been full
        kit.status = Kit.Status.full;
        stateChanged();
    }

    @Override
    public void msgKitInspected(boolean result) {
//        print("msgKitInspected");
        
        synchronized(kitStand){
        kitStand.get(2).status = result ? Kit.Status.verified : Kit.Status.error;
    }        
        stateChanged();
    }

    // ********* SCHEDULER *********
    @Override
    public boolean pickAndExecuteAnAction() {
        print("testing location after two executions , 2 and 1 empty? " + kitStand.isEmpty(2) + kitStand.isEmpty(1));
        print("request State is " + requestedKitFromConveyor);
        //if the stand is not empty at 2 and has been verified, send to conveyer.
        if (!kitStand.isEmpty(2) && kitStand.get(2).status == Kit.Status.verified) {
            //if kit is ready to leave cell
            print("sending verified kit to the conveyor");
            sendVerifiedKitToConveyor();
            return true;
        }
        
        //if no kit is being inspected and the one at 1 is full, move to inspection.
        if (!kitStand.isEmpty(1) && kitStand.get(1).status == Kit.Status.full && kitStand.isEmpty(2)) {
            // if kit is ready for inspection
            moveFullKitToInspection(kitStand.get(1));
            return true;
        }
        if (!kitStand.isEmpty(0) && kitStand.get(0).status == Kit.Status.full && kitStand.isEmpty(2)) {
            // if kit is ready for inspection
            moveFullKitToInspection(kitStand.get(0));
            return true;
        }
        /*
        if (kitStand.availability() > 0 && !requestedKitFromConveyor) {
            // if tempstand is empty
            requestEmptyKitFromConveyor();
            return true;
        }
        */
        //check for a kit availa
        
        if (kitRequestsFromPartsAgent > 0){
            if((!kitStand.isEmpty(0)) && kitStand.get(0).status==Kit.Status.empty) {
            // if parts agent needs empty kit
            giveEmptyKitToPartsAgent(0);
            kitRequestsFromPartsAgent--;
            return true;
            }
            else if((!kitStand.isEmpty(1)) && kitStand.get(1).status==Kit.Status.empty){
            giveEmptyKitToPartsAgent(1);
            kitRequestsFromPartsAgent--;
            return true;
            }
            else if(requestedKitFromConveyor==false && kitStand.isEmpty(1) && kitStand.isEmpty(2)&& kitStand.isEmpty(0))
                requestEmptyKitFromConveyor();
        }
        return false;
    }

    // ********** ACTIONS **********
    private void sendVerifiedKitToConveyor() {
        Kit k;
        //kitStand.remove might happen when kits are being added in the messages
        synchronized(kitStand){
         k=kitStand.remove(2);
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
     //   moveKitToInspectionAndUpdateKittingStand(kit);
        camera.msgKitIsFull(kitStand.get(2));
        stateChanged();
    }
    
    private void moveKitToInspectionAndUpdateKittingStand(Kit k){
    if(k.standNum==Kit.StandNum.zero){
        kitStand.setNull(0);
        DoMoveKitFrom0to2();
    }
    if(k.standNum==Kit.StandNum.one){
            kitStand.setNull(1);
            DoMoveKitFrom1to2();
    }
    //update the kitStand
    k.standNum=Kit.StandNum.two;
    
    }
    
    private void giveEmptyKitToPartsAgent(int num) {
        //might not work because the kitStand maybe modified in messages (running in sender's thread). 
       
        //if (kitStand.availableToGive(1) || kitStand.availableToGive(0)) {
         //   int i = kitStand.availableToGive(1) ? 1 : 0;
            print("Notifying the parts agent that an empty kit: [" + kitStand.get(num).name + "] is ready.");
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
    public void setConveyor(Conveyor agent) {
        conveyor = agent;
    }

    public void setCamera(Camera agent) {
        camera = agent;
    }

    public void setPartsAgent(PartsInterface agent) {
        partsAgent = agent;
    }

    public KitStand getKitStand() {
        return this.kitStand;
    }

    public void setAll(Camera camera, Conveyor conveyor,
            PartsInterface partsAgent) {
        this.camera = camera;
        this.conveyor = conveyor;
        this.partsAgent = partsAgent;
    }

    public void DoMoveKitFromConveyorTo0() {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_PICK_UP_EMPTY_KIT);
            this.fpm.sendMessage(Message.KAM_PICK_UP_EMPTY_KIT);
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }

    }

    public void DoMoveKitFromConveyorTo1() {
        if (this.client != null) {

            this.client.sendMessage(Message.KAM_PICK_UP_EMPTY_KIT_TO_ACTIVE);
            this.fpm.sendMessage(Message.KAM_PICK_UP_EMPTY_KIT_TO_ACTIVE);
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }

    public void DoMoveKitFrom0to1() {
        if (this.client != null) {

            this.client.sendMessage(Message.KAM_MOVE_EMPTY_KIT_TO_ACTIVE);
            this.fpm.sendMessage(Message.KAM_MOVE_EMPTY_KIT_TO_ACTIVE);
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }

    public void DoMoveKitFrom1to2() {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_MOVE_ACTIVE_KIT_TO_INSPECTION);
            this.fpm.sendMessage(Message.KAM_MOVE_ACTIVE_KIT_TO_INSPECTION);
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }

    }

    public void DoMoveKitFrom0to2() {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_MOVE_FROM_0_TO_2);
            this.fpm.sendMessage(Message.KAM_MOVE_FROM_0_TO_2);
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }

    public void DoMoveKitFrom2ToConveyor(Kit k) {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_DROP_OFF_FULL_KIT);
            this.fpm.sendMessage(Message.KAM_DROP_OFF_FULL_KIT);
            print("Supplying kit to the conveyer?");
        } else {
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }
}
