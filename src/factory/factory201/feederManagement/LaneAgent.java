package factory.factory201.feederManagement;

//import factory.factory200.laneManager.ServerForAgentLane;
//import factory.factory201.feederManagement.FeederAgent.myParts;
import factory.factory201.interfaces.Feeder;
import factory.factory201.interfaces.NestInterface;
import factory.factory201.interfaces.Lane;
import agent.Agent;
import factory.general.Part;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @brief agent for the Lane This class is the agent for the Lane agent, which
 * receives messages from nest to supply parts. It asks the feeder for parts if
 * it has less parts available. It sends the parts to the nest when it has
 * enough parts available.
 * @author Kevin Macwan
 * @version 0
 */
public class LaneAgent extends Agent implements Lane {

    private List<myParts> parts = Collections.synchronizedList(new ArrayList<myParts>());
    private NestInterface nest;
    private Feeder feeder;
   // private ServerForAgentLane animation;
    int laneNum;
    
    public LaneAgent(int num){
    	this.laneNum=num;
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
    public void msgRejectParts(int i) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class myParts {

        Part part;
        int partNum;
        int quantity;
        int supplyAmount=8;
        boolean send = false;

        public myParts(Part part, int quantity, int index) {
            this.part = part;
            this.quantity = quantity;
            this.partNum=index;

        }
    }

 
    public void msgNeedPart(Part part) {
        for (myParts p : parts) {
            if (p.part.type == part.type) {
                p.send = true;
                p.supplyAmount = 8; 	//send 8 parts in v0.
                //p.state=PartState.canSend;
                stateChanged();
                return;
            }
          
        }
        stateChanged();

    }

   
    public void msgHereAreParts(Part part, int quantity) {
    	int partIndex=0;
        for (myParts p : parts) {
        	
        	System.out.println("checking for part " + p.part.type + "with " + part.type);
            if (p.part.type == part.type) {
            	System.out.println("quantity is " + p.quantity);
                p.quantity = p.quantity + quantity;
                System.out.println("updated quantity is " + p.quantity);
                stateChanged();
                return;
            }
        }
        
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

        //create a new type if the current list does not contain parts of this type.	
        parts.add(new myParts(part, quantity,partIndex));
        stateChanged();
        //stateChanged();
    }

   
    public boolean pickAndExecuteAnAction() {
     
        for (myParts p : parts) {
            if (p.send == true) {
            	System.out.println("there is an item to be sent");
            	System.out.println("The quantity is  " + p.quantity);
            	if(p.quantity>8){
                System.out.println("testing scheduler");
            		supplyPart(p);  
            		return true;
            	}    //supply part if it has correct quantity
            	else{
            	askForPart(p.part);
            	return true;
            	} //ask for parts if it is running low
            }
            
        }
        
        for(myParts p: parts){
        	if(p.quantity<8){
        		askForPart(p.part);}
        	return true;
        	
        }
        //return false if no scheduler rule is fired
        return false;
    }

    //ask for parts if it is low
    private void askForPart(Part p){
    	
    	//feeder must know which lane the message is from
    	System.out.println("testing need part message sending");
    	feeder.msgNeedPart(p,this);
    	
    }
    private void supplyPart(myParts part) {
    
    	/*
    	 * ANIMATION REQUIREMENT: SEND PART DOWN THE LANE TO A NEST, TO ACCESS THE PART NUMBER, USE  part.partNum
    	 * I AM NOT SURE HOW YOU ARE KEEPING A TRACK OF WHICH FEEDER DUMPS INTO WHICH NEST, I LEAVE THAT TO YOU
    	 * I AM SENDING THE MESSAGE TO THE NEST AGENT HEREAREPARTS(); GOT NOTHING TO DO WITH ANIMATION THOUGH
    	*/
    	
        nest.msgHereAreParts(part.part, part.supplyAmount);
        part.send = false;

        //update the myParts object
        part.quantity = part.quantity - part.supplyAmount;

    }

    public void setFeeder(Feeder feeder){
    	
    	this.feeder=feeder;
    }
    
    public void setNest(NestInterface nest){
    	
    	this.nest=nest;
    }
}
