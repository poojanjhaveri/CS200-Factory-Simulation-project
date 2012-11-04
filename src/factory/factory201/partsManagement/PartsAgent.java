package factory.partsManagement;

import agent.Agent;
import factory.ConfigFile;
import factory.Kit;
import factory.Part;
import factory.kitManagement.KitRobotAgent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class PartsAgent extends Agent {

    KitRobotAgent kitagent;
    Kit kit;
    NestAgent nest;
    private List<Part> inventory =
            Collections.synchronizedList(new ArrayList<Part>());
    private List<Part> grips =
            Collections.synchronizedList(new ArrayList<Part>());
    private List<Part> kitNeedsParts =
            Collections.synchronizedList(new ArrayList<Part>());
    private List<Kit> newKit =
            Collections.synchronizedList(new ArrayList<Kit>());

//Messages 
    public void msgHereIsKit(Kit k){
    	newKit.add(kit);
    	stateChanged();
    	
    }
      
    public void msgHereIsPart(Part p) {
        inventory.add(p);
        stateChanged();
    }

    public void msgEmptyKitReady(int num) {
        switch (num) {
            case 1:
                kit.kittingStandNumber = Kit.KittingStandNumber.one;
                break;
            case 2:
                kit.kittingStandNumber = Kit.KittingStandNumber.two;
                break;
            case 3:
                kit.kittingStandNumber = Kit.KittingStandNumber.three;
                break;
            default:
                kit.kittingStandNumber = Kit.KittingStandNumber.none;
        }
        stateChanged();
    }
//Scheduler

    @Override
    protected boolean pickAndExecuteAnAction() {
        if (!configInfo.isEmpty()) {
            setConfiguration();
            return true;
        }

        if (!inventory.isEmpty() && kit.kittingStandNumber!=Kit.KittingStandNumber.none){
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
        	newKit.removeAll();
        	return true;
        }
        else
        	startNewKit(kit);

        return false;
    }
//Actions

    private void giveKitToKitAgent() {
        kitagent.msgKitIsComplete(kit);
    }
    
    private void startNewKit(Kit k){
    	kitNeedsParts.removeAll();
    	this.kit = k;
    	for(int i=0; i<k.length; i++){
    		kitNeedsParts.add(k.parts[i]);
    	}
    	kitagent.msgNeedEmptyKit();
    	for (int i = 0; i < kit.parts.length; i++) {
            nest.msgNeedPart(kit.parts[i]);
    	}
    	stateChanged();
    }

    private void pickUpPart(Part p) {
        grips.add(p);
        DoPickUpPart(p);
        kitNeedsParts.remove(p);
        if (grips.size() == 4 || kitNeedsParts.isEmpty())
        	putPartsInKit();
        stateChanged();
    }

    private void putPartsInKit() {
        for (Part p: grips) {
            DoPutPartInKit(grips.remove(p));
        }
        
    }
}
