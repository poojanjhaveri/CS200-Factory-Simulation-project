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
import factory.factory201.test.mock.MockCamera;
import factory.factory201.test.mock.MockLane;
import factory.general.Nest;
import factory.general.Part;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NestAgent extends Agent implements NestInterface{
    Lane lane0;
    Lane lane1;
    Lane lane2;
    Lane lane3;
    Lane lane4;
    Lane lane5;
    Lane lane6;
    Lane lane7;
    Lane lanes[] = {lane0, lane1, lane2, lane3, lane4, lane5, lane6, lane7};
    Kit kit;
    
    MockLane lane;
	private List<Nest> myNests = Collections.synchronizedList(new ArrayList<Nest>());
    private Map <Integer, Part> nestParts = new HashMap<Integer, Part>();

    private List<Part> needParts =
            Collections.synchronizedList(new ArrayList<Part>());
    PartsInterface partsagent;
    
    MockCamera camera;
    
    private int nestNumber;
    private List<Nest> nests = Collections.synchronizedList(new ArrayList<Nest>());
    enum Status {none, needPart, gettingPart, full, gettingInspected, readyForKit, purge};
    
    NestAgent(String name) {
    super(name);
    myNests.add(new Nest(0));
    myNests.add(new Nest(1));
    myNests.add(new Nest(2));
    myNests.add(new Nest(3));
    myNests.add(new Nest(4));
    myNests.add(new Nest(5));
    myNests.add(new Nest(6));
    myNests.add(new Nest(7));
 }
    
//messages

    /**
     * This message checks if the part
     *
     * @param p This is a part
     */
    
    public void msgNewKit(List<Part> kitParts){
        
            for (Nest n: myNests){
                for(Part p: kitParts){
                if (n.parts.contains(p))
                    n.status = Nest.Status.hasPart;
                    kitParts.remove(p);
            }
        }
    }
    public void msgNeedPart(Part p) {
        
    	if (emptyNest()){
            for (Nest n: myNests){
                if (n.status == Nest.Status.empty){
                    n.setPart(p);
                    //nestParts.put(n.nestNum, p);
                    n.status = Nest.Status.needPart;
                    print("Part " + p + " is not taken by a nest, part is being assigned to the nest "+ n.nestNum);
                    break;
                }
            }
          
        }
        else{
            for (Nest n: myNests){
    		if(n.parts.contains(p)){
            giveToKit(n);}}}
    	
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
                        return true;
                    }
    				
    		}
    		
    		for (Nest n: myNests){
    			if (n.status == Nest.Status.full) {
                        requestInspection(n);
                        return true;
                    }
    			
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
    	
    	n.status = Nest.Status.gettingPart;
        lanes[n.nestNum].msgNeedPart(n.part);
    	stateChanged();
    }
    
    private void requestInspection(Nest n){
    	
        print("requesting inspecting for nest "+ n.getNestNum());
    	n.status = Nest.Status.gettingInspected;
        camera.msgNestIsFull(n);
    	stateChanged();
    }
    
    private void giveToKit(Nest n){
        
        //n.part.setNestNum(n.getNestNum());
    	partsagent.msgHereIsPart(n.parts.remove(0));
        
    	//n.howMany--;
        print("giving part " + n.part + " to kit now nest has " + n.parts.size());
        n.status = Nest.Status.none;
    	if (n.parts.size()<2)
    		n.status = Nest.Status.needPart;
    	stateChanged();	
    }
    
    private void purge(Nest n){
    n.parts.clear();
    //DoPurge();
    
    n.status = Nest.Status.none;
    stateChanged();
    }
    
    public boolean hasPart(Part p){
    	for (Nest n: myNests){
    	if (n.parts.contains(p))
    		return true;	}
    	return false;
    }
    
    public boolean emptyNest(){
        for (Nest n: myNests){
            if (n.status == Nest.Status.empty)
                return true;
        }
        return false;
    }
    
    public Nest getNest(int n){
        return myNests.get(n);
    }
    
    public void setCameraAgent(MockCamera c){
        this.camera = c;
    }
   /*
    public void setLane(Lane l){
        this.lane = l;
    }*/
    
    
    public void setPartsAgent(PartsInterface parts) {
        this.partsagent = parts;
    }
 }
