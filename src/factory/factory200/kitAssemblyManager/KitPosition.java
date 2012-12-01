/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory200.kitAssemblyManager;

import factory.general.Part;
import java.util.ArrayList;

/**
 * @brief a position to hold a kit
 * @author Deepa
 */
public class KitPosition {
        private KAMKit kit;
        private boolean filled;
        private int x;
        private int y;
        
        
        public KitPosition(){
            kit=null;
            //filled=false;
            
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
            return kit==null?false:true;
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
            //kit.setX(x);
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
            //kit.setY(y);
            this.y = y;
        }
}
