/**
A String serializable blueprint for a kit or a part.
@brief string serializable bp for kit/part
@author YiWei Roy Zheng
@version 0.1
 */

interface Blueprint{
    /**
@brief deserializes the passed string and turns it into a collectible
     */
    private ArrayList<Object> deserialize(String serialized);
    private ArrayList<Part> deserialize(String serialized);
    /**
@brief deserializes the passed string and adds to the current data 
     */
    private void updateOne(String serialized);
    /**
@brief serializes the current data into a deserializable string
     */
    private String serialize();
    /**
@brief turns the blueprint into a serialized string
     */
    public void recreate(String serialized);
}