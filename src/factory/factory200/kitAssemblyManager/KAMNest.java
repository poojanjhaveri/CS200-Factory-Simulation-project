/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory200.kitAssemblyManager;

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

    public KAMNest() {
        nest = new ImageIcon("pics/KAMnest.png");

        parts = new ArrayList<Part>();
    }

    /**
     * @return the nest
     */
    public ImageIcon getNest() {
        return nest;
    }

    public Part getPart() {

        if (this.parts.isEmpty()) {
            System.out.println("ERROR: PART ROBOT ASKING FOR NULL PART!");
            return null;
          
        } else{
            Part temp = this.parts.get(0);
            this.parts.remove(0);
            return temp;
        }
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

    public void updateParts() {
        //System.out.println("parts size: "+parts.size());
        if (this.parts.size() <= 4 && this.parts.size() > 0) {
            for (int i = 0; i < this.parts.size(); i++) {
                this.parts.get(i).getGUIPart().setX(this.getX());
                this.parts.get(i).getGUIPart().setY(this.getY() + 18 * i);
            }
        }
        if (this.parts.size() <= 8 && this.parts.size() > 4) {
            for (int i = 0; i < 4; i++) {
                this.parts.get(i).getGUIPart().setX(this.getX());
                this.parts.get(i).getGUIPart().setY(this.getY() + 18 * i);
            }
            for (int i = 4; i < parts.size(); i++) {
                this.parts.get(i).getGUIPart().setX(this.getX() + 20);
                this.parts.get(i).getGUIPart().setY(this.getY() + 18 * (i - 4));
                //System.out.println("parts size: " + parts.size());
            }
        }
    }
    //fijsod DO THESE TWO METHODS
    public void nestUp(){
        this.setNest(new ImageIcon("pics/KAMnest.png"));
        
    }
    
    public void nestDown(){
        /*int size=this.getPlaceholder().get(i).getKit().getParts().size();
        for(int k=0;k<size;k++){
           this.getPlaceholder().get(i).getKit().getParts().remove(0);
        }*/
        this.setNest(new ImageIcon("pics/nestDown.png"));
        int size=this.getParts().size();
        for (int k=0; k<size; k++){
            this.getParts().remove(0);
        }
        
    }
}
