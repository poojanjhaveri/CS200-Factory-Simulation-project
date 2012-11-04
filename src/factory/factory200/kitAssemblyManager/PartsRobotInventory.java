package factory.factory200.kitAssemblyManager;

import java.util.LinkedList;
import factory.general.Inventory;
import factory.general.Part;
/**
Inventory built specifically for the parts robot. Uses a queue to hold up to a maximum of 4 items;
@brief custom inventory for parts robot
@author YiWei Roy Zheng
@version 0.1
 */
public class PartsRobotInventory implements Inventory {
    LinkedList<Part> parts;///<queue of parts

    public PartsRobotInventory()
    {
        this.parts = new LinkedList<Part>();
    }
    public void addPart(Part in)
    {
        if(this.parts.size() > 4)
        {
            System.out.println("CRITICAL ERROR: ATTEMPTING TO GIVE PARTSROBOTINVENTORY MORE THAN 4 PARTS! LAST PART GIVEN WAS IGNORED.");
        } else
            this.parts.add(in);
    }
    public Integer size()
    {
        return this.parts.size();
    }
    public void clear()
    {
        this.parts.clear();
    }
    public Part get()
    {
        return this.parts.poll();
    }
}