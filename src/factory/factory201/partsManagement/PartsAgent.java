package factory.factory201.partsManagement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import agent.Agent;
import factory.factory201.interfaces.Camera;
import factory.factory201.interfaces.KitRobot;
import factory.factory201.interfaces.NestInterface;
import factory.factory201.interfaces.PartsInterface;
import factory.general.BlueprintKits;
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
    Kit kit0Info;
    Kit kit1Info;
    NestInterface nest;
    Camera camera;
    int kits=0;
    boolean kitZero = false;
    boolean kitOne = false;
    public List<Part> inventory, grips, kit0NeedsParts, kit1NeedsParts;
    public List<Kit> newKit;
    boolean emptyKitReady;
    public List<Kit> kitsStarted;

    public PartsAgent(String name) {
        super(name); 
        // Should these be creating NEW ArrayLists of things, or should they be getting the list
        // from somewhere else (i.e., the server)?
        this.inventory = Collections.synchronizedList(new ArrayList<Part>());
        this.grips = Collections.synchronizedList(new ArrayList<Part>());
        this.kit0NeedsParts = Collections.synchronizedList(new ArrayList<Part>());
        this.kit1NeedsParts = Collections.synchronizedList(new ArrayList<Part>());
        this.newKit = Collections.synchronizedList(new ArrayList<Kit>());
        this.kitsStarted = Collections.synchronizedList(new ArrayList<Kit>());
    }
    
//Messages 

    // message from server
    //@Override
    public void msgHereIsKit(List<Kit> newKits) {
        print("PartsAgent got message for new kits");
        for (Kit k: newKits){
        newKit.add(k);}
        DoGiveKitsInQueue(newKit);
        stateChanged();
    }

    @Override
    public void msgHereIsPart(Part p) {
        print("Got part " + p.getString() + "from nest");
        inventory.add(p);
        stateChanged();
    }

    // msg from kit robot
    @Override
    public void msgEmptyKitReady(Kit k) {

        if(k.standNum==Kit.StandNum.zero){
           kitZero=true;
           
          }
            else{
            kitOne=true;
            
            }

        print("got an empty kit for stand #" + k.standNum);
        stateChanged();
    }
//Scheduler

    @Override
    public boolean pickAndExecuteAnAction() {
       
        if (!newKit.isEmpty() && kits!=2) {
            kits++;
            startNewKit(newKit.remove(0));
            return true;
        } 
    
       
       if(kitZero && !kitsStarted.isEmpty()){
           setKit0(kitsStarted.remove(0));
           kitZero=false;
           return true;
       }
       if(kitOne && !kitsStarted.isEmpty()){
           setKit1(kitsStarted.remove(0));
           kitOne=false;
           return true;
       }
       
       if(!kit0NeedsParts.isEmpty()){
          if (!inventory.isEmpty()) {
                for(Part p: kit0NeedsParts){
                if(inventory.get(0).type==p.type){
                   pickUpPart0(inventory.remove(0));
            return true;
                }}
            }
      }
            
       if(!kit1NeedsParts.isEmpty()){
            if (!inventory.isEmpty()) {
                for(Part p: kit1NeedsParts){
                    if(inventory.get(0).type==p.type){
                        pickUpPart1(inventory.remove(0));
                    return true;
                }}
          }
            }
<<<<<<< HEAD

   
=======
>>>>>>> 920eeafe465768d0e4bad411900f03ea95c03269
       return false;
    }
//Actions

    private void giveKitToKitAgent(Kit k) {
        print("giving kitrobot complete kit #" + k.standNum);
        print("KIT0NEEDSPARTS SIZE: [" + kit0NeedsParts.size()+ "]");
        print("KIT1NEEDSPARTS SIZE: [" + kit1NeedsParts.size() + "]");
        print("INVENTORY SIZE: [" + inventory.size() + "]");
        kits--;
        if(k.standNum==Kit.StandNum.zero){
            kit0Info = null;
            //kit0=null;
        }
        else{
            kit1Info = null;
            //kit1 = null;
        }
            
        kitrobot.msgKitIsFull(k);
        stateChanged();
    }

    private void startNewKit(Kit k) {
       
        DoGiveKitsInAction(k);
        DoGiveKitsInQueue(newKit);
        
        print("New kit being started");
        camera.msgHereIsKitInfo(k);
        kitsStarted.add(k);
       
        for (int i = 0; i < k.getSize(); i++) {
            nest.msgNeedPart(k.getPart(i));}
        
        kitrobot.msgNeedEmptyKit();//change to put empty kits in list when recieve the message back
    	
        stateChanged();

    }
    
    private void pickUpPart0(Part p){
        grips.add(p);
        
        for (Part part: kit0NeedsParts){
            if (part.type == p.type){
              kit0NeedsParts.remove(part);
              break;
            }}
       
        DoPickUpPart(p.getNestNum());
        
        if (grips.size() == 4 || kit0NeedsParts.isEmpty()) {
        putPartsInKit(0);
        }
        if (kit0NeedsParts.isEmpty()){
            giveKitToKitAgent(kit0);}
        stateChanged();
    }
    
     private void pickUpPart1(Part p){
        grips.add(p);
        
        for (Part part: kit1NeedsParts){
            if (part.type == p.type){
              kit1NeedsParts.remove(part);
           //   print("REMOVING PART FROM KIT1NEEDSPARTS NEW SIZE IS " +  kit1NeedsParts.size());
               break;
            }}
      //  print("picking up part " + p.getString());
        
        DoPickUpPart(p.getNestNum());
       
        if (grips.size() == 4 || kit1NeedsParts.isEmpty()) {
        putPartsInKit(1);
        }
        if (kit1NeedsParts.isEmpty()){
            giveKitToKitAgent(kit1);}
        stateChanged();
    }

    private void setKit0(Kit k){
        kit0Info=k;
           kit0=kit0Info;
           kit0.status = Kit.Status.ready;
           kit0.standNum = Kit.StandNum.zero;
           for(int i=0; i<kit0Info.parts.size(); i++){
    		kit0NeedsParts.add(kit0Info.getPart(i));
    	}
    }
    
    private void setKit1(Kit k){
     kit1Info=k;
            kit1=kit1Info;
            kit1.status = Kit.Status.ready;
            kit1.standNum = Kit.StandNum.one;
            for(int i=0; i<kit1Info.parts.size(); i++){
    		kit1NeedsParts.add(kit1Info.getPart(i));
    	}   
    }

    private void putPartsInKit(int kitNum) {
        for (Part p : grips) {
            print("putting part " + p.getString() + " in kit number "+ kitNum);
        }
      /*  try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
        }*/
        grips.clear();
        DoPutInKit(kitNum);
      
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

    @Override
    public void msgNeedPart(Part partType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void msgHereAreParts(List<Part> parts) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    

    public void DoMoveToNest(int nestNum) {
        if (this.client != null) {
            this.fpm.sendMessage(Message.KAM_PARTS_MOVE_TO_NEST + ":" + nestNum);
            this.client.sendMessage(Message.KAM_PARTS_MOVE_TO_NEST + ":" + nestNum);
        } else {

            print("[ERROR] - Kit Assembly Manager is not online.");

        }
    }

    public void DoPickUpPart(int nestNum) {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_PARTS_PICK_PART + ":" + nestNum);
            this.fpm.sendMessage(Message.KAM_PARTS_PICK_PART + ":" + nestNum);
        } else {

            print("[ERROR] - Kit Assembly Manager is not online.");

        }
    }

    public void DoPutInKit(int kitNum) {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_PARTS_DROP_OFF_PARTS + ":" + kitNum);
            this.fpm.sendMessage(Message.KAM_PARTS_DROP_OFF_PARTS + ":" + kitNum);
        } else {

            print("[ERROR] - Kit Assembly Manager is not online.");

        }}
    
    public void DoGiveKitsInAction(Kit k){
<<<<<<< HEAD
=======


>>>>>>> 920eeafe465768d0e4bad411900f03ea95c03269
        if (this.client != null) {

            this.client.sendMessage(Message.KIT_IN_PRODUCTION+":"+k.getName());
        }
        else{
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
<<<<<<< HEAD
            
        }
=======
        
    }
>>>>>>> 920eeafe465768d0e4bad411900f03ea95c03269
    
    public void DoGiveKitsInQueue(List<Kit> kits){

        if (this.client != null) {
        BlueprintKits adhoc = new BlueprintKits((ArrayList)kits);
        this.client.sendMessage(Message.GIVE_KITS_IN_QUEUE+":"+adhoc.serialize());

    }
        else{
           print("[ERROR] - Kit Assembly Manager is not online."); 
        }
}
}
