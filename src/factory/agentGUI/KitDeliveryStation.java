package factory.agentGUI;

//Class: KitDeliveryStation

import javax.swing.ImageIcon;

//Deepa Borkar

       /**
keeps track of what is on the kit delivery station
interacts with the kit robot because the kit robot takes empty kits off the station and puts on finished kits on the station to be taken away
needs to know when an empty kit is needed on the kit stand
@brief 
@author Deepa Borkar
       */
    public class KitDeliveryStation {

    ImageIcon kitDeliveryStation;///<keeps track of the image of the kit delivery station
    ImageIcon emptyKit;///<image of empty kit
    ImageIcon finishedKit;///<image of finished kit

    /**
checks if an empty kit is available at the station
     */
    public Boolean isEmptyKitAvailable(){
        return null;
    }
    /**
checks if a finished kit is at the station
    */
public Boolean isFinishedKit()
    {
        return null;
    }
    /**
sends finished kit away if it is at the station
     */
public void sendFinishedKit()
    {
    }
    /**
stops delivery station if empty kit is waiting at the statino
    */
public void stopDeliveryStation()
    {

    }
    /**
if no empty kit is present at station, brings out another empty station
    */
public void    continueDeliveryStation(){

    }
    /**
sets the position of the delivery station
    */
public void setStatinoPos(Coordinate in)
    {
    }


}
