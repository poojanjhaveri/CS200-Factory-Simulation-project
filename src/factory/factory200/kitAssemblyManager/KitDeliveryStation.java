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
    
    public class PlaceHolder{
        private ImageIcon placeholder;
        private int x;
        private int y;
        private boolean show;
        private int number;
        private KAMKit kit;
        
        public PlaceHolder(){
            placeholder=new ImageIcon("pics/KAMplaceholder.png");
            show=true;
            kit=new KAMKit();
            
        }
        
        public KAMKit giveKit(){
            KAMKit temp=this.getKit();
            this.setKit(null);
            return temp;
        }
        
        

        /**
         * @return the placeholder
         */
        public ImageIcon getPlaceholder() {
            return placeholder;
        }

        /**
         * @param placeholder the placeholder to set
         */
        public void setPlaceholder(ImageIcon placeholder) {
            this.placeholder = placeholder;
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
         * @return the show
         */
        public boolean isShow() {
            return kit==null?false:true;
        }

        /**
         * @param show the show to set
         */
        public void setShow(boolean show) {
            this.show = show;
        }

        /**
         * @return the number
         */
        public int getNumber() {
            return number;
        }

        /**
         * @param number the number to set
         */
        public void setNumber(int number) {
            this.number = number;
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
    }
    
    
    public KitDeliveryStation(int kits){
        numEmptyKits=kits;
        placeholder=new ArrayList<PlaceHolder>();
        for(int i=0;i<numEmptyKits;i++){
            PlaceHolder temp = new PlaceHolder();
            temp.setNumber(i+1);
            placeholder.add(temp);
            
        }  
        int xStart=25;
        int yStart=680;
        for(int i=0;i<numEmptyKits;i++){
        placeholder.get(i).setX(xStart);
        placeholder.get(i).setY(yStart);
        }
    }
    
    public Boolean inPosition(){
        return (this.placeholder.get(0).getX()==KAMGraphicPanel.CONVEYERX && this.placeholder.get(0).getY()==KAMGraphicPanel.CONVEYERY);   
    }
    
    public KAMKit giveKit(){
        return this.placeholder.get(0).giveKit();
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
     * @return the emptyKits
     */
    

    /**
     * @param emptyKits the emptyKits to set
     */

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
