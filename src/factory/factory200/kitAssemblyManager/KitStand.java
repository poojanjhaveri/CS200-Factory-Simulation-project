package factory.factory200.kitAssemblyManager;



import java.awt.Graphics2D;
import java.util.*;
import javax.swing.*;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @brief the kit stand that holds active kits
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
    
    public KitStand(int x, int y){
        kitStand=new ImageIcon("pics/KAMkitStand.png");
        kitPositions=new ArrayList<KitPosition>();
        
        this.x=x;
        this.y=y;
        
        for(int i=1;i<=3;i++){
            kitPositions.add(new KitPosition());
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
    
    public void takeKit(KAMKit kit){
        KAMKit temp=kit;
        if(kit==null){
            System.out.println("GIVING KIT STAND A NULL KIT");
        }
        if(temp.getX()==kitPositions.get(0).getX() && temp.getY()-15==kitPositions.get(0).getY()){
            kitPositions.get(0).setKit(temp);
            kitPositions.get(0).getKit().updateParts();
            //kitPositions.get(0).setFilled(true);
            //System.out.println(kitPositions.get(0).isFilled());
        }
        else if(temp.getX()==kitPositions.get(1).getX() && temp.getY()-15==kitPositions.get(1).getY()){
            kitPositions.get(1).setKit(temp);
            kitPositions.get(1).getKit().updateParts();
            //kitPositions.get(1).setFilled(true);
            //System.out.println(kitPositions.get(1).isFilled());
        }
        else if(temp.getX()==kitPositions.get(2).getX() && temp.getY()-15==kitPositions.get(2).getY()){
            kitPositions.get(2).setKit(temp);
            kitPositions.get(2).getKit().updateParts();
            //kitPositions.get(2).setFilled(true);
            //System.out.println(kitPositions.get(2).isFilled());
        }
    }
    
    public KAMKit giveKit(int y){
        KAMKit returnKit=null;
        if(y==0){
            returnKit=kitPositions.get(0).getKit();
            kitPositions.get(0).setKit(null);
           //kitPositions.get(0).setFilled(false);
        }
        else if(y==1){
            returnKit=kitPositions.get(1).getKit();
            kitPositions.get(1).setKit(null);
            //kitPositions.get(1).setFilled(false);
        }
        else if(y==2){
            returnKit=kitPositions.get(2).getKit();
            kitPositions.get(2).setKit(null);
            //kitPositions.get(2).setFilled(false);
        }
        return returnKit;
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
