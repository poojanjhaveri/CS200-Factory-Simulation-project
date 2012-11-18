package factory.factory200.kitAssemblyManager;

//Class: KitDeliveryStation



import java.awt.*;
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
public class KitDeliveryStation {
    //THIS NUMBER WILL BE GIVEN BY THE SERVER=NUMBER OF KITS THAT SHOULD BE MADE IN FACTORY
    private int numEmptyKits;
    private ArrayList<PlaceHolder> placeholder;
    private int num;
    
    public KitDeliveryStation(int kits){
        numEmptyKits=kits;
        placeholder=new ArrayList<PlaceHolder>();
        if(numEmptyKits>0){
        for(int i=0;i<numEmptyKits;i++){
            PlaceHolder temp = new PlaceHolder();
            temp.setNumber(i+1);
            placeholder.add(temp);
            
        }  
        }
        int xStart=25;
        int yStart=680;
        for(int i=0;i<numEmptyKits;i++){
        placeholder.get(i).setX(xStart-10);
        placeholder.get(i).setY(yStart);
        }
    }
    
    public void addKit(){
        numEmptyKits++;
        PlaceHolder temp = new PlaceHolder();
        placeholder.add(temp);
    }
    
    public Boolean inPosition(){
        boolean inPlace=false;
        for(int i=0;i<this.getPlaceholder().size();i++){
           num=i;
           int yPlace=this.getPlaceholder().get(i).getY();
           if(yPlace==KAMGraphicPanel.EMPTY_CONVEYERY && this.getPlaceholder().get(i).isShow()){
               inPlace=true;
               break;
           }
                        
        }
        return inPlace;
        //return (this.placeholder.get(i).getX()==KAMGraphicPanel.EMPTY_CONVEYERX && this.placeholder.get(i).getY()==KAMGraphicPanel.EMPTY_CONVEYERY);   
    }
    public Boolean inEmptyPostion(){
        boolean inPlace=false;
        for(int i=0;i<this.getPlaceholder().size();i++){
           num=i;
           int yPlace=this.getPlaceholder().get(i).getY();
           if(yPlace==KAMGraphicPanel.FULL_CONVEYERY && !(this.getPlaceholder().get(i).isShow())){
               inPlace=true;
               break;
           }
                        
        }
        return inPlace;
    }
    
    public KAMKit giveKit(){
        return this.placeholder.get(num).giveKit();
    }
    
    public void takeKit(KAMKit kit){
       //PlaceHolder temp=new PlaceHolder();
       for(int i=0;i<this.getPlaceholder().size();i++){
           //temp=this.getPlaceholder().get(i);
           int yPlace=this.getPlaceholder().get(i).getY();
           if(yPlace==KAMGraphicPanel.FULL_CONVEYERY && !(this.getPlaceholder().get(i).isShow())){
               this.getPlaceholder().get(i).setKit(kit);
               this.getPlaceholder().get(i).getKit().updateParts();
               if(this.getPlaceholder().get(i).getKit().getParts().size() != 0){
                   this.getPlaceholder().get(i).getKit().setImage(new ImageIcon("pics/giftkit.png"));
                   int size=this.getPlaceholder().get(i).getKit().getParts().size();
                   for(int k=0;k<size;k++){
                   this.getPlaceholder().get(i).getKit().getParts().remove(0);
                   }
               }
               break;
           }
       }
    }

    /**
     * @return the numEmptyKits
     */
    public int getNumEmptyKits() {
        return numEmptyKits;
    }

    /**
     * @param numEmptyKits the numEmptyKits to set
     */
    public void setNumEmptyKits(int numEmptyKits) {
        this.numEmptyKits = numEmptyKits;
    }

    /**
     * @return the placeholder
     */
    public ArrayList<PlaceHolder> getPlaceholder() {
        return placeholder;
    }

    /**
     * @param placeholder the placeholder to set
     */
    public void setPlaceholder(ArrayList<PlaceHolder> placeholder) {
        this.placeholder = placeholder;
    }
    
    
}
