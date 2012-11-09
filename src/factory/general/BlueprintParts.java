/**
@brief stores part blueprints
 */

class BlueprintParts implements Blueprint{

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
    private ArrayList<Object> deserialize(String serialized)
    {

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

    }
    /**
@brief turns the blueprint into a serialized string
     */
    public void recreate(String serialized)
    {

    }
}