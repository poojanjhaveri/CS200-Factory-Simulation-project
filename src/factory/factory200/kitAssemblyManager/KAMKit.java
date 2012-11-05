/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory200.kitAssemblyManager;




import factory.general.Part;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Deepa
 */

//temporary kit class until an actual kit class is made...

public class KAMKit {
    private ImageIcon kit;
    //private int kitNumber;
    private int x;
    private int y;
    private ArrayList<Part> parts;
            
    public KAMKit(){
        kit=new ImageIcon("pics/KAMkit.png");
        parts=new ArrayList<Part>();
        //kitNumber=i;
    }

    /**
     * @return the kit
     */
    public ImageIcon getImage() {
        return getKit();
    }

    /**
     * @param kit the kit to set
     */
    public void setImage(ImageIcon kit) {
        this.setKit(kit);
    }

    /**
     * @return the kitNumber
     */
  

   
    

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }
    
    public void addPart(LinkedList<Part> in){
        for(int i=0;i<in.size();i++){
           parts.add(in.get(i)); 
        }
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
     * @return the kit
     */
    public ImageIcon getKit() {
        return kit;
    }

    /**
     * @param kit the kit to set
     */
    public void setKit(ImageIcon kit) {
        this.kit = kit;
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
