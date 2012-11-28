package factory.factory201.kitManagement;

import factory.general.*;

/**
 * @author Alex Young
 * @version 1
 * @brief Kit stand stores and controls kits for the kit robot
 */
public class KitStand {

    private KitRobotAgent agent;
    private Kit[] kits = new Kit[3];

    public KitStand(KitRobotAgent agent) {
        kits[0] = kits[1] = kits[2] = null;
        this.agent = agent;
    }

    public boolean addKit(Kit kit) {
        if (kits[1] == null) {
            kits[1] = kit;
            kits[1].standNum = Kit.StandNum.one;
            agent.DoMoveKitFromConveyorTo1();
            return true;
        } else if (kits[0] == null) {
            kits[0] = kit;
            kits[0].standNum = Kit.StandNum.zero;
            agent.DoMoveKitFromConveyorTo0();
            return true;
        } else {
            return false;
        }
    }

    public Kit get(int i) {
        return kits[i];
    }

    public Kit remove(int i) {
        Kit k = kits[i];
        kits[i] = null;
        k.standNum = Kit.StandNum.none;
        return k;
    }

    public boolean isEmpty(int i) {
        return (kits[i] == null);
    }

    public boolean contains(int i) {
        return (kits[i] != null);
    }

    public boolean availableToGive(int i) {
        return (kits[i] != null && !kits[i].beingUsedByPartsAgent);
    }

    public int availability() {
        if (kits[1] == null) {
            return 2;
        } else if (kits[0] == null) {
            return 1;
        } else {
            return 0;
        }
    }

    public void moveFullKitToInspection(Kit kit) {
        if (kit.equals(kits[1])) {
            kits[2] = kits[1];
            kits[1] = null;
            agent.DoMoveKitFrom1to2();
            if (kits[0] != null) {
                kits[1] = kits[0];
                kits[1].standNum = Kit.StandNum.one;
                kits[0] = null;
                agent.DoMoveKitFrom0to1();
            }
        } else if (kit.equals(kits[0])) {
            kits[2] = kits[0];
            kits[0] = null;
            agent.DoMoveKitFrom0to2();
        }
        kits[2].standNum = Kit.StandNum.two;

    }

    public boolean isEmpty() {
        return (kits[0] == null && kits[1] == null && kits[2] == null);
    }
    
    public void setNull(int index){kits[index]=null;}
}
