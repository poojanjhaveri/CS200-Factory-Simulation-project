package factory.factory201.partsManagement;

import agent.Agent;
import factory.factory200.kitAssemblyManager.KitAssemblyManager;
import factory.factory201.interfaces.KitRobot;
import factory.factory201.interfaces.NestInterface;
import factory.factory201.interfaces.PartsInterface;
import factory.factory201.kitManagement.KitRobotAgent;
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
public class PartsAgent extends Agent implements PartsInterface{
    KitAssemblyManager kam;
    KitRobot kitrobot;
    Kit kit;
    NestInterface nest;
    private List<Part> inventory =
            Collections.synchronizedList(new ArrayList<Part>());
    private List<Part> grips =
            Collections.synchronizedList(new ArrayList<Part>());
    private List<Part> kitNeedsParts =
            Collections.synchronizedList(new ArrayList<Part>());
    private List<Kit> newKit =
            Collections.synchronizedList(new ArrayList<Kit>());

    public void PartsAgent(){
        
    }
//Messages 
    public void msgHereIsKit(Kit k){
        print("PartsAgent got message for new kit");
    	newKit.add(kit);
    	stateChanged();
    	
    }
    
      
    public void msgHereIsPart(Part p) {
        print("got part " + p + "from nest");
        inventory.add(p);
        stateChanged();
    }

    
    
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
       
        
        if (!inventory.isEmpty() && kit.standNum!=Kit.StandNum.none){
        	pickUpPart(inventory.remove(0));
        	return true;
        }

        if (kit.status == Kit.Status.full) {
            giveKitToKitAgent();
            return true;
        }
        
        if (kitNeedsParts.isEmpty()){
        	giveKitToKitAgent();
        }
        	
        
        if (!newKit.isEmpty()){
        	startNewKit(newKit.get(newKit.size()));
        	newKit.clear();
        	return true;
        }
        else
        	startNewKit(kit);

        return false;
    }
//Actions

    private void giveKitToKitAgent() {
        print("giving kitrobot complete kit");
        kitrobot.msgKitIsFull();
    }
    
    private void startNewKit(Kit k){
        print("New kit being started");
    	kitNeedsParts.clear();
    	this.kit = k;
    	for(int i=0; i<kit.getSize(); i++){
    		kitNeedsParts.add(k.getPart(i));
    	}
    	kitrobot.msgNeedEmptyKit();
    	for (int i = 0; i < kit.getSize(); i++) {
            nest.msgNeedPart(kit.getPart(i));
    	}
    	stateChanged();
    }

    private void pickUpPart(Part p) {
        grips.add(p);
        
        
        kitNeedsParts.remove(p);
        print("picking up part "+ p);
        if (grips.size() == 4 || kitNeedsParts.isEmpty()){
            kam.getPartsRobot().moveToNestCommand(p.getNestNum());
            try{Thread.sleep(2);}
            catch(InterruptedException ex){}
            kam.pickPartCommand();}
        stateChanged();
    }

    private void putPartsInKit() {
        for (Part p: grips) {
            print("putting part " + p +" in kit");
            
                
                
        }
        kam.dropOffParts();
        
    }
    
    public void setKitRobot(KitRobot k){
        this.kitrobot=k;
    }
    
    public void setNestInterface(NestInterface n){
        this.nest=n;
    }
    
    public void setKitAssemblyManager(KitAssemblyManager k){
        this.kam = k;
    }

    @Override
    public void msgNeedPart(Part part) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void msgHereAreParts(Part part, int quantity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
