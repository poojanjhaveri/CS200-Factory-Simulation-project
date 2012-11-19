package factory.factory201.feederManagement;

import factory.factory201.interfaces.Feeder;
import factory.factory201.interfaces.Gantry;
import agent.Agent;
import factory.factory200.gantryRobotManager.GantryRobotManager;
import factory.factory200.laneManager.ServerSide.LMFeederForAgent;
import factory.factory200.laneManager.ServerSide.LMGantryRobotForAgent;
import factory.factory200.laneManager.ServerSide.LMServerMain;
import factory.general.Part;
import factory.general.HandleAManager;
import factory.general.Message;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @brief agent for the Gantry This class is the agent for the Gantry. The
 * Gantry receives message from feeder to supply parts. It supplies the parts
 * whenever it has enough parts available.
 * @author Kevin Macwan
 * @version 0
 */
public class GantryAgent extends Agent implements Gantry {

    //private List<myParts> parts = Collections.synchronizedList(new ArrayList<myParts>());
    private Feeder feeder;
    //holds info about all the feeders that are assigned to the gantry
    private List<myFeeder> myFeeders = Collections.synchronizedList(new ArrayList<myFeeder>());
    private List<myBin> bins = Collections.synchronizedList(new ArrayList<myBin>());

    Timer timer=new Timer();
    //---------------------------------------------------------------------------
    //private LMServerMain serverMain;
    //private LMGantryRobotForAgent animation;
    //private LMFeederForAgent animation1;
    /* Just for Testing */
    private GantryRobotManager animation;
    //---------------------------------------------------------------------------
    
    //numOfBins is the number of bins that gantry is initialized to, will be the same for v0, each bin has 8 parts.
    public GantryAgent(int numOfBins, String name){
    	super(name);
    	//---------------------------------------------------------------------------
    	//this.serverMain = serverMain;
    	//this.animation = serverMain.getForAgentGantry();
    	//this.animation1 = serverMain.getForAgentFeeder();
    	//---------------------------------------------------------------------------
    	
    	//this.feeder=feeder;
    	
    	//System.out.println("name and feeder assigned");
    	/*
        Part p1=new Part(1);
    	Part p2=new Part(2);
    	Part p3=new Part(3);
    	Part p4=new Part(4);
    	Part p5=new Part(5);
    	Part p6=new Part(6);
    	Part p7=new Part(7);
    	Part p8=new Part(8);
    	*/
    //	System.out.println("parts added");
    	//part 1 is in bin 1 
    /*
        bins.add(new myBin(p1,numOfBins,0));
    	
    	//part 2 is in bin 2 
    	bins.add(new myBin(p2,numOfBins,1));

    	//part 3 is in bin 3 
    	bins.add(new myBin(p3,numOfBins,2));
    	
    	//part 4 is in bin 4 
    	bins.add(new myBin(p4,numOfBins,3));
    	
    	//part 5 is in bin 5 
    	bins.add(new myBin(p5,numOfBins,4));
    	
    	//part 6 is in bin 6 
    	bins.add(new myBin(p6,numOfBins,5));
    	
    	//part 7 is in bin 7 
    	bins.add(new myBin(p7,numOfBins,6));
    	
    	//part 8 is in bin 8 
    	bins.add(new myBin(p8,numOfBins,7));
    //	System.out.println("bins added");
    */
    }

    
    /* to hold the info about its list of parts*/
    /*
    private class myParts {

        Part part;
        int quantity;
        int supplyAmount;
        boolean send = false;
        //PartState state=PartState.noState;

        public myParts(Part part, int quantity) {
            this.part.type = part.type;
            this.quantity = quantity;

        }
    }
    */
    private class myFeeder{
    	
    	//which feeder agent it is
    	Feeder feeder;
    	
    	//which part it requests for
    	Part requested_part;
    	
    	//how many parts are supposed to be sent, might be unecessary
    	int requested_qty;
    	
    	//set the requestState true when a message comes in "I need parts"
    	boolean requestState=false;
    	
    	//this is the index of the feeder
    	int index; 
    	public myFeeder(Feeder feeder, int index){
    		//constructor, to assign feeder
    		this.feeder=feeder;
    		this.index=index;
    	}
    }
    
    //holds info about the bin
    private class myBin{

    	//which part the bin has, will be 8 number of parts
    	public Part part;
        
        //represent how many quantity of the bin are with the gantry
        public int quantity;
        int index;
        //int supplyAmount;
        //boolean send = false;
        //PartState state=PartState.noState;

        public myBin(Part part, int quantity,int index) {
           
            this.quantity = quantity;
            this.index=index;
            //System.out.println("testing null pointer");
            this.part = part;
            //System.out.println("testing null pointer 1 ");
        }
    }

    public void msgNeedPart(Part part,Feeder feeder) {
    	
        
        print("msgNeedPart from Feeder for type " + part.type);
    	
        int count=0;
        
        //increase count everytime myParts doesn't have that type of part
        for (myBin b : bins) {
            if (b.part.type != part.type)
                count++;
        }
        
        //add if part doesn't exist
        if(count==bins.size())
        {
        bins.add(new myBin(part,8,part.type));
        }
        
        
        for(myFeeder f: myFeeders)
    	{
    		if(f.feeder==feeder){
    			
    			f.requested_part=part;
    			//System.out.println("requested type is " + f.requested_part.type);
    			f.requestState=true;
    			}
    		
    	}

    	stateChanged();
    }

    @Override
    public boolean pickAndExecuteAnAction() {
    
        synchronized(myFeeders){	
    	for(myFeeder f: myFeeders){
    		if(f.requestState==true){
    			for(myBin b: bins){
    				
    				//System.out.println("checking within bins to supply part since request state is true");
    				if(b.part.type==f.requested_part.type){
    					//System.out.println("b.part==f.requested_part for index " + f.index);
    					if(b.quantity>1){
    					print("I am supplying parts to feeder at index " + f.index );
    					f.requestState=false;
    					supplyPart(b,f);
                                        b.quantity=b.quantity-1;
    					return true;
    					}
    					
    				}
    			}
    		}
    	}
    }
    
    
        return false;
    }

    private void supplyPart(myBin b,myFeeder f) {
    	int binNum;
    	int feederNum;
    	binNum=b.index;
    	feederNum=f.index;
    	
        //create a list of parts to supply
        List<Part> parts = new ArrayList<Part>();
        
        for(int i=0;i<8;i++)
            parts.add(b.part);
    	//animation commands.
    	/*
    	 * THE BINNUM IS OBTAINED BY b.index, each bin has 8 parts of one type(Part part)
    	 * TO DETERMINE WHICH FEEDER IT GOES TO, USE f.index (that's initialized through constructor)
         */
    	//print("Sending message to feeder");
        print("sending message here are parts to " + f.index);

    	doSupplyPart(b,f);


        f.feeder.msgHereAreParts(parts);
        
        //animation.goToBin(binNum);
    	
        
        //print("request state has been set to false for " + f.index);
        //update the quantity of bins
        stateChanged();
        return;
    }
    private void doSupplyPart(myBin b,myFeeder f){
   	
     print("about to pick up bin");
  	//animation.ganbot.moveToBin(b.index);
    
     while(this.client == null)
         {
         //print("[ERROR] - Gantry Robot Manager is not online.");
         //return;
            try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
         }
     
         
     this.client.sendMessage(Message.MOVE_GANTRY_TO_BIN+":"+b.index);
       
         try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         this.client.sendMessage(Message.GANTRY_CARRY_A_BIN + ":" + b.index);
         this.client.sendMessage(Message.MOVE_GANTRY_TO_FEEDER+":"+f.index);
         
    	//animation.goToFeeder(f.index-1);
    	
    	try {
			Thread.sleep(7000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	this.client.sendMessage(Message.SUPPLY_PART_ON_FEEDER + ":" + f.index);
        
            try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        this.client.sendMessage(Message.MOVE_GANTRY_TO_DUMP);
    
        
         try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void setFeeder(Feeder feeder,int index) {
    //add feeders to the list
    myFeeders.add(new myFeeder(feeder,index));
    }
    
    // Just for testing
    public void setGantryRobotManager(GantryRobotManager grm) {
        this.animation = grm;
    }

    @Override //Unimplemented
    public void msgHereAreParts(List<Part> parts) {
        // TODO Auto-generated method stub
    }

	@Override //Unimplemented
	public void msgNeedPart(Part partType) {
		// TODO Auto-generated method stub
		
	}
}
