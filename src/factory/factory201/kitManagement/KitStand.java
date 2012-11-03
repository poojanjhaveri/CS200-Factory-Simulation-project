package factory.factory201.kitManagement;

import factory.general.*;

/**
 * 
 * @author Alex
 */
public class KitStand {
    
    /**
     * The kit stand has three stands
     * 1. Temporary stand to hold an empty kit
     * 2. Kitting stand which will hold the kit into which parts are being put
     * 3. Inspection stand where full kits are inspected
     */
    Kit[] kits = new Kit[3];
    
    /**
     * When adding a kit to the kit stand, the kitting stand has first
     * priority, then the temporary stand.
     * 
     * @param kit Kit to be added to the kit stand
     * @return True if the kit was successfully added, false otherwise
     */
    public boolean addKit(Kit kit) {
        if(kits[1] == null) {
            kits[1] = kit;
            return true;
        } else if(kits[0] == null) {
            kits[0] = kit;
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
        return k;
    }
    
    /**
     * Checks if the inspection stand is empty
     * @return Self-explanatory
     */
    public boolean inspectionStandIsEmpty() {
        return (kits[2] == null);
    }
    
    /**
     * Checks the availability of the kit stand
     * @return Self-explanatory
     */
    public int availability() {
        if(kits[1] == null) {
            return 2;
        } else if(kits[0] == null) {
            return 1;
        } else {
            return 0;
        }
    }
    
    /**
     * Moves the full kit to inspection stand and if there
     * is an empty kit in the temporary stand it moves it into 
     * the kitting stand
     */
    public void moveFullKitToInspection() {
        kits[2] = kits[1];
        if(kits[0] != null) {
            kits[1] = kits[0];
            kits[0] = null;
        } else {
            kits[1] = null;
        }
    }
    
    public boolean isEmpty() {
        return (kits[0] == null && kits[1] == null && kits[2] == null);
    }
}