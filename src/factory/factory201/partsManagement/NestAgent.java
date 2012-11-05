/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package factory.factory201.partsManagement;


import agent.Agent;
import factory.factory201.feederManagement.LaneAgent;
import factory.factory201.interfaces.Camera;
import factory.factory201.interfaces.Lane;
import factory.factory201.interfaces.NestInterface;
import factory.factory201.interfaces.PartsInterface;
import factory.factory201.kitManagement.CameraAgent;
import factory.general.Nest;
import factory.general.Part;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NestAgent extends Agent implements NestInterface{
    Lane lane1;
    Lane lane2;
    Lane lane3;
    Lane lane4;
	private List<Nest> myNests = Collections.synchronizedList(new ArrayList<Nest>());
	private Map <Nest, LaneAgent> lanes = new HashMap<Nest, LaneAgent>();

    private List<Part> needParts =
            Collections.synchronizedList(new ArrayList<Part>());
    PartsInterface partsagent;
    
    Camera camera;
    
    private int nestNumber;
    private List<Nest> nests = Collections.synchronizedList(new ArrayList<Nest>());
    enum Status {none, needPart, gettingPart, full, gettingInspected, readyForKit, purge};
    
    NestAgent() {

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
            myNests.add(new Nest(p, myNests.size()));
            for (Nest n: myNests){
    		if(n.part.equals(p)){
            switch(n.nestNum){
                case 0:  n.setLane(lane1);
                case 1:  n.setLane(lane1);
                break;
                case 2:  n.setLane(lane2);
                case 3:  n.setLane(lane2);
                break;
                case 4: n.setLane(lane3);
                case 5: n.setLane(lane3);
                break;
                case 6:  n.setLane(lane4);
                case 7:  n.setLane(lane4);
                break;
            }
            }
            }
        }
    	
        stateChanged();
    }

    public void msgHereAreParts(Part p, int quantity) {
        for (Nest n: myNests){
    		if(n.part.equals(p)){
        n.howMany += quantity;}}
        print("adding " + quantity + " " + p + " to the nest ");
        for (Nest n: myNests){
    		if(n.part.equals(p)){
        n.status = Nest.Status.full;}}
        stateChanged();
    }

    public void msgNestInspected(Nest n, boolean result) {
    	if (result){
    		n.status = Nest.Status.readyForKit;
                print("Nest inspected and verified");
    	}
        else{
    		n.status = Nest.Status.purge;
        print("Nest is not verified");}
    	stateChanged();
    }
    //scheduler

    @Override
    protected boolean pickAndExecuteAnAction() {
    	
    	if (!myNests.isEmpty()){
    		
    		for (Nest n: myNests){
    			if (n.status == Nest.Status.needPart) {
                        requestPart(n);
                    }
    				return true;
    		}
    		
    		for (Nest n: myNests){
    			if (n.status == Nest.Status.full) {
                        requestInspection(n);
                    }
    			return true;
    		}
    	}
    	
    		for (Nest n: myNests){
    			if (n.status == Nest.Status.readyForKit){
    				giveToKit(n);
    				return true;
    			}
    		}
    		
    		for (Nest n: myNests){
    			if (n.status == Nest.Status.purge){
    				purge(n);
    				return true;
    			}
    		}
        return false;
    }
    
    
    private void requestPart(Nest n){
       
    print("requesting " + n.part);
    	n.lane.msgNeedPart(n.part);
    	n.status = Nest.Status.gettingPart;
    	stateChanged();
    }
    
    private void requestInspection(Nest n){
    	camera.msgNestIsFull(n);
        print("requesting inspecting for nest "+ n.getNestNum());
    	n.status = Nest.Status.gettingInspected;
    	stateChanged();
    }
    
    private void giveToKit(Nest n){
        n.part.setNestNum(n.getNestNum());
    	partsagent.msgHereIsPart(n.part);
    	n.howMany--;
        print("giving part " + n.part + " to kit now nest has " + n.howMany);
    	if (n.howMany==0)
    		n.status = Nest.Status.needPart;
    	stateChanged();	
    }
    
    private void purge(Nest n){
    n.howMany = 0;
    //DoPurge();
    
    n.status = Nest.Status.needPart;
    stateChanged();
    }
    
    public boolean hasPart(Part p){
    	for (Nest n: myNests){
    	if (n.part.equals(p))
    		return true;	}
    	return false;
    }
    
    public void setCameraAgent(Camera c){
        this.camera = c;
    }
    
    public void setPartsAgent(PartsInterface parts) {
        this.partsagent = parts;
    }
 }
