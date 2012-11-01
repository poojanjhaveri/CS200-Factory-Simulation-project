package factory.feederManagement;

import agent.Agent;
import factory.Part;
import factory.interfaces.*;
import java.util.*;

/**
 * @brief agent for the Feeder This class is the agent for the Feeder which does
 * the following: 1) Get messages from the lanes when they need parts 2) Send
 * parts to the lanes 3) Request for parts from the gantry
 *
 * @author Kevin Macwan
 * @version 0
 */
public class FeederAgent extends Agent implements Feeder {

    private List<myParts> parts = Collections.synchronizedList(new ArrayList<myParts>());
    private Lane leftLane;
    private Lane rightLane;
    private Gantry gantry;
//public enum PartState {noState,canSend,needPart,sentRequest};
//test comment
    public enum SendTo {

        leftLane, rightLane, none
    };

    private class myParts {

        Part part_type;
        int quantity;
        int supplyAmount;
        boolean send = false;
        SendTo sendTo = SendTo.none;
        //PartState state=PartState.noState;

        public myParts(Part part, int quantity) {
            this.part_type.type = part.type;
            this.quantity = quantity;

        }
    }

    @Override
    public void msgNeedPart(Part part, Lane lane) {

        /*
         * Search in the myParts list and see if the request can be fulfilled by checking with the quantity of each part
         */
        for (myParts p : parts) {
            if (p.part_type.type == part.type) {

                //is the message from the left lane?
                if (lane == this.leftLane) {
                    p.send = true;
                    p.sendTo = SendTo.leftLane;
                    p.supplyAmount = leftLane.capacity;
                    //p.state=PartState.canSend;
                    stateChanged();
                    return;

                }

                //is the message from the right lane?
                if (lane == this.rightLane) {
                    p.send = true;
                    p.sendTo = SendTo.rightLane;
                    p.supplyAmount = rightLane.capacity;
                    //p.state=PartState.canSend;
                    stateChanged();
                    return;

                }

            }
        }



        stateChanged();

    }

    @Override
    public void msgHereAreParts(Part part, int quantity) {

        //add to the existing list of parts if the parts already exist
        for (myParts p : parts) {
            if (p.part_type.type == part.type) {
                p.quantity = p.quantity + quantity;

                /*
                 //if it received the part, and the quantity is less than the lane capacity, send another request.
                 if(p.quantity<lane.capacity){
                 p.state=PartState.needPart;
                 }
                 else
                 p.state=PartState.noState;
                 */
                stateChanged();
                return;
            }
        }

//create a new type if the current list does not contain parts of this type.	
        parts.add(new myParts(part, quantity));
        stateChanged();
    }

    @Override
    protected boolean pickAndExecuteAnAction() {

        for (myParts p : parts) {
            if (p.send == true) {
                if (p.sendTo == SendTo.leftLane) {
                    if (p.quantity < leftLane.capacity) {
                        needPart(p);
                    } else {
                        sendPartToLeftLane(p);
                    }
                }

                if (p.sendTo == SendTo.rightLane) {
                    if (p.quantity < rightLane.capacity) {
                        needPart(p);
                    } else {
                        sendPartToRightLane(p);
                    }
                }
                return true;
            }
        }

        //return false if no action is to be called
        return false;
    }

    private void needPart(myParts p) {

        gantry.msgNeedPart(p.part_type);
    }

    private void sendPartToLeftLane(myParts p) {
        leftLane.msgHereAreParts(p.part_type, p.quantity);
        p.send = false;
        p.sendTo = SendTo.none;
        //update the myParts object
        p.quantity = p.quantity - p.supplyAmount;
    }

    private void sendPartToRightLane(myParts p) {
        rightLane.msgHereAreParts(p.part_type, p.quantity);
        p.send = false;
        p.sendTo = SendTo.none;
        //update the myParts object
        p.quantity = p.quantity - p.supplyAmount;
    }

    /*Hack to set the left lane for the feeder*/
    public void setLeftLane(Lane lane) {
        this.leftLane = lane;
    }

    /*Hack to set the right lane for the feeder*/
    public void setRightLane(Lane lane) {
        this.rightLane = lane;
    }

    /*Hack to set the gantry for the feeder*/
    public void setGantry(Gantry gantry) {
        this.gantry = gantry;
    }

    @Override //Unimplemented Method
    public void msgNeedPart(Part part) {
        // TODO Auto-generated method stub
    }
}
