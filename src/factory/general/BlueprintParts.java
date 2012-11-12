package factory.general;

import java.util.ArrayList;
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/**
@brief stores part blueprints
 */

//number name description filename

public class BlueprintParts implements Blueprint, Serializable {

    ArrayList<Part> parts;
    /**
    @brief creates an empty blueprint parts
     */
    public BlueprintParts() {
        this.parts=new ArrayList<Part>();
    }
    /**
    @brief creates the object from a serialized string
     */
    public BlueprintParts(String serialized) {
        this.recreate(serialized);
    }
    /**
    @brief creates the object using a passed arraylist of parts
     */
    public BlueprintParts(ArrayList<Part> p) {
        this.parts = p;
    }
    public void add(Part in) {
        this.parts.add(in);
    }
    /**
    @brief deserializes the passed string and turns it into an arraylist of parts
     */
    public static ArrayList<Part> deserialize(String serialized) {
        ArrayList<String> stringform = Util.deserialize(serialized);
        ArrayList<Part> ret = new ArrayList<Part>();
        for(int i = 0; i != stringform.size(); i++) {
            ret.add(Part.deserialize(stringform.get(i)));
        }
        return ret;
    }
    public ArrayList<Part> getParts() {
        return this.parts;
    }
    public void removePart(Part pt) {
       parts.remove(pt);
    }
    
    public Part getPartAt(int i) {
        return this.parts.get(i);
    }
    
    public int getSize(){
    	return parts.size();
    	
    }
    /**
    @brief deserializes the passed string and adds to the current data
     */
    public void updateOne(String serialized) {
        this.parts.addAll(this.deserialize(serialized));
    }
    /**
    @brief serializes the current data into a deserializable string
     */
    public String serialize() {
        String toreturn ="";
        for(int i = 0; i != this.parts.size(); i++) {
            toreturn = toreturn+this.parts.get(i).serialize().length()+this.parts.get(i).serialize();
        }
        return toreturn;
    }
    /**
    @brief recreates the parts list using the passed serialized blueprintparts
     */
    public void recreate(String serialized) {
        this.parts = deserialize(serialized);
    }
    public void save() {
        try {
            FileOutputStream out = new FileOutputStream(FactoryState.PATH_BP_PARTS);
            ObjectOutputStream objOut = new ObjectOutputStream(out);
            objOut.writeObject(this);
	    System.out.println("BlueprintParts saved");
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED INVOKING BP PARTS SAVE METHOD!" + e.getMessage());
            e.printStackTrace();
        }
    }
    public void debug() {
        if(this.parts.size() == 0)
            System.out.println("BlueprintParts debug size 0.");
        for(int i = 0; i != this.parts.size(); i++) {
            System.out.println("line "+i+":"+this.parts.get(i).serialize());
        }
    }
    public static void main(String[] args) {
        System.out.print("Adding parts...");
        Part p = new Part("part1","is a part");
        p.setFilename("part1.png");
        BlueprintParts bp = new BlueprintParts();
        bp.add(p);
        p = new Part("part2","is (not) a part");
        p.setFilename("part2.png");
        bp.add(p);
        p = new Part("alfalfa","heyo");
        p.setFilename("gogo.png");
        bp.add(p);
        System.out.println("ADDED");
        System.out.println("SERIALIZED BLUEPRINT");
        System.out.println(bp.serialize());
        ArrayList<Part> des = bp.deserialize(bp.serialize());
        System.out.println("DESERIALIZED BLUEPRINT");
        for(int i = 0; i != des.size(); i++) {
            System.out.println("line 0:"+des.get(i).serialize());
        }
        System.out.println("SERIALIZED DESERIALIZED SERIALIZED BLUEPRINT");
        BlueprintParts bp2 = new BlueprintParts(des);
        System.out.println(bp2.serialize());
        System.out.println("SERIALIZED BLUEPRINT");
        System.out.println(bp.serialize());
        if(bp2.serialize().equals(bp.serialize()))
            System.out.println("Test passed");
        else System.out.println("TEST FAILED");

        System.out.println("Testing empty BlueprintParts...");
        bp = new BlueprintParts();
        bp.debug();
        String s = bp.serialize();
        System.out.println("Serialized: "+s);
        bp.recreate(s);
        bp.debug();
        if(s.equals(bp.serialize())) {
            System.out.println("Test passed");
        } else System.out.println("TEST FAILED");
    }
}