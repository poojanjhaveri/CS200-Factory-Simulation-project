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
        KAMKit temp=new KAMKit();
        temp=kit;
        if(temp.getX()==kitPositions.get(0).getX() && temp.getY()==kitPositions.get(0).getY()){
            kitPositions.get(0).setKit(temp);
            kitPositions.get(0).setFilled(true);
        }
        else if(temp.getX()==kitPositions.get(1).getX() && temp.getY()==kitPositions.get(1).getY()){
            kitPositions.get(1).setKit(temp);
            kitPositions.get(1).setFilled(true);
        }
        else if(temp.getX()==kitPositions.get(1).getX() && temp.getY()==kitPositions.get(1).getY()){
            kitPositions.get(2).setKit(temp);
            kitPositions.get(2).setFilled(true);
        }
    }
    
    public KAMKit giveKit(int y){
        KAMKit returnKit=new KAMKit();
        if(kitPositions.get(0).getY()==y){
            returnKit=kitPositions.get(0).getKit();
            kitPositions.get(0).setFilled(false);
        }
        else if(kitPositions.get(1).getY()==y){
            returnKit=kitPositions.get(1).getKit();
            kitPositions.get(1).setFilled(false);
        }
        else if(kitPositions.get(2).getY()==y){
            returnKit=kitPositions.get(2).getKit();
            kitPositions.get(2).setFilled(false);
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
