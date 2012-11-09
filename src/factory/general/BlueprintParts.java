package factory.general;

import java.util.ArrayList;
import java.io.Serializable;

/**
@brief stores part blueprints
 */

//number name description filename

class BlueprintParts implements Blueprint, Serializable {

    ArrayList<Part> parts;

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
    public void add(Part in)
    {
        this.parts.add(in);
    }
    /**
    @brief deserializes the passed string and turns it into a collectible
     */
    public ArrayList<Part> deserialize(String serialized)
    {
        ArrayList<String> stringform = new ArrayList<String>();

        for(int i = 0; i != serialized.length(); i++)
        {
            String integer = "";
            if(serialized.charAt(i) == '(')
            {
                Integer chars = integer.toInteger();
                integer = "";
                String part = "";
                for(int ii = 0; ii != chars; ii++)
                {
                    part = part + serialized.charAt(ii+i);
                }
                stringform.add(part);
                i += chars;
            }
            else
            {
                integer = integer + serialized.charAt(i);
            }
        }
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
            toreturn = toreturn+this.parts.get(i).toString().length()+this.parts.get(i).toString();
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
        Part p = new Part("part1","is a part");
        p.setFilename("part1.png");
        BlueprintParts bp = new BlueprintParts();
        bp.add(p);
        p = new Part("part2","is not a part");
        p.setFilename("part2.png");
        bp.add(p);
        System.out.println(bp.serialize());
    }
}