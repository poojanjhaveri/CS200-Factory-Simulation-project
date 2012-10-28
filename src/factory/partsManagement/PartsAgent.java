import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import agent.Agent;
/**Factory PartsAgent
 * gets kit information from server and obtains necessary parts to complete a kit
 * has 4 grips to pick up parts to build the kit
 * @author James Dalton
 * 
 *
 */

public class PartsAgent extends Agent{
KitAgent kitagent;
Kit kit;

NestAgent nest;
//nest array needed
private List<configFile> configInfo =
Collections.synchronizedList(new ArrayList<configFile>());
private Map<Part, Integer> inventory = new HashMap<Part, Integer>();
Part grips[];
public class Grip{
	public boolean occupied;
	}



//Messages
public void msgHereIsConfig(configFile){
	configInfo.add(configFile);
	stateChanged();
}

public void msgHereAreParts(Part p, int quantity){
	inventory.put(Part, quantity);
	stateChanged();
}

public void msgEmptyKitReady(int num){
	kit.kittingStandNumber = num;
	stateChanged();
}
//Scheduler
protected boolean pickAndExecuteAnAction() {
	if (!configInfo.isEmpty()){
		setConfiguration();
		return true;
	}
	
	if (!inventory.isEmpty() && kit.status == Kit.Status.empty){
		int n = 4;
		int grip = 0;
		if (kit.kitNeedsParts<4)
			n = kit.kitNeedsParts;
		for (int i=0; i<kit.parts.length; i++){
			if (!kit.parts[i].inKit)
				if (inventory.containsKey(kit.parts[i])){
				pickUpPart(kit.parts[i], grip);
				kit.parts[i].inKit = true;
				grip++;
				if (grip == n)
					putPartsInKit(n);
					return true;
				}
		} 
	}
	
	if (kit.status == Kit.Status.full){
		giveKitToKitAgent();
		
	}
	
	return false;
}
//Actions
private void setConfiguration(){
	if (configInfo.hasNewKit()){
		{kit = configInfo.getKit();
		kitagent.msgNeedEmptyKit();}
	for (int i=0; i<kit.parts.length; i++){
		nest.msgNeedPart(kit.parts[i]);
		
	}}
}

private void giveKitToKitAgent(){
	kitagent.msgKitIsComplete();
}
private void pickUpPart(Part p, int g){
	grips[g] = p;
	doPickUpPart(p);
	kit.kitNeedsParts--;
	if (kit.kitNeedsParts == 0){
		kit.status = Kit.Status.full;
	}
	inventory.put(p, inventory.get(p)-1);
}

private void putPartsInKit(int n){
	for (int i =0; i<n; i++){
	doPutPartInKit(grips[i]); 
	kit.kitNeedsParts--;}
}
}
