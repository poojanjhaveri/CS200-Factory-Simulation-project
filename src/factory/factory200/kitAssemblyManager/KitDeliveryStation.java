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
    private ArrayList<KAMKit> emptyKits;
    private ArrayList<PlaceHolder> placeholder;
    
    public class PlaceHolder{
        private ImageIcon placeholder;
        private int x;
        private int y;
        private boolean show;
        
        public PlaceHolder(){
            placeholder=new ImageIcon("KAMplaceholder.png");
            show=true;
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
            return show;
        }

        /**
         * @param show the show to set
         */
        public void setShow(boolean show) {
            this.show = show;
        }
    }
    
    
    public KitDeliveryStation(int kits){
        numEmptyKits=kits;
        emptyKits=new ArrayList<KAMKit>();
        placeholder=new ArrayList<PlaceHolder>();
        for(int i=0;i<numEmptyKits;i++){
            emptyKits.add(new KAMKit(i+1));
            placeholder.add(new PlaceHolder());
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
     * @return the emptyKits
     */
    public ArrayList<KAMKit> getEmptyKits() {
        return emptyKits;
    }

    /**
     * @param emptyKits the emptyKits to set
     */
    public void setEmptyKits(ArrayList<KAMKit> emptyKits) {
        this.emptyKits = emptyKits;
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
