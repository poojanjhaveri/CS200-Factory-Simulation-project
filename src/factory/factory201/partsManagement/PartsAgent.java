package factory.factory201.partsManagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import agent.Agent;
import factory.factory201.interfaces.Camera;
import factory.factory201.interfaces.KitRobot;
import factory.factory201.interfaces.NestInterface;
import factory.factory201.interfaces.PartsInterface;
import factory.general.Kit;
import factory.general.Message;
import factory.general.Part;

/**
 * Factory PartsAgent gets kit information from server and obtains necessary
 * parts to complete a kit has 4 grips to pick up parts to build the kit
 *
 * @brief gets kit information from server and obtains necessary parts to
 * complete a kit
 * @author James Dalton
 *
 *
 */
public class PartsAgent extends Agent implements PartsInterface {

    // KitAssemblyManager kam; // Should not have!
    KitRobot kitrobot;
    Kit kit0;
    Kit kit1;
    Kit kitInfo;
    NestInterface nest;
    Camera camera;
    public List<Part> inventory, grips, kit0NeedsParts, kit1NeedsParts;
    public List<Kit> newKit;
    boolean emptyKitReady;

    public PartsAgent(String name) {
        super(name); 
        // Should these be creating NEW ArrayLists of things, or should they be getting the list
        // from somewhere else (i.e., the server)?
        this.inventory = Collections.synchronizedList(new ArrayList<Part>());
        this.grips = Collections.synchronizedList(new ArrayList<Part>());
        this.kit0NeedsParts = Collections.synchronizedList(new ArrayList<Part>());
        this.kit1NeedsParts = Collections.synchronizedList(new ArrayList<Part>());
        this.newKit = Collections.synchronizedList(new ArrayList<Kit>());
    }
    
//Messages 

    // message from server
    @Override
    public void msgHereIsKit(Kit k) {
        print("PartsAgent got message for new kit");
        newKit.add(k);
        //if(newKit.)
        
        stateChanged();
    }

    @Override
    public void msgHereIsPart(Part p) {
        print("got part " + p.getString() + "from nest");
        inventory.add(p);
        stateChanged();
    }

    // msg from kit robot
    @Override
    public void msgEmptyKitReady(Kit k) {

        if(k.standNum==Kit.StandNum.zero){
            this.kit0 =k;
            kit0.status = Kit.Status.ready;
            }
            else{
            this.kit1=k;
            kit1.status = Kit.Status.ready;
            }

        print("got an empty kit for stand #" + k.standNum);
        stateChanged();
    }
//Scheduler

    @Override
    public boolean pickAndExecuteAnAction() {

        
        if (kit0!=null && kit1!=null){
            if (!inventory.isEmpty() && kit0.status == Kit.Status.ready && grips.size() != 4) {
            pickUpPart(inventory.remove(0));
            return true;
            }
        
            if (!inventory.isEmpty() && kit1.status == Kit.Status.ready && grips.size() != 4) {
            pickUpPart(inventory.remove(0));
            return true;
            }
        
            if (kit0.status == Kit.Status.full) {
                print("giving kit to kitagent");
                giveKitToKitAgent(kit0);
                return true;
            }
        
            if (kit1.status == Kit.Status.full) {
                print("giving kit to kitagent");
                giveKitToKitAgent(kit1);
                return true;
            }

            if (kit0NeedsParts.isEmpty()) {
                print("giving kit to kitagent");
                giveKitToKitAgent(kit0);
                return true;
            }
            
            if (kit1NeedsParts.isEmpty()) {
                print("giving kit to kitagent");
                giveKitToKitAgent(kit1);
                return true;
            }
}
       if (!newKit.isEmpty()) {
           
           startNewKit(newKit.remove(0));
            //newKit.clear();
            return true;
        } 


        return false;
    }
//Actions

    private void giveKitToKitAgent(Kit k) {
        print("giving kitrobot complete kit");

        kitrobot.msgKitIsFull(k);
        k.status = Kit.Status.empty;

        if(newKit.isEmpty()){
            newKit.add(kitInfo);
            print("Adding kit of size "+ kitInfo.getSize() + " to newKit list");}
        stateChanged();
    }

    private void startNewKit(Kit k) {
       /* if(kitInfo!=null && kitInfo!=k){
           nest.setNestPurge(k.parts); 
        }*/
        this.kitInfo = k;
        print("New kit being started");
        camera.msgHereIsKitInfo(k);
    	//kitNeedsParts.clear();
   
        if(k.standNum == Kit.StandNum.zero || k.standNum == Kit.StandNum.none){
        kitrobot.msgNeedEmptyKit();
    	this.kit0 = k;
        for(int i=0; i<k.parts.size(); i++){
    		kit0NeedsParts.add(k.getPart(i));
    	}
       
    	for (int i = 0; i < k.getSize(); i++) {
            nest.msgNeedPart(k.getPart(i));
    	}}
        if(k.standNum == Kit.StandNum.one || k.standNum == Kit.StandNum.none){
            kitrobot.msgNeedEmptyKit();
    	this.kit1 = k;
        for(int i=0; i<k.parts.size(); i++){
    		kit1NeedsParts.add(k.getPart(i));
    	}
       
    	for (int i = 0; i < k.getSize(); i++) {
            nest.msgNeedPart(k.getPart(i));
    	}}
      
    	stateChanged();

    }

    private void pickUpPart(Part p) {
        grips.add(p);
        boolean next = true;
        int kitNum=-1000;
        for (Part part: kit0NeedsParts){
            if (part.type == p.type){
              kit0NeedsParts.remove(part);
              next = false;
              kitNum=0;
              break;
            }}
        if(next){
        for (Part part: kit1NeedsParts){
            if (part.type == p.type){
              kit1NeedsParts.remove(part);
              kitNum=1;
              break;
            }}}
        
        print("picking up part " + p.getString());
        //kam.getKitStand().getKitPositions().get(1).setFilled(true);
         DoMoveToNest(p.getNestNum());//**COMMENTED OUT FOR UNIT TEST
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            print("stopped sleeping");
        }
        DoPickUpPart(p.getNestNum());
         //**COMMENTED OUT FOR UNIT TEST
        if (grips.size() == 4 || kit0NeedsParts.isEmpty() || kit1NeedsParts.isEmpty()) {

            putPartsInKit(kitNum);
        }
        stateChanged();
    }

    private void putPartsInKit(int kitNum) {
        for (Part p : grips) {
            print("putting part " + p.getString() + " in kit number "+ kitNum);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
        }
        grips.clear();
        DoPutInKit(kitNum);
        //COMMENTED OUT FOR UNIT TEST
       
        //print("Kitneedsparts size =" + kitNeedsParts.size());
        stateChanged();
    }
    
    public void setCamera(Camera c){
        this.camera = c;
    }
    public void setKitRobot(KitRobot k) {
        this.kitrobot = k;
    }

    public void setNestInterface(NestInterface n) {
        this.nest = n;
    }

    // Shouldn't need this anymore
    // public void setKitAssemblyManager(KitAssemblyManager k) {
    //     this.kam = k;
    // }

    @Override
    public void msgNeedPart(Part partType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void msgHereAreParts(List<Part> parts) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void DoMoveToNest(int nestNum){

           // kam.getPartsRobot().moveToNestCommand(nestNum);

	    this.client.sendMessage(Message.KAM_PARTS_MOVE_TO_NEST+":"+nestNum);
    }
    
    public void DoPickUpPart(int nestNum){
     //this.client.sendMessage(KAM_PARTS_PICK_PART+":"+nestNum);
     //this.fpm.sendMessage(KAM_PARTS_PICK_PART+":"+nestNum);
    }

    public void DoPutInKit(int kitNum){
    //  kam.getPartsRobot().dropOffParts(kitNum);  
      this.client.sendMessage(Message.KAM_PARTS_DROP_OFF_PARTS+":"+kitNum);
      this.fpm.sendMessage(Message.KAM_PARTS_DROP_OFF_PARTS+":"+kitNum);
    }
}
