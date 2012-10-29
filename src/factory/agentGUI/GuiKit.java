package factory.agentGUI;

import factory.agentGUI.GuiPart;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author : Poojan Jhaveri
 * @brief : Kit containing the parts for production
 */
public class GuiKit implements Serializable {

    /**
     * @param args
     */
    String name; //name of the Kit
    ArrayList<GuiPart> partslist;  // collection of parts in the kit
//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//    }
    //getters and setters as needed 
}