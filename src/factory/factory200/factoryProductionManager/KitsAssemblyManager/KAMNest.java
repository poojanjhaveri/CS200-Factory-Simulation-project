/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory200.factoryProductionManager.KitsAssemblyManager;




import factory.general.Part;
import java.util.ArrayList;
import javax.swing.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Deepa
 */

//temporary nest class until an actual nest class is made...
public class KAMNest {
    private ImageIcon nest;
    //private int nestNumber;
    private int x;
    private int y;
    private ArrayList<Part> parts;
    
    
    public KAMNest(){
        nest=new ImageIcon("pics/KAMnest.png");
        
        parts = new ArrayList<Part>();
    }

    /**
     * @return the nest
     */
    public ImageIcon getNest() {
        return nest;
    }
    
    public Part getPart(){
        
        if(parts.isEmpty())
            return null;
        
        Part temp=parts.get(0);
        parts.remove(0);
        return temp;
    }

    /**
     * @param nest the nest to set
     */
    public void setNest(ImageIcon nest) {
        this.nest = nest;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the parts
     */
    public ArrayList<Part> getParts() {
        return parts;
    }

    /**
     * @param parts the parts to set
     */
    public void setParts(ArrayList<Part> parts) {
        this.parts = parts;
    }
}