
package factory.general;

import java.util.ArrayList;
/**
A generic inventory that uses an ArrayList to store parts.
@brief generic arraylist inventory
@author YiWei Roy Zheng
@version 0.1
 */
class GenericInventory implements Inventory{
    ArrayList<Part> parts;///<holds the parts

    public GenericInventory()
    {
	this.parts = new ArrayList<Parts>();
    }
    public void addPart(Part p){
	this.parts.add(p);
}
    public void clear()
    {
	this.parts.clear();
    }
    public Integer size()
    {
	return this.parts.size();
    }
    public Part get(Integer i)
    {
	return this.parts.get(i);
    }
}