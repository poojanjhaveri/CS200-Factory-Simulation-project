package factory.factory201.kitManagement;

import factory.factory200.kitAssemblyManager.KitAssemblyManager;
import factory.general.*;

/**
 * @author Alex Young
 * @version 1
 */
public class KitStand {

    private KitAssemblyManager KAM;
    /**
     * The kit stand has three stands 1. Temporary stand to hold an empty kit 2.
     * Kitting stand which will hold the kit into which parts are being put 3.
     * Inspection stand where full kits are inspected
     */
    private Kit[] kits = new Kit[3];

    public KitStand() {
        kits[0] = kits[1] = kits[2] = null;
//        Kit k = new Kit("Test");
//        k.status = Kit.Status.full;
//        kits[1] = k;
    }

    /**
     * When adding a kit to the kit stand, the kitting stand has first priority,
     * then the temporary stand.
     *
     * @param kit Kit to be added to the kit stand
     * @return True if the kit was successfully added, false otherwise
     */
    public boolean addKit(Kit kit) {
        if (kits[1] == null) {
            kits[1] = kit;
            kits[1].standNum = Kit.StandNum.one;
            return true;
        } else if (kits[0] == null) {
            kits[0] = kit;
            kits[0].standNum = Kit.StandNum.zero;
            return true;
        } else {
            return false;
        }
    }

    /**
     * Allows access to array of kits
     *
     * @param i Index
     * @return Kit at given index
     */
    public Kit get(int i) {
        return kits[i];
    }

    /**
     * Removes kit at index i and returns it
     *
     * @param i Index
     * @return Kit that was removed
     */
    public Kit remove(int i) {
        Kit k = kits[i];
        kits[i] = null;
        k.standNum = Kit.StandNum.none;
        return k;
    }

    /**
     * Checks if the inspection stand is empty
     *
     * @return Self-explanatory
     */
    public boolean isEmpty(int i) {
        return (kits[i] == null);
    }

    /**
     * Checks the availability of the kit stand
     *
     * @return Self-explanatory
     */
    public int availability() {
        if (kits[1] == null) {
            return 2;
        } else if (kits[0] == null) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Moves the full kit to inspection stand and if there is an empty kit in
     * the temporary stand it moves it into the kitting stand
     */
    public void moveFullKitToInspection(Kit kit) {
        if(kit.equals(kits[1])) {
            kits[2] = kits[1];
            kits[1] = null;
            
            if (kits[0] != null) {
                kits[1] = kits[0];
                kits[1].standNum = Kit.StandNum.one;
                kits[0] = null;
            }
        } else if(kit.equals(kits[0])) {
            kits[2] = kits[0];
            kits[0] = null;
        }
        kits[2].standNum = Kit.StandNum.two;
        
    }

    public boolean isEmpty() {
        return (kits[0] == null && kits[1] == null && kits[2] == null);
    }
    
    private void DoMoveKitFromConveyorTo0() {
        
    }
    
    private void DoMoveKitFromConveyorTo1() {
        
    }
    
    private void DoMoveKitFrom0to1() {
        KAM.getKitRobot().moveEmptyKitToActive();
	
    }
    
    private void DoMoveKitFrom1to2() {
        
    }
    
    public void setKitAssemblyManager(KitAssemblyManager KAM) {
        this.KAM = KAM;
    }
}
