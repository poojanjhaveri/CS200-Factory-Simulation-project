package factory.factory200.kitAssemblyManager;


import java.awt.Graphics2D;
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
    private ArrayList<KitPosition> kitPositions;

    /**
     * @return the kitPositions
     */
    public ArrayList<KitPosition> getKitPositions() {
        return kitPositions;
    }

    /**
     * @param kitPositions the kitPositions to set
     */
    public void setKitPositions(ArrayList<KitPosition> kitPositions) {
        this.kitPositions = kitPositions;
    }
    
    public class KitPosition{
        private KAMKit kit;
        private boolean filled;
        private int x;
        private int y;
        
        public KitPosition(int i){
            kit=new KAMKit(i);
            filled=false;
        }

        /**
         * @return the kit
         */
        public KAMKit getKit() {
            return kit;
        }

        /**
         * @param kit the kit to set
         */
        public void setKit(KAMKit kit) {
            this.kit = kit;
        }

        /**
         * @return the filled
         */
        public boolean isFilled() {
            return filled;
        }

        /**
         * @param filled the filled to set
         */
        public void setFilled(boolean filled) {
            this.filled = filled;
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
            kit.setX(x);
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
            kit.setY(y);
            this.y = y;
        }
        
        
        
    }
    public KitStand(int x, int y){
        kitStand=new ImageIcon("KAMkitStand.png");
        kitPositions=new ArrayList<KitPosition>();
        
        this.x=x;
        this.y=y;
        
        for(int i=1;i<=3;i++){
            kitPositions.add(new KitPosition(i));
        }
        int startingX=x+25;
        int startingY=y+10;
        
        kitPositions.get(0).setX(startingX);
        kitPositions.get(0).setY(startingY);
        //kitPositions.get(0).setFilled(true);
        kitPositions.get(1).setX(startingX);
        kitPositions.get(1).setY(startingY+125);
        kitPositions.get(2).setX(startingX);
        kitPositions.get(2).setY(startingY+250);
        
        
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
