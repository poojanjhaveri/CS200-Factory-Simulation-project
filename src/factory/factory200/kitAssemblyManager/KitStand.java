package factory.factory200.kitAssemblyManager;

import java.util.*;
import javax.swing.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Deepa
 */
public class KitStand {
    private ImageIcon kitStand;
    private int x;
    private int y;
    ArrayList<KAMKit> kitPositions;
    
    
    public KitStand(){
        kitStand=new ImageIcon("KAMkitStand.png");
        kitPositions=new ArrayList<KAMKit>();
        
    }

    /**
     * @return the kitStand
     */
    public ImageIcon getKitStand() {
        return kitStand;
    }

    /**
     * @param kitStand the kitStand to set
     */
    public void setKitStand(ImageIcon kitStand) {
        this.kitStand = kitStand;
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
