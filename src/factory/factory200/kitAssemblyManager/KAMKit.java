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

//temporary kit class until an actual kit class is made...

public class KAMKit {
    private ImageIcon kit;
    private int kitNumber;
    private int x;
    private int y;
            
    public KAMKit(int i){
        kit=new ImageIcon("pics/KAMkit.png");
        kitNumber=i;
    }

    /**
     * @return the kit
     */
    public ImageIcon getImage() {
        return kit;
    }

    /**
     * @param kit the kit to set
     */
    public void setImage(ImageIcon kit) {
        this.kit = kit;
    }

    /**
     * @return the kitNumber
     */
    public int getKitNumber() {
        return kitNumber;
    }

    /**
     * @param kitNumber the kitNumber to set
     */
    public void setKitNumber(int kitNumber) {
        this.kitNumber = kitNumber;
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
