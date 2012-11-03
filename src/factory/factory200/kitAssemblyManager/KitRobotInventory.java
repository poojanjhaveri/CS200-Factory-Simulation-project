package factory.factory200.kitAssemblyManager;

import java.util.LinkedList;

public class KitRobotInventory implements Inventory{
    LinkedList<Part> parts;///<queue of parts

    public KitRobotInventory()
    {
	this.parts = new LinkedList<Part>();
    }
    public void pickup(Part in)
    {
 
    }

}