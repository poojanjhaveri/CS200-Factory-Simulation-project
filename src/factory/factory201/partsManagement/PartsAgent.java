package factory.factory201.partsManagement;

import agent.Agent;
import factory.ConfigFile;
import factory.general.Kit;
import factory.general.Part;
import factory.factory201.kitManagement.KitRobotAgent;
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
//nest array needed
    private List<ConfigFile> configInfo =
            Collections.synchronizedList(new ArrayList<ConfigFile>());
    private Map<Part, Integer> inventory = new HashMap<Part, Integer>();
    Part grips[];

    public class Grip {

        public boolean occupied;
    }

//Messages
    public void msgHereIsConfig(ConfigFile file) {
        configInfo.add(file);
        stateChanged();
    }

    public void msgHereAreParts(Part p, int quantity) {
        inventory.put(p, quantity);
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

        if (!inventory.isEmpty() && kit.status == Kit.Status.empty) {
            int n = 4;
            int grip = 0;
            if (kit.kitNeedsParts < 4) {
                n = kit.kitNeedsParts;
            }
            for (int i = 0; i < kit.parts.length; i++) {
                if (!kit.parts[i].inKit) {
                    if (inventory.containsKey(kit.parts[i])) {
                        pickUpPart(kit.parts[i], grip);
                        kit.parts[i].inKit = true;
                        grip++;
                        if (grip == n) {
                            putPartsInKit(n);
                        }
                        return true;
                    }
                }
            }
        }

        if (kit.status == Kit.Status.full) {
            giveKitToKitAgent();

        }

        return false;
    }
//Actions

    private void setConfiguration() {
        if (true //                configInfo.hasNewKit()
                ) {
            {
//                kit = configInfo.remove(0);
                kitagent.msgNeedEmptyKit();
            }
            for (int i = 0; i < kit.parts.length; i++) {
                nest.msgNeedPart(kit.parts[i]);

            }
        }
    }

    private void giveKitToKitAgent() {
        kitagent.msgKitIsFull(new Kit());
    }

    private void pickUpPart(Part p, int g) {
        grips[g] = p;
//        DoPickUpPart(p);
        kit.kitNeedsParts--;
        if (kit.kitNeedsParts == 0) {
            kit.status = Kit.Status.full;
        }
        inventory.put(p, inventory.get(p) - 1);
    }

    private void putPartsInKit(int n) {
        for (int i = 0; i < n; i++) {
//            DoPutPartInKit(grips[i]);
            kit.kitNeedsParts--;
        }
    }
}
