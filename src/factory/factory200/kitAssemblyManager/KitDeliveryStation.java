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
    int numEmptyKits;
    ArrayList<KAMKit> emptyKits;
    
    public KitDeliveryStation(int kits){
        numEmptyKits=kits;
        emptyKits=new ArrayList<KAMKit>();
        for(int i=0;i<numEmptyKits;i++){
            emptyKits.add(new KAMKit(i+1));
        }
        
    }
    
    
}
