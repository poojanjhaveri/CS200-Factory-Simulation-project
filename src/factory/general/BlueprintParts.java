import java.util.ArrayList;

/**
@brief stores part blueprints
 */

//number name description filename

class BlueprintParts implements Blueprint, Serializable{

    ArrayList<Part> parts;

    public BlueprintParts(){

    }
    /**
@brief creates the object from a serialized string
     */
    public BlueprintParts(String serialized)
    {
	this.recreate(serialized);
    }
    /**
@brief deserializes the passed string and turns it into a collectible
     */
    private ArrayList<Part> deserialize(String serialized)
    {
ArrayList<String> stringform = new ArrayList<String>();
return null;
    }
    /**
@brief deserializes the passed string and adds to the current data 
     */
    private void updateOne(String serialized)
    {

    }
    /**
@brief serializes the current data into a deserializable string
     */
    private String serialize()
    {
	String toreturn ="";	
	for(int i = 0; i != this.parts.size(); i++)
	    {
		toreturn = toreturn+this.parts.get(i).toString().length()+this.parts.get(i).toString();
		    }
    }
    /**
@brief turns the blueprint into a serialized string
     */
    public void recreate(String serialized)
    {

    }
}