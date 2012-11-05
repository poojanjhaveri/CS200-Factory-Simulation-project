package factory.factory201.partsManagement;

<<<<<<< HEAD

=======
>>>>>>> f94b363f54db50564d74358acf2a093e595e7c9d
import agent.Agent;
import factory.factory201.feederManagement.LaneAgent;
import factory.factory201.interfaces.Lane;
import factory.factory201.kitManagement.CameraAgent;
import factory.general.Part;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NestAgent extends Agent {

	private List<Nest> myNests = Collections.synchronizedList(new ArrayList<Nest>());
	private Map <Nest, LaneAgent> lanes = new HashMap<Nest, LaneAgent>();

    private List<Part> needParts =
            Collections.synchronizedList(new ArrayList<Part>());
    PartsAgent partsagent;
    
    CameraAgent camera;
    
    private int nestNumber;
    private List<Nest> nests = Collections.synchronizedList(new ArrayList<Nest>());
    enum Status {none, needPart, gettingPart, full, gettingInspected, readyForKit, purge};
    
    NestAgent() {

    }
    class Nest{
    	private int threshold;
        private Part part;
        private int howMany = 0;
        Lane lane;
        Status status;
        int nestNumber;
        
        Nest (Part p){
        	this.part = p;
        	this.threshold = 10/p.getSize();
        	this.status = Status.needPart;
                this.nestNumber = myNests.size();
        }
        
        public void setNestNum(int nestNum){
            this.nestNumber = nestNum;
        }
        public void setPart(Part p){
        	this.part = p;
        	this.threshold = 10/p.getSize();
        }
        
        public void setLane(Lane lane){
        	this.lane = lane;
        }
        
        
        public int getNestNumber(){
            return nestNumber;
        }
        
    }
//messages

    /**
     * This message checks if the part
     *
     * @param p This is a part
     */
    public void msgNeedPart(Part p) {
    	if (!hasPart(p)){
            print("Part " + p + " is not taken by a nest, a new nest is being created");
            myNests.add(new Nest(p));
        }
    	
        stateChanged();
    }

    public void msgHereAreParts(Part p, int quantity) {
        part = p;
        howMany += quantity;
        print("adding " + quantity + " " + p + " to the nest " + getNest(p));
        for (Nest n: myNests){
    		if(n.part.equals(p)){
        n.status = Status.full;}}
        stateChanged();
    }

    public void msgNestInspected(Nest n, boolean result) {
    	if (result){
    		n.status = Status.readyForKit;
                print("Nest inspected and verified");
    	}
        else{
    		n.status = Status.purge;
        print("Nest is not verified");}
    	stateChanged();
    }
    //scheduler

    @Override
    protected boolean pickAndExecuteAnAction() {
    	
    	if (!myNests.isEmpty()){
    		
    		for (Nest n: myNests){
    			if (n.status == Status.needPart) {
                        requestPart(n);
                    }
    				return true;
    		}
    		
    		for (Nest n: myNests){
    			if (n.status == Status.full) {
                        requestInspection(n);
                    }
    			return true;
    		}
    	}
    	
    		for (Nest n: myNests){
    			if (n.status == Status.readyForKit){
    				giveToKit(n);
    				return true;
    			}
    		}
    		
    		for (Nest n: myNests){
    			if (n.status == Status.purge){
    				purge(n);
    				return true;
    			}
    		}
        return false;
    }
    
   /* private Nest getNest(Part p){
    	for (Nest n: myNests){
    		if(n.part.equals(p))
    			return n;
    	}
        
    }*/
    
    
    private void requestPart(Nest n){
       
    print("requesting " + n.part);
    	n.lane.msgNeedPart(n.part);
    	n.status = Status.gettingPart;
    	stateChanged();
    }
    
    private void requestInspection(Nest n){
    	camera.msgNestIsFull(n);
        print("requesting inspecting for nest "+ n.getNestNumber());
    	n.status = Status.gettingInspected;
    	stateChanged();
    }
    
    private void giveToKit(Nest n){
    	partsagent.msgHereIsPart(n.part);
    	n.howMany--;
        print("giving part " + n.part + " to kit now nest has " + n.howMany);
    	if (n.howMany==0)
    		n.status = Status.needPart;
    	stateChanged();	
    }
    
    private void purge(Nest n){
    n.howMany = 0;
    //DoPurge();
    n.status = Status.needPart;
    stateChanged();
    }
    
    public boolean hasPart(Part p){
    	for (Nest n: myNests){
    	if (n.part.equals(p))
    		return true;	}
    	return false;
    }
    
    public void setPartsAgent(PartsAgent parts) {
        this.partsagent = parts;
    }
 }
