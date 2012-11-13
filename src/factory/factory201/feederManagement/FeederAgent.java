package factory.factory201.feederManagement;
import factory.factory200.laneManager.*;
import factory.factory201.interfaces.Feeder;
import factory.factory201.interfaces.Lane;
import factory.factory201.interfaces.Gantry;
import agent.Agent;
import com.sun.corba.se.impl.activation.ServerMain;
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
    private Lane leftLane;
    private Lane rightLane;
    private Lane lane;
    
    /*
     * THESE LEFT AND RIGHT COUNT VARIABLES WILL BE USED TO SET THE LEFT/RIGHT LANE ON.
     * STARTS WITH LEFTLANE==RIGHTLANE, THE PROGRAM CHOSES SENDTOLEFTLANE(). SO LEFTLANE IS INCREMENTED
     * NEXT WHEN THE SCHEDULER RUNS, IT SEES THAT RIGHTLANE HAS LESS PARTS THAN LEFT LANE SO IT WILL
     * SEND TO RIGHTLANE, AND INCREMENT RIGHTLANE COUNT
     * I TRIED TO BRING IT IN, BUT IT GAVE ME WEIRD RESULTS AND IT NEVER SWITCHED THE LANES.
     * PLEASE SEE IF YOU HAVE ANY FUNCTION FOR THIS REQUIREMENT, WE ARE DONE!
     * */
    private int leftCount=0;
    private int rightCount=0;
    
    private Gantry gantry;
    public int feederNum;
    boolean requestState=false;
    //--------------------------------------------------------------
    private ServerMain serverMain;
    private ServerForAgentFeeder animation;
  //---------------------------------------------------------------
    
    //public enum PartState {noState,canSend,needPart,sentRequest};
    
    public FeederAgent(String name, int index,ServerMain serverMain){
        super(name);
     	
    	System.out.println("testing null pointer exception");
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
   
    	//---------------------------------------------------------------------------
    	this.serverMain = serverMain;
    	this.animation = serverMain.getForAgentFeeder();
    	
    	// Initialization
    	this.feederNum=index;
    	//animation.setSwitchOn(feederNum-1);
    	//animation.setDiverterSwitchLeft(feederNum-1);
    	//animation.setDiverterSwitchRight(feederNum-1);
    	animation.setFeedPartsSwitchOn(feederNum-1);
    	//---------------------------------------------------------------------------
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
    	print("Receiving message need part from lane to feeder at " + feederNum);
        /*
         * Search in the myParts list and see if the request can be fulfilled by checking with the quantity of each part
         */
        for (myParts p : parts) {
            if (p.part.type == part.type) {
            	//print("setting sendTrue");
            	p.send=true;
            	p.supplyAmount=8;
            	stateChanged();
            	return;
            	/*
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
                    p.supplyAmount = 8;
                    //p.state=PartState.canSend;
                    stateChanged();
                    return;

                }
                */

            }
        }



        stateChanged();

    }

    public void msgHereAreParts(Part part, int quantity) {
    	int partIndex=0;
    	requestState=false;
    	print("qty received is " + quantity);
        //add to the existing list of parts if the parts already exist
        for (myParts p : parts) {
            if (p.part.type == part.type) {
            	
            	print("updating quantity from " + p.quantity);
                p.quantity = p.quantity + quantity;

                /*
                 //if it received the part, and the quantity is less than the lane capacity, send another request.
                 if(p.quantity<lane.capacity){
                 p.state=PartState.needPart;
                 }
                 else
                 p.state=PartState.noState;
                 */
                print("updated quantity after receiving from feeder is " + p.quantity);
                stateChanged();
                return;
            }
        }

//create a new type if the current list does not contain parts of this type.	
        if(part.type==Type.p1)
        	partIndex=1;
        if(part.type==Type.p2)
        	partIndex=2;
        if(part.type==Type.p3)
        	partIndex=3;
        if(part.type==Type.p4)
        	partIndex=4;
        if(part.type==Type.p5)
        	partIndex=5;
        if(part.type==Type.p6)
        	partIndex=6;
        if(part.type==Type.p7)
        	partIndex=7;
        if(part.type==Type.p8)
        	partIndex=8;
        parts.add(new myParts(part,quantity,partIndex));
        stateChanged();
    }

    public boolean pickAndExecuteAnAction() {

    	synchronized(parts){
        for (myParts p : parts) {
        	//print("testing scheduler for feederAgent" + feederNum + p.sendTo);
            if (p.send == true) {
                //if (p.sendTo == SendTo.leftLane) {
                	//System.out.println("left lane needs a part " + feederNum);
                    if (p.quantity < 8) {
                        //System.out.println("left lane needs a part but qty is less than 8" + feederNum);
                    	if(requestState==false){
                        print("I need part because qty is " + p.quantity);
                    	if(leftCount<1 || rightCount<1)
                        needPart(p);
                        requestState=true;
                        return true;
                        }
                    }
                    else {
                        sendPartToLane(p);
                        p.send = false;
                        //p.sendTo = SendTo.none;
                        //update the myParts object
                        p.quantity = p.quantity - p.supplyAmount;
                        return true;
                    }
                    //	return true;
            	}
        	}
    	}
        //return false if no action is to be called
        return false;
    }

    private void needPart(myParts p) {

    	print("sending need part message to gantry from " + feederNum);
        gantry.msgNeedPart(p.part,this);
        stateChanged();
    }

    private void sendPartToLane(myParts p) {
        /* 
         * FEEDERNUM IS INITIALIZED THROUGH THE CONSTRUCTOR SO THAT EVERY FEEDER HAS A UNIQUE NUMBER
         * PART NUM IS ACCESSED THROUGH p.index
         * PUT IN WHILE LOOP SO THAT ONE ANIMATION FUNCTION EXECUTES TO COMPLETION BEFORE OTHER ONE STARTS
         * */
    	
    	//animation.dumpBinBoxIntoFeeder(feederNum); //revise
    	//animation.feedToLeftLane(feederNum, p.index);
    	//animation.fillInFeeder(feederNum,p.index, p.supplyAmount);
    	lane.msgHereAreParts(p.part,8);
    	dosendPartToLane(p);
    	//animation.setDiverterSwitchLeft(feederNum-1);
    
        stateChanged();
    }
    private void dosendPartToLane(myParts p){
    	try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	animation.setDiverterSwitchLeft(feederNum-1);
    /*
     * I WANT THIS TO WORK BUT IT DOES NOT WORK IN THE ANIMATION.
     */ 
    	/*
			try {
				serverMain.anim.acquire();
				animation.setDiverterSwitchLeft(feederNum-1);
		    	
				leftCount++;
		    	
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			//print("I have received the order and will send it to the cook");
			//giveOrderToCook(customer);
			//stateChanged();
		
    	//flood the lanes with atmost 8 parts.
    	if(leftCount<=rightCount && leftCount<1)
    	{
    	print("i am sending on the left because leftCount is " + leftCount);
    	animation.setDiverterSwitchLeft(feederNum-1);
    	leftCount++;
    	return;
    	}
    	
    	else if(rightCount<leftCount && rightCount<1){
    		print("i am sending on the right because rightCount is " + rightCount);
    		animation.setDiverterSwitchRight(feederNum-1);
        	rightCount++;
        	return;
    	}
    /*
    	if(leftCount>1 && rightCount>1=1)
    		animation.setSwitchOff(feederNum-1);
    */
    	//leftLane.msgHereAreParts(p.part, p.supplyAmount);
    	
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

    public void setLane(Lane lane){
    	this.lane=lane;
    	
    }
    @Override //Unimplemented Method
    public void msgNeedPart(Part part) {
        // TODO Auto-generated method stub
    }
}
