package factory.factory201.partsManagement;

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
        this.nestNumber = nestNum;
        this.lane = lane;
    }
    class Nest{
    	private int threshold;
        private Part part;
        private int howMany = 0;
        Lane lane;
        Status status;
        
        Nest (Part p){
        	this.part = p;
        	this.threshold = 10/p.size;
        	this.status = Status.needPart;
        }
        
        public void setPart(Part p){
        	this.part = p;
        	this.threshold = 10/p.size;
        }
        
        public void setLane(Lane lane){
        	this.lane = lane;
        }
        
    }
//messages

    /**
     * This message checks if the part
     *
     * @param p This is a part
     */
    public void msgNeedPart(Part p) {
    	if (!hasPart())
    	myNests.add(new Nest(p));
        stateChanged();
    }

    public void msgHereAreParts(Part p, int quantity) {
        part = p;
        howMany += quantity;
        myNests.getNest(p).status = Status.full;
        stateChanged();
    }

    public void msgNestInspected(Nest n, boolean result) {
    	if (result){
    		n.status = Status.readyForKit;
    	}
    	else
    		n.status = Status.purge;
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
    
    public Nest getNest(Part p){
    	for (Nest n: MyNests){
    		if(n.part.equals(p))
    			return n;
    	}
    }
    
    
    private void requestPart(Nest n){
    	n.lane.msgNeedPart(n.part);
    	n.status = Status.gettingPart;
    	stateChanged();
    }
    
    private void requestInspection(Nest n){
    	camera.msgNestIsFull(this);
    	n.status = Status.gettingInspected;
    	stateChanged();
    }
    
    private void giveToKit(Nest n){
    	partsagent.msgHereIsPart(Part p);
    	n.howMany--;
    	if (howMany==0)
    		n.status = Status.needPart;
    	stateChanged();	
    }
    
    private void purge(Nest n){
    n.howMany = 0;
    DoPurge();
    n.status = Status.needPart;
    stateChanged();
    }
    
    public boolean hasPart(Part p){
    	for (Nest n: myNest){
    	if (n.part.equals(p))
    		return true;	}
    	return false;
    }
    
    public void setPartsAgent(Parts parts) {
        this.partsagent = parts;
    }
