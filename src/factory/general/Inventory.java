package factory.general;

//import java.util.HashMap;
//import java.util.Map;

/**
Universal interface for holding parts.
@brief holds a set of parts
@author YiWei Roy Zheng
 */
public interface Inventory {

    public void addPart(Part p){}
    public void clear();
    public Integer size();

}
