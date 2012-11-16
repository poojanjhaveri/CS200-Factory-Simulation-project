package factory.factory201.partsManagement;

import agent.Agent;
import factory.factory200.kitAssemblyManager.KitAssemblyManager;
import factory.factory201.interfaces.Camera;
import factory.factory201.interfaces.KitRobot;
import factory.factory201.interfaces.NestInterface;
import factory.factory201.interfaces.PartsInterface;
import factory.general.Kit;
import factory.general.Part;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    KitAssemblyManager kam;
    KitRobot kitrobot;
    Kit kit0;
    Kit kit1;
    NestInterface nest;
    Camera camera;
    public List<Part> inventory, grips, kit0NeedsParts, kit1NeedsParts;
    public List<Kit> newKit;
    boolean emptyKitReady;

    public PartsAgent(String name) {
        super(name);
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
        if(k.standNum==Kit.StandNum.zero)
            kit0.status = Kit.Status.ready;
        else
            kit1.status = Kit.Status.ready;
        print("got an empty kit for stand #" + k.standNum);
        stateChanged();
    }
//Scheduler

    @Override
    public boolean pickAndExecuteAnAction() {

        if (!newKit.isEmpty()) {
           startNewKit(newKit.remove(0));
            //newKit.clear();
            return true;
        }
        if (kit0!=null || kit1!=null){
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


        
        if (!inventory.isEmpty() && kit0.status == Kit.Status.ready && grips.size() != 4) {
            pickUpPart(inventory.remove(0));
            return true;
        }
        
        if (!inventory.isEmpty() && kit1.status == Kit.Status.ready && grips.size() != 4) {
            pickUpPart(inventory.remove(0));
            return true;
        }
        }
        


        return false;
    }
//Actions

    private void giveKitToKitAgent(Kit k) {
        print("giving kitrobot complete kit");

        kitrobot.msgKitIsFull(k);
        k.status = Kit.Status.empty;

        if(newKit.isEmpty()){
            newKit.add(k);
            print("Adding kit of size "+ k.getSize() + " to newKit list");}
        stateChanged();
    }

    private void startNewKit(Kit k) {

        
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
        for (Part part: kit0NeedsParts){
            if (part.type == p.type){
              kit0NeedsParts.remove(part);
              next = false;
              break;
            }}
        if(next){
        for (Part part: kit1NeedsParts){
            if (part.type == p.type){
              kit1NeedsParts.remove(part);
              break;
            }}}
        
        print("picking up part " + p.getString());
        //kam.getKitStand().getKitPositions().get(1).setFilled(true);
//        kam.getPartsRobot().moveToNestCommand(p.getNestNum()); **COMMENTED OUT FOR UNIT TEST
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            print("stopped sleeping");
        }
    //    kam.getPartsRobot().pickPartCommand(p.getNestNum()); **COMMENTED OUT FOR UNIT TEST
        if (grips.size() == 4 || kit0NeedsParts.isEmpty() || kit1NeedsParts.isEmpty()) {

            putPartsInKit();
        }
        stateChanged();
    }

    private void putPartsInKit() {
        for (Part p : grips) {
            print("putting part " + p.getString() + " in kit");
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
        }
        grips.clear();
   //     kam.getPartsRobot().dropOffParts(); **COMMENTED OUT FOR UNIT TEST
       
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

    public void setKitAssemblyManager(KitAssemblyManager k) {
        this.kam = k;
    }

    @Override
    public void msgNeedPart(Part partType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void msgHereAreParts(List<Part> parts) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
