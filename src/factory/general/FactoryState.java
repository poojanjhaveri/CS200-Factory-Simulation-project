package factory.general;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.Exception;

/**
This is the data model for the Server. It contains the following
<ul>
<li>List of parts usable
<li>List of kits produceable
</ul>
@brief contains and maintains the state of all data for the server
@author YiWei Roy Zheng
@version 0.2
 */

public class FactoryState {
    public static final String PATH_BP_KITS="bpkits.data";
    public static final String PATH_BP_PARTS="bpparts.data";

    BlueprintParts bpparts;///<stores all possible parts
    BlueprintKits bpkits;///<stores all possible kits

    public FactoryState() {
        this.loadBlueprintKits();
        this.loadBlueprintParts();
        System.out.println("BlueprintKits and BlueprintParts loaded successfully");

        bpparts.save();
        bpkits.save();
    }
    public Kit getKitById(Integer id)
    {
       return this.bpkits.getKitById(id);
    }
    public Part getPartById(Integer id)
    {
        return this.bpparts.getPartById(id);
    }
    public BlueprintKits getBlueprintKits()
    {
	return this.bpkits;
    }
    public BlueprintParts getBlueprintParts()
    {
	return this.bpparts;
    }
    public void loadBlueprintKits() {
        File f = new File(FactoryState.PATH_BP_KITS);
        if (f.exists()) {
            try {
                FileInputStream fin = new FileInputStream(FactoryState.PATH_BP_KITS);
                ObjectInputStream oin = new ObjectInputStream(fin);
                Object obj = oin.readObject();
                if (obj instanceof BlueprintKits) {
                    this.bpkits = (BlueprintKits) obj;
                } else throw new Exception("wtf");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error occurred attempting to deserialize the BlueprintKits!" + e.getMessage());
                System.out.println("CREATING NEW "+FactoryState.PATH_BP_KITS);
                this.bpkits = new BlueprintKits();
            }
        } else {
            System.out.println("WARNING: COULD NOT FIND "+FactoryState.PATH_BP_KITS+" CREATING NEW!");
            this.bpkits = new BlueprintKits();
        }
    }
    public void loadBlueprintParts() {
        File f = new File(FactoryState.PATH_BP_PARTS);
        if (f.exists()) {
            try {
                FileInputStream fin = new FileInputStream(FactoryState.PATH_BP_PARTS);
                ObjectInputStream oin = new ObjectInputStream(fin);
                Object obj = oin.readObject();
                if (obj instanceof BlueprintParts) {
                    this.bpparts = (BlueprintParts) obj;
                } else throw new Exception("wtf");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error occurred attempting to deserialize the BlueprintParts!" + e.getMessage());
                System.out.println("CREATING NEW "+FactoryState.PATH_BP_PARTS);
                this.bpparts = new BlueprintParts();
            }
        } else {
            System.out.println("WARNING: COULD NOT FIND "+FactoryState.PATH_BP_PARTS+" CREATING NEW!");
            this.bpparts = new BlueprintParts();
        }
    }
    /**
@brief removes a part by its id
     */
    public void removePartById(Integer id)
    {
	this.bpparts.removePart(id);
    }
    /**
@brief removes a kit by its id
     */
    public void removeKitById(Integer id)
    {
	this.bpkits.removeKit(id);
    }
    public static void main(String[] args) {
        FactoryState fs = new FactoryState();
    }
}