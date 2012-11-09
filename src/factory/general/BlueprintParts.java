package factory.general;

import java.util.ArrayList;
import java.io.Serializable;

/**
@brief stores part blueprints
 */

//number name description filename

class BlueprintParts implements Blueprint, Serializable{

    ArrayList<Part> parts;

    public BlueprintParts(){
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
return null;
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

    }

    public static void main(String[] args)
    {
	Part p = new Part("part1","is a part");
	BlueprintParts bp = new BlueprintParts();
	bp.add(p);
	System.out.println(bp.serialize());
    }
}