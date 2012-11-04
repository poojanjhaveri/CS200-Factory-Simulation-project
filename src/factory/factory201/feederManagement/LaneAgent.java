package factory.factory201.feederManagement;

//import factory.factory200.laneManager.ServerForAgentLane;
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
    
    public void LaneAgent(int num){
    	this.laneNum=num;
    	
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
            this.part.type = part.type;
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
            if (p.part.type == part.type) {
                p.quantity = p.quantity + quantity;
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
        stateChanged();
    }

   
    protected boolean pickAndExecuteAnAction() {
     
        for (myParts p : parts) {
            if (p.send == true) {
            	if(p.quantity>8)
                supplyPart(p);      //supply part if it has correct quantity
            	else
            	askForPart(p.part); //ask for parts if it is running low
            }
            return true;
        }

        //return false if no scheduler rule is fired
        return false;
    }

    //ask for parts if it is low
    private void askForPart(Part p){
    	feeder.msgNeedPart(p);
    	
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
}
