package factory.general;

import java.util.ArrayList;
import java.io.Serializable;

/**
@brief stores part blueprints
 */

//number name description filename

class BlueprintParts implements Blueprint, Serializable {

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
    public BlueprintParts(String serialized)
    {
        this.recreate(serialized);
    }
    /**
    @brief creates the object using a passed arraylist of parts
     */
    public BlueprintParts(ArrayList<Part> p)
    {
        this.parts = p;
    }
    public void add(Part in)
    {
        this.parts.add(in);
    }
    /**
    @brief deserializes the passed string and turns it into an arraylist of parts
     */
    public ArrayList<Part> deserialize(String serialized)
    {
	ArrayList<String> stringform = Util.deserialize(serialized);
        ArrayList<Part> ret = new ArrayList<Part>();
        for(int i = 0; i != stringform.size(); i++)
        {
            ret.add(Part.deserialize(stringform.get(i)));
        }
        return ret;
    }
    /**
    @brief deserializes the passed string and adds to the current data
     */
    public void updateOne(String serialized)
    {

    }
    /**
    @brief serializes the current data into a deserializable string
     */
    public String serialize()
    {
        String toreturn ="";
        for(int i = 0; i != this.parts.size(); i++)
        {
            toreturn = toreturn+this.parts.get(i).serialize().length()+this.parts.get(i).serialize();
        }
        return toreturn;
    }
    /**
    @brief turns the blueprint into a serialized string
     */
    public void recreate(String serialized)
    {
        this.parts = deserialize(serialized);
    }

    public static void main(String[] args)
    {
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
        for(int i = 0; i != des.size(); i++)
        {
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
    }
}