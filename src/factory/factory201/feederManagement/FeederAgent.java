package factory.factory201.feederManagement;
//import factory.factory200.laneManager.*;

import factory.factory201.interfaces.Feeder;
import factory.factory201.interfaces.Lane;
import factory.factory201.interfaces.Gantry;
import agent.Agent;
import factory.general.Part;
import factory.general.Part.Type;

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
   // private ServerForAgentFeeder animation;
    private Lane leftLane;
    private Lane rightLane;
    private Gantry gantry;
    public int feederNum=0;
//public enum PartState {noState,canSend,needPart,sentRequest};
//test comment
    public FeederAgent(int index){
    	
    	System.out.println("testing null pointer exception");
    	this.feederNum=index;
    	Part p1=new Part(1);
    	Part p2=new Part(2);
    	Part p3=new Part(3);
    	Part p4=new Part(4);
    	Part p5=new Part(5);
    	Part p6=new Part(6);
    	Part p7=new Part(7);
    	Part p8=new Part(8);
    	System.out.println("All parts created");
    	
    	//part type, quantity, index (quantity started with is 0
    	parts.add(new myParts(p1,0,1));
     	parts.add(new myParts(p2,0,2));
     	parts.add(new myParts(p3,0,3));
     	parts.add(new myParts(p4,0,4));
     	parts.add(new myParts(p5,0,5));
     	parts.add(new myParts(p6,0,6));
     	parts.add(new myParts(p7,0,7));
     	parts.add(new myParts(p8,0,8));
     	System.out.println("parts added to the list");
    }
    public enum SendTo {

        leftLane, rightLane, none
    };

    private class myParts {

        Part part;
        int quantity;
        int index;
        int supplyAmount=8;
        boolean send = false;
        SendTo sendTo = SendTo.none;
        //PartState state=PartState.noState;

        public myParts(Part part, int quantity,int index) {
            this.part = part;
            this.quantity = quantity;
            this.index=index;

        }
    }

    @Override
    public void msgNeedPart(Part part, Lane lane) {

        /*
         * Search in the myParts list and see if the request can be fulfilled by checking with the quantity of each part
         */
        for (myParts p : parts) {
            if (p.part.type == part.type) {

                //is the message from the left lane?
                if (lane == this.leftLane) {
                    p.send = true;
                    p.sendTo = SendTo.leftLane;
                    p.supplyAmount = 8; //8 parts are passed over
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
    	int partIndex=0;
        //add to the existing list of parts if the parts already exist
        for (myParts p : parts) {
            if (p.part.type == part.type) {
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
        if(part.type==part.type.p1)
        	partIndex=1;
        if(part.type==part.type.p2)
        	partIndex=2;
        if(part.type==part.type.p3)
        	partIndex=3;
        if(part.type==part.type.p4)
        	partIndex=4;
        if(part.type==part.type.p5)
        	partIndex=5;
        if(part.type==part.type.p6)
        	partIndex=6;
        if(part.type==part.type.p7)
        	partIndex=7;
        if(part.type==part.type.p8)
        	partIndex=8;
        parts.add(new myParts(part,quantity,partIndex));
        stateChanged();
    }

    public boolean pickAndExecuteAnAction() {

        for (myParts p : parts) {
            if (p.send == true) {
                if (p.sendTo == SendTo.leftLane) {
                	System.out.println("left lane needs a part");
                    if (p.quantity < 8) {
                        System.out.println("left lane needs a part but qty is less than 8");
                    	needPart(p);
                        return true;
                    } else {
                        sendPartToLeftLane(p);
                        return true;
                    }
                }

                if (p.sendTo == SendTo.rightLane) {
                    if (p.quantity < rightLane.capacity) {
                        needPart(p);
                        return true;
                    } else {
                        sendPartToRightLane(p);
                        return true;
                    }
                }
                return true;
            }
        }

        //return false if no action is to be called
        return false;
    }

    private void needPart(myParts p) {

    	System.out.println("sending message to gantry");
        gantry.msgNeedPart(p.part,this);
    }

    private void sendPartToLeftLane(myParts p) {
        /*
         * FEEDERNUM IS INITIALIZED THROUGH THE CONSTRUCTOR SO THAT EVERY FEEDER HAS A UNIQUE NUMBER
         * PART NUM IS ACCESSED THROUGH p.index
         * PUT IN WHILE LOOP SO THAT ONE ANIMATION FUNCTION EXECUTES TO COMPLETION BEFORE OTHER ONE STARTS
         * */
    	
    	//animation.dumpBinBoxIntoFeeder(feederNum); //revise
    	//animation.feedToLeftLane(feederNum, p.index);
    	//animation.fillInFeeder(feederNum,p.index, p.supplyAmount);
    	leftLane.msgHereAreParts(p.part, p.supplyAmount);
        p.send = false;
        p.sendTo = SendTo.none;
        //update the myParts object
        p.quantity = p.quantity - p.supplyAmount;
        stateChanged();
    }

    private void sendPartToRightLane(myParts p) {
    	
    	//animation.dumpBinBoxIntoFeeder(feederNum);
    	//animation.feedToRightLane(feederNum, p.index);
    	//animation.fillInFeeder(feederNum,p.index,p.supplyAmount);
    	
        rightLane.msgHereAreParts(p.part, p.quantity);
        p.send = false;
        p.sendTo = SendTo.none;
        //update the myParts object
        p.quantity = p.quantity - p.supplyAmount;
        stateChanged();
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
