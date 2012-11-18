/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory200.kitAssemblyManager;

import javax.swing.ImageIcon;

/**
 *
 * @author Deepa
 */
public class PlaceHolder{
        private ImageIcon placeholder;
        private int x;
        private int y;
        private boolean show;
        private int number;
        private KAMKit kit;

        
        public PlaceHolder(){
            placeholder=new ImageIcon("pics/KAMplaceholder.png");
            //show=true;
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
