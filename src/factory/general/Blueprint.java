package factory.general;

import java.util.ArrayList;


/**
A String serializable blueprint for a kit or a part.
@brief string serializable bp for kit/part
@author YiWei Roy Zheng
@version 0.1
 */
public interface Blueprint {
    /**
    @brief deserializes the passed string and adds to the current data
     */
    void updateOne(String serialized);
    /**
    @brief serializes the current data into a deserializable string
     */
    String serialize();
    /**
    @brief turns the blueprint into a serialized string
     */
    public void recreate(String serialized);
}