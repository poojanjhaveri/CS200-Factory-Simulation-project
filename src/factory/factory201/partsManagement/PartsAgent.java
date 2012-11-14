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
    Kit kit;
    NestInterface nest;
    Camera camera;
    private List<Part> inventory, grips, kitNeedsParts;
    private List<Kit> newKit;

    public PartsAgent(String name) {
        super(name);
        this.inventory = Collections.synchronizedList(new ArrayList<Part>());
        this.grips = Collections.synchronizedList(new ArrayList<Part>());
        this.kitNeedsParts = Collections.synchronizedList(new ArrayList<Part>());
        this.newKit = Collections.synchronizedList(new ArrayList<Kit>());

    }
    
//Messages 

    @Override
    public void msgHereIsKit(Kit k) {
        print("PartsAgent got message for new kit");
        newKit.add(kit);
        stateChanged();

    }

    @Override
    public void msgHereIsPart(Part p) {
        print("got part " + p.getString() + "from nest");
        inventory.add(p);
        stateChanged();
    }

    @Override
    public void msgEmptyKitReady(Kit kit) {
        /* switch (kit.num) {
         case 1:
         kit.standNum = Kit.StandNum.zero;
         break;
         case 2:
         kit.standNum = Kit.StandNum.one;
         break;
         case 3:
         kit.standNum = Kit.StandNum.two;
         break;
         default:
         kit.standNum = Kit.StandNum.none;
         }*/
        print("PartsAgent got message for new kit");
        newKit.add(kit);
        print("got an empty kit for stand #" + kit.standNum);
        stateChanged();
    }
//Scheduler

    @Override
    protected boolean pickAndExecuteAnAction() {

        if (!newKit.isEmpty()) {
            startNewKit(newKit.remove(newKit.size()-1));
            //startNewKit(newKit.get(0));
            return true;
        }


        if (kit != null) {
            if (kit.status == Kit.Status.full) {
                giveKitToKitAgent();
                return true;
            }

            if (kitNeedsParts.isEmpty()) {
                giveKitToKitAgent();
                return true;
            }


        }
        if (!inventory.isEmpty() && kit.standNum != Kit.StandNum.none && grips.size() != 4) {
            pickUpPart(inventory.remove(0));
            return true;
        }


        return false;
    }
//Actions

    private void giveKitToKitAgent() {
        print("giving kitrobot complete kit");
        kitrobot.msgKitIsFull();
        kit.status = Kit.Status.empty;
        kit.standNum = Kit.StandNum.zero;
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            print("stopped sleeping for 10 seconds");
        }
        kitrobot.msgNeedEmptyKit();
        stateChanged();
    }

    private void startNewKit(Kit k) {

        // kitrobot.msgNeedEmptyKit();
        print("New kit being started");
        camera.msgHereIsKitInfo(k);
    	kitNeedsParts.clear();
    	this.kit = k;
       // kit.standNum = Kit.StandNum.one;
    	for(int i=0; i<kit.getSize(); i++){
    		kitNeedsParts.add(kit.getPart(i));
    	}
        
        
    	for (int i = 0; i < kit.getSize(); i++) {
            nest.msgNeedPart(kit.getPart(i));
    	}
    	stateChanged();

    }

    private void pickUpPart(Part p) {
        grips.add(p);


        kitNeedsParts.remove(p);
        print("picking up part " + p.getString());
        //kam.getKitStand().getKitPositions().get(1).setFilled(true);
        kam.getPartsRobot().moveToNestCommand(p.getNestNum());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            print("stopped sleeping");
        }
        kam.getPartsRobot().pickPartCommand(p.getNestNum());
        if (grips.size() == 4 || kitNeedsParts.isEmpty()) {

            putPartsInKit();
        }
        stateChanged();
    }

    private void putPartsInKit() {
        for (Part p : grips) {
            print("putting part " + p.getString() + " in kit");
        }
        grips.clear();
        kam.getPartsRobot().dropOffParts();
        try {
            Thread.sleep(3);
        } catch (InterruptedException ex) {
        }

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
