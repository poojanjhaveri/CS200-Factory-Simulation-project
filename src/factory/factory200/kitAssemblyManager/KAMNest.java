/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory200.kitAssemblyManager;



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
    private int nestNumber;
    private int x;
    private int y;
    
    public KAMNest(int i){
        nest=new ImageIcon("KAMnest.png");
        nestNumber=i;
    }

    /**
     * @return the nest
     */
    public ImageIcon getNest() {
        return nest;
    }

    /**
     * @param nest the nest to set
     */
    public void setNest(ImageIcon nest) {
        this.nest = nest;
    }

    /**
     * @return the nestNumber
     */
    public int getNestNumber() {
        return nestNumber;
    }

    /**
     * @param nestNumber the nestNumber to set
     */
    public void setNestNumber(int nestNumber) {
        this.nestNumber = nestNumber;
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
}
