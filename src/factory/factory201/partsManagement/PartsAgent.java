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
    int kits=0;
    boolean kitZero = false;
    boolean kitOne = false;
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
    //@Override
    public void msgHereIsKit(List<Kit> newKits) {
        print("PartsAgent got message for new kits");
        for (Kit k: newKits){
        newKit.add(k);}
        stateChanged();
    }

    @Override
    public void msgHereIsPart(Part p) {
   //     print("got part " + p.getString() + "from nest");
        inventory.add(p);
        stateChanged();
    }

    // msg from kit robot
    @Override
    public void msgEmptyKitReady(Kit k) {

        if(k.standNum==Kit.StandNum.zero){
            this.kit0 =k;
            kitZero=true;
            kit0.status = Kit.Status.ready;
            
            }
            else{
            this.kit1=k;
            kitOne=true;
            kit1.status = Kit.Status.ready;
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
            //newKit.clear();
            return true;
        } 
        
        if (kit0!=null && kits!=0){
        
            if (!inventory.isEmpty() && kit0.status == Kit.Status.ready && grips.size() != 4) {
            pickUpPart0(inventory.remove(0));
            return true;
            }
     
        
            if (kit0NeedsParts.isEmpty()) {
                kitZero=false;
                print("giving kit0 to kitagent");
                giveKitToKitAgent(kit0);
                return true;
            }    
}
        
        if(kit1!=null && kits!=0){
         
            if (!inventory.isEmpty() && kit1.status == Kit.Status.ready && grips.size() != 4) {
            pickUpPart1(inventory.remove(0));
            return true;
            }  
            
           
            
             if (kit1NeedsParts.isEmpty()) {
                 kitOne=false;
                print("giving kit1 to kitagent");
                giveKitToKitAgent(kit1);
                return true;
            }
        }
       

        
        return false;
    }
//Actions

    private void giveKitToKitAgent(Kit k) {
        //print("giving kitrobot complete kit #" + k.standNum);
        
        kitrobot.msgKitIsFull(k);
       // k.status = Kit.Status.empty;
        kits--;
        if(k.standNum==Kit.StandNum.zero){
            kit0=null;
        }
        else
            kit1=null;
        
        stateChanged();
    }

    private void startNewKit(Kit k) {
       /* if(kitInfo!=null && kitInfo!=k){
           nest.setNestPurge(k.parts); 
        }*/
        DoGiveKitsInAction(k);
        DoGiveKitsInQueue(newKit);
        this.kitInfo = k;
        print("New kit being started");
        camera.msgHereIsKitInfo(k);
    	//kitNeedsParts.clear();
   
        if(kit0==null){
        kitrobot.msgNeedEmptyKit();
    	this.kit0 = k;
        for(int i=0; i<k.parts.size(); i++){
    		kit0NeedsParts.add(k.getPart(i));
    	}
       
    	for (int i = 0; i < k.getSize(); i++) {
            nest.msgNeedPart(k.getPart(i));
    	}
    }
        else{
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
    
    private void pickUpPart0(Part p){
        grips.add(p);
        
        for (Part part: kit0NeedsParts){
            if (part.type == p.type){
              kit0NeedsParts.remove(part);
           //   print("REMOVING PART FROM KIT0NEEDSPARTS NEW SIZE IS " +  kit0NeedsParts.size());
              
              break;
            }}
       // print("picking up part " + p.getString());
        
        DoPickUpPart(p.getNestNum());
      
        if (grips.size() == 4 || kit0NeedsParts.isEmpty()) {
        putPartsInKit(0);
        }
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
        stateChanged();
    }
/*
    private void pickUpPart(Part p) {
        grips.add(p);
        boolean next = true;
        int kitNum=-1000;
        
        for (Part part: kit0NeedsParts){
            if (part.type == p.type){
              kit0NeedsParts.remove(part);
              
              kitNum=0;
              break;
            }}
        if(next){
        for (Part part: kit1NeedsParts){
            if (part.type == p.type){
              kit1NeedsParts.remove(part);
              print("REMOVING PART FROM KIT1NEEDSPARTS");
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
      
        if (grips.size() == 4 || kit0NeedsParts.isEmpty() || kit1NeedsParts.isEmpty()) {
        putPartsInKit(kitNum);
        }
        stateChanged();
    }*/

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
         //   print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }

    public void DoPickUpPart(int nestNum) {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_PARTS_PICK_PART + ":" + nestNum);
            this.fpm.sendMessage(Message.KAM_PARTS_PICK_PART + ":" + nestNum);
        } else {
         //   print("[ERROR] - Kit Assembly Manager is not online.");
        }
    }

    public void DoPutInKit(int kitNum) {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_PARTS_DROP_OFF_PARTS + ":" + kitNum);
            this.fpm.sendMessage(Message.KAM_PARTS_DROP_OFF_PARTS + ":" + kitNum);
        } else {
           // print("[ERROR] - Kit Assembly Manager is not online.");
        }}
    
    public void DoGiveKitsInAction(Kit k){
        
        }
    
    public void DoGiveKitsInQueue(List<Kit> kits){
        
    }
}
