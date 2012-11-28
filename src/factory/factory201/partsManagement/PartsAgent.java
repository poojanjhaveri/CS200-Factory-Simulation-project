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
import java.util.concurrent.Semaphore;

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

    Semaphore s = new Semaphore(0, true);
    Kit kit0;//pointer to kit on stand number 0
    Kit kit1;//pointer to kit on stand number 1
    Kit kit0Info;//information for kit on stand number 0
    Kit kit1Info;//information for kit on stand number 1
    int kits=0;//used to keep track of how many kits are being worked on
    boolean kitZero = false;//used to tell if kit stand number 0 has an empty kit ready
    boolean kitOne = false;//used to tell if kit stand number 1 has an empty kit ready
    public List<Part> inventory, grips, kit0NeedsParts, kit1NeedsParts;
    public ArrayList<Kit> newKit;
    boolean emptyKitReady;
    boolean requestState=false;
    public List<Kit> kitsStarted;
    
    KitRobot kitrobot;
    NestInterface nest;
    Camera camera;

    public PartsAgent(String name) {
        super(name); 
        inventory = Collections.synchronizedList(new ArrayList<Part>());//the parts that the nestAgent has given to the partsAgent to use
        grips = Collections.synchronizedList(new ArrayList<Part>());//grips, max size of 4
        kit0NeedsParts = Collections.synchronizedList(new ArrayList<Part>());//parts that kit0 needs to be completed
        kit1NeedsParts = Collections.synchronizedList(new ArrayList<Part>());//parts that kit1 needs to be completed
        newKit = (new ArrayList<Kit>());//kits given from server to work on
        kitsStarted = Collections.synchronizedList(new ArrayList<Kit>()); //used to set all the kit pointers above (kit1, kit1info, kit1needsparts)
    }
    
//Messages 

    public void msgAnimationComplete(){
        s.release();
    }
    
    public void msgHereIsKit(ArrayList<Kit> newKits) {// message from server
        print("PartsAgent got message for new kits");
        for (Kit k: newKits){
        newKit.add(k);}
        DoGiveKitsInQueue(newKit);
        requestState=false;
        stateChanged();
    }

    @Override
    public void msgHereIsPart(Part p) {
        print("Got part " + p.getInt() + "from nest");
        inventory.add(p);
        stateChanged();
    }

    // msg from kit robot
    @Override
    public void msgEmptyKitReady(Kit k) {//kitrobot will have put it on a stand number, this will set the pointers

        if(k.standNum==Kit.StandNum.zero){
            kit0=k;
            kit0.standNum = Kit.StandNum.zero;
            kitZero=true;//means an empty kit is ready for kit stand 0
        }
        else if(k.standNum==Kit.StandNum.one) {
            kit1=k;
            kit1.standNum = Kit.StandNum.one;
            kitOne=true;
        }
            
        else{
            print("ERROR IN MSGEMPTYKITREADY");
           }

        print("got an empty kit for stand #" + k.standNum);
        stateChanged();
    }
//Scheduler

    @Override
    public boolean pickAndExecuteAnAction() {
       
        if (!newKit.isEmpty() && kits!=2) {//if there are not already 2 kits being worked on by this agent and there are new kit requests
            kits++;
            startNewKit(newKit.remove(0));
            requestState=true;
            return true;
        } 
    
       
       if(kitZero && !kitsStarted.isEmpty()){
           setKit0(kitsStarted.remove(0));//put all of this kit information into kit0 objects
           kitZero=false;//empty kit is no longer ready on kit stand 0
           return true;
       }
       if(kitOne && !kitsStarted.isEmpty()){
           setKit1(kitsStarted.remove(0));
           kitOne=false;
           return true;
       }
       
       if(!kit0NeedsParts.isEmpty()){//if kit 0 needs parts
          if (!inventory.isEmpty()) {//if the parts agent has parts to work with
                for(Part p: kit0NeedsParts){
                if(inventory.get(0).type==p.type){
                   pickUpPart0(inventory.remove(0));//put the part in a grip if the kit needs it
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
           // kit0.parts.clear();//remove if k.parts.size()==0
        }
        else{
            kit1Info = null;
           // kit1.parts.clear();
        }
           

        kitrobot.msgKitIsFull(k);
        print("KIT SIZE IS " + k.parts.size());
        stateChanged();
    }

    private void startNewKit(Kit k) {
       
        DoGiveKitsInAction(k);//giving the kit being worked on to Matt for gui display
       // DoGiveKitsInQueue(newKit);
        
        print("New kit being started" + k.getName());
        camera.msgHereIsKitInfo(k);//later used by the camera when inspecting the full kit
        kitsStarted.add(k);
       
        for (int i = 0; i < k.getSize(); i++) {
            nest.msgNeedPart(k.getPart(i));
        }
        
        //if(requestState==true)
        {
            kitrobot.msgNeedEmptyKit();//requests an empty kit for the new kit that needs to be made
            requestState=false;
        }
            
        stateChanged();

    }
    
    private void pickUpPart0(Part p){//pick up part being used for kit on stand number 0
        grips.add(p);
        DoMoveToNest(p.getNestNum());
        for (Part part: kit0NeedsParts){
            if (part.type == p.type){
                kit0.parts.add(p);//adding part to kit0
              kit0NeedsParts.remove(part);//kit0 no longer needs the part
              break;
            }}
       
        DoPickUpPart(p.getNestNum());
        /*
        for(int i=0;i<kit0NeedsParts.size();i++)
        {
        print("parts still present with partsrobot " + kit0NeedsParts.get(i));
        }*/
        if (grips.size() == 4 || kit0NeedsParts.isEmpty()) {//if the kit needs no more parts or the grips are full
        putPartsInKit(0);
        }
        
        if (kit0NeedsParts.isEmpty()){
            giveKitToKitAgent(kit0);}
        stateChanged();
    }
    
     private void pickUpPart1(Part p){
        grips.add(p);
        DoMoveToNest(p.getNestNum());
        for (Part part: kit1NeedsParts){
            if (part.type == p.type){
                kit1.parts.add(p);
              kit1NeedsParts.remove(part);
           //   print("REMOVING PART FROM KIT1NEEDSPARTS NEW SIZE IS " +  kit1NeedsParts.size());
               break;
            }}
      //  print("picking up part " + p.getInt());
        
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
        print("kit0 size ["+ k.parts.size() + "]");
          // kit0=kit0Info;
           kit0.status = Kit.Status.ready;
           kit0.standNum = Kit.StandNum.zero;
           for(int i=0; i<kit0Info.parts.size(); i++){
    		kit0NeedsParts.add(kit0Info.getPart(i));
    	}
    }
    
    private void setKit1(Kit k){
        print("kit1 size ["+ k.parts.size() + "]");
        kit1Info=k;
           // kit1=kit1Info;
            kit1.status = Kit.Status.ready;
            kit1.standNum = Kit.StandNum.one;
            for(int i=0; i<kit1Info.parts.size(); i++){
    		kit1NeedsParts.add(kit1Info.getPart(i));
    	}   
    }

    private void putPartsInKit(int kitNum) {
        for (Part p : grips) {
            print("putting part " + p.getInt() + " in kit number "+ kitNum);
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
            s.acquireUninterruptibly();
            try {
            Thread.sleep(5000);
            } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         } 
        
        } else {
            print("DOMOVETONEST NUM ["+ nestNum+ "]");
            //print("[ERROR] - Kit Assembly Manager is not online.");

        }
    }

    public void DoPickUpPart(int nestNum) {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_PARTS_PICK_PART + ":" + nestNum);
            this.fpm.sendMessage(Message.KAM_PARTS_PICK_PART + ":" + nestNum);
            s.acquireUninterruptibly();
        try {
         Thread.sleep(1500);
         } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         }
        } else {
            print("DOMOVETONEST NUM ["+ nestNum+ "]");
           // print("[ERROR] - Kit Assembly Manager is not online.");

        }
    }

    public void DoPutInKit(int kitNum) {
        if (this.client != null) {
            this.client.sendMessage(Message.KAM_PARTS_DROP_OFF_PARTS + ":" + kitNum);
            this.fpm.sendMessage(Message.KAM_PARTS_DROP_OFF_PARTS + ":" + kitNum);
            s.acquireUninterruptibly();
        try {
         Thread.sleep(10000);
         } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         }
        print("Test-Dropping off parts");
        } else {
            print("DOMOVETOKIT NUM ["+ kitNum+ "]");
           // print("[ERROR] - Kit Assembly Manager is not online.");

        }}
    
    public void DoGiveKitsInAction(Kit k){
        if (this.client != null) {

            this.fpm.sendMessage(Message.KIT_IN_PRODUCTION+":"+k.getName());
        try {
         Thread.sleep(5000);
         } catch (InterruptedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
         }
        }
        else{
            print("[ERROR] - Kit Assembly Manager is not online.");
        }
            
        }
        
    
    
    public void DoGiveKitsInQueue(ArrayList<Kit> kits1){

        if (this.fpm != null) {
        BlueprintKits adhoc = new BlueprintKits(kits1);
        this.fpm.sendMessage(Message.GIVE_KITS_IN_QUEUE+":"+adhoc.serialize());

    }
        else{
           print("[ERROR] - Kit Assembly Manager is not online."); 
        }
}
}
